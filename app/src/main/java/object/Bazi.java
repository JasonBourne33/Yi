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

    //岁阴，岁破，月建，月破，日支，日破
    private String suiYin;
    private String suiPo;
    private String yueJian;
    private String yuePo;
    private String riZhi;
    private String riPo;
    private ArrayList<String> bazi = new ArrayList<>(); //年月日时的 干支 （年干，年支，月干，月支，日干，日支，时干，时支）
    private HashMap<String,String> yueJianMap = new HashMap<>(); //月建，月破，日支，日破
    private ArrayList<String> luma = new ArrayList<>(); //禄，马，生，旺，墓
    //禄马生旺墓
    private String lu;
    private String ma;
    private String sheng;
    private String wang;
    private String mu;

    /**
     * @param y 年 干支
     * @param m 月
     * @param d 日
     * @param h 时
     */
    private int i;

    public void initBazi(String y, String m, String d, String h) {
        initBase();

        bazi.add(y); //先放到arrayList，再放到 本卦对象
        bazi.add(m);
        bazi.add(d);
        bazi.add(h);

        String suiYin = y.substring(1, 2);
        String yueJian = m.substring(1, 2);
        String riZhi = d.substring(1, 2);

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





//        System.out.println("月破=== " + yuePo);
//        System.out.println("日破=== " + riPo);

        String riGan = d.substring(0, 1);
//        Tiangan.getInstance().init(riGan);
//        System.out.println(Tiangan.getInstance().getInfo());

        // 禄的代码
        // 丁 阴 火 帝旺是第5位  月地支是 午，寅卯辰巳午
        String ganWuXing = wuXingMap.get(riGan);
        i = tianGanList.indexOf(riGan) + 1; //wuXingList从第0位开始，+1 方便后面判断奇偶
        String diWang = diWangMap.get(ganWuXing); //先获取帝旺
        int diWangInt = yueDiZhiList.indexOf(diWang); //再获取帝旺对应的位置
        //根据帝旺的位置寻找其他的十二长生的月地支
        String linGuan = yueDiZhiList.get(diWangInt - 1); //临冠
        sheng = yueDiZhiList.get((diWangInt - 4) < 0 ? 12 - Math.abs(diWangInt - 4) : diWangInt - 4); //十二长生中的 生
        mu = yueDiZhiList.get((diWangInt + 4) > 11 ? (diWangInt + 4) - 12 : diWangInt + 4); //十二长生中的 墓


        if ((i & 1) == 0) { //偶数，阴天干，在帝旺上
            lu = diWang;
        } else { //阳天干,在临冠上
            lu = linGuan;
        }


        //马 的代码  驿马三合顶头冲
        int sanHePos = sanHeList.indexOf(riZhi);
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

    private HashMap<String, String> liuChong; //存六冲关系
    private HashMap<String, String> wuXingMap;  //天干的五行
    private HashMap<String, String> diWangMap;  //五行的帝旺
    private ArrayList<String> wuXingList;   //五行
    private ArrayList<String> changShengList;   //十二长生
    private ArrayList<String> yueDiZhiList; //月地支 寅卯辰
    private ArrayList<String> sanHeList; //按照三合的来算
    private ArrayList<String> tianGanList; //按照三合的来算

    private void initBase() {

        liuChong = new HashMap<>(); //六冲
        liuChong.put("子", "午");
        liuChong.put("午", "子");
        liuChong.put("丑", "未");
        liuChong.put("未", "丑");
        liuChong.put("寅", "申");
        liuChong.put("申", "寅");
        liuChong.put("卯", "酉");
        liuChong.put("酉", "卯");
        liuChong.put("辰", "戌");
        liuChong.put("戌", "辰");
        liuChong.put("巳", "亥");
        liuChong.put("亥", "巳");

        wuXingMap = new HashMap<>();
        wuXingMap.put("甲", "木");
        wuXingMap.put("乙", "木");
        wuXingMap.put("丙", "火");
        wuXingMap.put("丁", "火");
        wuXingMap.put("戊", "土");
        wuXingMap.put("己", "土");
        wuXingMap.put("艮", "金");
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
        sanHeList.add("寅");
        sanHeList.add("午");
        sanHeList.add("戌");
        sanHeList.add("巳");
        sanHeList.add("酉");
        sanHeList.add("丑");
        sanHeList.add("申");
        sanHeList.add("子");
        sanHeList.add("辰");
        sanHeList.add("亥");
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
}
