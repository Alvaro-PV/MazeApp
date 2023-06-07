package com.example.mazeapp.MazeGenerator;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

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
        void setInitialParameters(int width, int height, String method);
        int getcWidth();
        int getcHeight();
        int[][] getCellMatrix();
        int[][] getNextFrame();

    }
}
