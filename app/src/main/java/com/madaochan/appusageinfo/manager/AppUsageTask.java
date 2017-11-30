package com.madaochan.appusageinfo.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.madaochan.appusageinfo.model.AppUsageInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30 030.
 */

public class AppUsageTask extends AsyncTask<Void, Void, List<AppUsageInfo>> {

    private WeakReference<Context> mContextRef;
    private WeakReference<GetAppInfoListener> mListenerRef;

    public AppUsageTask(@NonNull Context context, @NonNull GetAppInfoListener listener) {
        this.mContextRef = new WeakReference<>(context);
        this.mListenerRef = new WeakReference<>(listener);
    }

    @Override
    protected List<AppUsageInfo> doInBackground(Void... voids) {
        if (getContext() == null) {
            return new ArrayList<>();
        }

        AppUsageManager manager = new AppUsageManager();
        manager.scanApps(getContext());
        return manager.getUserAppList();
    }

    @Override
    protected void onPostExecute(List<AppUsageInfo> appUsageInfos) {
        super.onPostExecute(appUsageInfos);
        if (getListener() != null) {
            getListener().onFinish(appUsageInfos);
        }
    }

    private Context getContext() {
        return mContextRef != null ? mContextRef.get() : null;
    }

    private GetAppInfoListener getListener() {
        return mListenerRef != null ? mListenerRef.get() : null;
    }
}
