package object;

import java.util.ArrayList;

public class BaGua {
    private ArrayList<Boolean> yao; //六爻的阴阳
    private ArrayList<String> ganZhi;
    private ArrayList<String> yaoWuxing; //爻五行
    private ArrayList<String> relation; //相对本宫是父母，兄弟 等
    private String name;    //64卦里的名字（还没做）
    private String benWuxing; //本宫五行
    private String benGong; //本宫五行 （比如 本宫离，五行火）
    private String bianguaPosition; //在第几变卦找到本宫，可能归魂，游魂


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

    @Override
    public String toString() {
        return "BaGua{" +
                "yao=" + yao +
                ", ganZhi=" + ganZhi +
                ", yaoWuxing=" + yaoWuxing +
                ", relation=" + relation +
                ", name='" + name + '\'' +
                ", benWuxing='" + benWuxing + '\'' +
                ", benGong='" + benGong + '\'' +
                ", bianguaPosition='" + bianguaPosition + '\'' +
                '}';
    }
}
