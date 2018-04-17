package com.android.game2048;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏控件
 */
public class GameView extends LinearLayout {

    private final int LINES = 4;
    /**
     * 卡片矩阵/列表，4行4列
     */
    private Card[][] cardsMap = new Card[LINES][LINES];
    /**
     * 空卡片集合，其中的Point是卡片所在矩阵位置如(1,3)、(2,3)【cardsMap二维数组的两个下标】
     */
    private List<Point> emptyPoints = new ArrayList<Point>();
    /**
     * 分数
     */
    private MainActivity.Score score;


    public GameView(Context context) {
        this(context,null);
//        super(context);
//        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }


    private void initGameView() {
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.GRAY);

        setOnTouchListener(new View.OnTouchListener() {

            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;


                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }

                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        Card.width = (Math.min(width, height) - 10) / LINES;//

        addCards();
        startGame();
    }

    /**
     * 添加卡片
     */
    private void addCards() {

        Card c;

        LinearLayout line;
        LinearLayout.LayoutParams lineLp;

        //遍历y轴[即行]
        for (int y = 0; y < LINES; y++) {
            line = new LinearLayout(getContext());
            lineLp = new LinearLayout.LayoutParams(-1, Card.width);
            addView(line, lineLp);//添加行父布局

            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int x = 0; x < LINES; x++) {
                c = new Card(getContext());
                line.addView(c, Card.width, Card.width);// 行父布局添加卡片控件

                cardsMap[x][y] = c; // 将卡片控件赋值给矩阵(数组)
            }
        }
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        ////遍历y轴[即行]
        for (int y = 0; y < LINES; y++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int x = 0; x < LINES; x++) {
                //清“空”所有卡片
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
    }

    /**
     * 给一个空卡片内容添加数字2或4
     */
    private void addRandomNum() {

        emptyPoints.clear();//清空空卡片集合

        //calculate how many empty points
        //遍历y轴[即行]
        for (int y = 0; y < LINES; y++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int x = 0; x < LINES; x++) {

                if (cardsMap[x][y].getNum() <= 0) {

                    emptyPoints.add(new Point(x, y));//将为空的卡片存入集合
                }
            }
        }

        if (emptyPoints.size() > 0) {

            Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
            cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);//随机新产生两个新卡片的数字2或4
            cardsMap[p.x][p.y].addScaleAnimation();//空卡片内容添加数字时执行动画
        }
    }


    /**
     * 向左滑
     */
    private void swipeLeft() {

        boolean merge = false;//是否合并，即滑动方向、相邻两个卡片数字是否相等

        //遍历y轴[即行]
        for (int y = 0; y < LINES; y++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int x = 0; x < LINES; x++) {

                // 遍历 目的是 ？？
                for (int x1 = x + 1; x1 < LINES; x1++) { // x1=x+1 : 当前第二层遍历到的卡片,所在行(y不变)的下一个数字

                    if (cardsMap[x1][y].getNum() > 0) {//cardsMap[x1][y]--当前第三层遍历到的卡片

                        if (cardsMap[x][y].getNum() <= 0) { // cardsMap[x][y] -- 当前第二层遍历到的卡片
                            //如果 当前第二层遍历到的卡片数据<= 0，將后一个数字前置
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;//
                            merge = true;

                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            //如果 当前第二层遍历到的卡片数据与后一个数字相等，当前第二层遍历到的卡片数据*2,后一个数字置零
                            //及时当前第二层遍历到的卡片数据与后一个数字的后一个数字不为零，但仍会做上一个判断语句，
                            // 最后起到的效果为，将当行数据相邻相等的合并，其后往前替换
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    /**
     * 向右滑
     */
    private void swipeRight() {

        boolean merge = false;//是否合并，即滑动方向、相邻两个卡片数字是否相等
        //遍历y轴[即行]
        for (int y = 0; y < LINES; y++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int x = LINES - 1; x >= 0; x--) {

                // 遍历 目的是？
                for (int x1 = x - 1; x1 >= 0; x1--) {//x1 = x - 1

                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {

                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    /**
     * 向上滑
     */
    private void swipeUp() {

        boolean merge = false;//是否合并，即滑动方向、相邻两个卡片数字是否相等
        //遍历y轴[即行]
        for (int x = 0; x < LINES; x++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int y = 0; y < LINES; y++) {

                for (int y1 = y + 1; y1 < LINES; y1++) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {

                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;

                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;

                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    /**
     * 向下滑
     */
    private void swipeDown() {

        boolean merge = false;//是否合并，即滑动方向、相邻两个卡片数字是否相等
        //遍历y轴[即行]
        for (int x = 0; x < LINES; x++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int y = LINES - 1; y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {

                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {

                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            score.addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    /**
     * 检测是否全部填满不可再玩
     */
    private void checkComplete() {

        boolean complete = true; // 是否全部填满，不可再玩

        ALL:
        //遍历y轴[即行]
        for (int y = 0; y < LINES; y++) {
            //遍历X轴[即列] , 即按笔序一个一个的检测
            for (int x = 0; x < LINES; x++) {
                if (cardsMap[x][y].getNum() == 0 || // 当前遍历到的卡片数字为零，可以继续玩

                        (x > 0 && cardsMap[x][y].equals(cardsMap[x - 1][y])) || //  每行(y没变)第2、3、4列的数据（x>0），与前一列数据一样

                        (x < LINES - 1 && cardsMap[x][y].equals(cardsMap[x + 1][y])) || //每行(y没变)不是第4列的(x<LINES-1)，与后一列数据一样

                        (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1])) || // 每列(x没变)非第1行的数据（y>0），与前一行数据一样

                        (y < LINES - 1 && cardsMap[x][y].equals(cardsMap[x][y + 1]))) { // 每列(x没变)非最后行的数据（y<LINES-1），与后一行数据一样

                    complete = false;// 可以继续玩
                    break ALL;
                }
            }
        }

        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("Finished").setMessage("Game Over").setPositiveButton("start again?", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();
        }

    }

    /** 设置游戏分数
     * @param score
     */
    public void setScore(MainActivity.Score score) {

        this.score = score;

    }


}
