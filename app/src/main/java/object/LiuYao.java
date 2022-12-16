package object;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class LiuYao {

    private static volatile LiuYao instance = null;

    private LiuYao() {
    }

    public static LiuYao getInstance() {
        if (instance == null) {
            synchronized (LiuYao.class) {
                if (instance == null) {
                    instance = new LiuYao();
                }
            }
        }
        return instance;
    }

    private int y1; //传入的爻
    private int y2;
    private int y3;
    private int y4;
    private int y5;
    private int y6;
    private String yongShen;

    private int by1; //b变卦 y爻 1
    private int by2;
    private int by3;
    private int by4;
    private int by5;
    private int by6;

    //存变卦阴阳的List
    private ArrayList<Boolean> bianGuaList;

    /***
     * 0少阴 1老阴 2少阳 3老阳
     * @param y1
     * @param y2
     * @param y3
     * @param y4
     * @param y5
     * @param y6
     */
    private ArrayList<Boolean> yaoList = new ArrayList<>(); //存 六爻阴阳 的List

    /***
     * 给卦里的爻纳干支
     */
    private ArrayList<Boolean> xiaJinGua; //下面的经卦
    private ArrayList<Boolean> shangJinGua;
    private ArrayList<String> xiaGuaGanzhi; //下经卦干支
    private ArrayList<String> shangGuaGanzhi;
    ArrayList<String> name; //经卦名


    /**
     * 初始化 卦干支，根据 下上经卦 判断 64卦名
     *
     * @param yaoList 六爻阴阳true，false
     * @param bagua   八卦对象
     */
    private void initGuaGanZhi(ArrayList<Boolean> yaoList, BaGua bagua) {

//        for (int i = 0; i < yaoList.size(); i++) {
//            System.out.println(i+1 +"initGuaGanZhi 爻是=== "+yaoList.get(i));
//        }

        //参考ArryList的 equels 方法写一个 JinGua的equals

        xiaJinGua = new ArrayList<>();
        xiaJinGua.add(yaoList.get(0));
        xiaJinGua.add(yaoList.get(1));
        xiaJinGua.add(yaoList.get(2));

        shangJinGua = new ArrayList<>();
        shangJinGua.add(yaoList.get(3));
        shangJinGua.add(yaoList.get(4));
        shangJinGua.add(yaoList.get(5));

        ArrayList<ArrayList<Boolean>> baGong = BaGuaInit.getBaGong(); //获取八卦

        name = new ArrayList();
//        System.out.println("xiaJinGua=== "+xiaJinGua);


        xiaGuaGanzhi = new ArrayList<>();

        for (int i = 0; i < baGong.size(); i++) {
//            System.out.println("baGong.get(i)=== "+baGong.get(i));
            if (baGong.get(i).equals(xiaJinGua)) {
//                System.out.println("下卦是=== " + baGuaString[i]);
                name.add(baGuaString[i]);
                ArrayList<String> ganZhi = BaGuaInit.getBaGuaList().get(i).getGanZhi();
//                System.out.println("下卦干支是=== " + ganZhi.get(i));

                xiaGuaGanzhi.add(ganZhi.get(0));
                xiaGuaGanzhi.add(ganZhi.get(1));
                xiaGuaGanzhi.add(ganZhi.get(2));
                bagua.setGanZhi(xiaGuaGanzhi); //第一次要用set，因为ArrayList还是空

                break;
            }
        }
        for (int i = 0; i < baGong.size(); i++) { //循环八卦
            if (baGong.get(i).equals(shangJinGua)) {
//                System.out.println("上卦是=== " + baGuaString[i]);
                name.add(baGuaString[i]);
                ArrayList<String> ganZhi = BaGuaInit.getBaGuaList().get(i).getGanZhi();
//                System.out.println("上卦干支是=== "+ganZhi.get(3));
//                System.out.println("上卦干支是=== "+ganZhi.get(4));
//                System.out.println("上卦干支是=== "+ganZhi.get(5));

                shangGuaGanzhi = new ArrayList<>();
                shangGuaGanzhi.add(ganZhi.get(3));
                shangGuaGanzhi.add(ganZhi.get(4));
                shangGuaGanzhi.add(ganZhi.get(5));
                bagua.getGanZhi().addAll(shangGuaGanzhi); //第二次用addAll
//                        (BaGuaInit.getBaGuaList().get(i).getGanZhi().get(3));

                break;
            }
        }

        //以后可以加入上下卦判断64卦的
        bagua.setName(name);

    }


    private BaGua benGua; //本卦
    private BaGua bianGua; //变卦
    private BaGua benGongGua; //本宫卦
    private String benWuxing; // 本宫五行
    private DuanGua duanGua = DuanGua.getInstance();
    private ArrayList<Integer> dongYaoList = new ArrayList<>();//存动爻，就是老阳和老阴的位置 从0开始
    private ArrayList<Integer> liandongList = new ArrayList<>();//存有用动爻和 暗动         从0开始
    private ArrayList<String> sanHeYaoDizhiList = new ArrayList<>();//存有用动爻，暗动 和 变爻 的地支，用来判断三合
    private ArrayList<Integer> yaoNumList = new ArrayList<>();//存少阴 老阴 少阳 老阳
    HashMap<String, String> liuHeList = DuanGua.getInstance().getLiuHe(); //六合
    HashMap<String, String> liuChongList = DuanGua.getInstance().getLiuChong(); //六冲

    public void initLiuYao(int y1, int y2, int y3, int y4, int y5, int y6, String yongShen) {
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.y6 = y6;
        this.yongShen = yongShen;

        initBase(); //根据老阴，老阳获取 阴 阳， 初始化BaGuaInit

        Boolean b6 = yinYangMap.get(y6);//根据老阴，老阳获取 阴 阳
        Boolean b5 = yinYangMap.get(y5);
        Boolean b4 = yinYangMap.get(y4);
        Boolean b3 = yinYangMap.get(y3);
        Boolean b2 = yinYangMap.get(y2);
        Boolean b1 = yinYangMap.get(y1);


        yaoList.add(b1);
        yaoList.add(b2);
        yaoList.add(b3);
        yaoList.add(b4);
        yaoList.add(b5);
        yaoList.add(b6);


        benGua = BaGuaInit.getBengGua();
        bianGua = BaGuaInit.getBianGua();
        benGongGua = BaGuaInit.getBenGongGua();
        benGua.setYao(yaoList); //设置本卦的爻阴阳


        baGongGuaBian(yaoList, benGua); //本卦 八宫式卦变 （游魂，归魂）
        getBianGua();   //变卦（少阴变老阴）
//        baGongGuaBian(bianGuaList, bianGua); //变卦 八宫式卦变 （游魂，归魂）
        benGongGua.setYao(gbList); //本宫的爻阴阳
        baGongGuaBian(yaoList, benGongGua); //本宫卦 八宫式卦变 （游魂，归魂）

        initGuaGanZhi(yaoList, benGua); //给本卦的六爻纳干支
        init64Gua(benGua); //根据上下经卦判断64卦名
        initGuaGanZhi(bianGuaList, bianGua); //给变卦纳干支
        init64Gua(bianGua);
        initGuaGanZhi(gbList, benGongGua); //给本宫卦纳干支

        initWuxing(benGua); //本卦五行 和 每一爻的五行 和对应关系（父母，子孙，兄弟，官鬼）

        initWuxing(bianGua, benWuxing); //变卦五行，以本卦为准
        initWuxing(benGongGua); //本宫卦五行


        duanGua(yongShen);
        System.out.println("断卦对象=== " + duanGua.toString());
        ;
    }




    //断卦
//    private void duanGua(String type) {
//        switch (type) {
//            case "占病":  //占病 子孙为用神，父母为忌神
//                zhanbing();
//                break;
//            case "自占病": //自占病：世爻为用神
//                zhanbing();
//                break;
//        }
//    }

    HashMap<String, String> inputList = new HashMap<>(); //存 输入（比如世爻），用神，忌神


    //断卦
    private void duanGua(String yongShenEle) {
        int j;
        String yongShen = "";
        if (yongShenEle.equals("世爻")) {
            j = BaGuaInit.getBengGua().getShiYao() - 1; //世爻的位置
            yongShen = BaGuaInit.getBengGua().getRelation().get(j);
        } else if (yongShenEle.equals("应爻")) {
            j = BaGuaInit.getBengGua().getYingYao() - 1; //应爻的位置
            yongShen = BaGuaInit.getBengGua().getRelation().get(j);
        } else { //剩下 兄弟，子孙 六亲
            yongShen = yongShenEle;
        }

        String jiShen = BaGuaInit.getJiShen().get(yongShen);
        String yuanShen = BaGuaInit.getYuanShen().get(yongShen);
        String chouShen = BaGuaInit.getChouShen().get(yongShen);
//        System.out.println("传入=== " + yongShenEle + " 用神===" + yongShen + " 忌神=== " + jiShen);
        inputList = new HashMap<>();
        inputList.put("传入", yongShenEle);
        inputList.put("用神", yongShen);
        inputList.put("元神", yuanShen);
        inputList.put("忌神", jiShen);
        inputList.put("仇神", chouShen);

        duanGua.setInput(inputList);

        initDuanBase();//进神，退神初始化

        int yongShenPos = 0; //用神的位置
        int jiShenPos = 0;
        int yuanShenPos = 0;
        int chouShenPos = 0;
        int shiyaoPos = BaGuaInit.getBengGua().getShiYao();
        int yingyaoPos = BaGuaInit.getBengGua().getYingYao();
        boolean yongShenFlag = true; //如果有两个用神（比如两个子孙），获取第一个用神
        boolean jiShenFlag = true;
        boolean yuanShenFlag = true;
        boolean chouShenFlag = true;
        String yongShenGanzhi = ""; //用神的干支
        String jiShenGanzhi; //忌神的干支
        String yuanShenGanzhi; //元神的干支
        String chouShenGanzhi; //仇神的干支
        String yongshenDizhi = ""; //用神的地支
        String bianYongShenDizi = ""; //变爻的用神的地支
        String jishenDizhi = ""; //忌神地支
        String yuanshenDizhi = ""; //元神地支
        String choushenDizhi = ""; //仇神地支
        String yongshenWuxing = "";//用神五行
        String bianYongshenWuxing = "";//变爻的用神的五行
        String jishenWuxing = "";//忌神五行
        String yuanshenWuxing = "";//元神五行
        String choushenWuxing = "";//仇神五行
        //取用神 世应 > 动变	> 特殊 > 动爻
        for (int i = 0; i < benGua.getRelation().size(); i++) { //找出用神和忌神地支
            if (benGua.getRelation().get(i).equals(yongShen)) { //找子孙在哪一爻（子孙为用神）
                yongShenGanzhi = benGua.getGanZhi().get(i);
                yongshenDizhi = yongShenGanzhi.substring(1, 2);
                yongShenPos = i + 1;
                yongshenWuxing = dizhiWuxingMap.get(yongshenDizhi);
                bianYongShenDizi = bianGua.getGanZhi().get(i).substring(1, 2); //变爻用神的地支
                bianYongshenWuxing = dizhiWuxingMap.get(bianYongShenDizi);
//                if(yongShenPos == shiyaoPos ||yongShenPos == yingyaoPos) {//世应都有，有动取动 太细分，现在还没搞定
//                    if(dongYaoList.contains(yongShenPos-1) && yongShenFlag){//有动爻就取动爻
//                        System.out.println("有动爻就取动爻===");
//                        for (int k = 0; k < dongYaoList.size(); k++) { //遍历动爻
//                            if(dongYaoList.get(k)==yongShenPos-1){
//                                duanGua.getYongshenInfo().add(i + 1 + "爻 本卦中找到 用神 " + yongShen
//                                        + "=== " + bianYongShenDizi + bianYongshenWuxing);
//                                duanGua.getYuanshenPos().put("用神", yongShenPos);
//                            }
//                        }
//                        duanGua.getYongshenInfo().add(i + 1 + "爻 本卦中找到 用神 " + yongShen + "=== " + yongshenDizhi + yongshenWuxing);
//                        duanGua.getYuanshenPos().put("用神", yongShenPos);
//                        yongShenFlag = false;
//                    }
                if (yongShenPos == shiyaoPos && yongShenFlag) { //如果用神在世爻
                    //断卦对象录入
                    duanGua.getYongshenInfo().add(i + 1 + "爻 本卦中找到 用神 " + yongShen + "=== " + yongshenDizhi + yongshenWuxing);
                    duanGua.getYuanshenPos().put("用神", yongShenPos);
                    yongShenFlag = false;
                }
                if (yongShenPos == yingyaoPos && yongShenFlag) { //如果用神在应爻
                    duanGua.getYongshenInfo().add(i + 1 + "爻 本卦中找到 用神 " + yongShen + "=== " + yongshenDizhi + yongshenWuxing);
                    duanGua.getYuanshenPos().put("用神", yongShenPos);
                    yongShenFlag = false;
                }
//                }
            }

            if (benGua.getRelation().get(i).equals(jiShen) && jiShenFlag) { //找父母在哪一爻（父母为忌神）
                jiShenGanzhi = benGua.getGanZhi().get(i); //父母的干支
                jishenDizhi = jiShenGanzhi.substring(1, 2);
                jiShenPos = i + 1;
                jishenWuxing = dizhiWuxingMap.get(jishenDizhi);
//                System.out.println(i + 1 + "爻 本卦中找到 忌神 " + jiShen + "=== " + jishenDizhi + jishenWuxing);
                duanGua.getYongshenInfo().add(i + 1 + "爻 本卦中找到 忌神 " + jiShen + "=== " + jishenDizhi + jishenWuxing);
                duanGua.getYuanshenPos().put("忌神", jiShenPos);
                jiShenFlag = false;
            }
            if (benGua.getRelation().get(i).equals(yuanShen) && yuanShenFlag) { //找元神在哪一爻
                yuanShenGanzhi = benGua.getGanZhi().get(i); //
                yuanshenDizhi = yuanShenGanzhi.substring(1, 2);
                yuanShenPos = i + 1;
                yuanshenWuxing = dizhiWuxingMap.get(yuanshenDizhi);
                duanGua.getYuanshenPos().put("元神", i + 1); //记录元神的位置
                yuanShenFlag = false;
            }
            if (benGua.getRelation().get(i).equals(chouShen) && chouShenFlag) { //找仇神在哪一爻
                chouShenGanzhi = benGua.getGanZhi().get(i); //
                choushenDizhi = chouShenGanzhi.substring(1, 2);
                chouShenPos = i + 1;
                choushenWuxing = dizhiWuxingMap.get(choushenDizhi);
                duanGua.getYuanshenPos().put("仇神", i + 1);
                chouShenFlag = false;
            }
        }
        String benGuaWuxing = ""; //本卦五行  飞神
        String benGongGuaWuxing = ""; //本宫卦五行 伏神
        String strInfo = ""; //临时存字符串
        if (yongShenFlag) { //在本卦中没找到用神，就从本宫找
            for (int i = 0; i < benGongGua.getRelation().size(); i++) {
                if (benGongGua.getRelation().get(i).equals(yongShen) && yongShenFlag) {
                    yongShenGanzhi = benGongGua.getGanZhi().get(i);
                    yongshenDizhi = yongShenGanzhi.substring(1, 2);
                    yongShenPos = i + 1;
//                    System.out.println(i + 1 + " 爻 本宫中找到用神子孙（伏神）的地支=== " + yongshenDizhi);
                    strInfo = yongshenDizhi + benGongGua.getYaoWuxing().get(i) + yongShen + "伏于" +
                            benGua.getGanZhi().get(i).substring(1, 2) + benGua.getYaoWuxing().get(i) + "之上";
                    System.out.println(strInfo);
                    duanGua.getYongshenInfo().add(strInfo);

                    benGuaWuxing = benGua.getYaoWuxing().get(i);
                    benGongGuaWuxing = benGongGua.getYaoWuxing().get(i);
                    strInfo = yongshenDizhi + benGongGuaWuxing + yongShen + "伏于" +
                            benGua.getGanZhi().get(i).substring(1, 2) + benGuaWuxing + "之下";
                    System.out.println(strInfo);
                    duanGua.getYongshenInfo().add(strInfo);
                    if (BaGuaInit.getWuxingKe().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来克伏");
                        duanGua.getYongshenInfo().add("飞来克伏");
                    }
                    if (BaGuaInit.getWuxingSheng().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来生伏");
                        duanGua.getYongshenInfo().add("飞来生伏");
                    }

                    yongShenFlag = false;
                }
            }
        }
        if (jiShenFlag) { //在本卦中没找到忌神
            for (int i = 0; i < benGongGua.getRelation().size(); i++) {
                if (benGongGua.getRelation().get(i).equals(jiShen) && jiShenFlag) { //找父母在哪一爻（父母为忌神）
                    jiShenGanzhi = benGongGua.getGanZhi().get(i);
                    jishenDizhi = jiShenGanzhi.substring(1, 2);
                    jiShenPos = i + 1;
//                    System.out.println( i+1 + " 爻 本宫中找到忌神父母（伏神）的地支=== " + jishenDizhi);
                    // 巳火父母伏于寅木之下
                    benGuaWuxing = benGua.getYaoWuxing().get(i);
                    benGongGuaWuxing = benGongGua.getYaoWuxing().get(i);
                    strInfo = jishenDizhi + benGongGuaWuxing + jiShen + "伏于" +
                            benGua.getGanZhi().get(i).substring(1, 2) + benGuaWuxing + "之下";
                    System.out.println(strInfo);
                    duanGua.getYongshenInfo().add(strInfo);
                    if (BaGuaInit.getWuxingKe().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来克伏");
                        duanGua.getYongshenInfo().add("飞来克伏");
                    }
                    if (BaGuaInit.getWuxingSheng().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来生伏");
                        duanGua.getYongshenInfo().add("飞来生伏");
                    }

                    jiShenFlag = false;
                }
            }
        }


        //开始断卦
        Integer dongYao = 0; //记录动爻的位置
        String dongYaoDizhi = ""; //动爻的地支
        String dongYaoWuxing = ""; //动爻的五行
        String bianYaoDizhi = "";//变爻的地支
        String bianYaoWuxing = "";//变爻的五行
        String dongYaoDizhiWuxing = "";   //动爻地支五行
        String bianYaoDizhiWuxing = "";   //变爻地支五行
        String yuejian = BaGuaInit.getBengGua().getYueJian().get("月建");
        String riPo = BaGuaInit.getBengGua().getYueJian().get("日破");
        HashMap<String, String> huaJueList = Bazi.getInstance().getHuaJue();

        if (dongYaoList.size() == 0) {
            System.out.println("此卦为 静卦");
            duanGua.getYongshenInfo().add("此卦为 静卦");
        }

        HashMap<String, String> twelveChangsheng;//十二长生
        twelveChangsheng = Bazi.getInstance().getTwelveChangsheng();


        //初始化 三合 用到的list
        for (int i = 0; i < dongYaoList.size(); i++) { //初始化三合 sanHeYaoDizhiList
            dongYao = dongYaoList.get(i);
            dongYaoDizhi = BaGuaInit.getBengGua().getGanZhi().get(dongYao).substring(1, 2); //动爻的地支
            bianYaoDizhi = BaGuaInit.getBianGua().getGanZhi().get(dongYao).substring(1, 2); //变爻地支
            //全收，不管无用动爻，遇到无用动爻后面再剔除
            sanHeYaoDizhiList.add(dongYaoDizhi); //收动爻
            sanHeYaoDizhiList.add(bianYaoDizhi); //收变爻
        }
        //初始化 动爻联动 用到的list
        liandongList.addAll(dongYaoList); //基础动爻位置
        String yaoDizhi;
        String riZhi = benGua.getYueJian().get("日支");
        String richongDizhi = liuChongList.get(riZhi); //和日支相冲的地支
        for (int i = 0; i < benGua.getGanZhi().size(); i++) { //遍历本卦
            yaoDizhi = benGua.getGanZhi().get(i).substring(1, 2);
            if (Bazi.getInstance().getXunKong().contains(yaoDizhi)) { //地支旬空
//                System.out.println(i+1+"爻 处于旬空===");
                benGua.setLinXunKong(i + 1);
                if (richongDizhi.equals(yaoDizhi)) {  //地支日冲
                    strInfo = i + 1 + "爻 处于旬空 又受日冲形成暗动";
                    duanGua.getDuanYu().add(strInfo);
                    System.out.println();
                    liandongList.add(i); //加上暗动
                }
            }
        }
        //动变爻三合判断
//        for (int i = 0; i < sanHeYaoDizhiList.size(); i++) {
//            System.out.println("sanHeYaoDizhiList.get(i)=== " + sanHeYaoDizhiList.get(i));
//        }
//        System.out.println("sanHeYaoDizhiList=== " + sanHeYaoDizhiList.toString());
//        HashMap<String, String> sanHeMap = Bazi.getInstance().getSanHeMap();
//        String sanHeWuxing = "";
//        String sanHeDizhi = "";
//        for (int i = 0; i < sanHeYaoDizhiList.size(); i++) { //判断三合
//            for (HashMap.Entry<String, String> entry : sanHeMap.entrySet()) {
//                sanHeWuxing = entry.getKey();
//                sanHeDizhi = entry.getValue();
//                if (sanHeDizhi.contains(sanHeYaoDizhiList.get(i))) {
//                    strInfo = "动爻和变爻中 " + sanHeDizhi + " 三合 " + sanHeWuxing;
//                    System.out.println("strInfo=== " + strInfo);
//                }
//            }
//        }


        Boolean sanHeHuo = sanHeYaoDizhiList.contains("寅") && sanHeYaoDizhiList.contains("午") && sanHeYaoDizhiList.contains("戌");
        Boolean sanHeJin = sanHeYaoDizhiList.contains("巳") && sanHeYaoDizhiList.contains("酉") && sanHeYaoDizhiList.contains("丑");
        Boolean sanHeShui = sanHeYaoDizhiList.contains("申") && sanHeYaoDizhiList.contains("子") && sanHeYaoDizhiList.contains("辰");
        Boolean sanHeMu = sanHeYaoDizhiList.contains("亥") && sanHeYaoDizhiList.contains("卯") && sanHeYaoDizhiList.contains("未");
        if (sanHeHuo) {
            strInfo = "动爻和变爻中 寅午戌 三合 火 局";
        }
        if (sanHeJin) {
            strInfo = "动爻和变爻中 巳酉丑 三合 金 局";
        }
        if (sanHeShui) {
            strInfo = "动爻和变爻中 申子辰 三合 水 局";
        }
        if (sanHeMu) {
            strInfo = "动爻和变爻中 亥卯未 三合 木 局";
        }
        duanGua.getDuanYu().add(strInfo);


        for (int i = 0; i < dongYaoList.size(); i++) { //遍历存动爻的List

            dongYao = dongYaoList.get(i); //获取动爻的位置
            dongYaoDizhi = BaGuaInit.getBengGua().getGanZhi().get(dongYao).substring(1, 2); //动爻的地支
            dongYaoWuxing = BaGuaInit.getBengGua().getYaoWuxing().get(dongYao); //动爻的五行
            dongYaoDizhiWuxing = dongYaoDizhi + dongYaoWuxing; //动爻的地支五行

            //变爻就是动爻位置对应变卦的哪一爻
            bianYaoDizhi = BaGuaInit.getBianGua().getGanZhi().get(dongYao).substring(1, 2);
            bianYaoWuxing = BaGuaInit.getBianGua().getYaoWuxing().get(dongYao); //变爻的五行
            bianYaoDizhiWuxing = bianYaoDizhi + bianYaoWuxing; //变爻的地支五行

            //变爻生克冲合用神
            if (yongShenPos == dongYao + 1) {
                if (yongshenWuxing.equals(BaGuaInit.getWuxingSheng().get(bianYaoWuxing))) { //如果变爻 生用神
                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头生 用神 "
                            + yongshenDizhi + yongshenWuxing + " 吉兆";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setJiXiong(true);
                }
                if (yongshenWuxing.equals(BaGuaInit.getWuxingKe().get(bianYaoWuxing))) { //如果变爻 克用神
                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头克制 用神 "
                            + yongshenDizhi + yongshenWuxing + " 凶兆";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setJiXiong(false);
                }
                if (yongshenWuxing.equals(DuanGua.getInstance().getLiuChong().get(bianYaoDizhi))) { //如果变爻 冲用神
                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头六冲 用神 "
                            + yongshenDizhi + yongshenWuxing + " 凶兆";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                }
                if (yongshenWuxing.equals(DuanGua.getInstance().getLiuHe().get(bianYaoDizhi))) { //如果变爻 合用神
                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头六合 用神 "
                            + yongshenDizhi + yongshenWuxing + " 吉兆";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                }
            }
            if (DuanGua.getInstance().getLiuChong().get(bianYaoDizhi).equals(yuejian)) { //变爻冲月建
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 被月建 "
                        + yuejian + " 冲克 临月破 化破(如果没回头作用就是真破)";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (DuanGua.getInstance().getLiuChong().get(bianYaoDizhi).equals(riPo)) { //变爻冲日支
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 被日支 "
                        + riPo + " 冲克 临日破 化散(如果没回头作用就是真破)";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }

            if (bianYaoDizhiWuxing.equals(huaJueList.get(dongYaoDizhiWuxing))) {//化绝
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 化 "
                        + riPo + " 化绝 剔除";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }

            //所有变爻生克冲合
            if (dongYaoWuxing.equals(BaGuaInit.getWuxingSheng().get(bianYaoWuxing))) {
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头生 "
                        + dongYaoDizhi + dongYaoWuxing + " 能用 加强";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (dongYaoWuxing.equals(BaGuaInit.getWuxingKe().get(bianYaoWuxing))) {
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头克 "
                        + dongYaoDizhi + dongYaoWuxing + " 无用 剔除";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (dongYaoDizhi.equals(DuanGua.getInstance().getLiuChong().get(bianYaoDizhi))) {
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头冲 "
                        + dongYaoDizhi + dongYaoWuxing + " 无用 剔除";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (dongYaoDizhi.equals(DuanGua.getInstance().getLiuHe().get(bianYaoDizhi))) {
                strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头冲 "
                        + dongYaoDizhi + dongYaoWuxing + " 能用 加强";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }

            //变爻生克冲合忌神
//            if (jiShenPos == dongYao + 1) {
//                if (jishenWuxing.equals(BaGuaInit.getWuxingSheng().get(bianYaoWuxing))) { //如果变爻 生忌神
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头生 忌神 "
//                            + yongshenDizhi + jishenWuxing + " 凶兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (jishenWuxing.equals(BaGuaInit.getWuxingKe().get(bianYaoWuxing))) { //如果变爻 克忌神
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头克制 忌神 "
//                            + yongshenDizhi + jishenWuxing + " 吉兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (jishenWuxing.equals(DuanGua.getInstance().getLiuChong().get(bianYaoDizhi))) { //如果变爻 克忌神
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头六冲 忌神 "
//                            + yongshenDizhi + jishenWuxing + " 吉兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (jishenWuxing.equals(DuanGua.getInstance().getLiuHe().get(bianYaoDizhi))) { //如果变爻 克忌神
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头六合 忌神 "
//                            + yongshenDizhi + jishenWuxing + " 凶兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//            }

            //变爻生克元元神
//            if (yuanShenPos == dongYao) {
//                if (yuanshenWuxing.equals(BaGuaInit.getWuxingSheng().get(bianYaoWuxing))) {
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头生 元神 "
//                            + yuanshenDizhi + yuanshenWuxing + " 吉兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (yuanshenWuxing.equals(BaGuaInit.getWuxingKe().get(bianYaoWuxing))) {
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头克制 元神 "
//                            + yuanshenDizhi + yuanshenWuxing + " 凶兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (yuanshenWuxing.equals(DuanGua.getInstance().getLiuChong().get(bianYaoDizhi))) {
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头六冲 元神 "
//                            + yuanshenDizhi + yuanshenWuxing + " 凶兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (yuanshenWuxing.equals(DuanGua.getInstance().getLiuHe().get(bianYaoDizhi))) {
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 回头六合 元神 "
//                            + yuanshenDizhi + yuanshenWuxing + " 吉兆";
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//            }

            //动爻生克冲合 用神
            if (yongshenWuxing.equals(BaGuaInit.getWuxingSheng().get(dongYaoWuxing))) { //如果动爻 生用神
                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 生 用神 "
                        + yongshenDizhi + yongshenWuxing + " 吉兆";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (yongshenWuxing.equals(BaGuaInit.getWuxingKe().get(dongYaoWuxing))) { //如果动爻 克用神
                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 克制 用神 "
                        + yongshenDizhi + yongshenWuxing + " 凶兆";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (yongshenWuxing.equals(DuanGua.getInstance().getLiuChong().get(dongYaoWuxing))) { //如果动爻 克用神
                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 六冲 用神 "
                        + yongshenDizhi + yongshenWuxing + " 凶兆";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            if (yongshenWuxing.equals(DuanGua.getInstance().getLiuHe().get(dongYaoWuxing))) { //如果动爻 克用神
                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 六合 用神 "
                        + yongshenDizhi + yongshenWuxing + " 吉兆";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
            }
            //动爻生克冲合 忌神
//            if (jishenWuxing.equals(BaGuaInit.getWuxingSheng().get(dongYaoWuxing))) { //如果动爻 生忌神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 生 忌神 "
//                        + jishenDizhi + jishenWuxing + " 吉兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
//            if (jishenWuxing.equals(BaGuaInit.getWuxingKe().get(dongYaoWuxing))) { //如果动爻 克忌神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 克制 忌神 "
//                        + jishenDizhi + jishenWuxing + " 凶兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
//            if (jishenWuxing.equals(DuanGua.getInstance().getLiuChong().get(dongYaoWuxing))) { //如果动爻 克忌神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 六冲 忌神 "
//                        + jishenDizhi + jishenWuxing + " 凶兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
//            if (jishenWuxing.equals(DuanGua.getInstance().getLiuHe().get(dongYaoWuxing))) { //如果动爻 克忌神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 六合 忌神 "
//                        + jishenDizhi + jishenWuxing + " 吉兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
            //动爻生克冲合元神
//            if (yuanshenWuxing.equals(BaGuaInit.getWuxingSheng().get(dongYaoWuxing))) { //如果动爻 生元神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 生 元神 "
//                        + yuanshenDizhi + yuanshenWuxing + " 吉兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
//            if (yuanshenWuxing.equals(BaGuaInit.getWuxingKe().get(dongYaoWuxing))) { //如果动爻 克用神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 克制 元神 "
//                        + yuanshenDizhi + yuanshenWuxing + " 凶兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
//            if (yuanshenWuxing.equals(DuanGua.getInstance().getLiuChong().get(dongYaoWuxing))) { //如果动爻 克用神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 六冲 元神 "
//                        + yuanshenDizhi + yuanshenWuxing + " 凶兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }
//            if (yuanshenWuxing.equals(DuanGua.getInstance().getLiuHe().get(dongYaoWuxing))) { //如果动爻 克用神
//                strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 六合 元神 "
//                        + yuanshenDizhi + yuanshenWuxing + " 吉兆";
//                System.out.println(strInfo);
//                duanGua.getDuanYu().add(strInfo);
//            }


            //十二长生
//            for (HashMap.Entry<String, String> entry : twelveChangsheng.entrySet()) {
//                if(bianYaoDizhi.equals(entry.getValue())){  //变爻 在 十二长生地支
//                    strInfo = "变爻 " + (dongYao + 1) + "爻 " + bianYaoDizhi + bianYaoWuxing + " 生 元神 "
//                            + yuanshenDizhi + yuanshenWuxing + " +1分";
//                }
//            }

            //动化进神，退神
            if (bianYaoDizhi.equals(duanGua.getJinShen().get(dongYaoDizhi))) { //动化进神
                strInfo = (dongYao + 1) + "爻 动化进神 " + dongYaoDizhi + " 化 " + bianYaoDizhi + " 力量较强";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
                System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
            }
            if (bianYaoDizhi.equals(duanGua.getTuiShen().get(dongYaoDizhi))) { //动化退神
                strInfo = (dongYao + 1) + "爻 动化退神 " + dongYaoDizhi + " 化 " + bianYaoDizhi + " 剔除";
                System.out.println(strInfo);
                duanGua.getDuanYu().add(strInfo);
                System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
            }
        }


        // 5.4) 元神与忌神同动，四也。
        if (dongYaoList.contains(duanGua.getYuanshenPos().get("元神")) &&
                dongYaoList.contains(duanGua.getYuanshenPos().get("忌神"))) { //忌神、元神同动，忌生元，元生用神
            strInfo = "卦中忌神、元神同动," + jishenWuxing + "动生" + yuanshenWuxing + "," + yuanshenWuxing + "动而生" +
                    yongshenWuxing + " +1";
            duanGua.getDuanYu().add(strInfo);
        }

        String benGuaDizhi;
        //动爻生克（联动） ，太多太乱，先停用
//        for (int i = 0; i < liandongList.size(); i++) {
//            dongYao = liandongList.get(i); //获取动爻的位置
//            dongYaoDizhi = BaGuaInit.getBengGua().getGanZhi().get(dongYao).substring(1, 2); //动爻的地支
//            dongYaoWuxing = BaGuaInit.getBengGua().getYaoWuxing().get(dongYao); //动爻的五行
//            dongYaoDizhiWuxing = dongYaoDizhi + dongYaoWuxing; //动爻的地支五行
//
//            //变爻就是动爻位置对应变卦的哪一爻
//            bianYaoDizhi = BaGuaInit.getBianGua().getGanZhi().get(dongYao).substring(1, 2);
//            bianYaoWuxing = BaGuaInit.getBianGua().getYaoWuxing().get(dongYao); //变爻的五行
//            bianYaoDizhiWuxing = bianYaoDizhi + bianYaoWuxing; //变爻的地支五行
//
//            for (int k = 0; k < benGua.getGanZhi().size(); k++) {
//                benGuaDizhi = benGua.getGanZhi().get(k).substring(1, 2);
//                benGuaWuxing = benGua.getYaoWuxing().get(k);
//                if (benGuaWuxing.equals(BaGuaInit.getWuxingSheng().get(dongYaoWuxing))) { //如果动爻 生其他
//                    strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 生 " + (k + 1) + " 爻的 "
//                            + benGuaDizhi + benGuaWuxing;
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//                if (benGuaWuxing.equals(BaGuaInit.getWuxingKe().get(dongYaoWuxing))) { //如果动爻 克其他
//                    strInfo = "动爻 " + (dongYao + 1) + "爻 " + dongYaoDizhi + dongYaoWuxing + " 克 " + (k + 1) + " 爻的 "
//                            + benGuaDizhi + benGuaWuxing;
//                    System.out.println(strInfo);
//                    duanGua.getDuanYu().add(strInfo);
//                }
//            }
//        }


//        System.out.println("用神=== " + yongshenDizhi + yongshenWuxing);
//        System.out.println("忌神=== " + jishenDizhi + jishenWuxing);
        String dizhiWuxing; //遍历获取地支五行
        String keDizhiWuxing;   //克制地支五行的属性
        String yongshenShengWuxing; //用神生的五行
        String yongshenKeWuxing; //用神克的五行
        String shengDizhiWuxing;    //生成地支五行的属性
        String heDizhi;     //六合的地支
        String chongDizhi;  //六冲的地支
        //用神忌神和 月建月破 的关系
        for (HashMap.Entry<String, String> entry : benGua.getYueJian().entrySet()) {//遍历月建月破
            if (entry.getValue().equals(yongshenDizhi)) { //用神地支处在...
                switch (entry.getKey()) {
                    case "月建":
                        strInfo = "用神地支 " + yongshenDizhi + " 临 " + entry.getKey() + " +2分";
                        System.out.println(strInfo);
                        duanGua.getDuanYu().add(strInfo);
                        duanGua.setScore(duanGua.getScore() + 2); //吉凶加分
                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                        break;
                    case "月破":
                        strInfo = "用神地支 " + yongshenDizhi + " 临 " + entry.getKey() + " -2分";
                        System.out.println(strInfo);
                        duanGua.getDuanYu().add(strInfo);
                        duanGua.setScore(duanGua.getScore() - 2); //吉凶加分
                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                        break;
                    case "日支":
                        strInfo = "用神地支 " + yongshenDizhi + " 临 " + entry.getKey() + " +2分";
                        System.out.println(strInfo);
                        duanGua.getDuanYu().add(strInfo);
                        duanGua.setScore(duanGua.getScore() + 2); //吉凶加分
                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                        break;
                }

            }
//            if (entry.getValue().equals(jishenDizhi)) { //忌神地支又处在
//                switch (entry.getKey()) {
//                    case "月建":
//                        strInfo = "忌神地支 " + jishenDizhi + " 又处在 " + entry.getKey() + " -1分";
//                        System.out.println(strInfo);
//                        duanGua.getDuanYu().add(strInfo);
//                        duanGua.setScore(duanGua.getScore() - 1); //吉凶加分
//                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//                        break;
//                    case "月破":
//                        strInfo = "忌神地支 " + jishenDizhi + " 又处在 " + entry.getKey() + " +1分";
//                        System.out.println(strInfo);
//                        duanGua.getDuanYu().add(strInfo);
//                        duanGua.setScore(duanGua.getScore() + 1); //吉凶加分
//                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//                        break;
//                    case "日支":
//                        strInfo = "忌神地支 " + jishenDizhi + " 又处在 " + entry.getKey() + " -0.5分";
//                        System.out.println(strInfo);
//                        duanGua.getDuanYu().add(strInfo);
//                        duanGua.setScore(duanGua.getScore() - 0.5); //吉凶加分
//                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//                        break;
//                    case "日破":
//                        strInfo = "忌神地支 " + jishenDizhi + " 又处在 " + entry.getKey() + " +0.5分";
//                        System.out.println(strInfo);
//                        duanGua.getDuanYu().add(strInfo);
//                        duanGua.setScore(duanGua.getScore() + 0.5); //吉凶加分
//                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//                        break;
//                }
//            }
//            if (entry.getValue().equals(yuanshenDizhi)) { //元神地支又处在
//                switch (entry.getKey()) {
//                    case "月建":
//                        strInfo = "元神地支 " + yuanshenDizhi + " 又处在 " + entry.getKey() + " -1分";
//                        System.out.println(strInfo);
//                        duanGua.getDuanYu().add(strInfo);
//                        duanGua.setScore(duanGua.getScore() - 1); //吉凶加分
//                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//                        break;
//                    case "日支":
//                        strInfo = "元神地支 " + yuanshenDizhi + " 又处在 " + entry.getKey() + " -0.5分";
//                        System.out.println(strInfo);
//                        duanGua.getDuanYu().add(strInfo);
//                        duanGua.setScore(duanGua.getScore() - 0.5); //吉凶加分
//                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//                        break;
//                }
//            }


            if ("月建".equals(entry.getKey())) { //月建的生克关系
                dizhiWuxing = dizhiWuxingMap.get(entry.getValue()); //月建的地支的五行
                shengDizhiWuxing = BaGuaInit.getWuxingSheng().get(dizhiWuxing);//月建所生的五行
                keDizhiWuxing = BaGuaInit.getWuxingKe().get(dizhiWuxing);
                yongshenShengWuxing = BaGuaInit.getWuxingKe().get(yongshenWuxing); //用神所生的五行
                yongshenKeWuxing = BaGuaInit.getWuxingKe().get(yongshenWuxing); //用神所克的五行
                heDizhi = liuHeList.get(entry.getValue());
                chongDizhi = liuChongList.get(entry.getValue());
//            //用神的克制关系
                if (yongshenWuxing.equals(shengDizhiWuxing)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 被 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 所生 +1分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() + 1);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }
                if (yongshenWuxing.equals(keDizhiWuxing)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 被 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 所克制 -1分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() - 1);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }

                if (yongshenDizhi.equals(heDizhi)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 与 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 六合 +2分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() + 2);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }
                if (yongshenDizhi.equals(chongDizhi)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 与 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 六冲，月破 -2分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() - 2);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }
                if (dizhiWuxing.equals(yongshenShengWuxing) || dizhiWuxing.equals(yongshenKeWuxing)) { //用神生克月建，泄
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 生克 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 泄 -1分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() - 1);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }


            }
            if ("日支".equals(entry.getKey())) { //日支的生克关系
                dizhiWuxing = dizhiWuxingMap.get(entry.getValue());
                keDizhiWuxing = BaGuaInit.getWuxingKe().get(dizhiWuxing);
                shengDizhiWuxing = BaGuaInit.getWuxingSheng().get(dizhiWuxing);
                heDizhi = liuHeList.get(entry.getValue());
                chongDizhi = liuChongList.get(entry.getValue());
//            //用神的克制关系
                if (yongshenWuxing.equals(shengDizhiWuxing)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 被 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 所生 +1分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() + 1);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }
                if (yongshenWuxing.equals(keDizhiWuxing)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 被 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 所克制 -1分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() - 1);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }

                if (yongshenDizhi.equals(heDizhi)) {
                    if (dongYaoList.contains(yongShenPos - 1)) { //动爻就是日绊
                        strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 与 "
                                + entry.getKey() + entry.getValue() + dizhiWuxing + " 动爻与日令六合，日绊";
                        System.out.println(strInfo);
                        duanGua.getDuanYu().add(strInfo);
                    } else { //静爻+2分
                        strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 与 "
                                + entry.getKey() + entry.getValue() + dizhiWuxing + " 静爻与日令六合 +2分";
                        System.out.println(strInfo);
                        duanGua.getDuanYu().add(strInfo);
                        duanGua.setScore(duanGua.getScore() + 2);
                        System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                    }
                }
                if (yongshenDizhi.equals(chongDizhi)) {
                    strInfo = "用神 " + yongshenDizhi + yongshenWuxing + " 与 "
                            + entry.getKey() + entry.getValue() + dizhiWuxing + " 六冲 -2分";
                    System.out.println(strInfo);
                    duanGua.getDuanYu().add(strInfo);
                    duanGua.setScore(duanGua.getScore() - 2);
                    System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
                }
            }

        }
        //用神和忌神是否持世
//        if (yongShenPos == BaGuaInit.getBengGua().getShiYao()) {
//            strInfo = "用神 " + yongShen + "爻持世，在 " + BaGuaInit.getBengGua().getShiYao() + " 爻  +1分";
//            System.out.println(strInfo);
//            duanGua.getDuanYu().add(strInfo);
//            duanGua.setScore(duanGua.getScore() + 1);
//            System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//        }
//        if (jiShenPos == BaGuaInit.getBengGua().getShiYao()) {
//            strInfo = "忌神 " + jiShen + "爻持世，在 " + BaGuaInit.getBengGua().getShiYao() + " 爻  -1分";
//            System.out.println(strInfo);
//            duanGua.getDuanYu().add(strInfo);
//            duanGua.setScore(duanGua.getScore() - 1);
//            System.out.println("duanGuaObj.getScore===" + duanGua.getScore());
//        }


    }


    private void initDuanBase() {


    }

    private HashMap<String, String> baguaWuxingMap; //八卦对应的五行
    private HashMap<String, String> dizhiWuxingMap; //地支对应的五行

    //本卦五行 和 每一爻的五行 和对应关系（父母，子孙，兄弟，官鬼）
    private void initWuxing(BaGua bagua) {
        String benGong = bagua.getBenGong(); //本宫 离

        baguaWuxingMap = BaGuaInit.getBaguaWuxingMap();
        dizhiWuxingMap = BaGuaInit.getDizhiWuxingMap();
//        BaGuaInit.getBaGuaList(). //获取地支

        bagua.setBenWuxing(baguaWuxingMap.get(benGong)); //设置本宫的五行
        benWuxing = bagua.getBenWuxing(); //获取本宫五行

        ArrayList<String> relationList = new ArrayList<>(); //存关系
        ArrayList<String> yaoWuxing = new ArrayList<>();
        String yaoDizhi; //截取地支
        String dizhiWuxing; //地支五行
        for (int i = 0; i < bagua.getGanZhi().size(); i++) {
            yaoDizhi = bagua.getGanZhi().get(i).substring(1, 2);
            dizhiWuxing = dizhiWuxingMap.get(yaoDizhi);
            yaoWuxing.add(dizhiWuxing);
            relationList.add(relation(dizhiWuxing, benWuxing));  //获取 地支和本宫 的关系
//            System.out.println("relation=== "+relation);
        }
        bagua.setYaoWuxing(yaoWuxing);
        bagua.setRelation(relationList); //存到八卦对象

    }

    //benWuxing就是ben
    private void initWuxing(BaGua bagua, String benWuxing) {
        String benGong = bagua.getBenGong(); //本宫 离

        baguaWuxingMap = BaGuaInit.getBaguaWuxingMap();
        dizhiWuxingMap = BaGuaInit.getDizhiWuxingMap();
//        BaGuaInit.getBaGuaList(). //获取地支

        bagua.setBenWuxing(benWuxing); //设置本宫的五行
//        String benWuxing = bagua.getBenWuxing(); //获取本宫五行

        ArrayList<String> relationList = new ArrayList<>(); //存关系
        ArrayList<String> yaoWuxing = new ArrayList<>();
        String yaoDizhi; //截取地支
        String dizhiWuxing; //地支五行
        for (int i = 0; i < bagua.getGanZhi().size(); i++) {
            yaoDizhi = bagua.getGanZhi().get(i).substring(1, 2);
            dizhiWuxing = dizhiWuxingMap.get(yaoDizhi);
            yaoWuxing.add(dizhiWuxing);
            relationList.add(relation(dizhiWuxing, benWuxing));  //获取 地支和本宫 的关系
//            System.out.println("relation=== "+relation);
        }
        bagua.setYaoWuxing(yaoWuxing);
        bagua.setRelation(relationList); //存到八卦对象

    }
    private void initBenGongWuxing(BaGua benGongGua) {

    }

    /**
     * 六爻和本宫的关系
     *
     * @param yao     爻五行
     * @param benGong 本宫五行
     * @return 关系（父母，子孙，兄弟，官鬼）
     */
    private String relation(String yao, String benGong) {
        String relation = ""; //存关系
        switch (benGong) {
            case "木":
                relation = BaGuaInit.getMu().get(yao);
                break;
            case "火":
                relation = BaGuaInit.getHuo().get(yao);
                break;
            case "金":
                relation = BaGuaInit.getJin().get(yao);
                break;
            case "水":
                relation = BaGuaInit.getShui().get(yao);
                break;
            case "土":
                relation = BaGuaInit.getTu().get(yao);
                break;
        }

        return relation;
    }

    /**
     * 变卦 （少阴变老阴）
     */
    private void getBianGua() {
//        (0+1)%4=1   少阴变老阴
//        (1+1)%4=2   老阴变少阳
//        (2+1)%4=3   少阳变老阳
//        (3+1)%4=0   老阳变少阴
        by1 = (y1 + 1) % 4;
        by2 = (y2 + 1) % 4;
        by3 = (y3 + 1) % 4;
        by4 = (y4 + 1) % 4;
        by5 = (y5 + 1) % 4;
        by6 = (y6 + 1) % 4;

        //b 变卦  b Boolean类型
        Boolean bb6 = yinYangMap.get(by6);
        Boolean bb5 = yinYangMap.get(by5);
        Boolean bb4 = yinYangMap.get(by4);
        Boolean bb3 = yinYangMap.get(by3);
        Boolean bb2 = yinYangMap.get(by2);
        Boolean bb1 = yinYangMap.get(by1);


        bianGuaList = new ArrayList();
        bianGuaList.add(bb1);
        bianGuaList.add(bb2);
        bianGuaList.add(bb3);
        bianGuaList.add(bb4);
        bianGuaList.add(bb5);
        bianGuaList.add(bb6);
        BaGuaInit.getBianGua().setYao(bianGuaList);
//        System.out.println(by1+" 一爻 "+bb1);
//        System.out.println(by2+" 二爻 "+bb2);
//        System.out.println(by3+" 三爻 "+bb3);
//        System.out.println(by4+" 四爻 "+bb4);
//        System.out.println(by5+" 五爻 "+bb5);
//        System.out.println(by6+" 六爻 "+bb6);

    }

    /**
     * 八宫式卦变（游魂卦，归魂卦）
     */
    private ArrayList<Boolean> gbBenGongList=new ArrayList<>();//本卦里变，给首宫卦用 八宫试卦变里存爻
    private ArrayList<Boolean> gbList;//变卦，本宫卦 给八宫试卦变用的

    private void baGongGuaBianBenGua(ArrayList<Boolean> yaoList, BaGua baGua) {
        //八公式卦变
//        ☰	☰	☰	☰	☴	☶	☲	☲
//        ☰	☴	☶	☷	☷	☷	☷	☰
//        本宫 一变 二变 三变	四  五  游魂	归魂

        Boolean flag = true; //很像冒泡的排序算法里的flag
        //                gb 卦变
        //卦变这个方法里用的 yaoList
//        gbList=yaoList; list的赋值用=会直接给对象，要用addAll
        gbBenGongList.addAll(yaoList);
        if (gbBenGongList.get(0).equals(gbBenGongList.get(3)) &&
                gbBenGongList.get(1).equals(gbBenGongList.get(4)) &&
                gbBenGongList.get(2).equals(gbBenGongList.get(5))
        ) { //上经卦 和 下经卦 相同就找出本宫了
            baGua.setBianguaPosition("此卦就是本宫卦===");
            baGua.setShiYao(6);
            baGua.setYingYao(3);

            flag = false;
        }
        Boolean iBoolean;
        if (flag) {
            for (int i = 0; i < gbBenGongList.size() - 1; i++) { //一到五变卦
                iBoolean = gbBenGongList.get(i);
                gbBenGongList.set(i, !iBoolean);   //从一爻开始变卦

                if (gbBenGongList.get(0).equals(gbBenGongList.get(3)) &&
                        gbBenGongList.get(1).equals(gbBenGongList.get(4)) &&
                        gbBenGongList.get(2).equals(gbBenGongList.get(5))
                ) { //上经卦 和 下经卦 相同就找出本宫了
//                    System.out.println(i + 1 + "变卦 找到本宫");
                    baGua.setBianguaPosition(i + 1 + "变卦 找到本宫===");
                    baGua.setShiYao(i + 1);
                    if (i == 2) {//三变卦的时候是 应爻是6
                        baGua.setYingYao(6);
                    } else {
                        baGua.setYingYao((i + 4) % 6);
                    }
                    flag = false;
                    break;
                }
            }
        }
        if (flag) { //五变卦都没有找到本宫
            //游魂卦
            gbBenGongList.set(3, !gbBenGongList.get(3));//变第三爻

            if (gbBenGongList.get(0).equals(gbBenGongList.get(3)) &&
                    gbBenGongList.get(1).equals(gbBenGongList.get(4)) &&
                    gbBenGongList.get(2).equals(gbBenGongList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("游魂卦 找到本宫===");
                baGua.setBianguaPosition("游魂卦 找到本宫===");
                baGua.setShiYao(4);
                baGua.setYingYao(1);
                flag = false;
            }
        }

        if (flag) { //游魂卦都没有找出本宫
            gbBenGongList.set(2, !gbBenGongList.get(2));//归魂卦
            gbBenGongList.set(1, !gbBenGongList.get(1));//归魂卦
            gbBenGongList.set(0, !gbBenGongList.get(0));//归魂卦

            if (gbBenGongList.get(0).equals(gbBenGongList.get(3)) &&
                    gbBenGongList.get(1).equals(gbBenGongList.get(4)) &&
                    gbBenGongList.get(2).equals(gbBenGongList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("归魂卦 才找到本宫===");
                baGua.setBianguaPosition("归魂卦 才找到本宫===");
                baGua.setShiYao(3);
                baGua.setYingYao(6);
                flag = false;
            }
        }

//        for (int i = gbBenGongList.size(); i > 0; i--) {
//            System.out.println(i + " 爻是=== " + gbBenGongList.get(i - 1));
//        }

        //参考ArryList的 equels 方法写一个 JinGua的equals
        xiaJinGua = new ArrayList<>(); //下面的经卦
        xiaJinGua.add(gbBenGongList.get(0));
        xiaJinGua.add(gbBenGongList.get(1));
        xiaJinGua.add(gbBenGongList.get(2));

        ArrayList<Boolean> shangJinGua = new ArrayList<>();
        shangJinGua.add(gbBenGongList.get(3));
        shangJinGua.add(gbBenGongList.get(4));
        shangJinGua.add(gbBenGongList.get(5));
        ArrayList<ArrayList<Boolean>> baGong = BaGuaInit.getBaGong(); //获取八卦
        for (int i = 0; i < baGong.size(); i++) { //本宫上三爻和下三爻一样
            if (baGong.get(i).equals(xiaJinGua)) {
//                System.out.println("本宫卦是=== " + baGuaString[i]);
                baGua.setBenGong(baGuaString[i]);
                break;
            }
        }
        //存本宫卦六爻
//        benGongGua = new ArrayList<>();
//        benGongGua.addAll(xiaJinGua);
//        benGongGua.addAll(shangJinGua);
    }

    private void baGongGuaBian(ArrayList<Boolean> yaoList, BaGua baGua) {
        //八公式卦变
//        ☰	☰	☰	☰	☴	☶	☲	☲
//        ☰	☴	☶	☷	☷	☷	☷	☰
//        本宫 一变 二变 三变	四  五  游魂	归魂

        Boolean flag = true; //很像冒泡的排序算法里的flag
        //                gb 卦变
        //卦变这个方法里用的 yaoList
        gbList = new ArrayList<>();
//        gbList=yaoList; list的赋值用=会直接给对象，要用addAll
        gbList.addAll(yaoList);
        if (gbList.get(0).equals(gbList.get(3)) &&
                gbList.get(1).equals(gbList.get(4)) &&
                gbList.get(2).equals(gbList.get(5))
        ) { //上经卦 和 下经卦 相同就找出本宫了
            baGua.setBianguaPosition("此卦就是本宫卦===");
            baGua.setShiYao(6);
            baGua.setYingYao(3);

            flag = false;
        }
        Boolean iBoolean;
        if (flag) {
            for (int i = 0; i < gbList.size() - 1; i++) { //一到五变卦
                iBoolean = gbList.get(i);
                gbList.set(i, !iBoolean);   //从一爻开始变卦

                if (gbList.get(0).equals(gbList.get(3)) &&
                        gbList.get(1).equals(gbList.get(4)) &&
                        gbList.get(2).equals(gbList.get(5))
                ) { //上经卦 和 下经卦 相同就找出本宫了
//                    System.out.println(i + 1 + "变卦 找到本宫");
                    baGua.setBianguaPosition(i + 1 + "变卦 找到本宫===");
                    baGua.setShiYao(i + 1);
                    if (i == 2) {//三变卦的时候是 应爻是6
                        baGua.setYingYao(6);
                    } else {
                        baGua.setYingYao((i + 4) % 6);
                    }
                    flag = false;
                    break;
                }
            }
        }
        if (flag) { //五变卦都没有找到本宫
            //游魂卦
            gbList.set(3, !gbList.get(3));//变第三爻

            if (gbList.get(0).equals(gbList.get(3)) &&
                    gbList.get(1).equals(gbList.get(4)) &&
                    gbList.get(2).equals(gbList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("游魂卦 找到本宫===");
                baGua.setBianguaPosition("游魂卦 找到本宫===");
                baGua.setShiYao(4);
                baGua.setYingYao(1);
                flag = false;
            }
        }

        if (flag) { //游魂卦都没有找出本宫
            gbList.set(2, !gbList.get(2));//归魂卦
            gbList.set(1, !gbList.get(1));//归魂卦
            gbList.set(0, !gbList.get(0));//归魂卦

            if (gbList.get(0).equals(gbList.get(3)) &&
                    gbList.get(1).equals(gbList.get(4)) &&
                    gbList.get(2).equals(gbList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("归魂卦 才找到本宫===");
                baGua.setBianguaPosition("归魂卦 才找到本宫===");
                baGua.setShiYao(3);
                baGua.setYingYao(6);
                flag = false;
            }
        }

//        for (int i = gbList.size(); i > 0; i--) {
//            System.out.println(i + " 爻是=== " + gbList.get(i - 1));
//        }

        //参考ArryList的 equels 方法写一个 JinGua的equals
        xiaJinGua = new ArrayList<>(); //下面的经卦
        xiaJinGua.add(gbList.get(0));
        xiaJinGua.add(gbList.get(1));
        xiaJinGua.add(gbList.get(2));

        ArrayList<Boolean> shangJinGua = new ArrayList<>();
        shangJinGua.add(gbList.get(3));
        shangJinGua.add(gbList.get(4));
        shangJinGua.add(gbList.get(5));
        ArrayList<ArrayList<Boolean>> baGong = BaGuaInit.getBaGong(); //获取八卦
        for (int i = 0; i < baGong.size(); i++) { //本宫上三爻和下三爻一样
            if (baGong.get(i).equals(xiaJinGua)) {
//                System.out.println("本宫卦是=== " + baGuaString[i]);
                baGua.setBenGong(baGuaString[i]);
                break;
            }
        }
        //存本宫卦六爻
//        benGongGua = new ArrayList<>();
//        benGongGua.addAll(xiaJinGua);
//        benGongGua.addAll(shangJinGua);
    }


    private HashMap<Integer, Boolean> yinYangMap = new HashMap<>();
    private ArrayList<String> dongSymbol = new ArrayList<>();   //动符号,阳变阴为o，阴变阳为x
    //0少阴，1老阴 为 false阴
    private String[] baGuaString = {"乾", "艮", "坎", "震", "坤", "兑", "离", "巽"};

    public ArrayList<String> getDongSymbol() {
        return dongSymbol;
    }

    private void initBase() {
        yaoNumList.add(y1);
        yaoNumList.add(y2);
        yaoNumList.add(y3);
        yaoNumList.add(y4);
        yaoNumList.add(y5);
        yaoNumList.add(y6);

        for (int i = 0; i < yaoNumList.size(); i++) { //遍历记录 动爻
            if (yaoNumList.get(i) == 1 || yaoNumList.get(i) == 3) {
                dongYaoList.add(i); //动爻记录有老阳 老阴的位置 i从0开始
//                System.out.println("动爻=== " + i);
            }
            if (yaoNumList.get(i) == 1) { //阴变阳为x
                dongSymbol.add("x");
//                dongSymbol.add(">");

            } else if (yaoNumList.get(i) == 3) { //阳变阴为o
                dongSymbol.add("o");
//                dongSymbol.add(">");
            } else {
                dongSymbol.add("  \t\t   ");
            }

        }

        BaGuaInit.getBengGua().setDongYaoList(dongYaoList);

        yinYangMap.put(0, false);
        yinYangMap.put(1, false);
        yinYangMap.put(2, true);
        yinYangMap.put(3, true);

        BaGuaInit.getInstance();
    }

    public DuanGua getDuanGuaObj() {
        return duanGua;
    }

    private void init64Gua(BaGua baGua) {
        String name64 = "";
        switch (name.get(0)) {
            case "乾":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "乾为天";
                        break;
                    case "艮":
                        name64 = "山天大畜";
                        break;
                    case "坎":
                        name64 = "水天需";
                        break;
                    case "震":
                        name64 = "雷天大壮";
                        break;
                    case "坤":
                        name64 = "地天泰";
                        break;
                    case "兑":
                        name64 = "泽天夬";
                        break;
                    case "离":
                        name64 = "火天大有";
                        break;
                    case "巽":
                        name64 = "风天小畜";
                        break;
                }
                break;
            case "艮":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天山遁";
                        break;
                    case "艮":
                        name64 = "艮为山";
                        break;
                    case "坎":
                        name64 = "水山蹇";
                        break;
                    case "震":
                        name64 = "山雷小过";
                        break;
                    case "坤":
                        name64 = "地山谦";
                        break;
                    case "兑":
                        name64 = "泽山咸";
                        break;
                    case "离":
                        name64 = "火山旅";
                        break;
                    case "巽":
                        name64 = "风山渐";
                        break;
                }
                break;
            case "坎":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天水讼";
                        break;
                    case "艮":
                        name64 = "山水蒙";
                        break;
                    case "坎":
                        name64 = "坎为水";
                        break;
                    case "震":
                        name64 = "雷水解";
                        break;
                    case "坤":
                        name64 = "地水师";
                        break;
                    case "兑":
                        name64 = "泽水困";
                        break;
                    case "离":
                        name64 = "火水未济";
                        break;
                    case "巽":
                        name64 = "风水涣";
                        break;
                }
                break;
            case "震":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天雷无妄";
                        break;
                    case "艮":
                        name64 = "山雷颐";
                        break;
                    case "坎":
                        name64 = "水雷屯";
                        break;
                    case "震":
                        name64 = "震为雷";
                        break;
                    case "坤":
                        name64 = "地雷复";
                        break;
                    case "兑":
                        name64 = "泽雷随";
                        break;
                    case "离":
                        name64 = "火雷噬嗑";
                        break;
                    case "巽":
                        name64 = "风雷益";
                        break;
                }
                break;
            case "坤":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天地否";
                        break;
                    case "艮":
                        name64 = "山地剥";
                        break;
                    case "坎":
                        name64 = "水地比";
                        break;
                    case "震":
                        name64 = "雷地豫";
                        break;
                    case "坤":
                        name64 = "坤为地";
                        break;
                    case "兑":
                        name64 = "泽地萃";
                        break;
                    case "离":
                        name64 = "火地晋";
                        break;
                    case "巽":
                        name64 = "风地观";
                        break;
                }
                break;
            case "兑":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天泽履";
                        break;
                    case "艮":
                        name64 = "山泽损";
                        break;
                    case "坎":
                        name64 = "水泽节";
                        break;
                    case "震":
                        name64 = "雷泽归妹";
                        break;
                    case "坤":
                        name64 = "地泽临";
                        break;
                    case "兑":
                        name64 = "兑为泽";
                        break;
                    case "离":
                        name64 = "火泽睽";
                        break;
                    case "巽":
                        name64 = "风泽中孚";
                        break;
                }
                break;
            case "离":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天火同人";
                        break;
                    case "艮":
                        name64 = "山火贲";
                        break;
                    case "坎":
                        name64 = "水火既济";
                        break;
                    case "震":
                        name64 = "雷火丰";
                        break;
                    case "坤":
                        name64 = "地火明夷";
                        break;
                    case "兑":
                        name64 = "泽火革";
                        break;
                    case "离":
                        name64 = "离为火";
                        break;
                    case "巽":
                        name64 = "风火家人";
                        break;
                }
                break;
            case "巽":
                switch (name.get(1)) {
                    case "乾":
                        name64 = "天风姤";
                        break;
                    case "艮":
                        name64 = "山风蛊";
                        break;
                    case "坎":
                        name64 = "水风井";
                        break;
                    case "震":
                        name64 = "雷风恒";
                        break;
                    case "坤":
                        name64 = "地风升";
                        break;
                    case "兑":
                        name64 = "泽风大过";
                        break;
                    case "离":
                        name64 = "火风鼎";
                        break;
                    case "巽":
                        name64 = "巽为风";
                        break;
                }
                break;
        }
        baGua.setName64(name64);
    }


    /**
     * 第二次占卦的时候要清理之前的数据
     */
    public void clear() {
        duanGua.getDuanYu().clear();
        xiaJinGua.clear();
        shangJinGua.clear();

        xiaGuaGanzhi.clear();
        shangGuaGanzhi.clear();

        yaoNumList.clear();
        dongYaoList.clear();
        yaoList.clear();
        dongSymbol.clear();

        System.out.println("clear=== ");
    }

}
