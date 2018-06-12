package com.walke.testui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * https://www.jianshu.com/p/67c2f4ae9a6e
 * https://www.jianshu.com/p/b373aec43c01
 *
 */
public class UITestActivity extends ActionBarActivity {

    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_test);
        mTextView = ((TextView) findViewById(R.id.ui_textView111));
        mEditText = (EditText) findViewById(R.id.ui_editText);
        mTextView.setText("Hello, "+mEditText.getText().toString().trim()+" !");
    }

    public void sayHello(View view) {
//        mTextView.setText("Hello, "+mEditText.getText().toString().trim()+" !");// 最后！前有个空格与test代码不一致故会报错
        mTextView.setText("Hello, " + mEditText.getText().toString().trim() + "!");
//        Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
    }
}
