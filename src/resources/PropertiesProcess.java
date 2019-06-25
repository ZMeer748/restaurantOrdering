package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * PropertiesProcess
 */
public class PropertiesProcess {

    private static Properties pps;

    private final static String ppsFilePath = "src/resources/.properties";

    static {
        pps = new Properties();
        try {
            pps.load(new FileInputStream("src/resources/.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String strKey) {
        return (String) pps.getProperty(strKey, "none");
    }

    public static void addProperty(String strKey, String strValue) {
        try {
            OutputStream out = new FileOutputStream(ppsFilePath);
            pps.setProperty(strKey, strValue);
            pps.store(out, "Update " + strKey + " name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(String strKey) {
        pps.remove(strKey);
        try {
            OutputStream out = new FileOutputStream(ppsFilePath);
            pps.remove(strKey);
            pps.store(out, "Remove " + strKey + " name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}