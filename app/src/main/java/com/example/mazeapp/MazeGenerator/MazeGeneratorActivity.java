package com.example.mazeapp.MazeGenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.mazeapp.R;

public class MazeGeneratorActivity extends AppCompatActivity implements MazeGeneratorContract.Activity{

    public static final String TAG = MazeGeneratorActivity.class.getSimpleName();
    MazeGeneratorContract.Presenter presenter;

    Button prevStepAndSaveButton;
    Button AutoStepAndSaveExitButton;
    Button nextStepAndExitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }catch(Exception e){}

        setContentView(R.layout.maze_generator_activity);
        MazeGeneratorScreen.configure(this);

        prevStepAndSaveButton = findViewById(R.id.prevStepAndSaveButton);
        AutoStepAndSaveExitButton = findViewById(R.id.AutoStepAndSaveExitButton);
        nextStepAndExitButton = findViewById(R.id.nextStepAndExitButton);

        prevStepAndSaveButton.setOnClickListener(view -> {

        });

        AutoStepAndSaveExitButton.setOnClickListener(view -> {
            presenter.saveCurrentMazeButtonClicked();
        });

        nextStepAndExitButton.setOnClickListener(view -> {
            presenter.onNextFrameButtonClicked();
        });

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

    @Override
    public  void onCurrentMazeSaved(){
        runOnUiThread(() -> {
            CharSequence text = "Maze Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        });
    }
}