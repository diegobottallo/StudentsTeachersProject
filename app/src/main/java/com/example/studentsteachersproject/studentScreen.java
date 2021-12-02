package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class studentScreen extends AppCompatActivity {

    SQLiteDatabase db;
    Intent intent;
    TextView tv_name_student, tv_last_name_student;
    RecyclerView rv_your_subjects_student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        tv_name_student=findViewById(R.id.tv_name_student);
        tv_last_name_student=findViewById(R.id.tv_last_name_student);
        rv_your_subjects_student=findViewById(R.id.rv_your_subjects_student);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        String name="";
        String lname="";

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT, null);
        while(tmp.moveToNext()) {
            int tutorId = tmp.getInt(1);
            if (id == tutorId) {
                name = tmp.getString(1);
                lname = tmp.getString(2);
            }
        }
        tmp.close();

        tv_name_student.setText(tv_name_student.getText()+" "+name);
        tv_last_name_student.setText(tv_last_name_student.getText()+" "+lname);
    }
}
