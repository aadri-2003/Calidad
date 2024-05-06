package com.example.todolist;

import android.widget.DatePicker;

import java.io.Serializable;

public class task implements Serializable {
    private String text;
    private String date;
    private int day;
    private int month;
    private int year;
    private String dateOrd;
    private String color;
    private String note;

    public task(String text, String date,int year, int month, int day, String date2, String color,String note) {
        this.year = year;
        this.month= month;
        this.day= day;
        this.text = text;
        this.date = date;
        this.dateOrd = date2;
        this.color = color;
        this.note=note;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDateOrd() {
        return dateOrd;
    }

    public void setDateOrd(String date) {
        this.dateOrd = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
