package walke.baselibrary.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

import walke.baselibrary.tools.LogUtil;
import walke.baselibrary.tools.PermissionUtil;
import walke.baselibrary.tools.ToastUtil;

/**
 * Created by walke.Z on 2018/1/10.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){

        }else {
            hideVirtualButtons();
            initState();
        }

    }
    //调用该方法后，虚拟案件就会被隐藏 从屏幕底部向上拉，可以再次显示
    @SuppressLint("NewApi")
    private void hideVirtualButtons() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    /**
     * 在需要实现沉浸式状态栏的Activity的布局中添加以下参数
     * android:fitsSystemWindows="true"
     * android:clipToPadding="true"
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /** 点击其他地方隐藏输入框 */
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /** 估计是：是否点击了其他区域
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public void checkPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (PermissionUtil.checkPermissionSetLack(this, permissions)) {
                requestPermissions(PermissionUtil.PERMISSIONS, PermissionUtil.DEFUALT_REQUEST_CODE);
                return;
            }
        }
    }

    public boolean isFixPermission(String[] permissions,int  requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (PermissionUtil.checkPermissionSetLack(this, permissions)) {
                requestPermissions(PermissionUtil.PERMISSIONS, requestCode);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (isGetAllPermission(grantResults)) {
            permissionsGetSuccess(requestCode);
            logI("doNext: ------------------获得全部授权");
        } else {
            permissionsGetFail(requestCode);
            logI("doNext: ------------------未获得全部授权");
        }
    }

    /**
     * @param requestCode 权限获取成功
     */
    public void permissionsGetSuccess(int requestCode){
    }

    /**
     * @param requestCode 权限获取失败
     */
    public void permissionsGetFail(int requestCode) {
    }

    private boolean isGetAllPermission(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                //只要存在一个权限未授权 则返回false
                return false;
            }
        }
        return true;
    }


    /**
     * 打开系统设置的应用管理界面
     */
    public void openSystemSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoft() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * @param editText 使指定EditText控件获取焦点并弹出软键盘
     */
    public void editTextGetFocus(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @param editText 进入界面使指定EditText控件获取焦点并弹出软键盘
     */
    public void enterGetFocus(final EditText editText) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editTextGetFocus(editText);
            }
        }, 200);//200
    }

    /**
     * @param view 通过点击使某控件获取焦点
     */
    public void setClickedGetFocus(final View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.performClick();
            }
        }, 200);
    }

    /**   SpannableStringBuilder的使用,TextView部分字体变颜色
     * @param tv
     * @param front
     * @param tag
     * @param after
     * @param color
     */
    public void tvSpannable(TextView tv, String front, String tag, String after, int color) {
        String str = front + tag + after;
        int winStart = str.indexOf(tag);
        int winEnd = winStart + tag.length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), winStart, winEnd, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(style);
    }

    /** SpannableStringBuilder的使用,TextView部分字体变颜色
     * @param tv
     * @param text 全文本
     * @param tag1 变色字体1
     * @param tag2 变色字体2
     * @param color
     */
    public void tvSpannableTwo(TextView tv, String text, String tag1,String tag2,int color) {
//        String str = "请携带此张彩票和身份证\n前往体彩中心兑奖";
        int bstart = text.indexOf(tag1);
        int bend = bstart + tag1.length();
        int fstart = text.indexOf(tag2);
        int fend = fstart + tag2.length();
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(color), bstart, bend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(color), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(style);
    }

    /** SpannableStringBuilder的使用,TextView部分字体变颜色
     * @param tv
     * @param text
     * @param tag
     */
    public void tvSpannable(TextView tv, String text, String tag) {
        if (TextUtils.isEmpty(text)) {
            tv.setText("");
            return;
        }
        if (text.contains("@")){
            String[] split = text.split("@");
            if (split.length==2) {
                String str = split[0] + tag + split[1];
                int winStart =  str.indexOf(tag);
                int winEnd = winStart + tag.length();
                SpannableStringBuilder style = new SpannableStringBuilder(str);
                style.setSpan(new ForegroundColorSpan(Color.RED), winStart, winEnd, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                tv.setText(style);
            }
        }
    }

    /** 文本替换
     * @param oldText
     * @param newText
     * @return
     */
    public String textReplace(String oldText, String newText) {
        if (TextUtils.isEmpty(oldText)&&!TextUtils.isEmpty(newText))
            return newText;
        else
            return oldText;
    }



    public void logI(String message){
        if (!TextUtils.isEmpty(message))
            LogUtil.i(this.getClass().getSimpleName(),"-------------------> "+message);
    }
    public void logD(String message){
        if (!TextUtils.isEmpty(message))
            LogUtil.d(this.getClass().getSimpleName(),"--------> "+message);
    }
    public void logE(String message){
        if (!TextUtils.isEmpty(message))
            LogUtil.e(this.getClass().getSimpleName(),"---------      -----------> "+message);
    }

    public void toast(String message) {
        if (!TextUtils.isEmpty(message))
            ToastUtil.showToast(this, message);
    }
    public void toastLong(String message) {
        if (!TextUtils.isEmpty(message))
            ToastUtil.toastLong(this, message);
    }

    public void midToast(String text) {
        if (!TextUtils.isEmpty(text))
            ToastUtil.showMiddleToast(this,text);
    }

    public void changeButtonBackground(final EditText[] ets, final Button btTag, final int bgEnableId, final int bgUnableId) {
        for (final EditText et : ets) {
            InputFilter[] ifs = et.getFilters();
            Class<? extends InputFilter[]> aClass = ifs.getClass();
            String simpleName = aClass.getSimpleName();
            Field[] fields = aClass.getFields();

            InputFilter inputFilter = ifs[0];
            Class<? extends InputFilter> aClass1 = inputFilter.getClass();
            int value=0;
            try {
                Field mMax = aClass1.getDeclaredField("mMax");
                mMax.setAccessible(true);
                value=(int)mMax.get(inputFilter);
                logI("111");

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
//            final int finalValue=value;
            final int finalValue=0;
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    boolean fix = false;
                    for (int i = 0; i < ets.length; i++) {
                        if (finalValue == 0) {
                            fix = ets[i].getText().toString().length() > 0;
                        } else{
                            fix = ets[i].getText().toString().length() == finalValue;
                        }
                    }
                    if (fix){
                        btTag.setBackgroundResource(bgEnableId);
                    }else {
                        btTag.setBackgroundResource(bgUnableId);
                    }
                }
            });
        }

    }

    public void etsAutoComplete(final EditText[] ets, final Button btTag,final int bgEnableId, final int bgUnableId) {
        for (final EditText et : ets) {
            InputFilter[] ifs = et.getFilters();
            InputFilter inputFilter = ifs[0];
            Class<? extends InputFilter> aClass1 = inputFilter.getClass();
            int value=0;
            try {
                Field mMax = aClass1.getDeclaredField("mMax");
                mMax.setAccessible(true);
                value=(int)mMax.get(inputFilter);
                logI("111");

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            final int finalValue=value;
//            final int finalValue=0;
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    boolean fix = false;
                    for (int i = 0; i < ets.length; i++) {
                        fix = ets[i].getText().toString().length() > 0;
                        if ( ets[i]==et){
                            if (ets[i].getText().toString().trim().length() == finalValue
                                    &&i+1<ets.length){
                                editTextGetFocus(ets[i+1]);
                            }
                        }
                       /* if (finalValue == 0) {
                            fix = ets[i].getText().toString().length() > 0;
                        } else{
                            fix = ets[i].getText().toString().length() == finalValue;
                        }*/
                    }
                    if (fix){
                        btTag.setBackgroundResource(bgEnableId);
                    }else {
                        btTag.setBackgroundResource(bgUnableId);
                    }
                }
            });
        }

    }

}
