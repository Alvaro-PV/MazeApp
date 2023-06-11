package com.example.mazeapp.MazeGenerator;

import com.example.mazeapp.App.MazeState;

import java.lang.ref.WeakReference;

public interface MazeGeneratorContract {
    interface Activity {
        void injectPresenter(Presenter presenter);
        void updateMazeView(int[][] cellMatrix, int cWidth, int cHeight);
        void updateToSavedMazeButtonLayout(boolean isLoggedIn, boolean isFavorite);
        void onCurrentMazeSaved();
    }
    interface Presenter {
        void injectActivity(WeakReference<Activity> Activity);
        void injectModel(Model model);
        void onStart();
        void onRestart();
        void onNextFrameButtonClicked();
        void onPreviousFrameButtonClicked();
        void saveAndFavoriteButtonClicked();
        interface SaveCurrentMazeCallback{
            void onSavedCurrentMaze();
        }
    }
    interface Model {
        void setupMaze(MazeState mazeState);
        MazeState getMazeState();
        int[][] getNextFrame();
        int[][] getPreviousFrame();
        void saveCurrentMaze(MazeGeneratorContract.Presenter.SaveCurrentMazeCallback callback);
        void setFavoriteRelation(int userId);
    }
}
