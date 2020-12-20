package com.Diachenko.WebFloorHelper.tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class DataFormat {
    public static ArrayList<Integer> getCoordinates (String coordinates){
        int size;
        String str = coordinates.replace("(" , "").replace(")","");
        String []str1 =  str.split(",");
        size = str1.length;
        ArrayList <Integer> Coord= new ArrayList<Integer>();
        for(int i=0;i<size;i++){
            Coord.add(Integer.parseInt(str1[i]));
        }
        return Coord;
    }
    public static JSONObject jsonConverter(ArrayList<Integer> coordinatesList){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(int i =0, j = 0;i<coordinatesList.size();i+=2,j++){
            jsonObject.put("x",coordinatesList.get(j));
            jsonObject.put("y",coordinatesList.get(j+1));
            jsonArray.add(jsonObject);
            jsonObject = new JSONObject();
            j++;
        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("room",jsonArray);
        return mainObj;
    }
    public static boolean templateChecking(String s) {
        boolean result;
        String str;
        if (s.charAt(s.length() - 1) == ' ' || s.charAt(s.length() - 1) == ')') {
            str = s + ",";
        } else {
            str = s;
        }
        result = str.matches("([\\s]*[\\(]{1}[\\s]*[0-9]{1,2}?[\\s]*[\\,]{1}[\\s]*[0-9]{1,2}?[\\s]*[\\)]{1}[\\s]*[\\,]{1}){1,}");
        return result;
    }
    public static ArrayList<Integer> coordinatesFromJson (String jsn){
        ArrayList<Integer> coordinatesList = new ArrayList<>();
        try {
            Object obj = new JSONParser().parse(jsn);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray room = (JSONArray) jsonObject.get("room");
            for (Object object : room) {
                JSONObject coordinatesObj = (JSONObject) object;
                coordinatesList.add(Integer.parseInt(coordinatesObj.get("x").toString()));
                coordinatesList.add(Integer.parseInt(coordinatesObj.get("y").toString()));
            }
        } catch (ParseException e) {
                e.printStackTrace();
        }
        return coordinatesList;
    }
}
