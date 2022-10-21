package object;

import java.util.ArrayList;

public class BaGua {
    private ArrayList<Boolean> yao;
    private ArrayList<String> ganZhi;
    private String name;
    private String relation; //相对本宫是父母，


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


    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
