package com.bkacad.hdt.todolist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bkacad.hdt.todolist.db.TodoDAO;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShowTaskAdapter extends BaseAdapter {
    private Context context;
    private List<Task> dataSource;
    private LayoutInflater layoutInflater;
    private TodoDAO todoDAO;

    public ShowTaskAdapter (Context context, List<Task> dataSource, TodoDAO todoDAO) {
        this.context = context;
        this.dataSource = dataSource;
        this.layoutInflater = layoutInflater.from(context);
        this.todoDAO = todoDAO;
    }
    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.task_layout, parent, false);
        TextView tvName = convertView.findViewById(R.id.textView4);
        ImageView imgTask = convertView.findViewById(R.id.imageView12);
        long taskId = dataSource.get(position).id;
        Button btn4 = convertView.findViewById(R.id.button5);
        Button btnUpdate = convertView.findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoDAO.deleteById(taskId);
                dataSource.clear();
                dataSource.addAll(todoDAO.getAll());
                notifyDataSetChanged();
                Toast.makeText(context,"Xoá thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTask.class);
                intent.putExtra("taskId", taskId);
                context.startActivity(intent);

            }
        });
        Task task = dataSource.get(position);
        tvName.setText(task.name);
        return convertView;
    }
}
