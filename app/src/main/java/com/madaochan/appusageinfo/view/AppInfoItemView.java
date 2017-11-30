package com.madaochan.appusageinfo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.madaochan.appusageinfo.R;
import com.madaochan.appusageinfo.model.AppUsageInfo;

/**
 * Created by Administrator on 2017/11/30 030.
 */

public class AppInfoItemView extends ConstraintLayout {

    private int mPosition;

    private ImageView mIcon;
    private TextView mNameTextView;
    private TextView mPackageNameTextView;
    private TextView mUsageTextView;

    public AppInfoItemView(Context context) {
        super(context);
        init();
    }

    public AppInfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppInfoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.app_info_item_view, this, true);
        mIcon = (ImageView) view.findViewById(R.id.app_icon);
        mNameTextView = (TextView) view.findViewById(R.id.app_name);
        mPackageNameTextView = (TextView) view.findViewById(R.id.app_package_name);
        mUsageTextView = (TextView) view.findViewById(R.id.app_usage);
    }

    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }

    public void setAppName(CharSequence appName) {
        mNameTextView.setText(appName);
    }

    public void setPackageName(CharSequence packageName) {
        mPackageNameTextView.setText(packageName);
    }

    public void setUsage(CharSequence usage) {
        mUsageTextView.setText(usage);
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public void setValue(@NonNull AppUsageInfo info, int position) {
        setPosition(position);
        setIcon(info.getIcon());
        setAppName(position + ". " + info.getName() + " " + info.getVersion());
        setPackageName(info.getPackageName());
        setUsage(String.valueOf(info.getVersionCode()));
    }

}
