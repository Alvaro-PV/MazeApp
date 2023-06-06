package com.example.mazeapp.MazeGenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.mazeapp.R;

public class MazeGeneratorActivity extends AppCompatActivity implements MazeGeneratorContract.Activity{

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
    }

    //public void

    @Override
    public void injectPresenter(MazeGeneratorContract.Presenter presenter){this.presenter = presenter;}

    @Override
    public void updateMazeView(int[][] cellMatrix, int cWidth, int cHeight){
        ((MazeGeneratorView) findViewById(R.id.mazeGeneratorView)).setCellMatrix(cellMatrix, cWidth, cHeight);
    }
}