package walke.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import walke.baselibrary.R;
import walke.baselibrary.tools.RegularUtil;
import walke.baselibrary.tools.ToastUtil;


/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2017/2/8.
 */
public class TextEditTextView extends LinearLayout {

    private final static int TYPE_DEFAULT =-1;
    private final static int PHONE =0;
    private final static int PASSWORD =1;
    private final static int PHONE_AUTH_CODE =2;
    private final static int IMAGE_AUTH_CODE =3;

    private int leftRightDistance;
    private String titleText,hintText;
    private EditText etInput;
    private TextView tvLeftTitle;

    public TextEditTextView(Context context) {
        this(context,null);
    }

    public TextEditTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.text_edittext, this);
        tvLeftTitle = ((TextView) findViewById(R.id.te_leftTitle));
        etInput = ((EditText) findViewById(R.id.te_rightHint));
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextEditTextView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TextEditTextView_leftTileText) {//在library中用不了switch
                titleText = a.getString(attr);
                if (!TextUtils.isEmpty(titleText))
                    tvLeftTitle.setText(titleText);

            } else if (attr == R.styleable.TextEditTextView_rightHintText) {
                hintText = a.getString(attr);
                if (!TextUtils.isEmpty(hintText))
                    etInput.setHint(hintText);

            } else if (attr == R.styleable.TextEditTextView_leftRightDistance) {
                leftRightDistance = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                        0, getResources().getDisplayMetrics()));
                if (leftRightDistance > 0) {
                    LayoutParams lp = (LayoutParams) etInput.getLayoutParams();
                    lp.setMargins(leftRightDistance, 0, 0, 0);
                    etInput.setLayoutParams(lp);
                }

            } else if (attr == R.styleable.TextEditTextView_etType) {
                int etType = a.getInt(attr, TYPE_DEFAULT);
                if (etType == PHONE) {
                    etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                    etInput.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                } else if (etType == PASSWORD) {
                    etInput.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);//InputType.TYPE_TEXT_VARIATION_PASSWORD
                    etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                    etInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else if (etType == PHONE_AUTH_CODE) {
                    etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                    etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else if (etType == IMAGE_AUTH_CODE) {
                    etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                    etInput.setInputType(InputType.TYPE_CLASS_NUMBER);//图形验证码先默认全为数字
                } else if (etType == TYPE_DEFAULT) {

                }

            }
        }
        a.recycle();
    }

    public EditText getRightEditText() {
        return etInput;
    }

    public TextView getLeftTextView() {
        return tvLeftTitle;
    }

    public void setTitleText(String titleText) {
        if (!TextUtils.isEmpty(titleText))
            tvLeftTitle.setText(titleText);
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
        if (!TextUtils.isEmpty(hintText))
            etInput.setHint(hintText);
    }



    public boolean isFitPhone() {
        String inputText = etInput.getText().toString().trim();
        if (TextUtils.isEmpty(inputText)) {
            ToastUtil.showToast(getContext(),"请输入手机号");
            return false;
        }else if (!RegularUtil.isPhone(inputText)){
            ToastUtil.showToast(getContext(),"请输入正确的手机号码");
            return false;
        }
        return true;
    }

    public boolean isFitPassword() {
        String inputText = etInput.getText().toString().trim();
        if (TextUtils.isEmpty(inputText)) {
            ToastUtil.showToast(getContext(),"请输入密码");
            return false;
        }else if (inputText.length()<6||inputText.length()>15){
            ToastUtil.showToast(getContext(),"密码格式有误，请重新输入(6~15位)");
            return false;
        }
        return true;
    }

    public boolean isFitPhoneAuthCode() {
        String inputText = etInput.getText().toString().trim();
        if (TextUtils.isEmpty(inputText)) {
            ToastUtil.showToast(getContext(),"请输入验证码");
            return false;
        }else if (inputText.length() <4){
            ToastUtil.showToast(getContext(),"验证码格式有误，请重新输入");
            return false;
        }
        return true;
    }
    public boolean isFitImageAuthCode() {
        String inputText = etInput.getText().toString().trim();
        if (TextUtils.isEmpty(inputText)) {
            ToastUtil.showToast(getContext(),"请输入验证码");
            return false;
        }else if (inputText.length() <4){
            ToastUtil.showToast(getContext(),"图形验证码格式有误，请重新输入");
            return false;
        }
        return true;
    }

}
