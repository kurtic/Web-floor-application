package com.Diachenko.WebFloorHelper.tools;

import java.util.ArrayList;

public class roomValidate {

    public static boolean amountCorners(ArrayList<Integer> coordinates) {
        if (coordinates.size() / 2 < 4) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean corners90(ArrayList<Integer> coordinates) {
        int length = coordinates.size();
        boolean result = true;
        for (int i = 0; i < length - 2; i += 2) {
            if (!coordinates.get(i).equals(coordinates.get(i + 2)) && !coordinates.get(i + 1).equals(coordinates.get(i + 3))) {
                result = false;
            }
        }
        if (!coordinates.get(length - 2).equals(coordinates.get(0)) && !coordinates.get(length - 1).equals(coordinates.get(1))) {
            result = false;
        }
        return result;
    }

    public static boolean cornerRepeating(ArrayList<Integer> coordinates) {
        for (int i = 0; i < coordinates.size()-1; i+=2) {
           for(int j=0;j<coordinates.size()-2; j+=2){
               if(i==j)
                   continue;

               if(coordinates.get(i).equals(coordinates.get(j)) && coordinates.get(i+1).equals(coordinates.get(j+1))) {
                   return true;
               }
           }
        }
        return false;
    }

    public static boolean diagonal(ArrayList<Integer> coordinates) {
        int corners_amount = coordinates.size()/2;
        if(corners_amount%2 != 0){
            return true;
        }
        return false;
    }
}
