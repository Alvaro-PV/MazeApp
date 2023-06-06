package com.example.mazeapp.MazeGenerator;

import java.lang.ref.WeakReference;

public interface MazeGeneratorContract {
    interface View{
        void injectPresenter(Presenter presenter);
    }
    interface Presenter{
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
    }
    interface Model{

    }
}
