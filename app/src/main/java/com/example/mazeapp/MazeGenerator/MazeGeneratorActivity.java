package com.example.mazeapp.MazeGenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.mazeapp.R;

public class MazeGeneratorActivity extends AppCompatActivity implements MazeGeneratorContract.Activity{

    public static String TAG = MazeGeneratorActivity.class.getSimpleName();
    MazeGeneratorContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }catch(Exception e){}

        setContentView(R.layout.maze_generator_activity);
        MazeGeneratorScreen.configure(this);

        if(savedInstanceState == null) presenter.onStart();
        else presenter.onRestart();

    }

    @Override
    public void injectPresenter(MazeGeneratorContract.Presenter presenter){this.presenter = presenter;}

    @Override
    public void updateMazeView(int[][] cellMatrix, int cWidth, int cHeight){
        Log.e(TAG, "updateMazeView()");
        ((MazeGeneratorView) findViewById(R.id.mazeGeneratorView)).setCellMatrix(cellMatrix, cWidth, cHeight);
    }

    public void onNextFrameButtonClicked(View view) {
        presenter.onNextFrameButtonClicked();
    }
}