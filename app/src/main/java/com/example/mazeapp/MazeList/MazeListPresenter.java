package com.example.mazeapp.MazeList;

import android.util.Log;

import com.example.mazeapp.App.MazeSetupState;
import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.Data.RepositoryContract;
import com.example.mazeapp.MazeGenerator.MazeGeneratorContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class MazeListPresenter implements MazeListContract.Presenter {

    public static String TAG = MazeListActivity.class.getSimpleName();
    private Mediator mediator;
    private WeakReference<MazeListContract.Activity> activity;
    private MazeListContract.Model model;
    public MazeListPresenter(Mediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public void fetchMazeListData() {
        Log.e(TAG, "fetchCategoryListData()");

        // call the model
        model.fetchMazeListData(() -> {
            activity.get().displayMazeList(model.getMazeList());
        });
    }

    @Override
    public void selectMazeItem(MazeListItem item) {
        Log.e(TAG, "selectMazeItem()");
        int[][] cellMatrix = new int[item.height][item.width];
        char[] rawMatrix = item.cellGrid.toCharArray();

        if(item.width * item.height != rawMatrix.length) {
            Log.e(TAG, "selectMazeItem(): tried to load a maze with incoherent data");
            return;
        }
        for(int x = 0; x < item.width; x++)
            for(int y = 0; y < item.height; y++)
                cellMatrix[x][y] = Character.getNumericValue(rawMatrix[y * item.width + x]);

        MazeSetupState mazeSetupState = new MazeSetupState(item.width, item.height, cellMatrix, item.method);
        mediator.setMazeSetupState(mazeSetupState);
        activity.get().navigateToMazeGeneratorActivity();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void injectActivity(WeakReference<MazeListContract.Activity> activity) {this.activity = activity;}

    @Override
    public void injectModel(MazeListContract.Model model) {this.model = model;}
}
