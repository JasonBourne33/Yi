package com.example.yi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import object.BaGua;
import object.BaGuaInit;
import object.Bazi;
import object.LiuYao;

public class GuaDetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView tvHead; //头部，日月，旬空
    private TextView tvLiuyao; //本卦信息
    private TextView duanInfo;
    private TextView baziInfo; //八字信息
    private TextView bianGuaInfo;
    private TextView benGongGuaInfo;
    private TextView tvXingSha; //星煞

    private TextView tvYao6;
    private TextView tvYao5;
    private TextView tvYao4;
    private TextView tvYao3;
    private TextView tvYao2;
    private TextView tvYao1;

    private TextView tvBYao6;
    private TextView tvBYao5;
    private TextView tvBYao4;
    private TextView tvBYao3;
    private TextView tvBYao2;
    private TextView tvBYao1;


    private ArrayList<TextView> tvList = new ArrayList<>(); //本卦 的
    private ArrayList<TextView> tvBianList = new ArrayList<>(); //变卦的


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gua_detail);
        mContext = this;

        tvHead = findViewById(R.id.tv_head);
        tvLiuyao = findViewById(R.id.tv_guaInfo);
        duanInfo = findViewById(R.id.tv_duanInfo);//断卦的信息
        baziInfo = findViewById(R.id.tv_bazi);//断卦的信息
        bianGuaInfo = findViewById(R.id.tv_bianGuaInfo);//变卦的信息
        benGongGuaInfo = findViewById(R.id.tv_benGongGuaInfo);//本宫的信息
        tvXingSha = findViewById(R.id.tv_xingSha);//本宫的信息
        tvYao6 = findViewById(R.id.tv_yao6); //本卦的爻
        tvYao5 = findViewById(R.id.tv_yao5);
        tvYao4 = findViewById(R.id.tv_yao4);
        tvYao3 = findViewById(R.id.tv_yao3);
        tvYao2 = findViewById(R.id.tv_yao2);
        tvYao1 = findViewById(R.id.tv_yao1);

        tvBYao6 = findViewById(R.id.tv_bYao6); //变爻
        tvBYao5 = findViewById(R.id.tv_bYao5);
        tvBYao4 = findViewById(R.id.tv_bYao4);
        tvBYao3 = findViewById(R.id.tv_bYao3);
        tvBYao2 = findViewById(R.id.tv_bYao2);
        tvBYao1 = findViewById(R.id.tv_bYao1);

        BaGua bengGua = BaGuaInit.getBengGua();
        String yueJian = bengGua.getYueJian().get("月建");
        String riGanzhi = bengGua.getBazi().get(2);
        String xunKong = Bazi.getInstance().getXunKong();
        HashMap<String, String> xingShaList = Bazi.getInstance().getXingSha();
        StringBuffer xingSha = new StringBuffer();
        for (HashMap.Entry<String,String> entry : xingShaList.entrySet()){
            xingSha.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append("      ");
        }
        tvXingSha.setText(xingSha);

        tvHead.setText(yueJian+"月 "+riGanzhi+"日   旬空:"+xunKong); // 我来组成头部
        ArrayList<Boolean> yaoList = BaGuaInit.getBengGua().getYao();
        tvLiuyao.setText("本卦= "+BaGuaInit.getBengGua().toString());
        duanInfo.setText(LiuYao.getInstance().getDuanGuaObj().toString()); //设置断卦信息
        baziInfo.setText(Bazi.getInstance().toString());
        bianGuaInfo.setText("变卦= "+BaGuaInit.getBianGua().toString()); //变卦信息
        benGongGuaInfo.setText("本宫卦= "+BaGuaInit.getBenGongGua().toString()); //本宫卦信息

        BaGua benGua = BaGuaInit.getBengGua();
        BaGua bianGua = BaGuaInit.getBianGua();
//        TextView textView = new TextView(mContext);
//        tvYao6.setText(benGua.getYao().get(5) == true ?"▆▆▆":"▆ ▆" +" " + benGua.getRelation().get(5) + benGua.getGanZhi().get(5) + benGua.getYaoWuxing().get(5));
//        tvYao5.setText(benGua.getYao().get(4) == true ?"▆▆▆":"▆ ▆" +" " + benGua.getRelation().get(4) + benGua.getGanZhi().get(4) + benGua.getYaoWuxing().get(4));
//        tvYao4.setText(benGua.getYao().get(3) == true ?"▆▆▆":"▆ ▆" +" " + benGua.getRelation().get(3) + benGua.getGanZhi().get(3) + benGua.getYaoWuxing().get(3));
//        tvYao3.setText(benGua.getYao().get(2) == true ?"▆▆▆":"▆ ▆" +" " + benGua.getRelation().get(2) + benGua.getGanZhi().get(2) + benGua.getYaoWuxing().get(2));
//        tvYao2.setText(benGua.getYao().get(1) == true ?"▆▆▆":"▆ ▆" +" " + benGua.getRelation().get(1) + benGua.getGanZhi().get(1) + benGua.getYaoWuxing().get(1));
//        tvYao1.setText(benGua.getYao().get(0) == true ?"▆▆▆":"▆ ▆" +" " + benGua.getRelation().get(0) + benGua.getGanZhi().get(0) + benGua.getYaoWuxing().get(0));
        tvList.add(tvYao1);
        tvList.add(tvYao2);
        tvList.add(tvYao3);
        tvList.add(tvYao4);
        tvList.add(tvYao5);
        tvList.add(tvYao6);

        tvBianList.add(tvBYao1);
        tvBianList.add(tvBYao2);
        tvBianList.add(tvBYao3);
        tvBianList.add(tvBYao4);
        tvBianList.add(tvBYao5);
        tvBianList.add(tvBYao6);


        int shiYao = BaGuaInit.getBengGua().getShiYao();
        int yingYao = BaGuaInit.getBengGua().getYingYao();
//        int bShiYao = BaGuaInit.getBianGua().getShiYao();
//        int bYingYao = BaGuaInit.getBengGua().getYingYao();
        //遍历TextView 加入内容
        for (int i = 0; i < tvList.size(); i++) {
            tvList.get(i).setTextSize(18);
            tvBianList.get(i).setTextSize(18);
            // 一个█ 相当于4个空格
//            String benguaYao = benGua.getYao().get(i) ? "███" : "█    █";
//            String bianguaYao = bianGua.getYao().get(i) ? "███" : "█    █";
            String benguaYao = benGua.getYao().get(i) ? "⚊" : "⚋";
            String bianguaYao = bianGua.getYao().get(i) ? "⚊" : "⚋";

            ArrayList<String> dongSymbolMap = LiuYao.getInstance().getDongSymbol();
            tvList.get(i).setText(
                    benguaYao + " " + benGua.getRelation().get(i) +
                            benGua.getGanZhi().get(i).substring(1,2) + benGua.getYaoWuxing().get(i) +
                            ((i + 1) == shiYao ? "世" : "") +
                            ((i + 1) == yingYao ? "应" : "")+
                            dongSymbolMap.get(i)  //阳变阴为o，阴变阳为x
//                           ">"
            );
            tvBianList.get(i).setText(
                    bianguaYao + " " + bianGua.getRelation().get(i) +
                            bianGua.getGanZhi().get(i).substring(1,2) + bianGua.getYaoWuxing().get(i) +
                            ((i + 1) == shiYao ? "世" : "") +
                            ((i + 1) == yingYao ? "应" : "")
            );
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiuYao.getInstance().clear();
        Bazi.getInstance().clear();
    }
}