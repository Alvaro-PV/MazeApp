package com.example.mazeapp.MazeList;

import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.Data.RepositoryContract;
import com.example.mazeapp.MazeGenerator.MazeGeneratorContract;

import java.lang.ref.WeakReference;
import java.util.List;

public interface MazeListContract {
    interface Activity {
        void injectPresenter(MazeListContract.Presenter presenter);

        void displayMazeList(List<MazeListItem> mazeList);
        void navigateToMazeGeneratorActivity();

    }
    interface Presenter {
        void injectActivity(WeakReference<MazeListContract.Activity> Activity);
        void injectModel(MazeListContract.Model model);
        void fetchMazeListData();
        void selectMazeItem(MazeListItem item);

        interface MazeListDataFetchedCallback{
            void onMazeListDataFetched();
        }
        void onStart();
        void onRestart();
    }

    interface Model {
        void fetchMazeListData(MazeListContract.Presenter.MazeListDataFetchedCallback callback);
        List<MazeListItem> getMazeList();
    }
}
