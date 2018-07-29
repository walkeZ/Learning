package com.example.caihui.dbdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    private DBHelper mDBHelper;
    private Dao<School, Integer> mSchoolDao;
    private Dao<Student, Integer> mStudentDao;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = ((TextView) findViewById(R.id.main_tv));
        mDBHelper = DBHelper.getIntance(this);
        try {
            mSchoolDao = mDBHelper.getSchoolDao();
            mStudentDao = mDBHelper.getStudentDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testDao();
    }

    private void testDao() {

        Student stu1=new Student();
        stu1.setId(100);
        stu1.setName("mils");
        stu1.setSchoolId(0);

        Student stu2=new Student();
        stu2.setName("li");
        stu2.setSchoolId(1);

        School school1=new School();
        school1.setId(200);
        school1.setName("大学");
        school1.setLocation("广东");

        School school2=new School();

        school2.setName("中学");
        school2.setLocation("广州");

        try {
            mSchoolDao.create(school1);
            mSchoolDao.create(school2);

            //获取表中所有的student
            List<School> ss=mSchoolDao.queryForAll();
            Log.d(TAG, "testDao: ss.size() = "+ss.size() );

            for (School s : ss) {
                Log.d(TAG, "testDao: getName="+s.getName()+"  getSchoolId="+s.getName());
            }
            mSchoolDao.delete(school1);
            ss=mSchoolDao.queryForAll();
            Log.d(TAG, "testDao: ss.size="+ss.size());
            for (School s : ss) {
                Log.d(TAG, "testDao: getName="+s.getName()+"  getSchoolId="+s.getId());
            }

            mStudentDao.create(stu1);
            mStudentDao.create(stu2);

            //获取表中所有的student
            List<Student> stus=mStudentDao.queryForAll();
            Log.d(TAG, "testDao: ss.size() = "+ss.size() );

            for (Student st : stus) {
                Log.d(TAG, "testDao: getName="+st.getName()+"  getSchoolId="+st.getSchoolId());
            }
            mStudentDao.delete(stu1);
            stus=mStudentDao.queryForAll();
            Log.d(TAG, "testDao: ss.size="+ss.size());
            for (Student st : stus) {
                Log.d(TAG, "testDao: getName="+st.getName()+"  getSchoolId="+st.getId());
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
