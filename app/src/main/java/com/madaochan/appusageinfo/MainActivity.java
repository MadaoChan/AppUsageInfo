package com.madaochan.appusageinfo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.madaochan.appusageinfo.manager.AppUsageTask;
import com.madaochan.appusageinfo.manager.GetAppInfoListener;
import com.madaochan.appusageinfo.model.AppUsageInfo;
import com.madaochan.appusageinfo.view.AppListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetAppInfoListener {

    private RecyclerView mContentList;
    private ProgressBar mLoadingCircle;
    private AppListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingCircle = (ProgressBar) findViewById(R.id.loading_circle);
        mContentList = (RecyclerView) findViewById(R.id.app_list);
        mContentList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mContentList.setVisibility(View.GONE);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getAppUsage();
    }

    private void getAppUsage() {
        AppUsageTask task = new AppUsageTask(getApplicationContext(), this);
        task.execute();
    }

    @Override
    public void onFinish(List<AppUsageInfo> list) {
        mLoadingCircle.setVisibility(View.GONE);
        mContentList.setVisibility(View.VISIBLE);
        mAdapter = new AppListAdapter(getApplicationContext());
        mAdapter.setList(list);
        mContentList.setHasFixedSize(true);
        mContentList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
