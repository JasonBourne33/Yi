package object;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    private String riGan;
    private String riZhi;
    private String riPo;
    private ArrayList<String> bazi = new ArrayList<>(); //年月日时的 干支 （年干，年支，月干，月支，日干，日支，时干，时支）
    private HashMap<String,String> yueJianMap = new HashMap<>(); //月建，月破，日支，日破
    private HashMap<String,String> twelveChangsheng = new HashMap<>(); //长生：地支
    private HashMap<String,String> liuChong;//六冲
    private HashMap<String,String> huaJue=new HashMap<>();//化绝
    private HashMap<String,String> sanHeMap=new HashMap<>();//动变爻的 三合
    private ArrayList<String> luma = new ArrayList<>(); //禄，马，生，旺，墓

    private LinkedHashMap<String,String> xingSha=new LinkedHashMap<>(); //13星煞
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






    public LinkedHashMap<String, String> getXingSha() {
        return xingSha;
    }

    public void setXingSha(LinkedHashMap<String, String> xingSha) {
        this.xingSha = xingSha;
    }

    public HashMap<String, String> getSanHeMap() {
        return sanHeMap;
    }

    public void setSanHeMap(HashMap<String, String> sanHeMap) {
        this.sanHeMap = sanHeMap;
    }

    public HashMap<String, String> getHuaJue() {
        return huaJue;
    }

    public void setHuaJue(HashMap<String, String> huaJue) {
        this.huaJue = huaJue;
    }

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

    public ArrayList<LinkedHashMap> getLiuShen() {
        return liuShen;
    }

    //六神    x.get(1).get("甲")
    private ArrayList<LinkedHashMap> liuShen=new ArrayList<>(); //把下面的六爻六神包起来
    private LinkedHashMap<String,String> liuShenYao6=new LinkedHashMap<>();
    private LinkedHashMap<String,String> liuShenYao5=new LinkedHashMap<>();
    private LinkedHashMap<String,String> liuShenYao4=new LinkedHashMap<>();
    private LinkedHashMap<String,String> liuShenYao3=new LinkedHashMap<>();
    private LinkedHashMap<String,String> liuShenYao2=new LinkedHashMap<>();
    private LinkedHashMap<String,String> liuShenYao1=new LinkedHashMap<>();
//           甲乙日 丙丁日 戊日日 己日日 庚辛日 壬癸日
//———————————————————————————
//    第 6 爻 玄武   青龙   朱雀   勾陈   螣蛇   白虎
//    第 5 爻 白虎   玄武   青龙   朱雀   勾陈   螣蛇
//    第 4 爻 螣蛇   白虎   玄武   青龙   朱雀   勾陈
//    第 3 爻 勾陈   螣蛇   白虎   玄武   青龙   朱雀
//    第 2 爻 朱雀   勾陈   螣蛇   白虎   玄武   青龙
//    第 1 爻 青龙   朱雀   勾陈   螣蛇   白虎   玄武
//    x.get(1).get("甲")
    public void initLiuShen(){ //横着存
        liuShenYao1.put("甲","青龙");
        liuShenYao1.put("乙","青龙");
        liuShenYao1.put("丙","朱雀");
        liuShenYao1.put("丁","朱雀");
        liuShenYao1.put("戊","勾陈");
        liuShenYao1.put("己","螣蛇");
        liuShenYao1.put("庚","白虎");
        liuShenYao1.put("辛","白虎");
        liuShenYao1.put("壬","玄武");
        liuShenYao1.put("癸","玄武");

        liuShenYao2.put("甲","朱雀");
        liuShenYao2.put("乙","朱雀");
        liuShenYao2.put("丙","勾陈");
        liuShenYao2.put("丁","勾陈");
        liuShenYao2.put("戊","螣蛇");
        liuShenYao2.put("己","白虎");
        liuShenYao2.put("庚","玄武");
        liuShenYao2.put("辛","玄武");
        liuShenYao2.put("壬","青龙");
        liuShenYao2.put("癸","青龙");

        liuShenYao3.put("甲","勾陈");
        liuShenYao3.put("乙","勾陈");
        liuShenYao3.put("丙","螣蛇");
        liuShenYao3.put("丁","螣蛇");
        liuShenYao3.put("戊","白虎");
        liuShenYao3.put("己","玄武");
        liuShenYao3.put("庚","青龙");
        liuShenYao3.put("辛","青龙");
        liuShenYao3.put("壬","朱雀");
        liuShenYao3.put("癸","朱雀");

        liuShenYao4.put("甲","螣蛇");
        liuShenYao4.put("乙","螣蛇");
        liuShenYao4.put("丙","白虎");
        liuShenYao4.put("丁","白虎");
        liuShenYao4.put("戊","玄武");
        liuShenYao4.put("己","青龙");
        liuShenYao4.put("庚","朱雀");
        liuShenYao4.put("辛","朱雀");
        liuShenYao4.put("壬","勾陈");
        liuShenYao4.put("癸","勾陈");

        liuShenYao5.put("甲","白虎");
        liuShenYao5.put("乙","白虎");
        liuShenYao5.put("丙","玄武");
        liuShenYao5.put("丁","玄武");
        liuShenYao5.put("戊","青龙");
        liuShenYao5.put("己","朱雀");
        liuShenYao5.put("庚","勾陈");
        liuShenYao5.put("辛","勾陈");
        liuShenYao5.put("壬","螣蛇");
        liuShenYao5.put("癸","螣蛇");

        liuShenYao6.put("甲","玄武");
        liuShenYao6.put("乙","玄武");
        liuShenYao6.put("丙","青龙");
        liuShenYao6.put("丁","青龙");
        liuShenYao6.put("戊","朱雀");
        liuShenYao6.put("己","勾陈");
        liuShenYao6.put("庚","螣蛇");
        liuShenYao6.put("辛","螣蛇");
        liuShenYao6.put("壬","白虎");
        liuShenYao6.put("癸","白虎");

        liuShen.add(liuShenYao1);
        liuShen.add(liuShenYao2);
        liuShen.add(liuShenYao3);
        liuShen.add(liuShenYao4);
        liuShen.add(liuShenYao5);
        liuShen.add(liuShenYao6);
    }

    /**
     * 每个实例都调用这个方法
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

        BaGuaInit.getBengGua().setBazi(bazi);
        xunKong=xunKongList.get(d);



//        System.out.println("月破=== " + yuePo);
//        System.out.println("日破=== " + riPo);


        riGan = d.substring(0, 1);
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


        BaGuaInit.getBengGua().setYueJian(yueJianMap);
        BaGuaInit.getBengGua().setLuma(luma);

        //星煞
        initXingSha();

    }


    //13星煞
    private HashMap<String,String> guiRen=new HashMap<String,String>();
    private HashMap<String,String> luShen=new HashMap<String,String>();
    private HashMap<String,String> yangRen=new HashMap<String,String>();
    private HashMap<String,String> wengChang=new HashMap<String,String>();
    private HashMap<String,String> yiMa=new HashMap<String,String>();
    private HashMap<String,String> taoHua=new HashMap<String,String>();
    private HashMap<String,String> jiangXing=new HashMap<String,String>();
    private HashMap<String,String> jieSha=new HashMap<String,String>();
    private HashMap<String,String> huaGai=new HashMap<String,String>();
    private HashMap<String,String> mouXing=new HashMap<String,String>();
    private String tianYi;
    private HashMap<String,String> tianXi=new HashMap<String,String>();
    private HashMap<String,String> zaiSha=new HashMap<String,String>();

    private HashMap<String, String> wuXingMap;  //天干的五行
    private HashMap<String, String> diWangMap;  //五行的帝旺
    private ArrayList<String> wuXingList;   //五行
    private ArrayList<String> sanHeList; //按照三合的来算
    private ArrayList<String> changShengList;   //十二长生
    private ArrayList<String> yueDiZhiList; //月地支 寅卯辰
    private ArrayList<String> tianGanList; //按照三合的来算

    HashMap<String, String> jinShenList = new HashMap<>(); //进神
    HashMap<String, String> tuiShenList = new HashMap<>(); //退神
    HashMap<String, String> liuHeList = new HashMap<>(); //六合
    HashMap<String, String> liuChongList = new HashMap<>(); //六冲
    private DuanGua duanGua=DuanGua.getInstance();
    public void initBaziBase() {

//        长生 	帝旺	墓
//        火	寅	午	戌		火的三合是 寅午戌
//        金	巳	酉	丑		金的三合是 巳酉丑
//        水	申	子	辰
//        木	亥	卯	未
        sanHeMap.put("火","寅午戌");
        sanHeMap.put("金","巳酉丑");
        sanHeMap.put("水","申子辰");
        sanHeMap.put("木","亥卯 未");

        huaJue.put("子水","巳火");
        huaJue.put("酉金","寅木");

        //进神退神
        initJinTuiShen();

        //六十甲子找旬空
        initXunKong();

        //六冲六合
        initLiuChongHe();


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

        //驿马三合顶头冲用的
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




       //六神
       initLiuShen();

    }

    private void initLiuChongHe() {
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
    }

    private void initJinTuiShen() {
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
    }

    private void initXunKong() {
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
    }

    private void initXingSha() {
        //逢甲与戊日贵人星在丑，未；逢乙与己日贵人星在子，申；逢丙与丁日贵人星在亥，酉；逢庚与辛日贵人星在午，寅；逢壬与癸日贵人星在卯，巳。
        guiRen.put("甲","丑,未");
        guiRen.put("戊","丑,未");
        guiRen.put("乙","子,申");
        guiRen.put("己","子,申");
        guiRen.put("丙","亥,酉");
        guiRen.put("丁","亥,酉");
        guiRen.put("庚","午,寅");
        guiRen.put("辛","午,寅");
        guiRen.put("壬","卯,巳");
        guiRen.put("癸","卯,巳");

        //逢甲日禄在寅，逢乙日禄在卯，逢丙日与戊日禄在巳，逢丁日与己日禄 在午，逢庚日禄在申，逢辛日禄在酉，逢壬日禄在亥，逢癸日禄在子。
        luShen.put("甲","寅");
        luShen.put("乙","卯");
        luShen.put("丙","巳");
        luShen.put("丁","午");
        luShen.put("戊","巳");
        luShen.put("己","午");
        luShen.put("庚","申");
        luShen.put("辛","酉");
        luShen.put("壬","亥");
        luShen.put("癸","子");
        //逢甲日刃在卯，逢乙日刃在寅，逢丙日与戊日刃在午，逢丁日与己日刃在巳，逢庚日刃在酉，逢辛日刃在申，逢壬日刃在子，逢癸日刃在亥。
        yangRen.put("甲","卯");
        yangRen.put("乙","寅");
        yangRen.put("丙","午");
        yangRen.put("丁","巳");
        yangRen.put("戊","午");
        yangRen.put("己","巳");
        yangRen.put("庚","酉");
        yangRen.put("辛","申");
        yangRen.put("壬","子");
        yangRen.put("癸","亥");
        //逢甲日文昌在巳，逢乙日文昌在午，逢丙日与戊日文昌在申，逢丁日与己日文昌在酉，逢庚日文昌在亥，逢辛日文昌在子，逢壬日文昌在寅，逢癸日文昌在卯。
        wengChang.put("甲","巳");
        wengChang.put("乙","午");
        wengChang.put("丙","申");
        wengChang.put("丁","酉");
        wengChang.put("戊","申");
        wengChang.put("己","酉");
        wengChang.put("庚","亥");
        wengChang.put("辛","子");
        wengChang.put("壬","寅");
        wengChang.put("癸","卯");
        //逢申子辰日马星在寅，逢巳酉丑日马星在亥，逢寅午戌日马星在申，逢亥卯未日马星在巳。
        yiMa.put("子","寅");
        yiMa.put("丑","亥");
        yiMa.put("寅","申");
        yiMa.put("卯","巳");
        yiMa.put("辰","寅");
        yiMa.put("巳","亥");
        yiMa.put("午","申");
        yiMa.put("未","巳");
        yiMa.put("申","寅");
        yiMa.put("酉","亥");
        yiMa.put("戌","申");
        yiMa.put("亥","巳");
        //逢申子辰日桃花在酉，逢巳酉丑日桃花在午，逢寅午戌日桃花在卯，逢亥卯未日桃花在子。
        taoHua.put("子","酉");
        taoHua.put("丑","午");
        taoHua.put("寅","卯");
        taoHua.put("卯","子");
        taoHua.put("辰","酉");
        taoHua.put("巳","午");
        taoHua.put("午","卯");
        taoHua.put("未","子");
        taoHua.put("申","酉");
        taoHua.put("酉","午");
        taoHua.put("戌","卯");
        taoHua.put("亥","子");
        //逢申子辰日将星在子，逢巳酉丑日将星在酉，逢寅午戌日将星在午，逢亥卯未日将星在卯。
        jiangXing.put("子","子");
        jiangXing.put("丑","酉");
        jiangXing.put("寅","午");
        jiangXing.put("卯","卯");
        jiangXing.put("辰","子");
        jiangXing.put("巳","酉");
        jiangXing.put("午","午");
        jiangXing.put("未","卯");
        jiangXing.put("申","子");
        jiangXing.put("酉","酉");
        jiangXing.put("戌","午");
        jiangXing.put("亥","卯");
        //逢申子辰日劫煞在巳，逢巳酉丑日劫煞在寅，逢寅午戌日劫煞在亥，逢亥卯未日劫煞在申。
        jieSha.put("子","巳");
        jieSha.put("丑","寅");
        jieSha.put("寅","亥");
        jieSha.put("卯","申");
        jieSha.put("辰","巳");
        jieSha.put("巳","寅");
        jieSha.put("午","亥");
        jieSha.put("未","申");
        jieSha.put("申","巳");
        jieSha.put("酉","寅");
        jieSha.put("戌","亥");
        jieSha.put("亥","申");
        //逢申子辰日华盖在辰，逢巳酉丑日华盖在丑，逢寅午戌日华盖在戌，逢亥卯未日华盖在未。
        huaGai.put("子","辰");
        huaGai.put("丑","丑");
        huaGai.put("寅","戌");
        huaGai.put("卯","未");
        huaGai.put("辰","辰");
        huaGai.put("巳","丑");
        huaGai.put("午","戌");
        huaGai.put("未","未");
        huaGai.put("申","辰");
        huaGai.put("酉","丑");
        huaGai.put("戌","戌");
        huaGai.put("亥","未");
        //逢申子辰日谋星在戌，逢巳酉丑日谋 星在未，逢寅午戌日谋星在辰，逢亥卯未日谋星在丑。
        mouXing.put("子","戌");
        mouXing.put("丑","未");
        mouXing.put("寅","辰");
        mouXing.put("卯","丑");
        mouXing.put("辰","戌");
        mouXing.put("巳","未");
        mouXing.put("午","辰");
        mouXing.put("未","丑");
        mouXing.put("申","戌");
        mouXing.put("酉","未");
        mouXing.put("戌","辰");
        mouXing.put("亥","丑");
        //春天占卜天喜在 戌，夏天占卜天喜在丑，秋天占卜天喜在辰，冬天占卜天喜在未。
        tianXi.put("子","未");
        tianXi.put("丑","未");
        tianXi.put("寅","戌");
        tianXi.put("卯","戌");
        tianXi.put("辰","戌");
        tianXi.put("巳","丑");
        tianXi.put("午","丑");
        tianXi.put("未","丑");
        tianXi.put("申","辰");
        tianXi.put("酉","辰");
        tianXi.put("戌","辰");
        tianXi.put("亥","未");
        //逢申子辰日灾煞在午，逢巳酉丑日灾煞在卯，逢寅午戌日灾煞在子，逢亥卯未日灾煞在酉。
        zaiSha.put("子","午");
        zaiSha.put("丑","卯");
        zaiSha.put("寅","子");
        zaiSha.put("卯","酉");
        zaiSha.put("辰","午");
        zaiSha.put("巳","卯");
        zaiSha.put("午","子");
        zaiSha.put("未","酉");
        zaiSha.put("申","午");
        zaiSha.put("酉","卯");
        zaiSha.put("戌","子");
        zaiSha.put("亥","酉");

        //顺序 驿马、桃花、禄神、羊刃、贵人、谋星、文昌、将星、天医、天喜、灾煞、劫煞、华盖
        xingSha.put("驿马",yiMa.get(riZhi));
        xingSha.put("桃花",taoHua.get(riZhi));
        xingSha.put("禄神",luShen.get(riGan));
        xingSha.put("羊刃",yangRen.get(riGan));

        xingSha.put("谋星",mouXing.get(riZhi));
        xingSha.put("文昌",wengChang.get(riGan));
        xingSha.put("将星",jiangXing.get(riZhi));
        int yueJianPos = yueDiZhiList.indexOf(yueJian); //月建的位置
        tianYi=yueDiZhiList.get((yueJianPos - 1) < 0 ? 12 - Math.abs(yueJianPos - 1) : yueJianPos - 1); //天医
        xingSha.put("天医",tianYi);
        xingSha.put("天喜",tianXi.get(yueJian));
        xingSha.put("灾煞",zaiSha.get(riZhi));
        xingSha.put("劫煞",jieSha.get(riZhi));
        xingSha.put("华盖",huaGai.get(riZhi));
        xingSha.put("贵人",guiRen.get(riGan));
    }


    @Override
    public String toString() {
        return "Bazi{" +
                "  xingSha=" + xingSha +
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
