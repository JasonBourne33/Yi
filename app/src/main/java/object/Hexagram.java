package object;

import java.util.ArrayList;

public class Hexagram {

    public static JinGua qian;  //乾
    public static JinGua gen;   //艮
    public static JinGua kan;   //坎
    public static JinGua zhen;  //震
    public static JinGua kun;   //坤
    public static JinGua dui;   //兑
    public static JinGua li;    //离
    public static JinGua xun;   //巽


    private static volatile Hexagram instance = null;

    private Hexagram() {
    }

    public static Hexagram getInstance() {
        if (instance == null) {
            synchronized (Hexagram.class) {
                if (instance == null) {
                    instance = new Hexagram();
                }
            }
        }
        return instance;
    }

    public void initHexagram() {
        qian = new JinGua(true,true,true);
        gen =  new JinGua(false,false,true);
        kan =  new JinGua(false,true,false);
        zhen = new JinGua(true,false,false);

        kun =  new JinGua(false,false,false);
        dui =  new JinGua(true,true,false);
        li =   new JinGua(true,false,true);
        xun =  new JinGua(false,true,true);

    }



}
