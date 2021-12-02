package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class functionYourSubjectScreen extends AppCompatActivity {

    SQLiteDatabase db;
    Intent intent;
    Button btn_add_student_subject, btn_search_avrg_student_subject, btn_search_name_student_subject;
    TextView tv_subject_name;
    EditText et_name_new_student_subject, et_lastname_new_student_subject,
            et_avrg_new_student_subject, et_id_new_student_subject, et_avrg_search_student_subject,
            et_name_search_student_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions_your_subject);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        btn_add_student_subject = findViewById(R.id.btn_add_student_subject);
        btn_search_avrg_student_subject = findViewById(R.id.btn_search_avrg_student_subject);
        btn_search_name_student_subject = findViewById(R.id.btn_search_name_student_subject);
        tv_subject_name = findViewById(R.id.tv_subject_name);
        et_name_new_student_subject = findViewById(R.id.et_name_new_student_subject);
        et_lastname_new_student_subject = findViewById((R.id.et_lastname_new_student_subject));
        et_avrg_new_student_subject = findViewById(R.id.et_avrg_new_student_subject);
        et_id_new_student_subject = findViewById(R.id.et_id_new_student_subject);
        et_avrg_search_student_subject = findViewById(R.id.et_avrg_search_student_subject);
        et_name_search_student_subject = findViewById(R.id.et_name_search_student_subject);


        intent = getIntent();
        int id = intent.getIntExtra("id", 0);


        int subjectId = 0;

        Cursor tmp = db.rawQuery("select "+Utils.TABLE_TEACHER_COL_SUBJECT_ID+" from "+ Utils.TABLE_NAME_TEACHER+" WHERE "+Utils.TABLE_TEACHER_COL_ID+" = '"+id+"'", null);
        while(tmp.moveToNext()) {
            subjectId = tmp.getInt(0);
        }
        tmp.close();

        String subject="";

        tmp = db.rawQuery("select "+Utils.TABLE_SUBJECT_COL_NAME+" from "+ Utils.TABLE_NAME_SUBJECT+" WHERE "+Utils.TABLE_SUBJECT_COL_ID+" = '"+subjectId+"'", null);
        while(tmp.moveToNext()) {
            subject = tmp.getString(0);
        }
        tmp.close();

        //Fix it
        String final_tv = (tv_subject_name.getText()+" ")+subject.toString()+"hola";

        tv_subject_name.setText(final_tv);


        btn_add_student_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_search_avrg_student_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_search_name_student_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    }
