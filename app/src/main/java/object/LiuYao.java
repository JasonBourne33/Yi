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

    private int y1;
    private int y2;
    private int y3;
    private int y4;
    private int y5;
    private int y6;

    /***
     * 0少阴 1老阴 2少阳 3老阳
     * @param y1
     * @param y2
     * @param y3
     * @param y4
     * @param y5
     * @param y6
     */
    private ArrayList<Boolean> yaoList;
    public void initLiuYao(int y1, int y2, int y3, int y4, int y5, int y6) {
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.y6 = y6;

        initBase();

        Boolean b6 = yinYangMap.get(y6);
        Boolean b5 = yinYangMap.get(y5);
        Boolean b4 = yinYangMap.get(y4);
        Boolean b3 = yinYangMap.get(y3);
        Boolean b2 = yinYangMap.get(y2);
        Boolean b1 = yinYangMap.get(y1);



        yaoList = new ArrayList<>();
        yaoList.add(b1);
        yaoList.add(b2);
        yaoList.add(b3);
        yaoList.add(b4);
        yaoList.add(b5);
        yaoList.add(b6);


        baGongGuaBian();



    }

    private void baGongGuaBian() {
        //八公式卦变
//        ☰	☰	☰	☰	☴	☶	☲	☲
//        ☰	☴	☶	☷	☷	☷	☷	☰
//        本宫 一变 二变 三变	四  五  游魂	归魂

        Boolean flag = true; //很像的冒泡排序算法里的flag
        for (int i = 0; i < yaoList.size() - 1; i++) { //一到五变卦
            Boolean iBoolean = yaoList.get(i);
            yaoList.set(i, !iBoolean);   //从一爻开始变卦
            System.out.println(iBoolean);


            if (yaoList.get(0).equals(yaoList.get(3)) &&
                    yaoList.get(1).equals(yaoList.get(4)) &&
                    yaoList.get(2).equals(yaoList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println(i+1 +"变卦 找到本宫");
                flag = false;
                break;
            }
        }

        if (flag) { //五变卦都没有找到本宫
            yaoList.set(3, !yaoList.get(3));//游魂卦

            if (yaoList.get(0).equals(yaoList.get(3)) &&
                    yaoList.get(1).equals(yaoList.get(4)) &&
                    yaoList.get(2).equals(yaoList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("游魂卦 找到本宫");
                flag = false;
            }
        }
        if (flag) { //游魂卦都没有找出本宫
            yaoList.set(2, !yaoList.get(2));//归魂卦
            yaoList.set(1, !yaoList.get(1));//归魂卦
            yaoList.set(0, !yaoList.get(0));//归魂卦

            if (yaoList.get(0).equals(yaoList.get(3)) &&
                    yaoList.get(1).equals(yaoList.get(4)) &&
                    yaoList.get(2).equals(yaoList.get(5))
            ) { //上经卦 和 下经卦 相同就找出本宫了
                System.out.println("===归魂卦 才找到本宫=== ");
            }
        }

        for (int i = yaoList.size(); i > 0; i--) {
            System.out.println(i  + " 爻是=== " + yaoList.get(i-1));
        }

        //参考ArryList的 equels 方法写一个 JinGua的equals
    }



    private HashMap<Integer, Boolean> yinYangMap; //0少阴，1老阴 为 false阴
    private void initBase() {
        yinYangMap = new HashMap<>();
        yinYangMap.put(0, false);
        yinYangMap.put(1, false);
        yinYangMap.put(2, true);
        yinYangMap.put(3, true);
    }

}
