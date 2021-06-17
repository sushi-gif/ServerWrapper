package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void error(String error){
        System.err.println(getCurrentTime() + " [ERRORE] " + error);
    }

    public static void info(String info){
        System.out.println(getCurrentTime() + " [INFO] " + info);
    }

    public static void core(String core){
        System.out.println(getCurrentTime() + " [CORE] " + core);
    }

    private static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
