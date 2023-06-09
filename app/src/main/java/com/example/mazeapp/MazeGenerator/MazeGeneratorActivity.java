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

    Button prevStepButtonView, nextStepButtonView, saveAndFavoriteButtonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }catch(Exception e){}

        setContentView(R.layout.maze_generator_activity);
        MazeGeneratorScreen.configure(this);

        prevStepButtonView = findViewById(R.id.prevStepButtonView);
        nextStepButtonView = findViewById(R.id.nextStepButtonView);
        saveAndFavoriteButtonView = findViewById(R.id.saveAndFavoriteButtonView);

        prevStepButtonView.setOnClickListener(view -> {
            presenter.onPreviousFrameButtonClicked();
        });

        nextStepButtonView.setOnClickListener(view -> {

            presenter.onNextFrameButtonClicked();
        });

        saveAndFavoriteButtonView.setOnClickListener(view -> {
            presenter.saveAndFavoriteButtonClicked();
        });

        presenter.onStart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void injectPresenter(MazeGeneratorContract.Presenter presenter){this.presenter = presenter;}

    @Override
    public void updateMazeView(int[][] cellMatrix, int cWidth, int cHeight){
        Log.e(TAG, "updateMazeView()");
        ((MazeGeneratorView) findViewById(R.id.mazeGeneratorView)).setCellMatrix(cellMatrix, cWidth, cHeight);
    }

    @Override
    public void updateToSavedMazeButtonLayout(boolean isLoggedIn, boolean isFavorite){
        runOnUiThread(() -> {
            Log.e(TAG, "updateButtonLayout()");
            prevStepButtonView.setVisibility(View.GONE);
            nextStepButtonView.setVisibility(View.GONE);

            saveAndFavoriteButtonView.setEnabled(isLoggedIn);

            if(isFavorite) saveAndFavoriteButtonView.setText(R.string.saveAndFavoriteButtonViewUnfavoriteState);
            else saveAndFavoriteButtonView.setText(R.string.saveAndFavoriteButtonViewFavoriteState);
        });
    }

    @Override
    public  void onCurrentMazeSaved(){
        runOnUiThread(() -> {
            CharSequence text = getString(R.string.savedMaze);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        });
    }

    @Override
    public void onCurrentMazeSetFavorite(){
        runOnUiThread(() -> {
            CharSequence text = getString(R.string.addedMazeToFavorites);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        });
    }
}