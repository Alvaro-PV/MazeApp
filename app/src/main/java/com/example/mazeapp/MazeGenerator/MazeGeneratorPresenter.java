package com.example.mazeapp.MazeGenerator;

import android.util.Log;

import com.example.mazeapp.App.MazeState;
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
        MazeState mazeState = mediator.getMazeSetupState();
        if(mazeState == null) return;

        model.setupMaze(mazeState);
        onNextFrameButtonClicked();
        if(mazeState.loadingFromSave) activity.get().updateToSavedMazeButtonLayout(mediator.getActiveUser().id != -1, false);
    }

    @Override
    public void onRestart(){

    }
    @Override
    public void onNextFrameButtonClicked(){
        activity.get().updateMazeView(model.getNextFrame(), model.getMazeState().width, model.getMazeState().height);
    }

    @Override
    public void onPreviousFrameButtonClicked(){
        activity.get().updateMazeView(model.getPreviousFrame(), model.getMazeState().width, model.getMazeState().height);
    }

    @Override
    public void saveAndFavoriteButtonClicked(){
        if(!model.getMazeState().loadingFromSave)
            model.saveCurrentMaze(() -> {
                activity.get().onCurrentMazeSaved();
                activity.get().updateToSavedMazeButtonLayout(mediator.getActiveUser().id != -1, false);
            });
        else {
            model.setFavoriteRelation(mediator.getActiveUser().id);
        }
    }

    @Override
    public void injectActivity(WeakReference<MazeGeneratorContract.Activity> activity) { this.activity = activity;}

    @Override
    public void injectModel(MazeGeneratorContract.Model model) {
        this.model = model;
    }
}
