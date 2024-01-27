    package com.bkacad.hdt.todolist;

    import android.app.AlertDialog;
    import android.app.Dialog;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.DialogFragment;

    import com.bkacad.hdt.todolist.db.MyDB;
    import com.bkacad.hdt.todolist.db.TodoDAO;

    import java.util.ArrayList;
    import java.util.List;

    public class ShowTask extends AppCompatActivity {
        ListView lv1;
        Button bt1;
        TextView tv1;
        private List<Task> dataSource;
        private  ShowTaskAdapter showTaskAdapter;
        private MyDB dbHelper;
        private TodoDAO todoDAO;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.show_task);

            lv1 = findViewById(R.id.listView1);
            dbHelper = new MyDB(this);
            todoDAO = new TodoDAO(dbHelper);
            bt1 = findViewById(R.id.button);
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(ShowTask.this, AddTask.class);
                startActivity(intent);
                }
            });
            tv1 = findViewById(R.id.textView2);
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShowTask.this, AddTask.class);
                    startActivity(intent);
                }
            });
//            todoDAO.insert(new Task("Thi"));
            dataSource = todoDAO.getAll();


        // Khởi tạo Adapter
            showTaskAdapter = new ShowTaskAdapter(this,dataSource, todoDAO);

        // Gán Adapter cho ListView
            lv1.setAdapter(showTaskAdapter);


//            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    long taskId = dataSource.get(position).id;
//                    todoDAO.deleteById(taskId);
//                    dataSource.clear();
//                    dataSource.addAll(todoDAO.getAll());
//                    showTaskAdapter.notifyDataSetChanged();
//                    Toast.makeText(ShowTask.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
//                }
//            });



        }


    }
