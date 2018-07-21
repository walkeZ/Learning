package com.walke.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = ((TextView) findViewById(R.id.main_tvHelloWorld));
        mTextView.setText("hello,world!");
        String str1="我来自广东";

        Log.i("walke: MainActivity", "onCreate:------> "+str1);

    }
}
