package com.example.mazeapp.MazeGenerator;

import com.example.mazeapp.Mediator;

import java.lang.ref.WeakReference;

public class MazeGeneratorPresenter implements MazeGeneratorContract.Presenter {
    private WeakReference<MazeGeneratorContract.View> view;
    private MazeGeneratorContract.Model model;
    private Mediator mediator;

    public MazeGeneratorPresenter(Mediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public void injectView(WeakReference<MazeGeneratorContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MazeGeneratorContract.Model model) {
        this.model = model;
    }
}
