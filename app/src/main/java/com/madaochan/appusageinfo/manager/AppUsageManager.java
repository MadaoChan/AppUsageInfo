package com.madaochan.appusageinfo.manager;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;

import com.madaochan.appusageinfo.model.AppUsageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Get app list from system
 * @author MadaoChan
 * @since 2017/11/30
 */

public class AppUsageManager {

    private final String TAG = "AppUsageManager";

    List<AppUsageInfo> mSystemAppList;
    List<AppUsageInfo> mUserAppList;

    public AppUsageManager() {
        mSystemAppList = new ArrayList<>();
        mUserAppList = new ArrayList<>();
    }

    /**
     * Get installed app's info
     * (This method takes a long time to run if device has a lot of apps,
     *  should put it into a worker thread)
     * @param context context
     */
    @WorkerThread
    public void scanApps(@NonNull Context context) {
        PackageManager pm = context.getApplicationContext().getPackageManager();

        // 0 means @PackageInfoFlags.GET_META_DATA
        List<PackageInfo> packageInfoList = pm.getInstalledPackages(0);

        if (packageInfoList == null) {
            return;
        }

        for (PackageInfo packageInfo : packageInfoList) {
            String appName = packageInfo.applicationInfo.loadLabel(pm).toString();

            boolean isSystemApp = isSystemApp(packageInfo);
            AppUsageInfo appUsageInfo = new AppUsageInfo(appName, packageInfo, pm, isSystemApp);

            if (isSystemApp) {
                mSystemAppList.add(appUsageInfo);
            } else {
                mUserAppList.add(appUsageInfo);
            }
        }
    }

    private void getAppUsage(@NonNull Context context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            return;
        }

        UsageStatsManager statsManager =
                (UsageStatsManager)context.getSystemService(Context.USAGE_STATS_SERVICE);

        if (statsManager == null) {
            return;
        }

        List<UsageStats> usageList =
                statsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, 0,
                        System.currentTimeMillis());

        for (UsageStats stats : usageList) {
            Log.e("Usage",
                    stats.getPackageName() + " " + stats.getTotalTimeInForeground());
        }
    }

    private void getAppUsage(@NonNull AppUsageInfo appUsageInfo, @NonNull Context context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1
                || TextUtils.isEmpty(appUsageInfo.getPackageName())) {
            return;
        }

        UsageStatsManager statsManager =
                (UsageStatsManager)context.getSystemService(Context.USAGE_STATS_SERVICE);

        if (statsManager == null) {
            return;
        }

        List<UsageStats> usageList =
                statsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, 0,
                        System.currentTimeMillis());

        for (UsageStats stats : usageList) {
            if (appUsageInfo.getPackageName().equals(stats.getPackageName())) {

                Log.e("Usage",
                        stats.getPackageName() + " " + stats.getTotalTimeInForeground() + " ");
                break;
            }
        }
    }

    private boolean isSystemApp(@NonNull PackageInfo packageInfo) {
        ApplicationInfo appInfo = packageInfo.applicationInfo;
        return (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0;
    }

    public List<AppUsageInfo> getSysteAppList() {
        return mSystemAppList;
    }

    public List<AppUsageInfo> getUserAppList() {
        return mUserAppList;
    }

    public List<AppUsageInfo> getAppList() {
        List<AppUsageInfo> list = new ArrayList<>();
        list.addAll(mUserAppList);
        list.addAll(mSystemAppList);
        return list;
    }
}
