package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class teacherScreen extends AppCompatActivity {

    RecyclerView rv_your_subject, rv_your_classroom;
    Button btn_your_subject, btn_your_classroom;
    SQLiteDatabase db;
    Intent intent;
    TextView tv_your_subject, tv_your_classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        rv_your_classroom=findViewById(R.id.rv_your_classroom);
        rv_your_subject=findViewById(R.id.rv_your_subject);
        btn_your_classroom=findViewById(R.id.btn_your_classroom);
        btn_your_subject=findViewById(R.id.btn_your_subject);
        tv_your_classroom=findViewById(R.id.tv_your_classroom);
        tv_your_subject=findViewById(R.id.tv_your_subject);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String classroom="";

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_CLASSROOM, null);
        while(tmp.moveToNext()) {
            int tutorId = tmp.getInt(1);
            if (id == tutorId) {
              classroom = tmp.getString(0);
            }
        }
        tmp.close();

        if(!classroom.equals("")) {
            tv_your_classroom.setText(tv_your_classroom.getText() + " " + classroom);
        } else{
            tv_your_classroom.setText(tv_your_classroom.getText()+" no classroom");
        }


        int subjectId=0;


        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_TEACHER, null);
        while(tmp.moveToNext()) {
            int tutorId = tmp.getInt(0);
            if (id == tutorId) {
                subjectId = tmp.getInt(3);
            }
        }
        tmp.close();

        String subject="";

        tmp = db.rawQuery("select name from "+ Utils.TABLE_NAME_SUBJECT+" WHERE id = '"+subjectId+"'", null);
        while(tmp.moveToNext()) {
                subject = tmp.getString(0);
        }
        tmp.close();

        tv_your_subject.setText(tv_your_subject.getText()+" "+subject);

        String finalClassroom = classroom;
        btn_your_classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalClassroom.equals("")) {
                    intent = new Intent(teacherScreen.this, functionYourClassroomScreen.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    Toast.makeText(teacherScreen.this, "You are not a tutor of any class", Toast.LENGTH_SHORT).show();
                }

            }
        });

        String finalSubject = subject;
        btn_your_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalSubject.equals("")) {
                    intent = new Intent(teacherScreen.this, functionYourSubjectScreen.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    Toast.makeText(teacherScreen.this, "You are not currently teacher of any subject", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv_your_classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rv_your_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
}
}
