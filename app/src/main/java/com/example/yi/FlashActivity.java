package com.example.yi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import object.BaGuaInit;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        BaGuaInit.getInstance().initHexagram(); //初始化八卦基础

        Intent intent=new Intent();
        intent.setClass(this,ManuallyFillYaoActivity.class);
        startActivity(intent);




    }
}