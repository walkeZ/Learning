package com.walke.realm.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.walke.realm.R;
import com.walke.realm.adapter.StudentEditAdapter;
import com.walke.realm.entity.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.add_recyclerView)
    RecyclerView mAddRecyclerView;
    private List<Student> mStudents;
    private StudentEditAdapter mStudentEditAdapter;

    @Override
    protected int rootLayout() {
        return R.layout.activity_add;
    }

    @Override
    protected void initView() {
        mToolBar.setTitle("增加");
        LinearLayoutManager lm=new LinearLayoutManager(this);
        mAddRecyclerView.setLayoutManager(lm);
        initData();
        mStudentEditAdapter = new StudentEditAdapter(this,mStudents,R.layout.list_student_5_0_shipei);
//        mStudentEditAdapter = new StudentEditAdapter(this,mStudents,R.layout.list_student);
        mAddRecyclerView.setAdapter(mStudentEditAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
//       mToolBar=(Toolbar) findViewById(R.id.toolBar);

    }

    private void initData() {
        //List(列表)，元素以线性方式存储，元素可重复、有序，
        // ArrayList：通过index(索引/小标)访问/查询，故查询快，向中间插入慢
        // LinkedList 链式结构，向中间插入与删除快，查询慢
        // Vector 与 ArrayList相似，特点：线程安全(速度比较慢)

        // Set(集合) 元素不可重复，无序。存放的是对象的引用，无重复对象
        // HashSet 按照哈希算法来存取集合中的对象，存取速度较快
        // TreeSet 实现了SortedSet接口，能够对集合中的元素排序

        // Map(映射) 是一种把键对象(key)和值对象(value)映射的集合，可以(k-v)一对一、一对多。不可多对一，key唯一不可重复，value可重复
        //          无序(是通过映射关系，用key获取value)
        // 注意Map 没有继承Collection类
        // PS:List、Collection、Set、Map都是接口，不能直接实例化，ArrayList、HashMap等是其对应的具体实现类，
        //    接口能继承接口，不能继承类、能再实现接口。[类可以单继承多实现]
        mStudents = new ArrayList<>();
        mStudents.add(new Student("1000","walke",21,true,"清远"));
        mStudents.add(new Student("1001","jason",22,true,"广州"));
        mStudents.add(new Student("1003","peter",21,true,"福建厦门"));
        mStudents.add(new Student("1004","jack",23,true,"上海"));
        mStudents.add(new Student("1005","rose",20,true,"南京"));
        mStudents.add(new Student("1002","Arui",20,true,"湖南长沙"));
        mStudents.add(new Student("1006","walke2",21,true,"清远"));
        mStudents.add(new Student("1007","walke3",21,true,"清远"));
        mStudents.add(new Student("1008","walke4",21,true,"清远"));
        mStudents.add(new Student("1009","walke5",21,true,"清远"));
        mStudents.add(new Student("1010","walke6",21,true,"清远"));
        mStudents.add(new Student("1011","walke7",21,true,"清远"));

    }

}
