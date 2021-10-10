package util;

import java.io.*;
import java.util.Properties;

public final class PropertiesUtil {

    public static void initPropertiesFile() throws IOException {
        Properties p = new Properties();

        OutputStream os = new FileOutputStream("Ray.properties", false);

        p.setProperty("botToken", "BotTokenHEre");
        p.setProperty("ownerId", "OWNERID");
        p.setProperty("Prefix", "!");
        p.setProperty("GeneralName", "Name bot here");


        p.store(os, "Ray.properties " );

        os.close();
    }

    public static Properties getProperties() throws IOException {

        try {
            Properties p = new Properties();
            InputStream configFile = new FileInputStream("Ray.properties");
            p.load(configFile);
            configFile.close();
            return p;
        } catch (FileNotFoundException e){
            initPropertiesFile();
            return null;
        }
    }
}
