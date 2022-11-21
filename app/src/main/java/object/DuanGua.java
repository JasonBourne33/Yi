package object;

import java.util.ArrayList;
import java.util.HashMap;

public class DuanGua {

    private static volatile DuanGua instance = null;
    private DuanGua() {
    }

    public static DuanGua getInstance() {
        if (instance == null) {
            synchronized (DuanGua.class) {
                if (instance == null) {
                    instance = new DuanGua();
                }
            }
        }
        return instance;
    }

//    private ArrayList<String> input=new ArrayList<>(); //传入，用神，忌神
    private HashMap<String,String> input=new HashMap<>(); //传入，用神，忌神，元神，仇神
    private HashMap<String,String> jinShen=new HashMap<>(); //进神
    private HashMap<String,String> tuiShen=new HashMap<>(); //退神
    private HashMap<String,String> liuHe=new HashMap<>(); //六合
    private HashMap<String, String> liuChong = new HashMap<>(); //六冲
    private HashMap<String,Integer> yuanshenPos=new HashMap<>(); //用神，忌神，元神，仇神位置
    private ArrayList<String> yongshenInfo=new ArrayList<>(); //用神忌神哪里来，
    private ArrayList<String> duanYu=new ArrayList<>(); //断语
    private Boolean jiXiong;    //吉凶 true为吉


    public Boolean getJiXiong() {
        return jiXiong;
    }

    public void setJiXiong(Boolean jiXiong) {
        this.jiXiong = jiXiong;
    }

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

    public HashMap<String, Integer> getYuanshenPos() {
        return yuanshenPos;
    }

    public void setYuanshenPos(HashMap<String, Integer> yuanshenPos) {
        this.yuanshenPos = yuanshenPos;
    }

    public HashMap<String, String> getJinShen() {
        return jinShen;
    }

    public void setJinShen(HashMap<String, String> jinShen) {
        this.jinShen = jinShen;
    }

    public HashMap<String, String> getTuiShen() {
        return tuiShen;
    }

    public void setTuiShen(HashMap<String, String> tuiShen) {
        this.tuiShen = tuiShen;
    }

    public HashMap<String, String> getLiuHe() {
        return liuHe;
    }

    public void setLiuHe(HashMap<String, String> liuHe) {
        this.liuHe = liuHe;
    }

    public HashMap<String, String> getLiuChong() {
        return liuChong;
    }

    public void setLiuChong(HashMap<String, String> liuChong) {
        this.liuChong = liuChong;
    }

    @Override
    public String toString() {
        return "DuanGuaObj{" +
                "\r\ninput=" + input +
                "\r\n yongshenInfo=" + yongshenInfo +
                "\r\nduanYu=" + duanYu +
                "\r\nscore=" + score +
                "\r\nyuanshenPos=" + yuanshenPos +
                '}';

    }
}
