package com.madaochan.appusageinfo.manager;

import com.madaochan.appusageinfo.model.AppUsageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30 030.
 */

public interface GetAppInfoListener {
    void onFinish(List<AppUsageInfo> list);
}
