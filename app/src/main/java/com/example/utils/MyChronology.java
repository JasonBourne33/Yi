package com.example.utils;

import java.util.Calendar;
import java.util.Date;

import object.LiuYao;

public class MyChronology {


    /**
     * 记录天干的信息
     */
    private static String[] tianGanInfo = {"0","甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
            "壬", "癸"};
    private static String[] diZhiInfo = {"0","子", "丑", "寅", "卯", "辰", "巳", "午", "未",
            "申", "酉", "戌", "亥"};

    /**
     * 关于阴历的相关信息
     * 转成二进制后
     * 17-20：表示闰月的月份
     * 比如2020年是 0x07954，0111 1001 0101 0100 ，
     */
    private static int[] lunarInfo = {
            0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,//1900-1909
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,//1910-1919
            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,//1920-1929
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,//1930-1939
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,//1940-1949
            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5b0, 0x14573, 0x052b0, 0x0a9a8, 0x0e950, 0x06aa0,//1950-1959
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,//1960-1969
            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b6a0, 0x195a6,//1970-1979
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,//1980-1989
            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,//1990-1999
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,//2000-2009
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,//2010-2019
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,//2020-2029
            0x05aa0, 0x076a3, 0x096d0, 0x04afb, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,//2030-2039
            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0,//2040-2049
            /**Add By JJonline@JJonline.Cn**/
            0x14b63, 0x09370, 0x049f8, 0x04970, 0x064b0, 0x168a6, 0x0ea50, 0x06b20, 0x1a6c4, 0x0aae0,//2050-2059
            0x0a2e0, 0x0d2e3, 0x0c960, 0x0d557, 0x0d4a0, 0x0da50, 0x05d55, 0x056a0, 0x0a6d0, 0x055d4,//2060-2069
            0x052d0, 0x0a9b8, 0x0a950, 0x0b4a0, 0x0b6a6, 0x0ad50, 0x055a0, 0x0aba4, 0x0a5b0, 0x052b0,//2070-2079
            0x0b273, 0x06930, 0x07337, 0x06aa0, 0x0ad50, 0x14b55, 0x04b60, 0x0a570, 0x054e4, 0x0d160,//2080-2089
            0x0e968, 0x0d520, 0x0daa0, 0x16aa6, 0x056d0, 0x04ae0, 0x0a9d4, 0x0a2d0, 0x0d150, 0x0f252,//2090-2099
            0x0d520,//2100
    };

    /**
     * 60甲子表
     */
    private static String[] JiaziList = {
            "不想后面-1",
            "甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午", "辛未", "壬申", "癸酉",
            "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未",
            "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳",
            "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯",
            "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑",
            "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥",
    }; //45 己酉

    /**
     * 初始化年月日对应的天干地支
     *
     * @param year
     * @param month
     * @param day
     */
    public static void initGanZhi(int year, int month, int day) {
        //获取现在的时间
        Calendar nowaday = Calendar.getInstance();
        nowaday.set(year, month - 1, day);//原本的代码，-1可能错了
        Date date = nowaday.getTime();
        System.out.println("getTime  === " + date);
        System.out.println("getTime x2 === " + date.getTime());

        int weekYear = nowaday.getWeekYear();
//        int quotient=(weekYear-3)/60;   //取商
        int remainder = (weekYear - 3) % 60;   //取余数
        String yearGanZhi = JiaziList[remainder]; //根据余数来对应 六十甲子表
        int yearTiangan = remainder / 10;   //余数的十位是 年天干

        System.out.println("remainder=== " + remainder);
//        System.out.println("yearTiangan=== " + yearTiangan);
        System.out.println("yearGanZhi=== " + yearGanZhi);

        // 月 五虎遁
        int monthTianganBegin=fiveTiger(yearTiangan); //根据年天干获取 月的开始天干
        System.out.println("monthTianganBegin=== "+monthTianganBegin);
        System.out.println("month=== "+month);
        int monthTiangan=(monthTianganBegin+month)%10; //顺着推出输入的 月的天干
        System.out.println("monthTiangan=== "+monthTiangan);
        String monthGanZhi=tianGanInfo[monthTiangan]+diZhiInfo[month+3]; //天干 地支
        System.out.println("monthGanZhi=== "+monthGanZhi);
//        String monthGanzhiString=JiaziList[monthGanzhi];
//        System.out.println("monthGanzhiString=== "+monthGanzhiString);


//        int c =weekYear%100;    //去除后两位
//        System.out.println("c=== " + c);
    }

    /**
     * 传入年天干的 int
     * 返回月天干的 int
     */
    public static int fiveTiger(int yearTiangan) {
        int monthTiangan = 0;
        switch (yearTiangan) {
            case 0: //甲己之年丙作首
                monthTiangan = 2;
                break;
            case 5:
                monthTiangan = 2;
                break;
            case 1: //乙庚之年戊为头
                monthTiangan = 4;
                break;
            case 6:
                monthTiangan = 4;
                break;
            case 2: //丙辛之年寻庚上
                monthTiangan = 6;
                break;
            case 7:
                monthTiangan = 6;
                break;
            case 3: //丁壬壬寅顺水流
                monthTiangan = 8;
                break;
            case 8:
                monthTiangan = 8;
                break;
            case 4: //天干是 戊 或 癸 的年份第一个月的天干是 甲
                monthTiangan = 0;
                break;
            case 9:
                monthTiangan = 0;
                break;
        }
        return monthTiangan+1;
    }

// Chronology 的  begin==================

    /**
     * 获取农历某年的总天数
     *
     * @param year
     * @return
     */
    private int daysOfYear(int year) {
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunarInfo[year - 1900] & i) == 0 ? 0 : 1;
        }
        //获取闰月的天数
        int daysOfLeapMonth;
        if ((lunarInfo[year - 1900] & 0xf) != 0) {
            daysOfLeapMonth = (lunarInfo[year - 1900] & 0x10000) == 0 ? 29 : 30;
        } else {
            daysOfLeapMonth = 0;
        }
        return sum + daysOfLeapMonth;
    }


// Chronology 的  end==================


    public static void main(String[] args) {
//        initGanZhi(2017, 9, 17);
//        initGanZhi(2020, 6, 16);
        //丁酉年 己酉月 丁未日 丙午时   闰六月
//        Chronology.getInstance().initGanZhi(2017,9,17,12);

        //庚子年 壬午月 庚寅日 甲申时   闰四月
//        Chronology.getInstance().initGanZhi(2020,6,16,16);

        //己丑年 壬申月 丁亥日 甲辰时   闰五月
//        Chronology.getInstance().initGanZhi(2009,8,10,7);

        //8月 Bazi会报错  23时日干支会报错
//        Chronology.getInstance().initGanZhi(2017,8,9,22);

        //丁酉 年 , 乙巳 月 , 丙申 日    学魔法50 占病
//        Chronology.getInstance().initGanZhi(2017,5,9,22);
//        System.out.println(Chronology.getInstance().getGanZhi());



        //泰卦    上☷ 下☰   学魔法29       从下面一爻往上写
//        LiuYao.getInstance().initLiuYao(2,2,2,0,0,0); //0少阴 1老阴 2少阳 3老阳
        //大有卦  上☲ 下☰
//        LiuYao.getInstance().initLiuYao(2,2,2,2,0,2); //0少阴 1老阴 2少阳 3老阳

        //未济卦  上☲ 下 ☵   学魔法47 硬币起卦
        LiuYao.getInstance().initLiuYao(1,2,0,3,1,2); //0少阴 1老阴 2少阳 3老阳


    }
}
