package com.walke.jardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.walke.myjar.SortTools;

/**  查询： Android SDK开发技术分享 。会有收获
 *   AndroidStudio项目打包成jar
 *   https://www.cnblogs.com/xxdh/p/6703746.html
 *   http://www.cnblogs.com/bokezhilu/p/7525037.html
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = ((TextView) findViewById(R.id.main_tv));

    }

    public void change(View view) {

        int[] arr=new int[]{8,9,5,6,3};

        int[] ints = SortTools.getInstance().bubbleSort(arr);
        StringBuilder sb=new StringBuilder();
        for (int anInt : ints) {
            Log.i("walke", "change: anInt = "+anInt);
            sb.append(anInt+" ， ");
        }

        mTextView.setText(sb.toString());

    }
}
