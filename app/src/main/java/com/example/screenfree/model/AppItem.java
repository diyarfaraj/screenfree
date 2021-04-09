package com.example.screenfree.model;

import android.graphics.drawable.Drawable;

public class AppItem {
    private Drawable app_icon;
    private String app_name;
    private String packageName;

    public AppItem(Drawable app_icon, String app_name, String packageName) {
        this.app_icon = app_icon;
        this.app_name = app_name;
        this.packageName = packageName;
    }

    public Drawable getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(Drawable app_icon) {
        this.app_icon = app_icon;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
