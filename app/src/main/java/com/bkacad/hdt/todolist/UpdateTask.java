package com.bkacad.hdt.todolist;

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

public class UpdateTask extends AppCompatActivity {
    private List<Task> dataSource;
    private  ShowTaskAdapter showTaskAdapter;
    private MyDB dbHelper;
    private TodoDAO todoDAO;
    Button btn1,btn2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_task);
        long taskId = getIntent().getLongExtra("taskId", -1);
        editText = findViewById(R.id.et1);
        dbHelper = new MyDB(this);
        todoDAO = new TodoDAO(dbHelper);
        dataSource = todoDAO.getAll();
        showTaskAdapter = new ShowTaskAdapter(this,dataSource, todoDAO);
        editText.setText(todoDAO.getTaskNameById(taskId));
        btn1 = findViewById(R.id.buttonBackUpdate);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateTask.this, ShowTask.class);
                startActivity(intent);
            }
        });
        btn2 = findViewById(R.id.buttonUpdate);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editText.getText().toString();
                todoDAO.update(new Task(userInput),taskId);
                Toast.makeText(UpdateTask.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateTask.this, ShowTask.class);
                startActivity(intent);
            }
        });

    }
}