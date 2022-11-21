package com.example.utils;

import object.BaGuaInit;
import object.Bazi;
import object.LiuYao;

public class ZengShanBuYi {
    public static void main(String[] args) {
        BaGuaInit.getInstance().initHexagram();

        // https://www.bilibili.com/video/BV15Z4y1R7Da/
        // 巳月 甲寅日 占往营中贸易 妻财为用神
//        Chronology.getInstance().initGanZhi(2020,5,11,7);
//        LiuYao.getInstance().initLiuYao(0,2,3,0,2,2,"妻财");

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

        //增删卜易 8页 乾卦世爻动
//        Chronology.getInstance().initGanZhi(2018,5,13,7);
//        LiuYao.getInstance().initLiuYao(2,2,2,2,2,3,"父母");

        //增删卜易 12页 例一：用神无根  自占病：世爻父母为用神，
//        Chronology.getInstance().initGanZhi(2017,5,8,7);
//        LiuYao.getInstance().initLiuYao(0,2,2,2,3,1,"世爻");

        //增删卜易 12页 例二：元神入墓  自占病  丑月戊子日
//        Chronology.getInstance().initGanZhi(2015,1,12,7);
//        LiuYao.getInstance().initLiuYao(3,0,2,2,3,2,"世爻");
        //请伊母再占一卦   母占子，子孙为用神
//        Chronology.getInstance().initGanZhi(2015,1,12,7);
//        LiuYao.getInstance().initLiuYao(2,1,2,2,0,2,"子孙");

        //增删卜易 13页 例一：回头相生  弟占兄 卯月己卯日
//        Chronology.getInstance().initGanZhi(2013,3,14,7);
//        LiuYao.getInstance().initLiuYao(2,0,0,1,0,0,"兄弟");
        //增删卜易 13页 例二：父母持世  巳月丙申日，占病（应该是父母占子）
//        Chronology.getInstance().initGanZhi(2017,5,9,7);
//        LiuYao.getInstance().initLiuYao(0,2,2,2,2,2,"子孙");

        //五行相克 13页 例一：有克无生  卯月戊辰日，占父官事
//        Chronology.getInstance().initGanZhi(2011,3,14,7);
//        LiuYao.getInstance().initLiuYao(1,0,1,2,2,1,"父母");

        //五行相克 13页 例二：克处逢生  卯月戊辰日，妹占兄官事
//        Chronology.getInstance().initGanZhi(2011,3,14,7);
//        LiuYao.getInstance().initLiuYao(0,1,0,2,2,2,"兄弟");


        //20页 申月甲辰日，占兄病      忌神、元神同动
//        Chronology.getInstance().initGanZhi(2022,8,19,7);
//        LiuYao.getInstance().initLiuYao(2,0,0,1,3,0,"兄弟");

        //page43 申月癸卯日，占乡试，得“恒之大过”
//        Chronology.getInstance().initGanZhi(2022,8,18,7);//报错
//        Chronology.getInstance().initGanZhi(2022,8,18,9);
//        LiuYao.getInstance().initLiuYao(0,2,2,2,1,0,"官鬼");

        //六爻公开课五 变爻 卯月 壬申日
//        Chronology.getInstance().initGanZhi(2022,3,20,9);
//        LiuYao.getInstance().initLiuYao(0,1,1,0,2,0,"官鬼");
        //六爻公开课五 动爻 酉月 甲辰日  16.25
//        Chronology.getInstance().initGanZhi(2028,9,16,9);
//        LiuYao.getInstance().initLiuYao(1,3,1,0,0,0,"官鬼");
        //六爻公开课五 日月 午月 戊辰日  11.45
        Chronology.getInstance().initGanZhi(2027,6,18,9);
        LiuYao.getInstance().initLiuYao(0,0,0,2,0,2,"兄弟");


        System.out.println(Chronology.getInstance().getGanZhi());
        System.out.println("本卦对象=== "+ BaGuaInit.getBengGua().toString());
        System.out.println("八字对象=== "+Bazi.getInstance().toString());
//        System.out.println("变卦对象=== "+BaGuaInit.getBianGua().toString());
//        System.out.println("本宫卦对象=== "+BaGuaInit.getBenGongGua().toString());
    }
}
