package property;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * PropertiesProcess
 */
public class PropertiesProcess {

    private static Properties pps;

    private final static String ppsFilePath = "rs.properties";

    static {
        if (!new File(ppsFilePath).exists()) {
            System.out.println(
                    "There is not properties file, creating it.\nPlease modify it, then restart the program.\n");
            try {
                File rs_f = new File(ppsFilePath);
                rs_f.createNewFile();
                FileWriter fw = new FileWriter(rs_f);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Database.user_password=#your db password#\n" + "Database.name=#your db name#\n"
                        + "Database.user_name=#your db username#");
                bw.flush();
                bw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pps = new Properties();
        try {
            // pps.load(PropertiesProcess.class.getResource(ppsFilePath).openStream());
            pps.load(new FileInputStream(ppsFilePath));
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