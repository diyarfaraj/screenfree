package com.example.screenfree.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screenfree.INterface.AppOnClickListener;
import com.example.screenfree.R;
import com.example.screenfree.ViewHolder.AppViewHolder;
import com.example.screenfree.model.AppItem;
import com.example.screenfree.utils.Utils;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {
    private Context mContext;
    private List<AppItem> appItemList;
    private Utils utils;

    public AppAdapter(Context mContext, List<AppItem> appItemList) {
        this.mContext = mContext;
        this.appItemList = appItemList;
        this.utils = new Utils(mContext);
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

        String pk = appItemList.get(position).getPackageName();
        if(utils.isLock(pk)){
            holder.lock_app.setImageResource(R.drawable.ic_outline_lock_24);
        }else {
            holder.lock_app.setImageResource(R.drawable.ic_baseline_lock_open_24);
        }

        holder.setListener(new AppOnClickListener() {
            @Override
            public void selectApp(int pos) {
                if(utils.isLock(pk)){
                    holder.lock_app.setImageResource(R.drawable.ic_baseline_lock_open_24);
                    utils.unLock(pk);
                }else {
                    holder.lock_app.setImageResource(R.drawable.ic_outline_lock_24);
                    utils.lock(pk);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
