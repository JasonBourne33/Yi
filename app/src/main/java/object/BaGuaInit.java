package object;

import java.util.ArrayList;

public class BaGuaInit {
    public static BaGua qian=new BaGua();  //乾
    public static BaGua gen =new BaGua();   //艮
    public static BaGua kan =new BaGua();   //坎
    public static BaGua zhen=new BaGua();  //震
    public static BaGua kun =new BaGua();   //坤
    public static BaGua dui =new BaGua();   //兑
    public static BaGua li  =new BaGua();    //离
    public static BaGua xun =new BaGua();   //巽
    public static ArrayList<BaGua> baGuaList=new ArrayList<>();

    public static ArrayList<BaGua> getBaGuaList() {
        return baGuaList;
    }

    public static ArrayList<Boolean> qianL;  //乾
    public static ArrayList<Boolean> genL ;   //艮
    public static ArrayList<Boolean> kanL ;   //坎
    public static ArrayList<Boolean> zhenL;  //震
    public static ArrayList<Boolean> kunL ;   //坤
    public static ArrayList<Boolean> duiL ;   //兑
    public static ArrayList<Boolean> liL  ;    //离
    public static ArrayList<Boolean> xunL ;   //巽



    public static ArrayList<String> qianGZ=new ArrayList<>();  //乾
    public static ArrayList<String> genGZ=new ArrayList<>();   //艮
    public static ArrayList<String> kanGZ=new ArrayList<>();   //坎
    public static ArrayList<String> zhenGZ=new ArrayList<>();  //震
    public static ArrayList<String> kunGZ=new ArrayList<>();   //坤
    public static ArrayList<String> duiGZ=new ArrayList<>();   //兑
    public static ArrayList<String> liGZ=new ArrayList<>();    //离
    public static ArrayList<String> xunGZ=new ArrayList<>();   //巽





    public static ArrayList<ArrayList<Boolean>> baGong;    //八宫

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

    public void initHexagram() {
        initYao();

        initName();

        initGanZhi();

        baGuaList.add(qian);
        baGuaList.add(gen );
        baGuaList.add(kan );
        baGuaList.add(zhen);
        baGuaList.add(kun );
        baGuaList.add(dui );
        baGuaList.add(li  );
        baGuaList.add(xun );

    }

    private void initGanZhi() {
        qianGZ.add("甲子");
        qianGZ.add("甲寅");
        qianGZ.add("甲辰");
        qianGZ.add("壬午");
        qianGZ.add("壬申");
        qianGZ.add("壬戊");

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

    private void initYao() {
        qianL = new ArrayList<>();
        genL = new ArrayList<>();
        kanL = new ArrayList<>();
        zhenL = new ArrayList<>();
        kunL = new ArrayList<>();
        duiL = new ArrayList<>();
        liL = new ArrayList<>();
        xunL = new ArrayList<>();
        baGong = new ArrayList<>();

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


}
