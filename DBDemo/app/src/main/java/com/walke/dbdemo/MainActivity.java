package com.walke.dbdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.walke.dbdemo.db.dao.OriginDaoTest;
import com.walke.dbdemo.db.dao.UserDao;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://www.jianshu.com/p/28912c2f31db
 */
public class MainActivity extends AppCompatActivity {

    private OriginDaoTest mOriginDaoTest;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mOriginDaoTest = new OriginDaoTest(this);
        mUserDao = new UserDao(this);
        TextView tv = (TextView) findViewById(R.id.textView4);
        tv.setText("在log输出中查看效果\n DB path: "+mOriginDaoTest.getDBHelper().getReadableDatabase().getPath());

    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button://add
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                mOriginDaoTest.add();
                break;
            case R.id.button2://query
                Toast.makeText(this, "query", Toast.LENGTH_SHORT).show();
                mOriginDaoTest.queryAll();
                break;
            case R.id.button3://delete
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                mOriginDaoTest.deleteOne();
                break;
            case R.id.button4://query-BY
                Toast.makeText(this, "query-BY", Toast.LENGTH_SHORT).show();
                mOriginDaoTest.queryByName("lisi");
                break;
            case R.id.button5://delete-all
                Toast.makeText(this, "delete-all", Toast.LENGTH_SHORT).show();
                mOriginDaoTest.deleteAll();
                break;
            case R.id.button6://update
                Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
                mOriginDaoTest.update();
                break;
            case R.id.button7: Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
                mUserDao.add();
                break;
            case R.id.button8://QUERY
                Toast.makeText(this, "QUERY", Toast.LENGTH_SHORT).show();
                mUserDao.queryAll();
                break;
            case R.id.button9://DELETE
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();
                mUserDao.deleteOneByName("Bbb");
                break;
            case R.id.button10://QUERY-BY
                Toast.makeText(this, "QUERY-BY", Toast.LENGTH_SHORT).show();
                mUserDao.queryByName("Ccc");
                break;
            case R.id.button11://DELETE-ALL
//                mUserDao.deleteOneById(1);
                mUserDao.deleteAll();
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button12://UPDATE
                Toast.makeText(this, "UPDATE", Toast.LENGTH_SHORT).show();
                mUserDao.updateWeightByName("Ccc", 80.80f);
                break;
        }
    }
}
