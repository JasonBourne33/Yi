package object;

import java.util.ArrayList;
import java.util.HashMap;

public class BaGuaInit {

    public static BaGua bengGua=new BaGua(); //本卦
    public static BaGua bianGua=new BaGua(); //变卦
    public static BaGua benGongGua=new BaGua(); //本宫卦

    public static BaGua qian=new BaGua();  //乾
    public static BaGua gen =new BaGua();   //艮
    public static BaGua kan =new BaGua();   //坎
    public static BaGua zhen=new BaGua();  //震
    public static BaGua kun =new BaGua();   //坤
    public static BaGua dui =new BaGua();   //兑
    public static BaGua li  =new BaGua();    //离
    public static BaGua xun =new BaGua();   //巽
    public static ArrayList<BaGua> baGuaList=new ArrayList<>(); //用的时候拿baGuaList

    public static ArrayList<BaGua> getBaGuaList() {
        return baGuaList;
    }

    public static ArrayList<Boolean> qianL = new ArrayList<>();   //乾 存爻的阴阳
    public static ArrayList<Boolean> genL = new ArrayList<>();     //艮
    public static ArrayList<Boolean> kanL = new ArrayList<>();     //坎
    public static ArrayList<Boolean> zhenL = new ArrayList<>();   //震
    public static ArrayList<Boolean> kunL = new ArrayList<>();     //坤
    public static ArrayList<Boolean> duiL = new ArrayList<>();     //兑
    public static ArrayList<Boolean> liL = new ArrayList<>();       //离
    public static ArrayList<Boolean> xunL = new ArrayList<>();     //巽



    public static ArrayList<String> qianGZ=new ArrayList<>();  //乾 存爻的干支
    public static ArrayList<String> genGZ=new ArrayList<>();   //艮
    public static ArrayList<String> kanGZ=new ArrayList<>();   //坎
    public static ArrayList<String> zhenGZ=new ArrayList<>();  //震
    public static ArrayList<String> kunGZ=new ArrayList<>();   //坤
    public static ArrayList<String> duiGZ=new ArrayList<>();   //兑
    public static ArrayList<String> liGZ=new ArrayList<>();    //离
    public static ArrayList<String> xunGZ=new ArrayList<>();   //巽

    public static HashMap<String,String> baguaWuxingMap=new HashMap<>(); //八卦五行 （离卦，属火）
    public static HashMap<String,String> dizhiWuxingMap=new HashMap<>(); //地支五行 （寅，属木）
    public static HashMap<String,String> wuxingSheng=new HashMap<>(); //五行的生化
    public static HashMap<String,String> wuxingKe=new HashMap<>(); //五行的克制

    public static HashMap<String,String> mu=new HashMap<>(); //木 和其他五行的关系（父母，兄弟，子孙，官鬼）
    public static HashMap<String,String> huo=new HashMap<>(); //火
    public static HashMap<String,String> jin=new HashMap<>(); //金
    public static HashMap<String,String> shui=new HashMap<>(); //水
    public static HashMap<String,String> tu=new HashMap<>(); //土


    public static HashMap<String,String> jiShen=new HashMap<>();//根据用神找忌神
    public static HashMap<String,String> yuanShen=new HashMap<>();//根据用神找元神
    public static HashMap<String,String> chouShen=new HashMap<>();//根据用神找仇神



    public static ArrayList<ArrayList<Boolean>> baGong=new ArrayList<>();    //八宫

    public static ArrayList<ArrayList<Boolean>> getBaGong() {
        return baGong;
    }


    /***
     * 单例
     */
    private static volatile BaGuaInit instance = null;

    private BaGuaInit() {
    }

    public static BaGuaInit getInstance() {
        if (instance == null) {
            synchronized (BaGuaInit.class) {
                if (instance == null) {
                    instance = new BaGuaInit();
                }
            }
        }
        return instance;
    }



    private void initJishen() {













        //父母克子孙，子孙克官鬼，官鬼克兄弟，兄弟克妻财，妻财克父母
        jiShen.put("子孙","父母"); //子孙为用神，父母为忌神  父母克子孙
        jiShen.put("父母","妻财");
        jiShen.put("妻财","兄弟");
        jiShen.put("兄弟","官鬼");
        jiShen.put("官鬼","子孙");

        //父母生兄弟，兄弟生子孙，子孙生妻财，妻财生官鬼，官鬼神父母
        yuanShen.put("子孙","兄弟");//子孙为用神，元神为兄弟，兄弟生子孙
        yuanShen.put("父母","官鬼");
        yuanShen.put("妻财","子孙");
        yuanShen.put("兄弟","父母");
        yuanShen.put("官鬼","妻财");

        //仇神克元神，生继神
        chouShen.put("子孙","官鬼"); //
        chouShen.put("父母","子孙");
        chouShen.put("妻财","父母"); //父母克子孙，生兄弟
        chouShen.put("兄弟","妻财");
        chouShen.put("官鬼","兄弟");

    }

    public void initHexagram() {

        initYao(); //八卦爻的阴阳

        initName(); //乾艮坎震

        initGanZhi(); //八卦爻的干支

        initWuxing(); //八卦的五行 和 地支的五行 五行和六亲的生克

        initJishen();//根据用神找忌神，元神，仇神

        Bazi.getInstance().initBaziBase();

        baGuaList.add(qian);
        baGuaList.add(gen );
        baGuaList.add(kan );
        baGuaList.add(zhen);
        baGuaList.add(kun );
        baGuaList.add(dui );
        baGuaList.add(li  );
        baGuaList.add(xun );

    }

    private void initWuxing() {
        baguaWuxingMap.put("坎","水");
        baguaWuxingMap.put("艮","土");
        baguaWuxingMap.put("震","木");
        baguaWuxingMap.put("巽","木");
        baguaWuxingMap.put("离","火");
        baguaWuxingMap.put("坤","土");
        baguaWuxingMap.put("兑","金");
        baguaWuxingMap.put("乾","金");

        dizhiWuxingMap.put("子","水");
        dizhiWuxingMap.put("丑","土");
        dizhiWuxingMap.put("寅","木");
        dizhiWuxingMap.put("卯","木");
        dizhiWuxingMap.put("辰","土");
        dizhiWuxingMap.put("巳","火");
        dizhiWuxingMap.put("午","火");
        dizhiWuxingMap.put("未","土");
        dizhiWuxingMap.put("申","金");
        dizhiWuxingMap.put("酉","金");
        dizhiWuxingMap.put("戌","土");
        dizhiWuxingMap.put("亥","水");

        wuxingSheng.put("金","水");
        wuxingSheng.put("水","木");
        wuxingSheng.put("木","火");
        wuxingSheng.put("火","土");
        wuxingSheng.put("土","金");

        wuxingKe.put("金","木");
        wuxingKe.put("木","土");
        wuxingKe.put("土","水");
        wuxingKe.put("水","火");
        wuxingKe.put("火","金");

        mu.put("水","父母");
        mu.put("火","子孙");
        mu.put("金","官鬼");
        mu.put("土","妻财");
        mu.put("木","兄弟");

        huo.put("木","父母");
        huo.put("土","子孙");
        huo.put("水","官鬼");
        huo.put("金","妻财");
        huo.put("火","兄弟");

        jin.put("土","父母");
        jin.put("水","子孙");
        jin.put("火","官鬼");
        jin.put("木","妻财");
        jin.put("金","兄弟");

        shui.put("金","父母");
        shui.put("木","子孙");
        shui.put("土","官鬼");
        shui.put("火","妻财");
        shui.put("水","兄弟");

        tu.put("火","父母");
        tu.put("金","子孙");
        tu.put("木","官鬼");
        tu.put("水","妻财");
        tu.put("土","兄弟");






    }


    //爻的干支
    private void initGanZhi() {
        qianGZ.add("甲子");
        qianGZ.add("甲寅");
        qianGZ.add("甲辰");
        qianGZ.add("壬午");
        qianGZ.add("壬申");
        qianGZ.add("壬戌");

        kunGZ.add("乙未");
        kunGZ.add("乙巳");
        kunGZ.add("乙卯");
        kunGZ.add("癸丑");
        kunGZ.add("癸亥");
        kunGZ.add("癸酉");

        zhenGZ.add("庚子");
        zhenGZ.add("庚寅");
        zhenGZ.add("庚辰");
        zhenGZ.add("庚午");
        zhenGZ.add("庚申");
        zhenGZ.add("庚戌");

        kanGZ.add("戊寅");
        kanGZ.add("戊辰");
        kanGZ.add("戊午");
        kanGZ.add("戊申");
        kanGZ.add("戊戌");
        kanGZ.add("戊子");

        genGZ.add("丙辰");
        genGZ.add("丙午");
        genGZ.add("丙申");
        genGZ.add("丙戌");
        genGZ.add("丙子");
        genGZ.add("丙寅");

        xunGZ.add("辛丑");
        xunGZ.add("辛亥");
        xunGZ.add("辛酉");
        xunGZ.add("辛未");
        xunGZ.add("辛巳");
        xunGZ.add("辛卯");

        liGZ.add("己卯");
        liGZ.add("己丑");
        liGZ.add("己亥");
        liGZ.add("己酉");
        liGZ.add("己未");
        liGZ.add("己巳");

        duiGZ.add("丁巳");
        duiGZ.add("丁卯");
        duiGZ.add("丁丑");
        duiGZ.add("丁亥");
        duiGZ.add("丁酉");
        duiGZ.add("丁未");


        qian.setGanZhi(qianGZ);
        gen .setGanZhi(genGZ );
        kan .setGanZhi(kanGZ );
        zhen.setGanZhi(zhenGZ);
        kun .setGanZhi(kunGZ );
        dui .setGanZhi(duiGZ );
        li  .setGanZhi(liGZ  );
        xun .setGanZhi(xunGZ );



    }

    //乾艮坎震
    private void initName() {
        qian.setName("乾");
        gen .setName("艮");
        kan .setName("坎");
        zhen.setName("震");
        kun .setName("坤");
        dui .setName("兑");
        li  .setName("离");
        xun .setName("巽");

    }

    //爻的阴阳
    private void initYao() {

        qianL.add(true);
        qianL.add(true);
        qianL.add(true);
        genL.add(false);
        genL.add(false);
        genL.add(true);
        kanL.add(false);
        kanL.add(true);
        kanL.add(false);
        zhenL.add(true);
        zhenL.add(false);
        zhenL.add(false);

        kunL.add(false);
        kunL.add(false);
        kunL.add(false);
        duiL.add(true);
        duiL.add(true);
        duiL.add(false);
        liL.add(true);
        liL.add(false);
        liL.add(true);
        xunL.add(false); //巽下断，从下爻往上 add
        xunL.add(true);
        xunL.add(true);


        baGong.add(qianL);
        baGong.add(genL);
        baGong.add(kanL);
        baGong.add(zhenL);
        baGong.add(kunL);
        baGong.add(duiL);
        baGong.add(liL);
        baGong.add(xunL);

        qian.setYao(qianL);
        gen.setYao(genL );
        kan.setYao(kanL );
        zhen.setYao(zhenL);
        kun.setYao(kunL );
        dui.setYao(duiL );
        li.setYao(liL  );
        xun.setYao(xunL );

    }



    public static BaGua getQian() {
        return qian;
    }

    public static void setQian(BaGua qian) {
        BaGuaInit.qian = qian;
    }

    public static BaGua getGen() {
        return gen;
    }

    public static void setGen(BaGua gen) {
        BaGuaInit.gen = gen;
    }

    public static BaGua getKan() {
        return kan;
    }

    public static void setKan(BaGua kan) {
        BaGuaInit.kan = kan;
    }

    public static BaGua getZhen() {
        return zhen;
    }

    public static void setZhen(BaGua zhen) {
        BaGuaInit.zhen = zhen;
    }

    public static BaGua getKun() {
        return kun;
    }

    public static void setKun(BaGua kun) {
        BaGuaInit.kun = kun;
    }

    public static BaGua getDui() {
        return dui;
    }

    public static void setDui(BaGua dui) {
        BaGuaInit.dui = dui;
    }

    public static BaGua getLi() {
        return li;
    }

    public static void setLi(BaGua li) {
        BaGuaInit.li = li;
    }

    public static BaGua getXun() {
        return xun;
    }

    public static void setXun(BaGua xun) {
        BaGuaInit.xun = xun;
    }

    public static HashMap<String, String> getBaguaWuxingMap() {
        return baguaWuxingMap;
    }

    public static void setBaguaWuxingMap(HashMap<String, String> baguaWuxingMap) {
        BaGuaInit.baguaWuxingMap = baguaWuxingMap;
    }

    public static HashMap<String, String> getDizhiWuxingMap() {
        return dizhiWuxingMap;
    }

    public static void setDizhiWuxingMap(HashMap<String, String> dizhiWuxingMap) {
        BaGuaInit.dizhiWuxingMap = dizhiWuxingMap;
    }

    public static BaGua getBengGua() {
        return bengGua;
    }

    public static void setBengGua(BaGua bengGua) {
        BaGuaInit.bengGua = bengGua;
    }

    public static HashMap<String, String> getWuxingSheng() {
        return wuxingSheng;
    }

    public static void setWuxingSheng(HashMap<String, String> wuxingSheng) {
        BaGuaInit.wuxingSheng = wuxingSheng;
    }

    public static HashMap<String, String> getWuxingKe() {
        return wuxingKe;
    }

    public static void setWuxingKe(HashMap<String, String> wuxingKe) {
        BaGuaInit.wuxingKe = wuxingKe;
    }

    public static HashMap<String, String> getMu() {
        return mu;
    }

    public static HashMap<String, String> getHuo() {
        return huo;
    }

    public static HashMap<String, String> getJin() {
        return jin;
    }

    public static HashMap<String, String> getShui() {
        return shui;
    }

    public static HashMap<String, String> getTu() {
        return tu;
    }

    public static BaGua getBianGua() {
        return bianGua;
    }

    public static void setBianGua(BaGua bianGua) {
        BaGuaInit.bianGua = bianGua;
    }

    public static BaGua getBenGongGua() {
        return benGongGua;
    }

    public static void setBenGongGua(BaGua benGongGua) {
        BaGuaInit.benGongGua = benGongGua;
    }

    public static HashMap<String, String> getJiShen() {
        return jiShen;
    }

    public static void setJiShen(HashMap<String, String> jiShen) {
        BaGuaInit.jiShen = jiShen;
    }

    public static HashMap<String, String> getYuanShen() {
        return yuanShen;
    }

    public static HashMap<String, String> getChouShen() {
        return chouShen;
    }
}
