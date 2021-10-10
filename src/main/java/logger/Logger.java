package logger;
import util.PropertiesUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

public class Logger  {

        public static Properties config;

    static {
        try {
            config = PropertiesUtil.getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_BRIGHT_RED = "\u001b[31;1m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_CYAN = "\u001B[36m";

        public static void log(LogLevel level, String message){

            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
            SimpleDateFormat fileDate = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("[" + config.getProperty("GeneralName") + " - " + level + "] " + level.getColor() + message + ANSI_RESET);

        }
}

