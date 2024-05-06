package com.example.todolist;

import java.util.Comparator;

public class comparador implements Comparator<task> {
    public int compare(task a, task b) {
        return a.getDateOrd().compareTo(b.getDateOrd());
    }
}
