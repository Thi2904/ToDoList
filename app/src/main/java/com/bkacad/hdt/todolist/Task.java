package com.bkacad.hdt.todolist;

public class Task {
    public String name;
    public long id;
    public Task(String name){
        this.name = name;
    }
    public Task(long id,  String name) {
        this.name = name;
        this.id = id;

    }


}

