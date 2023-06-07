package com.example.mazeapp.MazeGenerator;

import androidx.fragment.app.FragmentActivity;

import com.example.mazeapp.Mediator;
import com.example.mazeapp.R;

import java.lang.ref.WeakReference;

public class MazeGeneratorScreen {
    public static void configure(MazeGeneratorContract.Activity activity) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) activity);

        Mediator mediator = Mediator.getInstance();

        int[] cellValues = context.get().getResources().getIntArray(R.array.cellValues);

        MazeGeneratorContract.Presenter presenter = new MazeGeneratorPresenter(mediator);
        MazeGeneratorContract.Model model = new MazeGeneratorModel(cellValues);
        MazeGeneratorView mazeGeneratorView = new MazeGeneratorView(context.get());

        presenter.injectActivity(new WeakReference<>(activity));
        presenter.injectModel(model);
        activity.injectPresenter(presenter);
    }
}
