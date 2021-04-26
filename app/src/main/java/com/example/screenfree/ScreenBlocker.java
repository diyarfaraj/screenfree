package com.example.screenfree;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScreenBlocker extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.empty_list_page);
        setPassword();
    }


    private void setPassword(){
        Dialog mDialog;
        mDialog=new Dialog(ScreenBlocker.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.empty_list_page);
        TextView ok,cancel;
        /*ok=(TextView) mDialog.findViewById(R.id.);
        cancel=(TextView) mDialog.findViewById(R.id.dialogno);*/
       /* ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                mDialog.cancel();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                mDialog.cancel();
            }
        });*/
        mDialog.show();
    }


}
