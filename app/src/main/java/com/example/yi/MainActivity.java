package com.example.yi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.utils.QuartileYao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Button btnManuallyFill;
    private Button btnGenerate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_generateview);

        mContext=this;
        btnManuallyFill = findViewById(R.id.btn_manuallyfill);
        btnGenerate = findViewById(R.id.btn_generateview);
        btnManuallyFill.setOnClickListener(this);
        btnGenerate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn_generateview:
                intent.setClass(mContext,GenerateYaoActivity.class);
                startActivity(intent);
                Log.i("i","btn_generateview ============== ");
                break;
            case R.id.btn_manuallyfill:
                intent.setClass(mContext,ManuallyFillYaoActivity.class);
                startActivity(intent);
                break;
        }
    }
}