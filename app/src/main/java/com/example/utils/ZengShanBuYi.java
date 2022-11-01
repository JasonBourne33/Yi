package com.example.utils;

import object.BaGuaInit;
import object.LiuYao;

public class ZengShanBuYi {
    public static void main(String[] args) {

        //未济卦  上☲ 下 ☵   学魔法47,51  硬币起卦  丁酉年 己酉月 丁未日 丙午时   闰六月 学魔法47,51
//        Chronology.getInstance().initGanZhi(2017,9,17,12);
//        LiuYao.getInstance().initLiuYao(1,2,0,3,1,2); //0少阴 1老阴 2少阳 3老阳

        //姤卦  学魔法50  增删卜易 13页  例二：父母持世  丁酉 年 , 乙巳 月 , 丙申 日
//        Chronology.getInstance().initGanZhi(2017,5,9,22);
//        LiuYao.getInstance().initLiuYao(0,2,2,2,2,2);

        //增删卜易 70页 学魔法52
//        Chronology.getInstance().initGanZhi(2018,3,13,12);
//        LiuYao.getInstance().initLiuYao(2,2,2,2,3,0);

        //增删卜易 13页 例一：回头相生  卯月 己卯日  兄弟为用神
//        Chronology.getInstance().initGanZhi(2013,3,14,7);
//        LiuYao.getInstance().initLiuYao(2,0,0,1,0,0);

        //增删卜易 12页 例一：用神无根  自占病：父母为用神，妻财为忌神
        Chronology.getInstance().initGanZhi(2018,5,13,7);
        LiuYao.getInstance().initLiuYao(0,2,2,2,3,1);

        System.out.println(Chronology.getInstance().getGanZhi());
        System.out.println("本卦对象=== "+ BaGuaInit.getBengGua().toString());
        System.out.println("变卦对象=== "+BaGuaInit.getBianGua().toString());
        System.out.println("本宫卦对象=== "+BaGuaInit.getBenGongGua().toString());
    }
}
