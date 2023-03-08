package com.het.led_het;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;

public class LedActivity extends AppCompatActivity {
    private static final String TAG = "LedActivity";
    private static final int WHAT_ERROR = -1;
    private static final int WHAT_START = 1;
    private static final int WHAT_DOWN = 2;
    //补光灯：/sys/class/gpio_show/flashled_status
    //氛围灯：/sys/class/gpio_show/led_red_status
    //用文件流去写上面这两个灯带文件，1是开灯，0是关灯
    private static final String LED_LIGHT = "/sys/class/gpio_show/flashled_status";
    private static final String LED_LIGHT2 = "/sys/class/gpio_show/led_red_status";
    private TextView tvLight1;
    private TextView tvLight2;
    private TextView tvCmdStatus;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_START:
                    tvCmdStatus.setText(mClickText + " 命令开始");
                    break;
                case WHAT_DOWN:
                    tvCmdStatus.setText(mClickText+ " 命令完成");
                    break;
                case WHAT_ERROR:
                    IOException obj = (IOException) msg.obj;
                    tvCmdStatus.setText(mClickText+ " 命令异常，" + obj.getMessage());
                    break;

            }
        }
    };
    private String mClickText;
    private boolean isOpenLight1;
    private boolean isOpenLight2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
        setContentView(R.layout.activity_led);
        tvCmdStatus = (TextView) findViewById(R.id.tvCmdStatus);
        tvLight1 = (TextView) findViewById(R.id.tvLight1);
        tvLight2 = (TextView) findViewById(R.id.tvLight2);

        // 设置横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLight1:

                isOpenLight1 = !isOpenLight1;
                Log.i("ArHui", "onClick: -----> ---" + isOpenLight1);
                mClickText = "tvLight1," + isOpenLight1 + ", ";
                mHandler.sendEmptyMessage(WHAT_START);
                if (isOpenLight1) {
                    tvLight1.setBackgroundResource(R.drawable.shape_light_on1);
                    cmdLedOn(LED_LIGHT);
                } else {
                    cmdLedOff(LED_LIGHT);
                    tvLight1.setBackgroundResource(R.drawable.shape_light_off);
                }

                break;
            case R.id.tvLight2:

                isOpenLight2 = !isOpenLight2;
                Log.i("ArHui", "onClick: -----> ---" + isOpenLight2);
                mClickText = "tvLight2," + isOpenLight2 + ", ";
                mHandler.sendEmptyMessage(WHAT_START);
                if (isOpenLight2) {
                    tvLight2.setBackgroundResource(R.drawable.shape_light_on2);
                    cmdLedOn(LED_LIGHT2);
                } else {
                    cmdLedOff(LED_LIGHT2);
                    tvLight2.setBackgroundResource(R.drawable.shape_light_off);
                }
                break;

        }
//        Toast.makeText(this, mClickText,Toast.LENGTH_LONG).show();
    }

    /**
     * https://blog.csdn.net/u013547134/article/details/37565963
     * //led开：
     */
    private void cmdLedOn(String address) {
        // TODO Auto-generated method stub
        DataOutputStream dos = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(process.getOutputStream());
//            dos.writeBytes("echo 1 > /sys/class/gpio/gpio74/value"+"\n");
            dos.writeBytes("echo 1 > " + address + "\n");

            dos.flush();
            dos.close();
            mHandler.sendEmptyMessage(WHAT_DOWN);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e(TAG, "cmdLedOff: ", e);
            Message message = mHandler.obtainMessage();
            message.what = WHAT_ERROR;
            message.obj = e;
            mHandler.sendMessage(message);
        }
    }

    //led关
    private void cmdLedOff(String address) {
        // TODO Auto-generated method stub
        DataOutputStream dos = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(process.getOutputStream());
//            dos.writeBytes("echo 0 > /sys/class/gpio/gpio74/value"+"\n");
            dos.writeBytes("echo 0 > " + address + "\n");
            dos.flush();
            dos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e(TAG, "cmdLedOff: ", e);
            Message message = mHandler.obtainMessage();
            message.what = WHAT_ERROR;
            message.obj = e;
            mHandler.sendMessage(message);
        }
    }
}