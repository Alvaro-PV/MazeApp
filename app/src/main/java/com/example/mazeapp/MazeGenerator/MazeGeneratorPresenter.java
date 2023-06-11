package com.example.mazeapp.MazeGenerator;

import android.util.Log;

import com.example.mazeapp.App.MazeSetupState;
import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.MazeLogAndSignIn.MazeLogAndSignInActivity;

import java.lang.ref.WeakReference;

public class MazeGeneratorPresenter implements MazeGeneratorContract.Presenter {

    public static final String TAG = MazeLogAndSignInActivity.class.getSimpleName();
    private WeakReference<MazeGeneratorContract.Activity> activity;
    private MazeGeneratorContract.Model model;
    private Mediator mediator;

    public MazeGeneratorPresenter(Mediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public void onStart(){
        Log.e(TAG, "onStart()");
        MazeSetupState mazeSetupState = mediator.getMazeSetupState();
        if(mazeSetupState == null) return;

        model.setupMaze(mazeSetupState);
        activity.get().updateMazeView(model.getNextFrame(), model.getWidth(), model.getHeight());
        if(mazeSetupState.loadingFromSave) activity.get().updateToSavedMazeButtonLayout(false);
    }

    @Override
    public void onRestart(){

    }
    @Override
    public void onNextFrameButtonClicked(){
        activity.get().updateMazeView(model.getNextFrame(), model.getWidth(), model.getHeight());
    }

    @Override
    public void onPreviousFrameButtonClicked(){
        activity.get().updateMazeView(model.getPreviousFrame(), model.getWidth(), model.getHeight());
    }

    @Override
    public void saveAndFavoriteButtonClicked(){
        model.saveCurrentMaze(() -> {
            activity.get().onCurrentMazeSaved();
        });
    }

    @Override
    public void injectActivity(WeakReference<MazeGeneratorContract.Activity> activity) { this.activity = activity;}

    @Override
    public void injectModel(MazeGeneratorContract.Model model) {
        this.model = model;
    }
}
