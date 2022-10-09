package com.example.utils;

import java.util.Scanner;

public class Chronology {

    public static void main(String[] args) {
        System.out.println("Please enter year: ");
        int year=getNumber();
        int i=year-3;
        int day=i%10;
        int lad=i%12;

        String a=getDay(day);
        String b=getLab(lad);

        System.out.println("公元"+year+"年是 "+a+b+"年");
    }

    private static int getNumber() {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        return i;
    }

    private static String getDay(int day) {
        String a="";
        switch (day){
            case 1:
                a="甲";
                break;
            case 2:
                a="乙";
                break;
            case 3:
                a="丙";
                break;
            case 4:
                a="丁";
                break;
            case 5:
                a="勿";
                break;
            case 6:
                a="己";
                break;
            case 7:
                a="庚";
                break;
            case 8:
                a="辛";
                break;
            case 9:
                a="壬";
                break;
            case 0:
                a="癸";
                break;
        }
        System.out.println("天干为："+a);
        return a;
    }

    private static String getLab(int lad) {
        String b="";
        switch (lad){
            case 1:
                b="子";
                break;
            case 2:
                b="丑";
                break;
            case 3:
                b="寅";
                break;
            case 4:
                b="卯";
                break;
            case 5:
                b="辰";
                break;
            case 6:
                b="巳";
                break;
            case 7:
                b="午";
                break;
            case 8:
                b="未";
                break;
            case 9:
                b="申";
                break;
            case 10:
                b="酉";
                break;
            case 11:
                b="戌";
                break;
            case 12:
                b="亥";
                break;
        }
        System.out.println("地支："+b);
        return b;
    }


}
