package com.example.screenfree.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screenfree.R;
import com.example.screenfree.ViewHolder.AppViewHolder;
import com.example.screenfree.model.AppItem;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {
    private Context mContext;
    private List<AppItem> appItemList;

    public AppAdapter(Context mContext, List<AppItem> appItemList) {
        this.mContext = mContext;
        this.appItemList = appItemList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_apps, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.name_app.setText(appItemList.get(position).getApp_name());
        holder.icon_app.setImageDrawable(appItemList.get(position).getApp_icon());
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
