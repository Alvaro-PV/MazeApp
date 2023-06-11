package com.example.mazeapp.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.mazeapp.Data.Database.UserItemDao;
import com.example.mazeapp.Data.Database.UserMazeItemRelationDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.mazeapp.Data.Database.AppDatabase;
import com.example.mazeapp.Data.Database.MazeListItemDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AppRepository implements RepositoryContract{

    public static String TAG = AppRepository.class.getSimpleName();


    public static final String DB_FILE = "mazeApp.db";
    public static final String JSON_FILE = "mazeApp.json";
    public static final String JSON_ROOT = "mazeList";

    private static AppRepository INSTANCE;

    private AppDatabase database;
    private Context context;


    public static RepositoryContract getInstance(Context context) {
        if(INSTANCE == null) INSTANCE = new AppRepository(context);
        return INSTANCE;
    }

    private AppRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, DB_FILE).build();
    }

    @Override
    public void loadAppData(final boolean clearFirst, final FetchAppDataCallback callback) {
        AsyncTask.execute(() -> {
            if(clearFirst) database.clearAllTables();

            boolean error = false;
            if(getMazeItemListDao().loadMazeList().size() == 0 ) error = !loadMazeListFromJSON(loadJSONFromAsset());

            if(callback != null) callback.onAppDataFetched(error);
        });
    }

    //--------------------------- MAZE LIST ---------------------------

    @Override
    public void getMazeListItem(final int id, final GetMazeListItemCallback callback) {
        AsyncTask.execute(() -> {
            if(callback != null) callback.setMazeListItem(getMazeItemListDao().loadMazeListItem(id));
        });
    }

    @Override
    public void getMazeList(final GetMazeListCallback callback) {
        AsyncTask.execute(() -> {
            if(callback != null) callback.setMazeList(getMazeItemListDao().loadMazeList());
        });
    }

    @Override
    public void getUnusedMazeListItemId(final GetUnusedMazeListItemIdCallback callback) {
        AsyncTask.execute(() -> {
            List<MazeListItem> mazeList = getMazeItemListDao().loadMazeList();
            boolean foundUnusedId = false;
            int id = 0;
            while (!foundUnusedId){
                foundUnusedId = true;
                for(MazeListItem mazeListItem : mazeList) if (id == mazeListItem.id){
                    foundUnusedId = false;
                    id++;
                }
            }
            if(callback != null) callback.onGetUnusedMazeListItemIdCallback(id);
        });
    }

    @Override
    public void deleteMazeListItem(final MazeListItem item, final DeleteMazeListItemCallback callback) {
        AsyncTask.execute(() -> {
            if(callback != null) {
                getMazeItemListDao().deleteMazeListItem(item);
                callback.onMazeListItemDeleted();
            }
        });
    }

    @Override
    public void updateMazeListItem(final MazeListItem item, final UpdateMazeListItemCallback callback) {
        AsyncTask.execute(() -> {
            if(callback != null) {
                getMazeItemListDao().updateMazeListItem(item);
                callback.onMazeListItemUpdated();
            }
        });
    }

    @Override
    public void addMazeListItem(final MazeListItem item, final AddMazeListItemCallback callback) {
        AsyncTask.execute(() -> {
            getMazeItemListDao().insertMazeListItem(item);
            if(callback != null) callback.onMazeListItemAdded();
        });
    }

    //--------------------------- USER LIST ---------------------------

    @Override
    public void getUnusedUserId(final GetUnusedUserIdCallback callback) {
        AsyncTask.execute(() -> {
            List<UserItem> userList = getUserItemDao().loadUserList();
            boolean foundUnusedId = false;
            int id = 0;
            while (!foundUnusedId){
                foundUnusedId = true;
                for(UserItem user : userList) if (id == user.id){
                    foundUnusedId = false;
                    id++;
                }
            }
            if(callback != null) callback.onGetUnusedUserIdCallback(id);
        });
    }

    @Override
    public void getUserList(final GetUserListCallback callback) {
        AsyncTask.execute(() -> {
            if(callback != null) callback.onGetUserList(getUserItemDao().loadUserList());
        });
    }

    @Override
    public void addUserItem(final UserItem item, final AddUserItemCallback callback) {
        AsyncTask.execute(() -> {
            Log.w(TAG, "Added user with credentials: " + item.id + ", " + item.username + ", " + item.password);
            getUserItemDao().insertUserItem(item);
            if(callback != null) callback.onMazeListItemAdded();
        });
    }

    private MazeListItemDao getMazeItemListDao() {
        return database.mazeListItemDao();
    }
    private UserItemDao getUserItemDao() {
        return database.userItemDao();
    }
    private UserMazeItemRelationDao getUserMazeItemRelationDao() {return database.userMazeItemRelationDao();}

    private boolean loadMazeListFromJSON(String json) {
        Log.e(TAG, "loadMazeListFromJSON()");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

            if (jsonArray.length() > 0) {
                final MazeListItem[] mazeList = gson.fromJson(jsonArray.toString(), MazeListItem[].class);
                for (MazeListItem mazeListItem: mazeList) getMazeItemListDao().insertMazeListItem(mazeListItem);
                Log.e(TAG, "loadMazeListFromJSON(): list loaded");
                return true;
            }
            Log.e(TAG, "loadMazeListFromJSON(): list failed to load");

        } catch (JSONException error) {
            Log.e(TAG, "error: " + error);
        }
        return false;
    }

    private String loadJSONFromAsset() {
        Log.e(TAG, "loadJSONFromAsset()");

        String json = null;
        try {
            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException error) {
            Log.e(TAG, "error: " + error);
        }

        return json;
    }

}
