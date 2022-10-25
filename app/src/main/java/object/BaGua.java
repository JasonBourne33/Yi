package object;

import java.util.ArrayList;

public class BaGua {
    private ArrayList<Boolean> yao; //六爻的阴阳
    private ArrayList<String> ganZhi; //六爻的天干和地支
    private ArrayList<String> yaoWuxing; //爻五行
    private ArrayList<String> relation; //相对本宫是父母，兄弟 等
    private ArrayList<String> bazi; //年月日时的 干支 （0年干，1年支，2月干，3月支，4日干，5日支，6时干，7时支）
    private ArrayList<String> suiyin; //0岁阴，1岁破，2月建，3月破，4日支，5日破
    private ArrayList<String> luma; //0禄，1马，2生，3旺，4墓
    private ArrayList<String> name64;   //0下卦，1上卦，2 在64卦里的名字


    private String name;    //经卦名
    private String benWuxing; //本宫五行
    private String benGong; //本宫五行 （比如 本宫离，五行火）
    private String bianguaPosition; //在第几变卦找到本宫，可能归魂，游魂、


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<String> getSuiyin() {
        return suiyin;
    }

    public void setSuiyin(ArrayList<String> suiyin) {
        this.suiyin = suiyin;
    }

    public ArrayList<String> getLuma() {
        return luma;
    }

    public void setLuma(ArrayList<String> luma) {
        this.luma = luma;
    }

    public ArrayList<String> getName64() {
        return name64;
    }

    public void setName64(ArrayList<String> name64) {
        this.name64 = name64;
    }


    @Override
    public String toString() {
        return "BaGua{" +
                "yao=" + yao +
                ", ganZhi=" + ganZhi +
                ", yaoWuxing=" + yaoWuxing +
                ", relation=" + relation +
                ", bazi=" + bazi +
                ", suiyin=" + suiyin +
                ", luma=" + luma +
                ", name64=" + name64 +
                ", name='" + name + '\'' +
                ", benWuxing='" + benWuxing + '\'' +
                ", benGong='" + benGong + '\'' +
                ", bianguaPosition='" + bianguaPosition + '\'' +
                '}';
    }
}
