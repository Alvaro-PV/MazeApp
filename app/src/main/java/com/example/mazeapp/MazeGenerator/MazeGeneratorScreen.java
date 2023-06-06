package com.example.mazeapp.MazeGenerator;

import androidx.fragment.app.FragmentActivity;

import com.example.mazeapp.Mediator;

import java.lang.ref.WeakReference;

public class MazeGeneratorScreen {
    public static void configure(MazeGeneratorContract.Activity activity) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) activity);

        Mediator mediator = Mediator.getInstance();

        MazeGeneratorContract.Presenter presenter = new MazeGeneratorPresenter(mediator);
        //CategoryListModel model = new CategoryListModel(repository);
        MazeGeneratorView mazeGeneratorView = new MazeGeneratorView(context.get());

        presenter.injectActivity(new WeakReference<>(activity));
        //presenter.injectModel(model);
        activity.injectPresenter(presenter);
    }
}
