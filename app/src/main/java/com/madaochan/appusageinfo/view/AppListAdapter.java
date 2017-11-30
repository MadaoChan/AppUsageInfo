package com.madaochan.appusageinfo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.madaochan.appusageinfo.R;
import com.madaochan.appusageinfo.model.AppUsageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30 030.
 */

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {

    private Context mContext;

    private List<AppUsageInfo> mList;

    public AppListAdapter(@NonNull Context context) {
        this.mContext = context;
    }

    public void setList(List<AppUsageInfo> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AppInfoItemView view = new AppInfoItemView(mContext);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mList != null) {
            holder.itemView.setValue(mList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public AppInfoItemView itemView;

        public ViewHolder(AppInfoItemView itemView) {
            super(itemView);
            this.itemView = itemView;
        }

    }
}
