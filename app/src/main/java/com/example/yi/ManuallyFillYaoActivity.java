package com.example.yi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.utils.Chronology;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import custom.DateTimeButton;
import object.BaGuaInit;
import object.LiuYao;

public class ManuallyFillYaoActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    private DateTimeButton dateTimeButton;
    private TextView tv6;
    private TextView tv5;
    private TextView tv4;
    private TextView tv3;
    private TextView tv2;
    private TextView tv1;
//    private TextView tvPickDate;
    private Spinner yao6;
    private Spinner yao5;
    private Spinner yao4;
    private Spinner yao3;
    private Spinner yao2;
    private Spinner yao1;
    private Spinner spYongshen;
    private Button btnGenerate;
    private ArrayList<String> yaoList = new ArrayList<>();
    private ArrayList<String> yongshenList = new ArrayList<>();
    private ArrayAdapter adapter; //为下拉列表定义一个适配器
    private ArrayAdapter yongshenAdapter; //为用神 下拉列表定义一个适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manually_fill_yao);

        mContext=this;
        btnGenerate = findViewById(R.id.btn_generate);
//        tvPickDate = findViewById(R.id.tv_pickDate);
        dateTimeButton = findViewById(R.id.dtb_pickdate); //日期时间选择控件
        tv6 = findViewById(R.id.tv_yao6);
        tv5 = findViewById(R.id.tv_yao5);
        tv4 = findViewById(R.id.tv_yao4);
        tv3 = findViewById(R.id.tv_yao3);
        tv2 = findViewById(R.id.tv_yao2);
        tv1 = findViewById(R.id.tv_yao1);
        yao6 = findViewById(R.id.sp_yao6);
        yao5 = findViewById(R.id.sp_yao5);
        yao4 = findViewById(R.id.sp_yao4);
        yao3 = findViewById(R.id.sp_yao3);
        yao2 = findViewById(R.id.sp_yao2);
        yao1 = findViewById(R.id.sp_yao1);
        spYongshen = findViewById(R.id.sp_yongShen);
        btnGenerate.setOnClickListener(this);
//        tvPickDate.setOnClickListener(this);
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
        yongshenList.add("父母");
        yongshenList.add("子孙");
        yongshenList.add("兄弟");
        yongshenList.add("官鬼");
        yongshenList.add("妻财");
        yongshenList.add("世爻");

        //适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yaoList);
        yongshenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yongshenList);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        yao6.setAdapter(adapter);
        yao5.setAdapter(adapter);
        yao4.setAdapter(adapter);
        yao3.setAdapter(adapter);
        yao2.setAdapter(adapter);
        yao1.setAdapter(adapter);
        spYongshen.setAdapter(yongshenAdapter);

//        tvPickDate.setText("年月日时");

    }

    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate:
                initLiuyao();
                Intent intent=new Intent();
                intent.setClass(mContext,GuaDetailActivity.class);
                startActivity(intent);
                break;
//            case R.id.tv_pickDate:
//                showDatePickerDialog(this, 0, tvPickDate, calendar);
//                break;
        }
    }

    /**
     * 日期选择
     *
     * @param activity
     * @param themeResId 3好用
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText("年月日时：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void initLiuyao() {
        System.out.println("yao6=== " + yao6.getSelectedItem().toString());
        System.out.println("getSelectedItemPosition=== " + yao6.getSelectedItemPosition());

        int p6 = yao6.getSelectedItemPosition(); //yao6 position
        int p5 = yao5.getSelectedItemPosition();
        int p4 = yao4.getSelectedItemPosition();
        int p3 = yao3.getSelectedItemPosition();
        int p2 = yao2.getSelectedItemPosition();
        int p1 = yao1.getSelectedItemPosition();
        int year = dateTimeButton.getYear();
        int month = dateTimeButton.getMonth();
        int day = dateTimeButton.getDay();
        int hour = dateTimeButton.getHour();
        String strYongshen = spYongshen.getSelectedItem().toString();
        System.out.println("p6~1= "+p6+p5+p4+p3+p2+p1);
        Chronology.getInstance().initGanZhi(year,month,day,hour);
        LiuYao.getInstance().initLiuYao(p1, p2, p3, p4, p5, p6, strYongshen);

        System.out.println("本卦对象=== "+ BaGuaInit.getBengGua().toString());
        System.out.println("变卦对象=== "+BaGuaInit.getBianGua().toString());
        System.out.println("本宫卦对象=== "+BaGuaInit.getBenGongGua().toString());


    }

}