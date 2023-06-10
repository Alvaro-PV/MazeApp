package com.example.mazeapp.Data.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mazeapp.Data.MazeListItem;
//import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

@Database(entities = {MazeListItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MazeListItemDao mazeListItemDao();
}