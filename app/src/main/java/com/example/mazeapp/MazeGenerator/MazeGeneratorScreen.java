package com.example.mazeapp.MazeGenerator;

import androidx.fragment.app.FragmentActivity;

import com.example.mazeapp.Mediator;

import java.lang.ref.WeakReference;

public class MazeGeneratorScreen {
    public static void configure(MazeGeneratorContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        Mediator mediator = Mediator.getInstance();

        MazeGeneratorContract.Presenter presenter = new MazeGeneratorPresenter(mediator);
        //CategoryListModel model = new CategoryListModel(repository);
        presenter.injectView(new WeakReference<>(view));
        //presenter.injectModel(model);
        view.injectPresenter(presenter);

    }
}
