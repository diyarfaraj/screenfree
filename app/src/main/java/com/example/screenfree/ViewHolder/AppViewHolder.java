package com.example.screenfree.ViewHolder;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screenfree.INterface.AppOnClickListener;
import com.example.screenfree.R;

import org.w3c.dom.Text;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon_app, lock_app;
    public TextView name_app;
    private AppOnClickListener listener;

    public void setListener(AppOnClickListener listener) {
        this.listener = listener;
    }

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);

        icon_app = itemView.findViewById(R.id.icon_app);
        lock_app = itemView.findViewById(R.id.lock_icon);
        name_app = itemView.findViewById(R.id.app_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectApp(getAdapterPosition());
            }
        });

    }
}
