package com.walke.realm.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.walke.realm.R;
import com.walke.realm.constants.IntentStr;
import com.walke.realm.entity.Student;
import com.walke.realm.util.RealmHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2018/5/25.
 */

public class UpdateActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.update_tvName)
    TextView mTvName;
    @BindView(R.id.update_etName)
    EditText mEtName;
    @BindView(R.id.update_btUpdate)
    Button mBtUpdate;

    private Student mStudent;

    private RealmHelper mRealmHelper;

    @Override
    protected int rootLayout() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        if (intent != null) {
            mStudent = (Student) intent.getSerializableExtra(IntentStr.STUDENT);
            if (mStudent!=null){
                mRealmHelper = new RealmHelper(this);
                mTvName.setText("姓名："+mStudent.getName()+"\n学号："+mStudent.getId());
            }else {
                toast("数据异常");
                finish();
            }
        }

    }

    @OnClick(R.id.update_btUpdate)
    public void onClick() {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            toast("请输入新名字");
            return;
        }
        mStudent.setName(name);
        mRealmHelper.updateOne(mStudent);
        setResult(RESULT_OK);
        finish();

    }
}
