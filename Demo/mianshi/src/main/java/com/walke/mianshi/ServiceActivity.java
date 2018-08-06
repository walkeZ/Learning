package com.walke.mianshi;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.walke.mianshi.services.BindServices;
import com.walke.mianshi.services.SimpleServices;

/**
 *
 */
public class ServiceActivity extends AppCompatActivity {

    private Button btStartService;
    private Intent mItTestService;



    private BindServiceConnection mConn;
    private BindServices mBindServices;
    private boolean mBond=false; // 是否已绑定
    private Intent mBindServiceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);

        btStartService = (Button) findViewById(R.id.service1_bt1);


    }



    public void click(View view) {
        // java switch 语句支持6种数据类型： byte、short、int、long和枚举(enum) + String
        switch (view.getId()) {
            case R.id.service1_bt1: // 启动服务普通
                mItTestService = new Intent(this, SimpleServices.class);
                mItTestService.putExtra("author","walke");
                this.startService(mItTestService);
                break;
            case R.id.service1_bt2: // 停止/终止服务
                stopService(mItTestService);
                break;

        }

    }


    public void bind(View view) {
        // java switch 语句支持6种数据类型： byte、short、int、long和枚举(enum) + String
        switch (view.getId()) {
            case R.id.service1_bt3: // 绑定服务
                mBindServiceIntent = new Intent(this, BindServices.class);
                mConn = new BindServiceConnection();
                bindService(mBindServiceIntent, mConn,BIND_AUTO_CREATE);//  BIND_AUTO_CREATE 常用
                this.startService(mBindServiceIntent);
                break;
            case R.id.service1_bt4: // 解绑
                unbindService(mConn);
                break;
            case R.id.service1_bt5: // 停止服务
                stopService(mBindServiceIntent);
                break;
            case R.id.service1_bt6: // 调用服务方法 ， 解绑后仍能调用，发现停止后也是还能调用
                if (mBindServices==null)
                    return;
                int randomNumber = mBindServices.getRandomNumber(10);
                Toast.makeText(this, "randomNumber = "+randomNumber, Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void jump(View view) {
        startActivity(new Intent(this,HomeActivity.class));
    }

    private class BindServiceConnection implements ServiceConnection{



        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBindServices = ((BindServices.MyBinder) service).getService();
            mBond=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBond=false;
        }
    }

}
