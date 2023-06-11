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
    public String cellGrid;


    @Override
    public String toString() {
        return "S: " + (width - 1) / 2 + "x" + (height - 1) / 2 + ", M: " + method + ", A: " + author;
    }
}