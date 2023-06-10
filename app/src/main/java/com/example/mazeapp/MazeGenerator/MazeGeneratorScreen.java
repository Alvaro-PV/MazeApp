package com.example.mazeapp.MazeGenerator;

import androidx.fragment.app.FragmentActivity;

import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.R;

import java.lang.ref.WeakReference;

public class MazeGeneratorScreen {
    public static void configure(MazeGeneratorContract.Activity activity) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) activity);

        Mediator mediator = Mediator.getInstance();

        int[] cellValues = context.get().getResources().getIntArray(R.array.cellValues);
        String[] methods = context.get().getResources().getStringArray(R.array.generationMethods);

        MazeGeneratorContract.Presenter presenter = new MazeGeneratorPresenter(mediator);
        MazeGeneratorContract.Model model = new MazeGeneratorModel(cellValues, methods);

        presenter.injectActivity(new WeakReference<>(activity));
        presenter.injectModel(model);
        activity.injectPresenter(presenter);
    }
}
