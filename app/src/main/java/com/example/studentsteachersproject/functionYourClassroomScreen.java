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

public class functionYourClassroomScreen extends AppCompatActivity {

    SQLiteDatabase db;
    Intent intent;
    Button btn_add_student_class, btn_search_avrg_student_class, btn_search_name_student_class;
    TextView tv_classroom_name;
    EditText et_name_new_student_class, et_lastname_new_student_class,
             et_avrg_new_student_class, et_id_new_student_class, et_name_search_student,
             et_avrg_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions_your_classroom);

        db=openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        btn_add_student_class=findViewById(R.id.btn_add_student_class);
        btn_search_avrg_student_class= findViewById(R.id.btn_search_avrg_student_class);
        btn_search_name_student_class=findViewById(R.id.btn_search_name_student_class);
        tv_classroom_name=findViewById(R.id.tv_classroom_name);
        et_name_new_student_class=findViewById(R.id.et_name_new_student_class);
        et_lastname_new_student_class=findViewById((R.id.et_lastname_new_student_class));
        et_avrg_new_student_class=findViewById(R.id.et_avrg_new_student_class);
        et_id_new_student_class=findViewById(R.id.et_id_new_student_class);
        et_avrg_search=findViewById(R.id.et_avrg_search_student);
        et_name_search_student=findViewById(R.id.et_name_search_student);


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

        tv_classroom_name.setText(tv_classroom_name.getText() + " " + classroom);


        String finalClassroom = classroom;
        btn_add_student_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name_new_student_class.getText().toString();
                String lName= et_lastname_new_student_class.getText().toString();
                String className= finalClassroom;
                String avrgString = et_avrg_new_student_class.getText().toString();
                int avrg = Integer.parseInt(avrgString);
                String idString = et_id_new_student_class.getText().toString();
                int idStudent = Integer.parseInt(idString);

                Cursor c = db.rawQuery("SELECT * FROM "+ Utils.TABLE_NAME_STUDENT+
                        " WHERE "+Utils.TABLE_STUDENT_COL_ID+" = " + idStudent, null);

                if(c.getCount() >= 1){
                    Toast.makeText(functionYourClassroomScreen.this, "There is already a student with this Id. " +
                            "   Please try again.", Toast.LENGTH_SHORT).show();
                }else{
                    db.execSQL("insert into "+Utils.TABLE_NAME_STUDENT+" values(" + idStudent + ", '"+name+"','"+lName+"','" + className + "'," + avrg + ")");
                    startActivity(new Intent( functionYourClassroomScreen.this, teacherScreen.class));
                }
            }
        });

        btn_search_avrg_student_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String avgString=et_avrg_search.getText().toString();
                int avg = Integer.parseInt(avgString);

            }
        });

        btn_search_name_student_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name_search_student.getText().toString();


            }
        });


    }
}
