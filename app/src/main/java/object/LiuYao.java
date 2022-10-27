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
    private void initGuaGanZhi(ArrayList<Boolean> yaoList ,BaGua bagua) {

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
        ArrayList<String> name64=new ArrayList();
        for (int i = 0; i < baGong.size(); i++) {
            if (baGong.get(i).equals(xiaJinGua)) {
//                    System.out.println("下卦是=== " + baGong.get(i));
                System.out.println("下卦是=== " + baGuaString[i]);
                name64.add(baGuaString[i]);
                ArrayList<String> ganZhi = BaGuaInit.getBaGuaList().get(i).getGanZhi();
                for (int j = 0; j < 3; j++) {
                    System.out.println("下卦干支是=== " + ganZhi.get(j));
                }
                ArrayList<String> xiaGuaGanzhi=new ArrayList<>();
                xiaGuaGanzhi.add(ganZhi.get(0));
                xiaGuaGanzhi.add(ganZhi.get(1));
                xiaGuaGanzhi.add(ganZhi.get(2));
                bagua.setGanZhi(xiaGuaGanzhi); //第一次要用set，因为ArrayList还是空

                break;
            }
        }
        for (int i = 0; i < baGong.size(); i++) { //循环八卦
            if (baGong.get(i).equals(shangJinGua)) {
                System.out.println("上卦是=== " + baGuaString[i]);
                name64.add(baGuaString[i]);
                ArrayList<String> ganZhi = BaGuaInit.getBaGuaList().get(i).getGanZhi();
                System.out.println("上卦干支是=== "+ganZhi.get(3));
                System.out.println("上卦干支是=== "+ganZhi.get(4));
                System.out.println("上卦干支是=== "+ganZhi.get(5));
                ArrayList<String> shangGuaGanzhi=new ArrayList<>();
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

    public void initLiuYao(int y1, int y2, int y3, int y4, int y5, int y6) {
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.y6 = y6;

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

        BaGua bengGua = BaGuaInit.getBengGua();
        BaGua bianGua = BaGuaInit.getBianGua();
        bengGua.setYao(yaoList); //设置本卦的爻阴阳


        baGongGuaBian(yaoList,bengGua); //本卦 八宫式卦变 （游魂，归魂）
        getBianGua();   //变卦（少阴变老阴）
        baGongGuaBian(bianGuaList,bianGua); //变卦 八宫式卦变 （游魂，归魂）

        initGuaGanZhi(yaoList,bengGua); //给卦纳干支
        initGuaGanZhi(bianGuaList,bianGua); //给卦纳干支

        initWuxing(bengGua); //本卦五行 和 每一爻的五行 和对应关系（父母，子孙，兄弟，官鬼）
        initWuxing(bianGua); //变卦五行
    }



    //本卦五行 和 每一爻的五行 和对应关系（父母，子孙，兄弟，官鬼）
    private void initWuxing(BaGua bagua) {
        String benGong = bagua.getBenGong(); //本宫 离
        HashMap<String, String> baguaWuxingMap = BaGuaInit.getBaguaWuxingMap(); //八卦对应的五行
        HashMap<String, String> dizhiWuxingMap = BaGuaInit.getDizhiWuxingMap(); //地支对应的五行
//        BaGuaInit.getBaGuaList(). //获取地支

        bagua.setBenWuxing(baguaWuxingMap.get(benGong)); //设置本宫的五行
        String benWuxing = bagua.getBenWuxing(); //获取本宫五行

        ArrayList<String> relation=new ArrayList<>(); //存关系
        ArrayList<String> yaoWuxing=new ArrayList<>();
        for (int i = 0; i < bagua.getGanZhi().size(); i++) {
            String yaoDizhi=bagua.getGanZhi().get(i).substring(1,2); //截取地支
            String dizhiWuxing = dizhiWuxingMap.get(yaoDizhi); //地支五行
            yaoWuxing.add(dizhiWuxing);
            relation.add(relation(dizhiWuxing,benWuxing));  //获取 地支和本宫 的关系
//            System.out.println("relation=== "+relation);
        }
        bagua.setYaoWuxing(yaoWuxing);
        bagua.setRelation(relation); //存到八卦对象

    }

    /**
     * 六爻和本宫的关系
     * @param yao 爻五行
     * @param benGong 本宫五行
     * @return  关系（父母，子孙，兄弟，官鬼）
     */
    private String relation(String yao,String benGong) {
        String relation=""; //存关系
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

    private void baGongGuaBian(ArrayList<Boolean> yaoList,BaGua baGua) {
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
            System.out.println("此卦就是本宫卦");
            baGua.setBianguaPosition("此卦就是本宫卦");
            baGua.setShiYao(6);
            baGua.setYingYao(3);

            flag = false;
        }
        if(flag) {
            for (int i = 0; i < gbList.size() - 1; i++) { //一到五变卦
                Boolean iBoolean = gbList.get(i);
                gbList.set(i, !iBoolean);   //从一爻开始变卦

                if (gbList.get(0).equals(gbList.get(3)) &&
                        gbList.get(1).equals(gbList.get(4)) &&
                        gbList.get(2).equals(gbList.get(5))
                ) { //上经卦 和 下经卦 相同就找出本宫了
                    System.out.println(i + 1 + "变卦 找到本宫");
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
        for (int i = 0; i < baGong.size(); i++) {
            if (baGong.get(i).equals(xiaJinGua)) {
//                    System.out.println("本宫卦是=== " + baGong.get(i));
                System.out.println("本宫卦是=== " + baGuaString[i]);
                baGua.setBenGong(baGuaString[i]);
                break;
            }
        }
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
