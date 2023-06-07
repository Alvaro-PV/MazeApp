package com.example.mazeapp.MazeGenerator;

import android.util.Log;

import com.example.mazeapp.Mediator;

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
        model.setInitialParameters(10, 20, "");
        activity.get().updateMazeView(model.getNextFrame(), model.getcWidth(), model.getcHeight());
    }

    @Override
    public void onRestart(){

    }
    @Override
    public void onNextFrameButtonClicked(){
        activity.get().updateMazeView(model.getNextFrame(), model.getcWidth(), model.getcHeight());
    }

    @Override
    public void injectActivity(WeakReference<MazeGeneratorContract.Activity> activity) { this.activity = activity;}

    @Override
    public void injectModel(MazeGeneratorContract.Model model) {
        this.model = model;
    }
}
