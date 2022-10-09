package com.example.yi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.QuartileYao;

public class GenerateYaoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGenerate;
    private Button btnNext;
    private TextView tvYao6;
    private TextView tvYao5;
    private TextView tvYao4;
    private TextView tvYao3;
    private TextView tvYao2;
    private TextView tvYao1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_yao);

        initView();
        btnGenerate.setOnClickListener(this);
    }

    private void initView() {
        btnGenerate = findViewById(R.id.btn_generate_yao);
        btnNext = findViewById(R.id.btn_next);
        tvYao6=findViewById(R.id.tv_yao6);
        tvYao5=findViewById(R.id.tv_yao5);
        tvYao4=findViewById(R.id.tv_yao4);
        tvYao3=findViewById(R.id.tv_yao3);
        tvYao2=findViewById(R.id.tv_yao2);
        tvYao1=findViewById(R.id.tv_yao1);
    }

    @Override
    public void onClick(View v) {
        Message msg;
        switch (v.getId()){
            case R.id.btn_generate_yao:
                msg = Message.obtain();
                msg.what=1;
                handler.sendMessage(msg);
                break;
            case R.id.btn_next:
                msg=Message.obtain();
                msg.what=2;
                handler.sendMessage(msg);
                break;
        }
    }


    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvYao1.setText(QuartileYao.getLaoYin());
                    tvYao2.setText(QuartileYao.getShaoYang());
                    tvYao3.setText(QuartileYao.getShaoYin());
                    tvYao4.setText(QuartileYao.getLaoYang());
                    tvYao5.setText(QuartileYao.getLaoYin());
                    tvYao6.setText(QuartileYao.getShaoYang());
                    break;
            }

        }
    };
}