package com.example.desile.yandextestapp;

/**
 * Created by DeSile on 4/7/2016.
 */
public class DeclensionBuilder {

    public static String[] trackCase = {"песня", "песни", "песен"};
    public static String[] albumCase = {"альбом", "альбома", "альбомов"};


    public static String build(String[] cases, int number){
        String strNum = "00" + String.valueOf(number);
        int last2 = (Integer.parseInt(strNum.substring(strNum.length()-2)));
        if(last2/10 != 1) {
            if (last2 % 10 == 1) {
                return number + " " + cases[0];
            }
            if ((last2 % 10 >= 2) && (last2 % 10 <= 4)) {
                return number + " " + cases[1];
            }
        }
        //else
        return number + " " + cases[2];
    }

}
