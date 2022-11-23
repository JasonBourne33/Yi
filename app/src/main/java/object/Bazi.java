package object;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class Bazi {
    /**
     * 单例模式
     */
    private static volatile Bazi instance = null;

    private Bazi() {
    }

    public static Bazi getInstance() {
        if (instance == null) {
            synchronized (Bazi.class) {
                if (instance == null) {
                    instance = new Bazi();
                }
            }
        }
        return instance;
    }


    public String getSuiYin() {
        return suiYin;
    }

    public String getSuiPo() {
        return suiPo;
    }

    public String getYueJian() {
        return yueJian;
    }

    public String getYuePo() {
        return yuePo;
    }

    public String getRiZhi() {
        return riZhi;
    }

    public String getRiPo() {
        return riPo;
    }

    public HashMap<String, String> getTwelveChangsheng() {
        return twelveChangsheng;
    }

    //岁阴，岁破，月建，月破，日支，日破
    private String suiYin;
    private String suiPo;
    private String yueJian;
    private String yuePo;
    private String riZhi;
    private String riPo;
    private ArrayList<String> bazi = new ArrayList<>(); //年月日时的 干支 （年干，年支，月干，月支，日干，日支，时干，时支）
    private HashMap<String,String> yueJianMap = new HashMap<>(); //月建，月破，日支，日破
    private HashMap<String,String> twelveChangsheng = new HashMap<>(); //长生：地支
    private HashMap<String,String> liuChong;//六冲
    private ArrayList<String> luma = new ArrayList<>(); //禄，马，生，旺，墓
    //禄马生旺墓
    private String lu;
    private String ma;
    private String sheng;   //长生1
    private String diWang;    //帝旺5
    private String mu;      //墓9

    private String muYu;    //沐浴2
    private String guanDai; //冠带3
    private String linGuan; //临冠4
    private String shuai;   //衰6
    private String bing;    //病7
    private String si;      //死8
    private String jue;     //绝10
    private String tai;     //胎11
    private String yang;    //养12

    private String  xunKong; //根据日支获取旬空
    private HashMap<String,String>  xunKongList=new HashMap<String,String>(); //根据日支获取旬空

    public String getXunKong() {
        return xunKong;
    }

    public void setXunKong(String xunKong) {
        this.xunKong = xunKong;
    }

    public HashMap<String, String> getXunKongList() {
        return xunKongList;
    }

    public void setXunKongList(HashMap<String, String> xunKongList) {
        this.xunKongList = xunKongList;
    }

    /**
     * @param y 年 干支
     * @param m 月
     * @param d 日
     * @param h 时
     */
    private int i;

    public void initBazi(String y, String m, String d, String h) {



        bazi.add(y); //先放到arrayList，再放到 本卦对象
        bazi.add(m);
        bazi.add(d);
        bazi.add(h);

        String suiYin = y.substring(1, 2);
        String yueJian = m.substring(1, 2);
        String riZhi = d.substring(1, 2);

        liuChong=duanGua.getLiuChong();
        this.suiYin = suiYin;
        this.suiPo = liuChong.get(suiYin);
        this.yueJian = yueJian;
        this.yuePo = liuChong.get(yueJian);
        this.riZhi = riZhi;
        this.riPo = liuChong.get(riZhi);

//        yueJianMap.put("岁阴",suiYin);//先放到hashMap，再放到 本卦对象
//        yueJianMap.put("岁破",suiPo);
        yueJianMap.put("月建",yueJian);
        yueJianMap.put("月破",yuePo);
        yueJianMap.put("日支",riZhi);
        yueJianMap.put("日破",riPo);

        xunKong=xunKongList.get(d);



//        System.out.println("月破=== " + yuePo);
//        System.out.println("日破=== " + riPo);

        String riGan = d.substring(0, 1);
//        Tiangan.getInstance().init(riGan);
//        System.out.println(Tiangan.getInstance().getInfo());

        // 禄的代码
        // 丁 阴 火 帝旺是第5位  月地支是 午，寅卯辰巳午
        String ganWuXing = wuXingMap.get(riGan);
        i = tianGanList.indexOf(riGan) + 1; //wuXingList从第0位开始，+1 方便后面判断奇偶
        diWang = diWangMap.get(ganWuXing); //先获取帝旺
        int diWangInt = yueDiZhiList.indexOf(diWang); //再获取帝旺对应的位置
        //根据帝旺的位置寻找其他的十二长生的月地支

        sheng = yueDiZhiList.get((diWangInt - 4) < 0 ? 12 - Math.abs(diWangInt - 4) : diWangInt - 4); //十二长生中的 生
        muYu = yueDiZhiList.get((diWangInt - 3) < 0 ? 12 - Math.abs(diWangInt - 3) : diWangInt - 3); //沐浴
        guanDai = yueDiZhiList.get((diWangInt - 2) < 0 ? 12 - Math.abs(diWangInt - 2) : diWangInt - 2);
        linGuan = yueDiZhiList.get((diWangInt - 1) < 0 ? 12 - Math.abs(diWangInt - 1) : diWangInt - 1);
        shuai = yueDiZhiList.get((diWangInt + 1) > 11 ? (diWangInt + 1) - 12 : diWangInt + 1); //衰
        bing = yueDiZhiList.get((diWangInt + 2) > 11 ? (diWangInt + 2) - 12 : diWangInt + 2); //病
        si = yueDiZhiList.get((diWangInt + 3) > 11 ? (diWangInt + 3) - 12 : diWangInt + 3); //死
        mu = yueDiZhiList.get((diWangInt + 4) > 11 ? (diWangInt + 4) - 12 : diWangInt + 4); //十二长生中的 墓
        jue = yueDiZhiList.get((diWangInt + 5) > 11 ? (diWangInt + 5) - 12 : diWangInt + 5);
        tai = yueDiZhiList.get((diWangInt + 6) > 11 ? (diWangInt + 6) - 12 : diWangInt + 6);
        yang = yueDiZhiList.get((diWangInt + 7) > 11 ? (diWangInt + 7) - 12 : diWangInt + 7);

        twelveChangsheng.put("长生",sheng);
        twelveChangsheng.put("沐浴",muYu);
        twelveChangsheng.put("临冠",linGuan);
        twelveChangsheng.put("冠带",guanDai);
        twelveChangsheng.put("帝旺",diWang);
        twelveChangsheng.put("衰",shuai);
        twelveChangsheng.put("病",bing);
        twelveChangsheng.put("死",si);
        twelveChangsheng.put("墓",mu);
        twelveChangsheng.put("绝",jue);
        twelveChangsheng.put("胎",tai);
        twelveChangsheng.put("养",yang);

//        System.out.println("diWangInt=== "+diWangInt);
//        System.out.println("sheng=== "+((diWangInt - 4) < 0 ? 12 - Math.abs(diWangInt - 4) : diWangInt - 4));
//        System.out.println("mu=== "+((diWangInt + 4) > 11 ? (diWangInt + 4) - 12 : diWangInt + 4));

        if ((i & 1) == 0) { //偶数，阴天干，在帝旺上
            lu = diWang;
        } else { //阳天干,在临冠上
            lu = linGuan;
        }


        //马 的代码  驿马三合顶头冲
        int sanHePos = sanHeList.indexOf(riZhi);
//        System.out.println("riZhi=== "+riZhi);
//        System.out.println("sanHePos=== "+sanHePos);
        int sanHe = sanHePos / 3; // 0是火的三合，1是金，2是水，3是木
        String sanHeDing = ""; // 存三合的顶头
        switch (sanHe) {
            case 0:
                sanHeDing = sanHeList.get(0);
                break;
            case 1:
                sanHeDing = sanHeList.get(3);
                break;
            case 2:
                sanHeDing = sanHeList.get(6);
                break;
            case 3:
                sanHeDing = sanHeList.get(9);
                break;
        }
//        System.out.println("sanHeDing=== "+sanHeDing);
        ma = liuChong.get(sanHeDing);

//        System.out.println("禄=== " + lu);
//        System.out.println("马=== " + ma);
//        System.out.println("生=== " + sheng);
//        System.out.println("旺=== " + diWang);
//        System.out.println("墓=== " + mu);
        luma.add(lu);
        luma.add(ma);
        luma.add(sheng);
        luma.add(diWang);
        luma.add(mu);


        BaGuaInit.getBengGua().setBazi(bazi);
        BaGuaInit.getBengGua().setYueJian(yueJianMap);
        BaGuaInit.getBengGua().setLuma(luma);


    }


    private HashMap<String, String> wuXingMap;  //天干的五行
    private HashMap<String, String> diWangMap;  //五行的帝旺
    private ArrayList<String> wuXingList;   //五行
    private ArrayList<String> changShengList;   //十二长生
    private ArrayList<String> yueDiZhiList; //月地支 寅卯辰
    private ArrayList<String> sanHeList; //按照三合的来算
    private ArrayList<String> tianGanList; //按照三合的来算

    HashMap<String, String> jinShenList = new HashMap<>(); //进神
    HashMap<String, String> tuiShenList = new HashMap<>(); //退神
    HashMap<String, String> liuHeList = new HashMap<>(); //六合
    HashMap<String, String> liuChongList = new HashMap<>(); //六冲
    private DuanGua duanGua=DuanGua.getInstance();
    public void initBaziBase() {

        //进神：亥化子，寅化卯，巳化午，申化酉，丑化辰，辰化未，未化戌。
        jinShenList.put("亥", "子");
        jinShenList.put("寅", "卯");
        jinShenList.put("巳", "午");
        jinShenList.put("申", "酉");
        jinShenList.put("丑", "辰");
        jinShenList.put("辰", "未");
        jinShenList.put("未", "戌");
        duanGua.setJinShen(jinShenList);

        //退神：子化亥，卯化寅，午化巳，酉化申，辰化丑，未化辰，戌化未。
        tuiShenList.put("子", "亥");
        tuiShenList.put("卯", "寅");
        tuiShenList.put("午", "巳");
        tuiShenList.put("酉", "申");
        tuiShenList.put("辰", "丑");
        tuiShenList.put("未", "辰");
        tuiShenList.put("戌", "未");
        duanGua.setTuiShen(tuiShenList);

        //六十甲子找旬空
        xunKongList.put("甲子","戌亥");
        xunKongList.put("乙丑","戌亥");
        xunKongList.put("丙寅","戌亥");
        xunKongList.put("丁卯","戌亥");
        xunKongList.put("戊辰","戌亥");
        xunKongList.put("己巳","戌亥");
        xunKongList.put("庚午","戌亥");
        xunKongList.put("辛未","戌亥");
        xunKongList.put("壬申","戌亥");
        xunKongList.put("癸酉","戌亥");
        xunKongList.put("甲戌","申酉");
        xunKongList.put("乙亥","申酉");
        xunKongList.put("丙子","申酉");
        xunKongList.put("丁丑","申酉");
        xunKongList.put("戊寅","申酉");
        xunKongList.put("己卯","申酉");
        xunKongList.put("庚辰","申酉");
        xunKongList.put("辛巳","申酉");
        xunKongList.put("壬午","申酉");
        xunKongList.put("癸未","申酉");
        xunKongList.put("甲申","午未");
        xunKongList.put("乙酉","午未");
        xunKongList.put("丙戌","午未");
        xunKongList.put("丁亥","午未");
        xunKongList.put("戊子","午未");
        xunKongList.put("己丑","午未");
        xunKongList.put("庚寅","午未");
        xunKongList.put("辛卯","午未");
        xunKongList.put("壬辰","午未");
        xunKongList.put("癸巳","午未");
        xunKongList.put("甲午","辰巳");
        xunKongList.put("乙未","辰巳");
        xunKongList.put("丙申","辰巳");
        xunKongList.put("丁酉","辰巳");
        xunKongList.put("戊戌","辰巳");
        xunKongList.put("己亥","辰巳");
        xunKongList.put("庚子","辰巳");
        xunKongList.put("辛丑","辰巳");
        xunKongList.put("壬寅","辰巳");
        xunKongList.put("癸卯","辰巳");
        xunKongList.put("甲辰","寅卯");
        xunKongList.put("乙巳","寅卯");
        xunKongList.put("丙午","寅卯");
        xunKongList.put("丁未","寅卯");
        xunKongList.put("戊申","寅卯");
        xunKongList.put("己酉","寅卯");
        xunKongList.put("庚戌","寅卯");
        xunKongList.put("辛亥","寅卯");
        xunKongList.put("壬子","寅卯");
        xunKongList.put("癸丑","寅卯");
        xunKongList.put("甲寅","子丑");
        xunKongList.put("乙卯","子丑");
        xunKongList.put("丙辰","子丑");
        xunKongList.put("丁巳","子丑");
        xunKongList.put("戊午","子丑");
        xunKongList.put("己未","子丑");
        xunKongList.put("庚申","子丑");
        xunKongList.put("辛酉","子丑");
        xunKongList.put("壬戌","子丑");
        xunKongList.put("癸亥","子丑");


        liuChongList.put("子", "午");
        liuChongList.put("午", "子");
        liuChongList.put("丑", "未");
        liuChongList.put("未", "丑");
        liuChongList.put("寅", "申");
        liuChongList.put("申", "寅");
        liuChongList.put("卯", "酉");
        liuChongList.put("酉", "卯");
        liuChongList.put("辰", "戌");
        liuChongList.put("戌", "辰");
        liuChongList.put("巳", "亥");
        liuChongList.put("亥", "巳");
        duanGua.setLiuChong(liuChongList);
        System.out.println("liuChongList=== "+liuChongList);
        System.out.println("duanGua.getLiuChong()=== "+duanGua.getLiuChong());

        //丑子六合土，寅亥六合木，卯戌六合火，辰酉六合金，巳申六合水 午未六合日月
        liuHeList.put("子", "丑");
        liuHeList.put("丑", "子");
        liuHeList.put("寅", "亥");
        liuHeList.put("亥", "寅");
        liuHeList.put("卯", "戌");
        liuHeList.put("戌", "卯");
        liuHeList.put("辰", "酉");
        liuHeList.put("酉", "辰");
        liuHeList.put("巳", "申");
        liuHeList.put("申", "巳");
        liuHeList.put("午", "未");
        liuHeList.put("未", "午");
        duanGua.setLiuHe(liuHeList);


        wuXingMap = new HashMap<>();
        wuXingMap.put("甲", "木");
        wuXingMap.put("乙", "木");
        wuXingMap.put("丙", "火");
        wuXingMap.put("丁", "火");
        wuXingMap.put("戊", "土");
        wuXingMap.put("己", "土");
        wuXingMap.put("庚", "金");
        wuXingMap.put("辛", "金");
        wuXingMap.put("壬", "水");
        wuXingMap.put("癸", "水");

        wuXingList = new ArrayList<>();
        wuXingList.add("木");
        wuXingList.add("火");
        wuXingList.add("金");
        wuXingList.add("水");
        wuXingList.add("土");

        changShengList = new ArrayList<>();
        changShengList.add("长生");
        changShengList.add("沐浴");
        changShengList.add("冠带");
        changShengList.add("临官");
        changShengList.add("帝旺");
        changShengList.add("衰");
        changShengList.add("病");
        changShengList.add("死");
        changShengList.add("墓");
        changShengList.add("绝");
        changShengList.add("胎");
        changShengList.add("养");

        yueDiZhiList = new ArrayList<>();
        yueDiZhiList.add("寅");
        yueDiZhiList.add("卯");
        yueDiZhiList.add("辰");
        yueDiZhiList.add("巳");
        yueDiZhiList.add("午");
        yueDiZhiList.add("未");
        yueDiZhiList.add("申");
        yueDiZhiList.add("酉");
        yueDiZhiList.add("戌");
        yueDiZhiList.add("亥");
        yueDiZhiList.add("子");
        yueDiZhiList.add("丑");

        diWangMap = new HashMap<>();
        diWangMap.put("木", "卯");
        diWangMap.put("火", "午");
        diWangMap.put("金", "酉");
        diWangMap.put("水", "亥");
        diWangMap.put("土", "辰");//现在还不知道土的帝旺是什么，但是不写会报错

        sanHeList = new ArrayList<>();//0~2 火的三合是 寅午戌   2~5 金的三合是 巳酉丑
        sanHeList.add("寅");//0~2 火的三合是 寅午戌
        sanHeList.add("午");
        sanHeList.add("戌");
        sanHeList.add("巳");//2~5 金的三合是 巳酉丑
        sanHeList.add("酉");
        sanHeList.add("丑");
        sanHeList.add("申"); //水的三合 申	子	辰
        sanHeList.add("子");
        sanHeList.add("辰");
        sanHeList.add("亥"); //木的三合 亥	卯	未
        sanHeList.add("卯");
        sanHeList.add("未");

        tianGanList = new ArrayList<>();
        tianGanList.add("甲");
        tianGanList.add("乙");
        tianGanList.add("丙");
        tianGanList.add("丁");
        tianGanList.add("戊");
        tianGanList.add("己");
        tianGanList.add("艮");
        tianGanList.add("辛");
        tianGanList.add("壬");
        tianGanList.add("癸");


    }

    @Override
    public String toString() {
        return "Bazi{" +
                ", bazi=" + bazi +
                ", yueJianMap=" + yueJianMap +
                ", twelveChangsheng='" + twelveChangsheng + '\'' +
                ", i=" + i +
                ", liuChong=" + liuChong +
                ", wuXingMap=" + wuXingMap +
                ", diWangMap=" + diWangMap +
                ", wuXingList=" + wuXingList +
                ", changShengList=" + changShengList +
                ", yueDiZhiList=" + yueDiZhiList +
                ", sanHeList=" + sanHeList +
                ", tianGanList=" + tianGanList +
                ", xunKong=" + xunKong +
                '}';
    }

    public void clear(){
        luma.clear();
        bazi.clear();
    }
}
