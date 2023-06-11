package com.example.mazeapp.MazeList;

import android.util.Log;

import com.example.mazeapp.App.MazeSetupState;
import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.Data.MazeListItem;

import java.lang.ref.WeakReference;

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

        MazeSetupState mazeSetupState = new MazeSetupState(item);
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
