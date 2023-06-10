package com.example.mazeapp.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.MazeGenerator.MazeGeneratorActivity;
import com.example.mazeapp.MazeList.MazeListActivity;
import com.example.mazeapp.MazeSetup.MazeSetupActivity;
import com.example.mazeapp.R;

public class MainMenuActivity extends AppCompatActivity {

    public static final String TAG = MazeGeneratorActivity.class.getSimpleName();

    Button createMazeButtonView, mazeListButtonView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        } catch (Exception e){}

        setContentView(R.layout.main_menu_activity);

        createMazeButtonView = findViewById(R.id.createMazeButtonView);
        createMazeButtonView.setOnClickListener(view -> {
            navigateToMazeSetupActivity();
        });

        mazeListButtonView = findViewById(R.id.mazeListButtonView);
        mazeListButtonView.setOnClickListener(view -> {
            navigateToMazeListActivity();
        });
    }

    private void navigateToMazeSetupActivity() {
        Intent intent = new Intent(this, MazeSetupActivity.class);
        startActivity(intent);
    }

    private void navigateToMazeListActivity() {
        Intent intent = new Intent(this, MazeListActivity.class);
        startActivity(intent);
    }
}
