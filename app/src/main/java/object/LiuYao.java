package object;

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
    private void initGuaGanZhi(ArrayList<Boolean> yaoList, BaGua bagua) {

//        for (int i = 0; i < yaoList.size(); i++) {
//            System.out.println(i+1 +"initGuaGanZhi 爻是=== "+yaoList.get(i));
//        }

        //参考ArryList的 equels 方法写一个 JinGua的equals
        ArrayList<Boolean> xiaJinGua = new ArrayList<>(); //下面的经卦
        xiaJinGua.add(yaoList.get(0));
        xiaJinGua.add(yaoList.get(1));
        xiaJinGua.add(yaoList.get(2));
        ArrayList<Boolean> shangJinGua = new ArrayList<>();
        shangJinGua.add(yaoList.get(3));
        shangJinGua.add(yaoList.get(4));
        shangJinGua.add(yaoList.get(5));

        ArrayList<ArrayList<Boolean>> baGong = BaGuaInit.getBaGong(); //获取八卦
        ArrayList<String> name64 = new ArrayList();
        for (int i = 0; i < baGong.size(); i++) {
            if (baGong.get(i).equals(xiaJinGua)) {
//                System.out.println("下卦是=== " + baGuaString[i]);
                name64.add(baGuaString[i]);
                ArrayList<String> ganZhi = BaGuaInit.getBaGuaList().get(i).getGanZhi();
                for (int j = 0; j < 3; j++) {
//                    System.out.println("下卦干支是=== " + ganZhi.get(j));
                }
                ArrayList<String> xiaGuaGanzhi = new ArrayList<>();
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
                name64.add(baGuaString[i]);
                ArrayList<String> ganZhi = BaGuaInit.getBaGuaList().get(i).getGanZhi();
//                System.out.println("上卦干支是=== "+ganZhi.get(3));
//                System.out.println("上卦干支是=== "+ganZhi.get(4));
//                System.out.println("上卦干支是=== "+ganZhi.get(5));
                ArrayList<String> shangGuaGanzhi = new ArrayList<>();
                shangGuaGanzhi.add(ganZhi.get(3));
                shangGuaGanzhi.add(ganZhi.get(4));
                shangGuaGanzhi.add(ganZhi.get(5));
                bagua.getGanZhi().addAll(shangGuaGanzhi); //第二次用addAll
//                        (BaGuaInit.getBaGuaList().get(i).getGanZhi().get(3));

                break;
            }
        }

        //以后可以加入上下卦判断64卦的
        bagua.setName64(name64);

    }


    private BaGua benGua;
    private BaGua bianGua;
    private BaGua benGongGua;

    public void initLiuYao(int y1, int y2, int y3, int y4, int y5, int y6, String yongShen) {
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.y6 = y6;
        this.yongShen = yongShen;

        initBase(); //根据老阴，老阳获取 阴 阳， 初始化BaGuaInit

        Boolean b6 = yinYangMap.get(y6);
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
        baGongGuaBian(bianGuaList, bianGua); //变卦 八宫式卦变 （游魂，归魂）
        benGongGua.setYao(gbList); //本宫的爻阴阳
        baGongGuaBian(gbList, benGongGua); //本宫卦

        initGuaGanZhi(yaoList, benGua); //给卦的六爻纳干支
        initGuaGanZhi(bianGuaList, bianGua); //给卦纳干支
        initGuaGanZhi(gbList, benGongGua); //给卦纳干支

        initWuxing(benGua); //本卦五行 和 每一爻的五行 和对应关系（父母，子孙，兄弟，官鬼）
        initWuxing(bianGua); //变卦五行
        initWuxing(benGongGua); //变卦五行

        duanGua(yongShen);
//        duanGua("子孙"); //子孙为用神
//        duanGua("父母");
//        duanGua("妻财");
//        duanGua("官鬼");
//        duanGua("兄弟");

//        duanGua("占病");
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

    //断卦
    private void duanGua(String yongShenEle) {

        int j;
        String yongShen = "";
        if (yongShen.equals("世爻")) {
            j = BaGuaInit.getBengGua().getShiYao() - 1; //世爻的位置
            yongShen = BaGuaInit.getBengGua().getRelation().get(j);
        } else if (yongShen.equals("应爻")) {
            j = BaGuaInit.getBengGua().getYingYao() - 1; //应爻的位置
            yongShen = BaGuaInit.getBengGua().getRelation().get(j);
        } else { //剩下 兄弟，子孙 六亲
            yongShen = yongShenEle;
        }

        String jiShen = BaGuaInit.getJiShen().get(yongShen);
        System.out.println("用神===" + yongShen + " jiShen=== " + jiShen);
        int yongShenPos = 0; //用神的位置
        int jiShenPos = 0;
        boolean yongShenFlag = true; //如果有两个用神（比如两个子孙），获取第一个用神
        boolean jiShenFlag = true;
        String yongShenGanzhi = ""; //用神的干支
        String jiShenGanzhi; //忌神的干支
        String yongshenDizhi = ""; //用神的地支
        String jishenDizhi = ""; //忌神地支
        for (int i = 0; i < benGua.getRelation().size(); i++) { //找出用神和忌神地支
            if (benGua.getRelation().get(i).equals(yongShen) && yongShenFlag) { //找子孙在哪一爻（子孙为用神）
                yongShenGanzhi = benGua.getGanZhi().get(i);
                yongshenDizhi = yongShenGanzhi.substring(1, 2);
                yongShenPos = i + 1;
                System.out.println(i + 1 + " 爻 本卦中找到用神 用神 " + yongShen + " 的地支=== " + yongshenDizhi);
                yongShenFlag = false;
            }
            if (benGua.getRelation().get(i).equals(jiShen) && jiShenFlag) { //找父母在哪一爻（父母为忌神）
                jiShenGanzhi = benGua.getGanZhi().get(i); //父母的干支
                jishenDizhi = jiShenGanzhi.substring(1, 2);
                jiShenPos = i + 1;
                System.out.println(i + 1 + " 爻 本卦中找到忌神 忌神 " + jiShen + " 的地支=== " + jishenDizhi);
                jiShenFlag = false;
            }
        }
        String benGuaWuxing = ""; //本卦五行  飞神
        String benGongGuaWuxing = ""; //本宫卦五行 伏神

        if (yongShenFlag) { //在本卦中没找到用神
            for (int i = 0; i < benGongGua.getRelation().size(); i++) {
                if (benGongGua.getRelation().get(i).equals(yongShen) && yongShenFlag) {
                    yongShenGanzhi = benGongGua.getGanZhi().get(i);
                    yongshenDizhi = yongShenGanzhi.substring(1, 2);
                    yongShenPos = i + 1;
//                    System.out.println(i + 1 + " 爻 本宫中找到用神子孙（伏神）的地支=== " + yongshenDizhi);

                    System.out.println(yongshenDizhi + benGongGua.getYaoWuxing().get(i) + yongShen + "伏于" +
                            benGua.getGanZhi().get(i).substring(1, 2) + benGua.getYaoWuxing().get(i) + "之上");

                    benGuaWuxing = benGua.getYaoWuxing().get(i);
                    benGongGuaWuxing = benGongGua.getYaoWuxing().get(i);
                    System.out.println(yongshenDizhi + benGongGuaWuxing + yongShen + "伏于" +
                            benGua.getGanZhi().get(i).substring(1, 2) + benGuaWuxing + "之下");
                    if (BaGuaInit.getWuxingKe().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来克伏");
                    }
                    if (BaGuaInit.getWuxingSheng().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来生伏");
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
                    System.out.println(jishenDizhi + benGongGuaWuxing + jiShen + "伏于" +
                            benGua.getGanZhi().get(i).substring(1, 2) + benGuaWuxing + "之下");
                    if (BaGuaInit.getWuxingKe().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来克伏");
                    }
                    if (BaGuaInit.getWuxingSheng().get(benGuaWuxing) == benGongGuaWuxing) {
                        System.out.println("飞来生伏");
                    }

                    jiShenFlag = false;
                }
            }
        }


        String yongshenWuxing = dizhiWuxingMap.get(yongshenDizhi);//用神五行
        String jishenWuxing = dizhiWuxingMap.get(jishenDizhi);//用神五行
        System.out.println("yongshenWuxing=== " + yongshenWuxing);
        System.out.println("jishenWuxing=== " + jishenWuxing);
        String dizhiWuxing;//遍历获取地支五行
        String keDizhiWuxing;//克制地支五行的属性
        String shengDizhiWuxing;//生成地支五行的属性
        //用神忌神和 月建月破 的关系
        for (HashMap.Entry<String, String> entry : benGua.getSuiyin().entrySet()) {//遍历月建月破
//            System.out.println(entry.getKey()+" " +entry.getValue());
            if (entry.getValue().equals(yongshenDizhi)) {
                System.out.println("用神地支 " + yongshenDizhi + " 又处在=== " + entry.getKey());
            }
            if (entry.getValue().equals(jishenDizhi)) {
                System.out.println("忌神地支 " + jishenDizhi + " 又处在=== " + entry.getKey());
            }

            dizhiWuxing = dizhiWuxingMap.get(entry.getValue());
            keDizhiWuxing = BaGuaInit.getWuxingKe().get(dizhiWuxing);
            shengDizhiWuxing = BaGuaInit.getWuxingSheng().get(dizhiWuxing);
//            //用神的克制关系
            if (yongshenWuxing == keDizhiWuxing) {
                System.out.println("用神 " + yongshenDizhi + yongshenWuxing + " 被 "
                        + entry.getKey() + entry.getValue() + dizhiWuxing + " 所克制");
            }
            if (yongshenWuxing == shengDizhiWuxing) {
                System.out.println("用神 " + yongshenDizhi + yongshenWuxing + " 被 "
                        + entry.getKey() + entry.getValue() + dizhiWuxing + " 所生");
            }
            //忌神的克制关系
            if (jishenWuxing == keDizhiWuxing) {
                System.out.println("忌神 " + jishenDizhi + jishenWuxing + " 被 "
                        + entry.getKey() + entry.getValue() + dizhiWuxing + " 所克制");
            }
            if (jishenWuxing == shengDizhiWuxing) {
                System.out.println("忌神 " + jishenDizhi + jishenWuxing + " 被 "
                        + entry.getKey() + entry.getValue() + dizhiWuxing + " 所生");
            }

        }
        //用神和忌神是否持世
        if (yongShenPos == BaGuaInit.getBengGua().getShiYao()) {
            System.out.println(yongShen + "爻持世，在 " + BaGuaInit.getBengGua().getShiYao() + " 爻");
        }
        if (jiShenPos == BaGuaInit.getBengGua().getShiYao()) {
            System.out.println(jiShen + "爻持世，在 " + BaGuaInit.getBengGua().getShiYao() + " 爻");
        }


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
        String benWuxing = bagua.getBenWuxing(); //获取本宫五行

        ArrayList<String> relationList = new ArrayList<>(); //存关系
        ArrayList<String> yaoWuxing = new ArrayList<>();
        for (int i = 0; i < bagua.getGanZhi().size(); i++) {
            String yaoDizhi = bagua.getGanZhi().get(i).substring(1, 2); //截取地支
            String dizhiWuxing = dizhiWuxingMap.get(yaoDizhi); //地支五行
            yaoWuxing.add(dizhiWuxing);
            relationList.add(relation(dizhiWuxing, benWuxing));  //获取 地支和本宫 的关系
//            System.out.println("relation=== "+relation);
        }
        bagua.setYaoWuxing(yaoWuxing);
        bagua.setRelation(relationList); //存到八卦对象

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
    private ArrayList<Boolean> gbList;

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
            baGua.setBianguaPosition("此卦就是本宫卦");
            baGua.setShiYao(6);
            baGua.setYingYao(3);

            flag = false;
        }
        if (flag) {
            for (int i = 0; i < gbList.size() - 1; i++) { //一到五变卦
                Boolean iBoolean = gbList.get(i);
                gbList.set(i, !iBoolean);   //从一爻开始变卦

                if (gbList.get(0).equals(gbList.get(3)) &&
                        gbList.get(1).equals(gbList.get(4)) &&
                        gbList.get(2).equals(gbList.get(5))
                ) { //上经卦 和 下经卦 相同就找出本宫了
//                    System.out.println(i + 1 + "变卦 找到本宫");
                    baGua.setBianguaPosition(i + 1 + "变卦 找到本宫");
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
            gbList.set(3, !gbList.get(3));//游魂卦

            if (gbList.get(0).equals(gbList.get(3)) &&
                    gbList.get(1).equals(gbList.get(4)) &&
                    gbList.get(2).equals(gbList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("游魂卦 找到本宫");
                baGua.setBianguaPosition("游魂卦 找到本宫");
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
                System.out.println("归魂卦 才找到本宫");
                baGua.setBianguaPosition("归魂卦 才找到本宫");
                baGua.setShiYao(3);
                baGua.setYingYao(6);
            }
        }

//        for (int i = gbList.size(); i > 0; i--) {
//            System.out.println(i + " 爻是=== " + gbList.get(i - 1));
//        }

        //参考ArryList的 equels 方法写一个 JinGua的equals
        ArrayList<Boolean> xiaJinGua = new ArrayList<>(); //下面的经卦
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


    private HashMap<Integer, Boolean> yinYangMap; //0少阴，1老阴 为 false阴
    private String[] baGuaString = {"乾", "艮", "坎", "震", "坤", "兑", "离", "巽"};

    private void initBase() {
        yinYangMap = new HashMap<>();
        yinYangMap.put(0, false);
        yinYangMap.put(1, false);
        yinYangMap.put(2, true);
        yinYangMap.put(3, true);

        BaGuaInit.getInstance().initHexagram(); //初始化静态变量
    }

}
