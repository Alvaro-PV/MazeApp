package com.example.mazeapp.Data.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mazeapp.Data.UserItem;

import java.util.List;
@Dao
public interface UserItemDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserItem(UserItem item);

    @Query("SELECT * FROM user")
    List<UserItem> loadUserList();

    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    UserItem loadUserListItem(int id);
}