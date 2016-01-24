package ru.om.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.om.R;
import ru.om.controller.Controller;
import ru.om.model.adapter.TrainingAdapter;
import ru.om.model.pojo.Training;

public class MainActivity extends AppCompatActivity implements Controller.TrainingCallbackListener {

    private Toolbar mToolbar;
    private RecyclerView mRV;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Training> mTrainingList = new ArrayList<>();
    private TrainingAdapter mTrainingAdapter;
    private Controller mController;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolbar();
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();

    }

    private void configToolbar() {
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void configViews() {
        mRV = (RecyclerView) this.findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe);

        mRV.setHasFixedSize(true);
        mRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRV.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        mTrainingAdapter = new TrainingAdapter(mTrainingList);
        mRV.setAdapter(mTrainingAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, 10000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Training training) {
        mTrainingAdapter.addTraining(training);
    }

    @Override
    public void onFetchProgress(List<Training> trainingList) {

    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }
}


