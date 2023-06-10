package com.example.mazeapp.Data.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mazeapp.Data.MazeListItem;

import java.util.List;
@Dao
public interface MazeListItemDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMazeListItem(MazeListItem item);

    @Update
    void updateMazeListItem(MazeListItem item);

    @Delete
    void deleteMazeListItem(MazeListItem item);

    @Query("SELECT * FROM mazeTable")
    List<MazeListItem> loadMazeList();

    @Query("SELECT * FROM mazeTable WHERE id = :id LIMIT 1")
    MazeListItem loadMazeListItem(int id);
}
