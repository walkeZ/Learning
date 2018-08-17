package com.walke.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("walke:   MainActivity", "   onCreate:-------> this；"+this);
        Context context = findViewById(R.id.main_tv).getContext();
        Log.i("walke:   MainActivity", "   onCreate:-------> context: "+context);

    }
}
