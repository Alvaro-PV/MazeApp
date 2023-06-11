package com.example.mazeapp.MazeLogAndSignIn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazeapp.App.Mediator;
import com.example.mazeapp.Data.AppRepository;
import com.example.mazeapp.Data.RepositoryContract;
import com.example.mazeapp.Data.UserItem;
import com.example.mazeapp.MainMenu.MainMenuActivity;
import com.example.mazeapp.MazeGenerator.MazeGeneratorActivity;
import com.example.mazeapp.MazeSetup.MazeSetupActivity;
import com.example.mazeapp.R;

import java.util.ArrayList;
import java.util.List;

public class MazeLogAndSignInActivity extends AppCompatActivity {

    public static final String TAG = MazeLogAndSignInActivity.class.getSimpleName();
    Mediator mediator;
    RepositoryContract repository;
    Button logInButtonView, signInButtonView, continueAsGuestButtonView;
    EditText usernameTextView, passwordTextView;
    TextView credentialsErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        mediator = Mediator.getInstance();
        repository = AppRepository.getInstance(this);

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        } catch (Exception e) {}

        setContentView(R.layout.maze_log_and_sign_in_activity);

        logInButtonView = findViewById(R.id.logInButtonView);
        signInButtonView = findViewById(R.id.signInButtonView);
        continueAsGuestButtonView = findViewById(R.id.continueAsGuestButtonView);
        usernameTextView = findViewById(R.id.usernameTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        credentialsErrorTextView = findViewById(R.id.credentialsErrorTextView);

        logInButtonView.setOnClickListener(view -> {
            onLogInButtonViewClicked();
        });
        signInButtonView.setOnClickListener(view -> {
            onSignInButtonViewClicked();
        });
        continueAsGuestButtonView.setOnClickListener(view -> {
            mediator.setActiveUser(new UserItem(-1, "Guest", ""));
            navigateToMainMenuActivity();
        });
    }

    private void onLogInButtonViewClicked(){
        if(!credentialsValid()) return;

        repository.getUserList(userList -> {
            for(UserItem user : userList){
                if(credentialsMatch(user)) {
                    mediator.setActiveUser(user);
                    navigateToMainMenuActivity();
                    return;
                }
            }
        });
        runOnUiThread(() -> {
            credentialsErrorTextView.setText(R.string.credentialsErrorTextViewLogInError);
        });
    }

    private void onSignInButtonViewClicked(){
        if(!credentialsValid()) return;

        repository.getUserList(userList -> {
            for(UserItem user : userList) if(usernameTextView.getText().toString().equals(user.username)) {
                runOnUiThread(() -> {
                    credentialsErrorTextView.setText(R.string.credentialsErrorTextViewSignInError);
                });
                return;
            }
            repository.getUnusedUserId(id -> {
                UserItem newUser = new UserItem(id, usernameTextView.getText().toString(), passwordTextView.getText().toString());
                repository.addUserItem(newUser, () -> {
                    mediator.setActiveUser(newUser);
                    navigateToMainMenuActivity();
                });
            });
        });
    }

    private boolean credentialsMatch(UserItem user){
        return usernameTextView.getText().toString().equals(user.username) && passwordTextView.getText().toString().equals(user.password);
    }

    private boolean credentialsValid(){
        if(usernameTextView.getText().toString().length() < getResources().getInteger(R.integer.usernameMinChars) ||
                usernameTextView.getText().toString().length() > getResources().getInteger(R.integer.usernameMaxChars)) {
            credentialsErrorTextView.setText(getString(
                    R.string.credentialsErrorTextViewInvalidCredentials,
                    "Username",
                    getResources().getInteger(R.integer.usernameMinChars),
                    getResources().getInteger(R.integer.usernameMaxChars)));
            return false;
        }
        if(passwordTextView.getText().toString().length() < getResources().getInteger(R.integer.passwordMinChars) ||
                passwordTextView.getText().toString().length() > getResources().getInteger(R.integer.passwordMaxChars)){
            credentialsErrorTextView.setText(getString(
                    R.string.credentialsErrorTextViewInvalidCredentials,
                    "Password",
                    getResources().getInteger(R.integer.passwordMinChars),
                    getResources().getInteger(R.integer.passwordMaxChars)));
            return false;
        }
        return true;
    }

    private void navigateToMainMenuActivity(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
