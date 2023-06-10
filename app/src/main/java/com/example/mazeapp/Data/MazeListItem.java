package com.example.mazeapp.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

//import com.google.gson.annotations.SerializedName;


@Entity(tableName = "mazeTable")
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
        return "S: " + width + "x" + height + ", M: " + method + ", A: " + author;
    }
}