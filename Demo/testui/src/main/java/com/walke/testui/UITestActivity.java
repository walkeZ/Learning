package com.walke.testui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
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
        mTextView = ((TextView) findViewById(R.id.ui_textView));
        mEditText = (EditText) findViewById(R.id.ui_editText);
        mTextView.setText("Hello, "+mEditText.getText().toString().trim()+" !");
    }

    public void sayHello(View view) {
//        Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
    }
}
