package com.example.mazeapp.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserItem {
    @PrimaryKey
    final public int id;

    final public String username;
    final public String password;

    public UserItem(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }
}