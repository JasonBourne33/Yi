package com.example.utils;

import java.util.Calendar;
import java.util.Date;

public class MyChronology {


    /**
     * 记录天干的信息
     */
    private String[] gan_info = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
            "壬", "癸"};
    private String[] zhi_info = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未",
            "申", "酉", "戌", "亥"};

    /**
     * 关于阴历的相关信息
     */
    private static int[] lunar_info = {
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
            "甲子","乙丑","丙寅","丁卯","戊辰","己巳","庚午","辛未","壬申","癸酉",
            "甲戌","乙亥","丙子","丁丑","戊寅","己卯","庚辰","辛巳","壬午","癸未",
            "甲申","乙酉","丙戌","丁亥","戊子","己丑","庚寅","辛卯","壬辰","癸巳",
            "甲午","乙未","丙申","丁酉","戊戌","己亥","庚子","辛丑","壬寅","癸卯",
            "甲辰","乙巳","丙午","丁未","戊申","己酉","庚戌","辛亥","壬子","癸丑",
            "甲寅","乙卯","丙辰","丁巳","戊午","己未","庚申","辛酉","壬戌","癸亥",
    };

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
        System.out.println("getTime x2 === " + date.getTime());

        int weekYear = nowaday.getWeekYear();
        int quotient=(weekYear-3)/60;   //取商
        int remainder=(weekYear-3)%60;   //取余数
        String ganZhi = JiaziList[remainder];


        Calendar baseDate = Calendar.getInstance(); //初始日期
        baseDate.set(1900,1,31);

        // 获取当前日期与1900年1月31日相差的天数
        int offset = (int) ((nowaday.getTime().getTime() - baseDate.getTime().getTime()) / 86400000L);
//        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
//        int iMonth, daysOfMonth = 0;
//        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
//            // 闰月
//            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
//                --iMonth;
//                leap = true;
//                daysOfMonth = leapDays(iYear);
//            } else
//                daysOfMonth = monthDays(iYear, iMonth);
//
//            offset -= daysOfMonth;
//            // 解除闰月
//            if (leap && iMonth == (leapMonth + 1))
//                leap = false;
//        }
//        // offset为0时，并且刚才计算的月份是闰月，要校正
//        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
//            if (leap) {
//                leap = false;
//            } else {
//                leap = true;
//                --iMonth;
//            }
//        }
//        // offset小于0时，也要校正
//        if (offset < 0) {
//            offset += daysOfMonth;
//            --iMonth;
//        }
//        // 设置对应的阴历月份
//        this.lunarMonth = lunarNumber[iMonth - 1] ;
//        if("一".equals(this.lunarMonth)){
//            this.lunarMonth ="正";
//        }
//        if("十二".equals(this.lunarMonth)){
//            this.lunarMonth ="腊";
//        }
//        if(leap){
//            this.lunarMonth ="闰"+this.lunarMonth;
//        }









//        int c =weekYear%100;    //去除后两位
//        System.out.println("c=== " + c);
    }

    public static void main(String[] args) {
        initGanZhi(2017, 7, 19);
//        initGanZhi(2020, 6, 16);
    }
}
