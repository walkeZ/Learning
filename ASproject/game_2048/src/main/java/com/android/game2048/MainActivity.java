package com.android.game2048;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 案例下载：
 * http://down.admin5.com/android/138951.html
 */
public class MainActivity extends Activity {

    private TextView scoreTextView, bestScoreTextView;
    private Button buttoNewGame;

    private GameView gameView = null;

    private Score score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttoNewGame = (Button) findViewById(R.id.btnNewGame);// 重新开始
        scoreTextView = (TextView) findViewById(R.id.score);//当前分数
        bestScoreTextView = (TextView) findViewById(R.id.bestScore);//最高分

        gameView = (GameView) findViewById(R.id.gameView);

//        ImageView child = new ImageView(this);
//        child.setImageResource(R.drawable.ic_launcher);
//        child.setLayoutParams(new LinearLayout.LayoutParams(200,200));
//        gameView.addView(child);
//        int childCount = gameView.getChildCount();
//        Log.e("ArHui", "addCards: -----> " + childCount);

        score = new Score();
        gameView.setScore(score);
        buttoNewGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        score.clearScore();
        showScore();
        showBestScore();
        gameView.startGame();
    }

    /**
     * 更新显示最高分数
     */
    private void showBestScore() {
        bestScoreTextView.setText(String.valueOf(score.getBestScore()));
    }

    /**
     * 更新显示分数
     */
    private void showScore() {
        scoreTextView.setText(String.valueOf(score.getScore()));
    }

    /**
     * 分数
     */
    class Score {
        private int score = 0;
        private static final String SP_KEY_BEST_SCORE = "bestScore";

        public void clearScore() {
            score = 0;
        }

        public int getScore() {
            return score;
        }

        public void addScore(int s) {
            score += s;
            showScore();
            saveBestScore(Math.max(score, getBestScore()));
            showBestScore();
        }

        /**
         * 使用SharePreference保存最高分数
         *
         * @param s
         */
        public void saveBestScore(int s) {
            Editor e = getPreferences(MODE_PRIVATE).edit();
            e.putInt(SP_KEY_BEST_SCORE, s);
            e.commit();
        }

        public int getBestScore() {
            return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE, 0);
        }
    }
}
