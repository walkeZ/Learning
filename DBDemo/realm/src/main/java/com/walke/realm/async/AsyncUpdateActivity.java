package com.walke.realm.async;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.walke.realm.R;
import com.walke.realm.activity.BaseActivity;
import com.walke.realm.constants.IntentStr;
import com.walke.realm.entity.Teacher;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by walke.Z on 2018/5/28.
 */

public class AsyncUpdateActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.aau_etName)
    EditText mEtName;
    @BindView(R.id.aau_tvDesc)
    TextView mTvDesc;

    private Realm mRealm;
    private Teacher mTeacher;
    private RealmAsyncTask mUpdateTask;

    @Override
    protected int rootLayout() {
        return R.layout.activity_async_update;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar, "异步修改");
        mRealm = Realm.getDefaultInstance();
        Serializable extra = getIntent().getSerializableExtra(IntentStr.TEACHER);
        if (extra != null) {
            mTeacher = ((Teacher) extra);
            mTvDesc.setText(mTeacher.toString());
        }
    }


    @OnClick(R.id.aau_btUpdate)
    public void onClick() {
        final String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast("请输入新的name");
            return;
        }
        mUpdateTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Teacher teacher = realm.where(Teacher.class).equalTo("id", mTeacher.getId()).findFirst();
                teacher.setName(name);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                toast("更新成功");
                setResult(RESULT_OK);
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                toast("更新失败");
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUpdateTask!=null&&!mUpdateTask.isCancelled())
            mUpdateTask.cancel();
    }
}
