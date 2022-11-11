package object;

import java.util.ArrayList;
import java.util.HashMap;

public class DuanGuaObj {
//    private ArrayList<String> input=new ArrayList<>(); //传入，用神，忌神
    private HashMap<String,String> input=new HashMap<>(); //传入，用神，忌神
    private ArrayList<String> yongshenInfo=new ArrayList<>(); //用神忌神哪里来，
    private ArrayList<String> duanYu=new ArrayList<>(); //断语
    private double score; //得分

    public HashMap<String, String> getInput() {
        return input;
    }

    public void setInput(HashMap<String, String> input) {
        this.input = input;
    }

    public ArrayList<String> getYongshenInfo() {
        return yongshenInfo;
    }

    public void setYongshenInfo(ArrayList<String> yongshenInfo) {
        this.yongshenInfo = yongshenInfo;
    }

    public ArrayList<String> getDuanYu() {
        return duanYu;
    }

    public void setDuanYu(ArrayList<String> duanYu) {
        this.duanYu = duanYu;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DuanGuaObj{" +
                "\r\ninput=" + input +
                "\r\n yongshenInfo=" + yongshenInfo +
                "\r\nduanYu=" + duanYu +
                "\r\nscore=" + score +
                '}';

    }
}
