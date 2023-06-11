package com.example.mazeapp.Data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_maze_item_table",
        primaryKeys = { "userId", "mazeId" },
        foreignKeys = {@ForeignKey(entity = UserItem.class,
                            parentColumns = "id",
                            childColumns = "userId"),
                        @ForeignKey(entity = MazeListItem.class,
                            parentColumns = "id",
                            childColumns = "mazeId")})
public class UserMazeItemRelation {
    public final int userId;
    public final int mazeId;

    public UserMazeItemRelation(int userId, int mazeId) {
        this.userId = userId;
        this.mazeId = mazeId;
    }
}