package com.example.caihui.dbdemo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by caihui on 2016/10/20.
 */
@DatabaseTable(tableName = "student")
public class Student {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

  /*  @DatabaseField(columnName = "schoolName")
    private String schoolName;*/

    @DatabaseField(columnName = "schoolId")
    private int schoolId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {

        this.schoolName = schoolName;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}
