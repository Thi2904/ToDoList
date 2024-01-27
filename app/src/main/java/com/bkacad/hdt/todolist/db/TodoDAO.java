package com.bkacad.hdt.todolist.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bkacad.hdt.todolist.Task;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    private final MyDB dbHelper;
    public TodoDAO(MyDB dbHelper) {
        this.dbHelper = dbHelper;
    }

    //CREATE

    public Task insert(Task task){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("work",task.name);
        long newId = db.insert("todo",null,values);
        task.id = newId;
        return task;
    }

    //READ
    public List<Task> getAll(){
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("todo",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Task task = new Task(cursor.getInt(0),cursor.getString(1));
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }
    //Update
    public void update(Task task,long id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("work",task.name);
        db.update("todo",values,"id = " + id,null);
    }

    //Del
    public void deleteById (long id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("todo","id = "+id, null);
    }
    public String getTaskNameById(long id) {
        String taskName = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = { "work" };
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query("todo", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            taskName = cursor.getString(cursor.getColumnIndex("work"));
        }
        cursor.close();
        return taskName;
    }
}



