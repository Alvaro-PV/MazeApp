package com.example.mazeapp.MazeGenerator;

import com.example.mazeapp.App.MazeSetupState;
import com.example.mazeapp.App.Mediator;

import java.lang.ref.WeakReference;

public class MazeGeneratorPresenter implements MazeGeneratorContract.Presenter {
    private WeakReference<MazeGeneratorContract.Activity> activity;
    private MazeGeneratorContract.Model model;
    private Mediator mediator;

    public MazeGeneratorPresenter(Mediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public void onStart(){
        MazeSetupState mazeSetupState = mediator.getMazeSetupState();
        if(mazeSetupState == null) return;

        if (mazeSetupState.cellMatrix == null) model.setGenerationParameters(mazeSetupState.width, mazeSetupState.height, mazeSetupState.method, mazeSetupState.showSteps);
        else model.setCompletedMatrix(mazeSetupState.width, mazeSetupState.height, mazeSetupState.cellMatrix, mazeSetupState.method);

        if(model.canShowSteps()) activity.get().updateMazeView(model.getNextFrame(), model.getcWidth(), model.getcHeight());
        else activity.get().updateMazeView(model.getCellMatrix(), model.getcWidth(), model.getcHeight());
    }

    @Override
    public void onRestart(){

    }
    @Override
    public void onNextFrameButtonClicked(){
        activity.get().updateMazeView(model.getNextFrame(), model.getcWidth(), model.getcHeight());
    }

    @Override
    public void saveCurrentMazeButtonClicked(){
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
