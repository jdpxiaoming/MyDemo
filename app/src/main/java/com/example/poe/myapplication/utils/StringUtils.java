package com.example.poe.myapplication.utils;

/**
 * Created by poe on 16-4-27.
 */
public class StringUtils {

    public static String filterCharacter(String input){
        if(null == input) return "";

        String result = input ;

        result = result.replaceAll("\"","&quot;");
        result = result.replaceAll("'","&apos;");
        if(result.contains("<")){
            result = result.replaceAll("<","&lt;");
        }
        if(result.contains(">")){
            result = result.replaceAll(">","&gt;");
        }

        return  result ;
    }
}
