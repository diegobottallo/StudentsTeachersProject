package com.example.studentsteachersproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class listStudentRecycle extends AppCompatActivity {

        ArrayList<Student> listStudents;
        RecyclerView recyclerViewStudents;
        SQLiteDatabase db;
        Student student;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_teacher_screen);

            db = openOrCreateDatabase(Utils.DATABASE_NAME,
                    MODE_PRIVATE, null);


            recyclerViewStudents= (RecyclerView) findViewById(R.id.rv_your_classroom);
            recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

            listStudents=new ArrayList<>();

            Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT, null);
            while (tmp.moveToNext()){
                student = new Student();
                student.setId(tmp.getInt(0));
                student.setName(tmp.getString(1));
                student.setLastName(tmp.getString(2));
                student.setClassName(tmp.getString(3));
                student.setAverage(tmp.getInt(4));


                listStudents.add(student);
            }
            tmp.close();

            adapterStudent adapter=new adapterStudent(listStudents);
            recyclerViewStudents.setAdapter(adapter);

        }
    }

