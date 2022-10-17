package object;

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

    private HashMap<String, String> liuChong; //存六合关系

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

    private String suiYin;
    private String suiPo;
    private String yueJian;
    private String yuePo;
    private String riZhi;
    private String riPo;

    /**
     * @param y 年
     * @param m 月
     * @param d 日
     * @param h 时
     */
    public void initBazi(String y, String m, String d, String h) {
        initBase();
        String suiYin = y.substring(1, 2);
        String yuePo = m.substring(1, 2);
        String riZhi = d.substring(1, 2);

        this.suiYin=suiYin;
        this.suiPo=liuChong.get(suiYin);
        this.yueJian=yueJian;
        this.yuePo=liuChong.get(yueJian);
        this.riZhi=riZhi;
        this.riPo=liuChong.get(riZhi);

        System.out.println("岁阴===" + suiYin);
        System.out.println("岁破===" + liuChong.get(suiYin));
        System.out.println("月建===" + yuePo);
        System.out.println("月破===" + liuChong.get(yuePo));
        System.out.println("日支===" + riZhi);
        System.out.println("日破===" + liuChong.get(riZhi));


    }


    private void initBase() {

        liuChong = new HashMap<>();
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


    }
}
