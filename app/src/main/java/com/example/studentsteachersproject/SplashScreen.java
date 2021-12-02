package com.example.studentsteachersproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    SQLiteDatabase db;
    ImageView img_school;
    int click=0;
    Cursor tmp;
    EditText et_admin_id;
    Button btn_classroom, btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        Utils.createTables(db);
        Utils.addALLToDb(db);

        btn_classroom=findViewById(R.id.btn_classroom);
        btn_login=findViewById(R.id.btn_login);
        et_admin_id=findViewById(R.id.et_admin_id);
        img_school=findViewById(R.id.img_school);

        btn_login.setVisibility(View.INVISIBLE);
        et_admin_id.setVisibility(View.INVISIBLE);

        img_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
                if(click==5){
                    btn_login.setVisibility(View.VISIBLE);
                    et_admin_id.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String id = et_admin_id.getText().toString();
                boolean found = false;
                Intent intent;
                tmp = db.rawQuery("select id from "+ Utils.TABLE_NAME_ADMIN, null);
                while (tmp.moveToNext()){
                    System.out.println(tmp.getInt(tmp.getColumnIndex("id")));
                    if(id.equals(String.valueOf(tmp.getInt(tmp.getColumnIndex("id"))))) {
                        intent = new Intent(SplashScreen.this, adminScreen.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        found = true;
                    }
                }

                if(!found) Toast.makeText(SplashScreen.this,
                        "Wrong id", Toast.LENGTH_SHORT).show();;


            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt_teacher:
                startActivity(new Intent(SplashScreen.this, teacherCheckScreen.class));
                return true;
            case R.id.opt_student:
                startActivity(new Intent(SplashScreen.this, studentCheckScreen.class));
                return true;
            case R.id.opt_allStudents:

                return true;
        }
        return true;
    }
}