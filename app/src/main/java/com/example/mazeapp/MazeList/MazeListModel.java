package com.example.mazeapp.MazeList;

import android.util.Log;

import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.Data.RepositoryContract;

import java.util.ArrayList;
import java.util.List;

public class MazeListModel implements MazeListContract.Model {
    public static String TAG = MazeListModel.class.getSimpleName();

    private RepositoryContract repository;

    private List<MazeListItem> mazeList;

    public MazeListModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchMazeListData(MazeListContract.Presenter.MazeListDataFetchedCallback callback) {
        Log.e(TAG, "fetchMazeListData()");
        repository.loadMazeList(false, error -> {
            if(error) return;
            repository.getMazeList(m -> {
                mazeList = m;
                callback.onMazeListDataFetched();
            });
        });
    }

    public List<MazeListItem> getMazeList() {
        return mazeList;
    }
}