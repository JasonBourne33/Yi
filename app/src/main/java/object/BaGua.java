package object;

import java.util.ArrayList;
import java.util.HashMap;

public class BaGua {
    private ArrayList<Boolean> yao; //六爻的阴阳
    private ArrayList<String> ganZhi; //六爻的天干和地支
    private ArrayList<String> yaoWuxing; //爻五行
    private ArrayList<String> relation; //相对本宫是父母，兄弟 等
    private ArrayList<String> bazi; //年月日时的 干支 （0年干，1年支，2月干，3月支，4日干，5日支，6时干，7时支）
    private HashMap<String,String> yueJian; //月建，月破，日支，日破

    private ArrayList<String> luma; //0禄，1马，2生，3旺，4墓
    private String name64;   //0下卦，1上卦，2 在64卦里的名字
    private ArrayList<Integer> dongYaoList = new ArrayList<>();//存动爻，就是老阳和老阴的位置 从0开始




    private ArrayList<String> name;    //经卦名
    private String baGuaName; //八卦名
    private String benWuxing; //本宫五行
    private String benGong; //本宫 （比如 本宫离）
    private String bianguaPosition; //在第几变卦找到本宫，可能归魂，游魂、
    private int shiYao; //世爻的位置，本宫是6，游魂4，归魂是3
    private int yingYao; //应爻的位置，本宫是3，游魂1，归魂是6
    private int linXunKong; //临旬空 1爻就是1



    public String getBaGuaName() {
        return baGuaName;
    }

    public void setBaGuaName(String baGuaName) {
        this.baGuaName = baGuaName;
    }

    public String getName64() {
        return name64;
    }

    public void setName64(String name64) {
        this.name64 = name64;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public int getLinXunKong() {
        return linXunKong;
    }

    public void setLinXunKong(int linXunKong) {
        this.linXunKong = linXunKong;
    }

    public ArrayList<Integer> getDongYaoList() {
        return dongYaoList;
    }

    public void setDongYaoList(ArrayList<Integer> dongYaoList) {
        this.dongYaoList = dongYaoList;
    }

    public String getBenGong() {
        return benGong;
    }

    public void setBenGong(String benGong) {
        this.benGong = benGong;
    }

    public ArrayList<String> getYaoWuxing() {
        return yaoWuxing;
    }

    public void setYaoWuxing(ArrayList<String> yaoWuxing) {
        this.yaoWuxing = yaoWuxing;
    }

    public String getBenWuxing() {
        return benWuxing;
    }

    public void setBenWuxing(String benWuxing) {
        this.benWuxing = benWuxing;
    }

    public ArrayList<String> getGanZhi() {
        return ganZhi;
    }

    public void setGanZhi(ArrayList<String> ganZhi) {
        this.ganZhi = ganZhi;
    }

    public ArrayList<Boolean> getYao() {
        return yao;
    }

    public void setYao(ArrayList<Boolean> yao) {
        this.yao = yao;
    }


    public ArrayList<String> getRelation() {
        return relation;
    }

    public void setRelation(ArrayList<String> relation) {
        this.relation = relation;
    }


    public String getBianguaPosition() {
        return bianguaPosition;
    }

    public void setBianguaPosition(String bianguaPosition) {
        this.bianguaPosition = bianguaPosition;
    }

    public ArrayList<String> getBazi() {
        return bazi;
    }

    public void setBazi(ArrayList<String> bazi) {
        this.bazi = bazi;
    }


    public ArrayList<String> getLuma() {
        return luma;
    }

    public void setLuma(ArrayList<String> luma) {
        this.luma = luma;
    }



    public int getShiYao() {
        return shiYao;
    }

    public void setShiYao(int shiYao) {
        this.shiYao = shiYao;
    }


    public int getYingYao() {
        return yingYao;
    }

    public void setYingYao(int yingYao) {
        this.yingYao = yingYao;
    }

    public HashMap<String, String> getYueJian() {
        return yueJian;
    }

    public void setYueJian(HashMap<String, String> yueJian) {
        this.yueJian = yueJian;
    }

    private static String ln = "\r\n";
    @Override
    public String toString() {
        return "{" +
                " \r\n yao=" + yao +
                " \r\n ganZhi=" + ganZhi +
                " \r\n relation=" + relation +
                " \r\n bazi=" + bazi +
                " \r\n yueJian=" + yueJian +
                " \r\n luma=" + luma +
                " \r\n name=" + name +
                " \r\n name64=" + name64 +
                " \r\n benWuxing='" + benWuxing + '\'' +
                " \r\n benGong='" + benGong + '\'' +
                " \r\n bianguaPosition='" + bianguaPosition + '\'' +
                '}';




    }
}
