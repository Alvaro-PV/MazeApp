package com.example.mazeapp.MazeList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mazeapp.Data.MazeListItem;
import com.example.mazeapp.MazeGenerator.MazeGeneratorActivity;
import com.example.mazeapp.MazeGenerator.MazeGeneratorContract;
import com.example.mazeapp.MazeSetup.MazeSetupActivity;
import com.example.mazeapp.R;

import java.util.List;

public class MazeListActivity extends AppCompatActivity implements MazeListContract.Activity {
    public static String TAG = MazeListActivity.class.getSimpleName();

    MazeListContract.Presenter presenter;

    private MazeListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maze_list_activity);

        listAdapter = new MazeListAdapter(view -> {
            MazeListItem item = (MazeListItem) view.getTag();
            presenter.selectMazeItem(item);
        });

        RecyclerView recyclerView = findViewById(R.id.maze_list);
        recyclerView.setAdapter(listAdapter);

        // do the setup
        MazeListScreen.configure(this);

        // do some work
        presenter.fetchMazeListData();
    }



    @Override
    public void displayMazeList(List<MazeListItem> mazeList) {
        Log.e(TAG, "displayMazeList()");
        runOnUiThread(() -> {listAdapter.setItems(mazeList);});
    }

    @Override
    public void navigateToMazeGeneratorActivity() {
        Intent intent = new Intent(this, MazeGeneratorActivity.class);
        startActivity(intent);
    }
    @Override
    public void injectPresenter(MazeListContract.Presenter presenter) {this.presenter = presenter;}
}
