package com.example.caihui.dbdemo.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.caihui.dbdemo.MainActivity;
import com.example.caihui.dbdemo.R;
import com.example.caihui.dbdemo.demo2.test.TestUtils;

public class Main2Activity extends Activity {

	private TestUtils mTestUtils;


	private Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		mTestUtils = new TestUtils(this);
		mTestUtils.addUserTest();
		mTestUtils.delUserTest();
		mTestUtils.updateUserTest();
		mTestUtils.queryUserTest();

	}

	public void test(View view) {
		startActivity(new Intent(this, MainActivity.class));
	}


}
