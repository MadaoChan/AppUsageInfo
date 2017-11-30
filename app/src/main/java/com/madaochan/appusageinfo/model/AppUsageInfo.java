package com.madaochan.appusageinfo.model;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/11/30 030.
 */

public class AppUsageInfo {

    private String mName;
    private String mPackageName;
    private String mVersion;
    private int mVersionCode;
    private Drawable mIcon;
    private boolean isUserApp;

    public AppUsageInfo() {
    }

    public AppUsageInfo(String appName, @NonNull PackageInfo packageInfo,
                        @NonNull PackageManager pm, boolean isUserApp) {
        this.mName = appName;
        this.mVersion = packageInfo.versionName;
        this.mPackageName = packageInfo.packageName;
        this.mVersionCode = packageInfo.versionCode;
        this.mIcon = packageInfo.applicationInfo.loadIcon(pm);
        this.isUserApp = isUserApp;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public int getVersionCode() {
        return mVersionCode;
    }

    public void setVersionCode(int mVersionCode) {
        this.mVersionCode = mVersionCode;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }
}
