package com.example.mazeapp.MazeGenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MazeGeneratorActivity extends AppCompatActivity implements MazeGeneratorContract.View{

    MazeGeneratorContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }catch(Exception e){}

        MazeGeneratorView mazeGeneratorView = new MazeGeneratorView(this);
        setContentView(mazeGeneratorView);

    }

    @Override
    public void injectPresenter(MazeGeneratorContract.Presenter presenter){this.presenter = presenter;}
}