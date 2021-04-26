
package com.example.screenfree.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.screenfree.PatternLockAct;
import com.example.screenfree.ScreenBlocker;
import com.example.screenfree.utils.Utils;

public class ReceiverApplock extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Utils utils = new Utils(context);
        String appRunning = utils.getLauncherTopApp();
        String lastApp = utils.getLastApp();
        if (appRunning != null) {
            Log.d("APP RUNNINGG: ", appRunning);
        }

        if(lastApp != null){
            Log.d("LAST APP: ", lastApp);
        }

        if(utils.isLock(appRunning)){
            if(!appRunning.equals(utils.getLastApp())){
                utils.clearLastApp();
                utils.setLastApp(appRunning);
                Intent i = new Intent(context, PatternLockAct.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("broadcast_receiver", "broadcast_receiver");
                context.startActivity(i);
            }
        }
    }
}