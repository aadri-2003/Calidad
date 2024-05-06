package com.example.todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ListManager implements Serializable {

    private ArrayList<task> tasklist;

    public ListManager() {
        this.tasklist = new ArrayList<>();
    }

    public ArrayList<task> getTasklist() {
        sortTaskList();
        return tasklist;
    }

    public void sortTaskList() {
        Collections.sort(tasklist, new comparador());
    }

    public void addTask(task t){
        tasklist.add(t);
    }
    public void addTaskbyPos(task t, int pos){
        tasklist.set(pos,t);
    }
    public void borrar(int n) {
        tasklist.remove(n);
    }
}
