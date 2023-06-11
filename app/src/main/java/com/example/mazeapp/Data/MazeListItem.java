package com.example.mazeapp.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "maze")
public class MazeListItem {
    @PrimaryKey
    public int id;

    public String author;
    public String method;
    public int width;
    public int height;
    public String cellString;

    public MazeListItem(int id, String author, String method, int width, int height, String cellString) {
        this.id = id;
        this.author = author;
        this.method = method;
        this.width = width;
        this.height = height;
        this.cellString = cellString;
    }

    public MazeListItem(MazeListItem other) {
        this.id = other.id;
        this.author = other.author;
        this.method = other.method;
        this.width = other.width;
        this.height = other.height;
        this.cellString = other.cellString;
    }


    @Override
    public String toString() {
        return "S: " + (width - 1) / 2 + "x" + (height - 1) / 2 + ", M: " + method + ", A: " + author;
    }
}