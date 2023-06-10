package com.example.mazeapp.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.mazeapp.Data.Database.AppDatabase;
import com.example.mazeapp.Data.Database.MazeListItemDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

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
    public void loadMazeList(final boolean clearFirst, final FetchMazeListDataCallback callback) {
        AsyncTask.execute(() -> {
            if(clearFirst) database.clearAllTables();

            boolean error = false;
            if(getMazeItemListDao().loadMazeList().size() == 0 ) error = !loadMazeListFromJSON(loadJSONFromAsset());

            if(callback != null) callback.onAppDataFetched(error);
        });
    }

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


    private MazeListItemDao getMazeItemListDao() {
        return database.mazeListItemDao();
    }

    /*private ProductDao getProductDao() {
        return database.productDao();
    }*/


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
