package com.bkacad.hdt.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bkacad.hdt.todolist.db.MyDB;
import com.bkacad.hdt.todolist.db.TodoDAO;

import java.util.List;

public class AddTask extends AppCompatActivity {
    private Context context;
    private List<Task> dataSource;
    private MyDB dbHelper;
    private TodoDAO todoDAO;
    private ShowTaskAdapter showTaskAdapter;
    Button btn, btn1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        dbHelper = new MyDB(this);
        todoDAO = new TodoDAO(dbHelper);
        btn = findViewById(R.id.buttonAdd);
        btn1 = findViewById(R.id.buttonBack);
        et1 = findViewById(R.id.et1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTask.this, ShowTask.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = et1.getText().toString(); // Move the line to here
                todoDAO.insert(new Task(userInput));
                Toast.makeText(AddTask.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTask.this, ShowTask.class);
                startActivity(intent);
            }
        });
    }
}