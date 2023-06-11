package com.example.mazeapp.Data.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.Data.UserItem;
import com.example.mazeapp.Data.UserMazeItemRelation;

import java.util.List;
@Dao
public interface UserMazeItemRelationDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserMazeItemRelationDao(UserMazeItemRelation item);

    @Delete
    void deleteUserMazeItemRelationDao(UserMazeItemRelation item);

    @Query("SELECT * FROM user INNER JOIN user_maze_item_table ON user.id = user_maze_item_table.userId WHERE user_maze_item_table.mazeId=:mazeId")
            List<UserItem> getUserItemsForMazeItem(final int mazeId);

    @Query("SELECT * FROM maze INNER JOIN user_maze_item_table ON maze.id = user_maze_item_table.mazeId WHERE user_maze_item_table.userId=:userId")
            List<MazeListItem> getMazeItemsForUserItem(final int userId);
}