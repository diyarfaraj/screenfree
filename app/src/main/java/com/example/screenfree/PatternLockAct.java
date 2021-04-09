package com.example.screenfree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.screenfree.model.Password;
import com.shuhart.stepview.StepView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.regex.Pattern;

public class PatternLockAct extends AppCompatActivity {

    StepView stepView;
    LinearLayout normalLayout;
    TextView status_password;
    RelativeLayout relativeLayout;
    Password utilsPassword;
    String userPassword;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lock);
        initLayout();
        initPatternListener();
    }

    private void initPatternListener() {
        PatternLockView patternLockView = findViewById(R.id.pattern_view);
        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                String pwd = PatternLockUtils.patternToString(patternLockView, pattern);
                if(pwd.length() < 4){
                    status_password.setText(utilsPassword.SCHEMA_FAILED);
                    patternLockView.clearPattern();
                    return;
                }
                if(utilsPassword.getPassword()== null){
                    if(utilsPassword.isFirstStep()){
                        userPassword = pwd;
                        utilsPassword.setFirstStep(false);
                        status_password.setText(utilsPassword.STATUS_NEXT_STEP);
                        stepView.go(1, true);
                    } else {
                        if(userPassword.equals(pwd)){
                            utilsPassword.setPassword(userPassword);
                            status_password.setText(utilsPassword.STATUS_PASSWORD_CORRECT);
                            stepView.done(true);
                            startAct();
                        } else {
                            status_password.setText(utilsPassword.STATUS_PASSWORD_INCORRECT);
                        }

                    }
                }else {
                    if(utilsPassword.isCorrect(pwd)){
                        status_password.setText(utilsPassword.STATUS_PASSWORD_CORRECT);
                        startAct();
                    } else {
                        status_password.setText(utilsPassword.STATUS_PASSWORD_INCORRECT);
                    }
                }
                patternLockView.clearPattern();

            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void startAct() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void initLayout() {
        stepView = findViewById(R.id.step_view);
        normalLayout = findViewById(R.id.normal_layout);
        relativeLayout = findViewById(R.id.root);
        status_password = findViewById(R.id.status_password);
        utilsPassword = new Password(this);
        status_password.setText(utilsPassword.STATUS_FIRST_STEP);
        if(utilsPassword.getPassword() == null){
            normalLayout.setVisibility(View.GONE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0,true);
        } else {
            normalLayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.GONE);
            int backColor = ResourcesCompat.getColor(getResources(),R.color.teal_200, null);
            relativeLayout.setBackgroundColor(backColor);
            status_password.setTextColor(Color.WHITE);

        }
    }

    @Override
    public void onBackPressed() {
        if(utilsPassword.getPassword()==null && !utilsPassword.isFirstStep()){
            stepView.go(0,true);
            utilsPassword.setFirstStep(true);
            status_password.setText(utilsPassword.STATUS_FIRST_STEP);
        }else {
            finish();
            super.onBackPressed();
        }
    }
}