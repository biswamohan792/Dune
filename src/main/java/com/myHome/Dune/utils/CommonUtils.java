package com.myHome.Dune.utils;

import java.util.Objects;

public class CommonUtils {
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    public static String genRandomId(int length){
        StringBuilder stringBuilder = new StringBuilder();
        while (length-->0)
            stringBuilder.append(ALPHABETS.charAt((int)Math.floor(Math.random()*ALPHABETS.length())));
        return stringBuilder.toString();
    }

    public static boolean anyNull(Object ...objects){
        for(Object crawl:objects)
            if(Objects.isNull(crawl)) return true;
        return false;
    }
}
