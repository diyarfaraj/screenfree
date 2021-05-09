package com.example.screenfree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screenfree.Adapters.AppAdapter;
import com.example.screenfree.model.AppItem;
import com.example.screenfree.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout_permission;
    LinearLayout empty_list;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
    }

    private void initView() {
        //TODO: ADD EMPTY LIST PAGE IF THERE IS NO ADDED TO BOOK
        RecyclerView recyclerView = findViewById(R.id.recycler_view_app);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppAdapter appAdapters = new AppAdapter(this, getAllApps());
        recyclerView.setAdapter(appAdapters);
        empty_list = findViewById(R.id.empty_list_layout);
        layout_permission = findViewById(R.id.layout_permission);

        if(!isAccessGranted()){
            setPermission(view);
        }
    }

    private void initAllAppList(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_app);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppAdapter appAdapters = new AppAdapter(this, getAllApps());
        recyclerView.setAdapter(appAdapters);
    }

    private List<AppItem> getAllApps() {
        List<AppItem> results = new ArrayList<>();
        PackageManager pk = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = pk.queryIntentActivities(intent, 0);
        for(ResolveInfo resolveInfo: resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            results.add(new AppItem(activityInfo.loadIcon(pk), activityInfo.loadLabel(pk).toString(),activityInfo.packageName));
        }
       // List<ApplicationInfo> packageInfos = getPackageManager().getInstalledApplications(0);
       // for (int i = 0; i < packageInfos.size(); i++) {
       //     if(packageInfos.get(i).icon > 0) {
       //        String name = packageInfos.get(i).loadLabel(getPackageManager()).toString();
       //         Drawable icon = packageInfos.get(i).loadIcon(getPackageManager());
       //       String packageName = packageInfos.get(i).packageName;
       //         results.add(new AppItem(icon,name, packageName));
       //    }

       // }
        return results;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("App List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }

    public void setPermission(View view) {
        startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
    }

    @Override
    protected void onResume() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            if(Utils.checkPermission(this)){
                layout_permission.setVisibility((View.GONE));
            }else{
                layout_permission.setVisibility(View.VISIBLE);
            }
        }
        super.onResume();
    }

    private boolean isAccessGranted() {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            }
            int mode = 0;
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName);
            }
            return (mode == AppOpsManager.MODE_ALLOWED);

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}