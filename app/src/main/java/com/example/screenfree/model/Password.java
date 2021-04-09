package com.example.screenfree.model;

import android.content.Context;

import io.paperdb.Paper;

public class Password {
    private  String PASSWORD_KEY = "PASSWORD KEY";
    public String STATUS_FIRST_STEP = "Draw an unlock pattern";
    public  String STATUS_NEXT_STEP = "Draw the pattern again to confirm";
    public  String STATUS_PASSWORD_CORRECT = "Pattern set";
    public  String STATUS_PASSWORD_INCORRECT = "Try again";
    public String SCHEMA_FAILED = "You must at least connect 4 dots";
    public boolean isFirstStep = true;

    public Password(Context ctx){
        Paper.init(ctx);
    }

    public void setPassword(String pwd){
        Paper.book().write(PASSWORD_KEY,pwd);
    }

    public String getPassword(){
        return Paper.book().read(PASSWORD_KEY);
    }

    public boolean isFirstStep(){
        return isFirstStep;
    }

    public void setFirstStep(boolean firstStep){
        isFirstStep = firstStep;
    }

    public boolean isCorrect(String pwd){
        return pwd.equals(getPassword());
    }
}
