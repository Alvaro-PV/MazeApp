package com.example.mazeapp.MazeList;

import androidx.fragment.app.FragmentActivity;

import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.Data.AppRepository;
import com.example.mazeapp.Data.RepositoryContract;

import java.lang.ref.WeakReference;

public class MazeListScreen {
    public static void configure(MazeListContract.Activity activity) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) activity);

        Mediator mediator = Mediator.getInstance();
        RepositoryContract repository = AppRepository.getInstance(context.get());

        MazeListContract.Presenter presenter = new MazeListPresenter(mediator);
        MazeListModel model = new MazeListModel(repository);
        presenter.injectActivity(new WeakReference<>(activity));
        presenter.injectModel(model);

        activity.injectPresenter(presenter);

    }
}
