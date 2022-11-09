package com.example.yi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ManuallyFillYaoActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private TextView tv6;
    private TextView tv5;
    private TextView tv4;
    private TextView tv3;
    private TextView tv2;
    private TextView tv1;
    private Spinner yao6;
    private Spinner yao5;
    private Spinner yao4;
    private Spinner yao3;
    private Spinner yao2;
    private Spinner yao1;
    private Button btnGenerate;
    private ArrayList<String> yaoList = new ArrayList<>();
    private ArrayAdapter adapter; //为下拉列表定义一个适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manually_fill_yao);


        btnGenerate = findViewById(R.id.btn_generateview);
        tv6=findViewById(R.id.tv_yao6);
        tv5=findViewById(R.id.tv_yao5);
        tv4=findViewById(R.id.tv_yao4);
        tv3=findViewById(R.id.tv_yao3);
        tv2=findViewById(R.id.tv_yao2);
        tv1=findViewById(R.id.tv_yao1);
        yao6 = findViewById(R.id.sp_yao6);
        yao5 = findViewById(R.id.sp_yao5);
        yao4 = findViewById(R.id.sp_yao4);
        yao3 = findViewById(R.id.sp_yao3);
        yao2 = findViewById(R.id.sp_yao2);
        yao1 = findViewById(R.id.sp_yao1);
//        btnGenerate.setOnClickListener(this);
        yao6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("66666666");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        yao5.setOnItemSelectedListener(this);
//        yao4.setOnItemSelectedListener(this);
//        yao3.setOnItemSelectedListener(this);
//        yao2.setOnItemSelectedListener(this);
//        yao1.setOnItemSelectedListener(this);
        yaoList.add("少阴");
        yaoList.add("老阴");
        yaoList.add("少阳");
        yaoList.add("老阳");


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yaoList);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        yao6.setAdapter(adapter);
        yao5.setAdapter(adapter);
        yao4.setAdapter(adapter);
        yao3.setAdapter(adapter);
        yao2.setAdapter(adapter);
        yao1.setAdapter(adapter);
//        yao1.setOnTouchListener(new Spinner.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.setVisibility(View.INVISIBLE);
//                Log.i("spinner", "Spinner Touch事件被触发!");
//                return false;
//            }
//        });
//        //焦点改变事件处理
//        yao1.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                // TODO Auto-generated method stub
//                v.setVisibility(View.VISIBLE);
//                Log.i("spinner", "Spinner FocusChange事件被触发！");
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate:
                initLiuyao();
                break;
        }
    }

    private void initLiuyao() {

    }

}