package com.example.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Chronology {
    /**
     * 对于年月日的天干地支
     */
    private int year_ganZhi;
    private int month_ganZhi;
    private int day_ganZhi;


//    /**
//     * 关于阴历的相关信息
//     * 农历年里面的月有多少天的信息
//     */
//    private static int[] lunar_info = {
//            0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
//            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
//            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
//            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
//            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
//            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
//            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
//            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
//            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,
//            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
//            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
//            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
//            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
//            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
//            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0
//    };

    /**
     * 关于阴历的相关信息
     * 转成二进制后
     * 17-20：表示闰月的月份
     * 比如2020年是 0x07954，0111 1001 0101 0100 ，
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
     * 记录天干的信息
     */
    private String[] ganInfo = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private String[] zhiInfo = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未",
            "申", "酉", "戌", "亥"};

//    private String[] tianGanInfo = {"拒绝0开始", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
//            "壬", "癸"};

    /**
     * 单例模式
     */
    private static volatile Chronology instance = null;

    private Chronology() {
    }

    public static Chronology getInstance() {
        if (instance == null) {
            synchronized (Chronology.class) {
                if (instance == null) {
                    instance = new Chronology();
                }
            }
        }
        return instance;
    }

    /**
     * 获取农历某年的总天数
     * &按位与的运算,运算规则即(两个为真才为真) 1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     * 3是011， 5是101， 3%5 是 001
     *
     * @param year
     * @return
     */
    private int daysOfYear(int year) {
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunar_info[year - 1900] & i) == 0 ? 0 : 1;
        }
        //获取闰月的天数
        int daysOfLeapMonth;
        if ((lunar_info[year - 1900] & 0xf) != 0) {
            daysOfLeapMonth = (lunar_info[year - 1900] & 0x10000) == 0 ? 29 : 30;
        } else {
            daysOfLeapMonth = 0;
        }
        return sum + daysOfLeapMonth;
    }

    private int getLeapMonth(int year) {
        //获取闰月的月份
        int x = -12 >>> 2;
        System.out.println("x=== " + x);

        return lunar_info[year - 1900] & 0xf;
    }

    /**
     * 初始化年月日对应的天干地支
     *
     * @param year
     * @param month
     * @param day
     */
    public void initGanZhi(int year, int month, int day, int hour) {

        //五鼠遁
        HashMap<Integer, Integer> hourTianganMap = new HashMap<>();
        //甲乙丙丁 1234
        hourTianganMap.put(1, 1); //甲己还作甲
        hourTianganMap.put(6, 1);
        hourTianganMap.put(2, 3); //乙庚丙作初
        hourTianganMap.put(7, 3);
        hourTianganMap.put(3, 5); //丙辛从戊起
        hourTianganMap.put(8, 5);
        hourTianganMap.put(4, 7); //丁壬庚子居
        hourTianganMap.put(9, 7);
        hourTianganMap.put(5, 9); //戊癸何方求，壬子时真途
        hourTianganMap.put(10, 9);

        //五虎遁
        HashMap<Integer, Integer> monthTianganMap = new HashMap<>();
        //甲乙丙丁 1234
        monthTianganMap.put(1, 3); //年天干 甲己 开头，正月时 丙寅
        monthTianganMap.put(6, 3);
        monthTianganMap.put(2, 5); //年天干 乙庚 开头，正月时 戊寅
        monthTianganMap.put(7, 5);
        monthTianganMap.put(3, 7); //年天干 丙辛 开头，正月时 庚寅
        monthTianganMap.put(8, 7);
        monthTianganMap.put(4, 9); //年天干 丁壬 开头，正月时 壬寅
        monthTianganMap.put(9, 9);
        monthTianganMap.put(5, 1); //年天干 戊癸 开头，正月时 甲寅
        monthTianganMap.put(10, 1);


        //我加入的 年算法
        Calendar nowaday = Calendar.getInstance();
        nowaday.set(year, month - 1, day);//原本的代码，-1可能错了
        Date date = nowaday.getTime();
        System.out.println("getTime=== " + date);

        int weekYear = nowaday.getWeekYear();
//        int quotient=(weekYear-3)/60;   //取商
        int remainder = (weekYear - 3) % 60;   //取余数
        String yearGanZhi = JiaziList[remainder]; //根据余数来对应 六十甲子表
//        int yearTiangan = remainder / 10;   //余数的十位是 年天干

        System.out.println("remainder=== " + remainder);
//        System.out.println("yearGanZhi=== " + yearGanZhi);
        mYearGanZhi = yearGanZhi;


        // 月算法
        int yearTiangan = remainder % 10; //JiaziList的 个位时天干
        System.out.println("yearTiangan=== " + yearTiangan);
        // -1 因为是 60甲子表里面的从1开始
        int monthTianganBegin = monthTianganMap.get(yearTiangan) - 1;
        System.out.println("monthTianganBegin=== " + monthTianganBegin);
        int monthTiangan = monthTianganBegin + month;
        //next  1 闰月是第几个月？ 2 公历怎么转换成农历

        int leapMonth = getLeapMonth(year);
        System.out.println("daysOfYear=== " + leapMonth);


//        //fHour是这一天经过的时辰数 hour+2因为 1:00已经是丑时了，还有本来子时跨天占一个小时
//        int fHour = (int) Math.round((double) (hour + 2) / 2);
//        //hourTianganBegin-1 因为要从这位开始数，只是定位到这里
//        int hourTianganInt = (hourTianganBegin - 1 + fHour) % 10;
//        String hourGanzhi = ganInfo[hourTianganInt] + zhiInfo[fHour - 1];//fHour-1 是因为 zhiInfo 从0开始数
//        mHourGanZhi = hourGanzhi;


        //我加入的日算法=========================
        int doubleDigit = year % 100;   //两位数，取2017里的 17
        int yearConstant = 0;   //年系数
        if (year >= 2000 && year <= 2099) {
            yearConstant = (doubleDigit + 7) * 5 + 15 + (doubleDigit + 19) / 4;
        } else if (year >= 1900 && year <= 1999) {
            yearConstant = (doubleDigit + 3) * 5 + 55 + (doubleDigit - 1) / 4;
        }

        yearConstant = yearConstant % 60;


        int monthDay = 0; //把前面月份天数加起来
        int dayJiazi;   //处理后的余数，对照六十甲子表的出干支
        boolean isLeepYear; //true 就是闰年
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + "是闰年");
            isLeepYear = true;
        } else {
            System.out.println(year + "不是闰年");
            isLeepYear = false;
        }

        if (isLeepYear) { //是闰年就用二月平 的leapMonths
            for (int j = 0; j < month - 1; j++) {
                monthDay += leapMonths[j];
            }
            dayJiazi = (yearConstant + monthDay + day) % 60;
        } else {
            for (int j = 0; j < month - 1; j++) {
                monthDay += months[j];
            }
            dayJiazi = (yearConstant + monthDay + day) % 60;
        }


        // 时算法
//       实验用的 dayJiazi 里 2017是44，2020是27
        int dayTianganInt = dayJiazi % 10;  //获取日天干 60甲子表里 个位数是天干
        mDayGanZhi = JiaziList[dayJiazi];  //获取日干支

        // -1 因为是从 dayTianganInt 里面获得的，而 dayTianganInt 是60甲子表里面的从1开始
        int hourTianganBegin = hourTianganMap.get(dayTianganInt) - 1;   //实验是庚，子时是庚子

        //fHour是这一天经过的时辰数 hour+2因为 1:00已经是丑时了，还有本来子时跨天占一个小时
        int fHour = (int) Math.round((double) (hour + 2) / 2);
        //hourTianganBegin-1 因为要从这位开始数，只是定位到这里
        int hourTianganInt = (hourTianganBegin - 1 + fHour) % 10;
        String hourGanzhi = ganInfo[hourTianganInt] + zhiInfo[fHour - 1];//fHour-1 是因为 zhiInfo 从0开始数
        mHourGanZhi = hourGanzhi;


        //        //获取现在的时间
//        Calendar calendar_now = Calendar.getInstance();
////        calendar_now.set(year, month - 1, day);//原本的代码，-1可能错了
//        calendar_now.set(year, month, day, hour, 0);//这样能正确显示
//        long date_now = calendar_now.getTime().getTime(); //输入的日期的毫秒
//        //获取1900-01-31的时间
//        Calendar calendar_ago = Calendar.getInstance();
//        calendar_ago.set(1900, 0, 31);
//        long date_ago = calendar_ago.getTime().getTime();
//        //86400000 = 24 * 60 * 60 * 1000    一天的毫秒数
//        long days_distance = (date_now - date_ago) / 86400000L; //输入日期 距离1900年1月31的天数
//        float remainder = (date_now - date_ago) % 86400000L;    //输入日期 距离1900年1月31的余数毫秒
//        //余数大于0算一天
//        if (remainder > 0) {
//            days_distance += 1;
//        }
//        //都是从甲子开始算起以1900-01-31为起点
//        //1899-12-22是农历1899年腊月甲子日  40：相差1900-01-31有40天
//        day_ganZhi = (int) days_distance + 40;
//        int getHours = calendar_now.getTime().getHours();
//        String hourDiZhi = hourDiZhiList[getHours];
//        //1898-10-01是农历甲子月  14：相差1900-01-31有14个月
//        month_ganZhi = 14;
//        int daysOfYear = 0;
//        int i;
//        for (i = 1900; i < 2050 && days_distance > 0; i++) {
//            daysOfYear = daysOfYear(i);
//            days_distance -= daysOfYear;
//            month_ganZhi += 12;
//        }
//        if (days_distance < 0) {
//            days_distance += daysOfYear;
//            i--;
//            month_ganZhi -= 12;
//        }
//        //农历年份
//        int myYear = i;
//        //1864年是甲子年
//        year_ganZhi = myYear - 1864;
//        //哪个月是闰月
//        int leap = lunar_info[myYear - 1900] & 0xf;
//        boolean isLeap = false;
//        int daysOfLeapMonth = 0;
//        for (i = 1; i < 13 && days_distance > 0; i++) {
//            //闰月
//            if (leap > 0 && i == (leap + 1) && !isLeap) {
//                isLeap = true;
//                if ((lunar_info[myYear - 1900] & 0xf) != 0) {
//                    daysOfLeapMonth = (lunar_info[myYear - 1900] & 0x10000) == 0 ? 29 : 30;
//                } else {
//                    daysOfLeapMonth = 0;
//                }
//                --i;
//            } else {
//                daysOfLeapMonth = (lunar_info[myYear - 1900] & (0x10000 >> i)) == 0 ? 29 : 30;
//            }
//            //设置非闰月
//            if (isLeap && i == (leap + 1)) {
//                isLeap = false;
//            }
//            days_distance -= daysOfLeapMonth;
//            if (!isLeap) {
//                month_ganZhi++;
//            }
//        }
//        if (days_distance == 0 && leap > 0 && i == leap + 1 && !isLeap) {
//            --month_ganZhi;
//        }
//        if (days_distance < 0) {
//            --month_ganZhi;
//        }

    }

    //算 时的地支 的数组
    private static String[] hourDiZhiList = {
            "子", "丑", "丑", "寅", "寅", "卯", "卯", "辰", "辰", "巳", "巳", "午", "午",
            "未", "未", "申", "申", "酉", "酉", "戌", "戌", "亥", "亥", "子",
    };

    //阳历天数
    final static int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    final static int[] leapMonths = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
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
    };
    private String mYearGanZhi;
    private String mMonthGanZhi;
    private String mDayGanZhi; //我自己做的日干支
    private String mHourGanZhi; //我自己做的时干支

    /**
     * 将年月日转化为天干地支的显示方法
     *
     * @param index
     * @return
     */
    private String ganZhi(int index) {
        return ganInfo[index % 10] + zhiInfo[index % 12];
    }

    /**
     * 获取天干地支
     *
     * @return
     */
    public String getGanZhi() {
//        return "农历 " + ganZhi(year_ganZhi) + " 年 , " + ganZhi(month_ganZhi) + " 月 , "
//                       + mDayGanZhi + " 日 ," + mHourGanZhi + " 时 " +
//                " year_ganZhi=== " + year_ganZhi + " month_ganZhi=== " + month_ganZhi + " day_ganZhi=== " + day_ganZhi;

        return "农历 " + mYearGanZhi + " 年 , " + " 0 月 , "
                + mDayGanZhi + " 日 ," + mHourGanZhi + " 时 " +
                " year_ganZhi=== " + year_ganZhi + " month_ganZhi=== " + month_ganZhi + " day_ganZhi=== " + day_ganZhi;
    }


    public int solar2Lunar(int year, int month, int day) {
        if (year < 1900 || year > 2100) {
            return -1;
        }
        if (year == 1900 && month == 1 && day < 31) {
            return -1;
        }
        

        return 0;
    }


}