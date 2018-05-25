package com.walke.realm.util;

import android.content.Context;

import com.walke.realm.entity.Student;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by walke.Z on 2018/5/24.
 */

public class RealmHelper {
    public static final  String DB_NAME="myRealm_realm";
    private Realm mRealm;

    public RealmHelper(Context  context) {
        mRealm = Realm.getDefaultInstance();
    }

    /** 增加个
     * @param student
     */
    public void addOne(Student student){
        try {
            mRealm.beginTransaction();
            mRealm.copyToRealm(student);
            mRealm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 删除个
     * @param student
     */
    public void deleteOne(Student student){
        try {
            Student stu = mRealm.where(Student.class).equalTo("id", student.getId()).findFirst();
            mRealm.beginTransaction();
            stu.deleteFromRealm();
            mRealm.commitTransaction();
        } catch (Exception e) {
            //java.lang.IllegalStateException: The Realm is already in a write transaction in /Users/cm/Realm/realm-java/realm/realm-library/src/main/cpp/io_realm_internal_SharedRealm.cpp line 102
            e.printStackTrace();
        }
    }

    /** 更新 个
     * @param student
     */
    public void updateOne(Student student){
        try {
            Student stu = mRealm.where(Student.class).equalTo("id", student.getId()).findFirst();
            mRealm.beginTransaction();
            stu.setName(student.getName());
//            stu.setId(student.getId()+"11");//主键不可改
//            stu=student;//这里改了引用 故不可用
            mRealm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 查询所有
     * @return
     */
    public List<Student> queryAll(){
        try {
            RealmResults<Student> students = mRealm.where(Student.class).findAll();
            /**
             * 对插叙结果，按id排序，z只能对查询结果排序
             */
            students=students.sort("id");
//        students=students.sort("id", Sort.DESCENDING);//降序排序
            return mRealm.copyFromRealm(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 查询个( 根据id(主键) )
     * @param id
     * @return
     */
    public Student queryOneById(String id){
        Student student = mRealm.where(Student.class).equalTo("id", id).findFirst();
        return student;
    }

    /** 查询个 根据age
     * @param age
     * @return
     */
    public Student queryByAge(int age){
        Student student = mRealm.where(Student.class).equalTo("age", age).findFirst();
        return student ;
    }

    /** 检查是否存在
     * @param id
     * @return
     */
    public boolean isStudentExist(String id){
        Student stu = mRealm.where(Student.class).equalTo("id", id).findFirst();
        if (stu==null)
            return false;
        return true;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void close(){
        if (mRealm!=null)
            mRealm.close();
    }
}
