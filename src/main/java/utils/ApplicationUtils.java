package utils;

import java.util.Date;

public class ApplicationUtils {
    public static boolean logEnabled = true;

    public static void log(String message){
        if(logEnabled) System.out.println(new Date()+"\t"+message);
    }

    public static Integer getInteger(String s){
        try{
            return Integer.parseInt(s);
        }catch (Exception e){
            log("Error parsing integer property "+e.getMessage());
        }
        return null;
    }
}
