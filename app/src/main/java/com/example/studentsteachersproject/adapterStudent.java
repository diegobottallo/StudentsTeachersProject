package com.example.studentsteachersproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class adapterStudent extends RecyclerView.Adapter<adapterStudent.studentViewHolder> {

        ArrayList<Student> studentList;

        public adapterStudent(ArrayList<Student> studentList) {
            this.studentList = studentList;
        }

        @Override
        public studentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_item_recycle_view,null,false);
            return new studentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(studentViewHolder holder, int position) {
            holder.name.setText(studentList.get(position).getName());
            holder.lname.setText(studentList.get(position).getLastName());
            holder.id.setText(studentList.get(position).getId());
            holder.average.setText(studentList.get(position).getAverage());
            holder.classroom.setText(studentList.get(position).getClassName());
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }

        public class studentViewHolder extends RecyclerView.ViewHolder {

            TextView name,lname,id, average, classroom;

            public studentViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.text_name);
                lname = (TextView) itemView.findViewById(R.id.text_last_name);
                id = (TextView) itemView.findViewById(R.id.text_student_id);
                average = (TextView) itemView.findViewById(R.id.text_average);
                classroom = (TextView) itemView.findViewById(R.id.text_classroom);
            }
        }
    }

