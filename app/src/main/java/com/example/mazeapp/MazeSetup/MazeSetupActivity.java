package com.example.mazeapp.MazeSetup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazeapp.App.MazeSetupState;
import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.MazeGenerator.MazeGeneratorActivity;
import com.example.mazeapp.R;

public class MazeSetupActivity extends AppCompatActivity {

    public static final String TAG = MazeGeneratorActivity.class.getSimpleName();
    Mediator mediator;

    EditText widthInputView, heightInputView;
    Button genMazeButtonView;
    TextView errorTextView;
    Spinner methodSpinnerView;
    ArrayAdapter<CharSequence> methodSpinnerViewAdapter;
    String currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        mediator = Mediator.getInstance();

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }catch(Exception e){}

        setContentView(R.layout.maze_setup_activity);

        widthInputView = findViewById(R.id.inputWidthView);
        heightInputView = findViewById(R.id.inputHeightView);
        genMazeButtonView = findViewById(R.id.generateMazeButtonView);
        errorTextView = findViewById(R.id.errorTextView);
        methodSpinnerView = findViewById(R.id.methodSpinnerView);
        methodSpinnerViewAdapter = ArrayAdapter.createFromResource(this, R.array.generationMethodsDisplayNames, android.R.layout.simple_spinner_item);

        methodSpinnerViewAdapter.setDropDownViewResource(R.layout.method_list_item);
        methodSpinnerView.setAdapter(methodSpinnerViewAdapter);
        methodSpinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentItem = getResources().getStringArray(R.array.generationMethods)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        genMazeButtonView.setOnClickListener(view -> {
            if(passStateToMazeGeneratorActivity()) navigateToMazeGeneratorActivity();
            else errorTextView.setText(R.string.mazeGenerationError);
        });
    }
    private boolean passStateToMazeGeneratorActivity() {
        int width, height;
        try{
            width = Integer.parseInt(widthInputView.getText().toString());
            height = Integer.parseInt(heightInputView.getText().toString());
        }catch (Exception e){return false;}
        if(currentItem == null || width <= 0 || height <= 0) return false;

        mediator.setMazeSetupState(new MazeSetupState(width, height, currentItem, true));
        return true;
    }

    private void navigateToMazeGeneratorActivity() {
        Intent intent = new Intent(this, MazeGeneratorActivity.class);
        startActivity(intent);
        finish();
    }
}
