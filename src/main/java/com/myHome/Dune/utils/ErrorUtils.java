package com.myHome.Dune.utils;

import java.util.Map;

public class ErrorUtils {

    public static Map<String,Object> customError(String message){
        return Map.of(
                "success",false,
                "error",message
        );
    }
    public static Map<String,Object> BAD_REQUEST(){
        return customError("BAD REQUEST!");
    }

    public static Map<String,Object> INTERNAL_SERVER_ERROR(){
        return customError("BAD REQUEST!");
    }
}
