package com.example.mazeapp.MazeGenerator;

import java.lang.ref.WeakReference;

public interface MazeGeneratorContract {
    interface Activity {
        void injectPresenter(Presenter presenter);
        void updateMazeView(int[][] cellMatrix, int cWidth, int cHeight);
    }
    interface Presenter {
        void injectActivity(WeakReference<Activity> Activity);
        void injectModel(Model model);
        void onStart();
        void onRestart();
        void onNextFrameButtonClicked();
    }
    interface Model {
        void setGenerationParameters(int width, int height, String method, boolean showSteps);
        void setCompletedMatrix(int width, int height, int[][] cellMatrix, String method);
        int getcWidth();
        int getcHeight();
        int[][] getCellMatrix();
        int[][] getNextFrame();

        boolean canShowSteps();

    }
}
