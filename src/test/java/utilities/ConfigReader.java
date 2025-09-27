package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            String[] possiblePaths = {
                    "configuration.properties",
                    "src/test/resources/configuration.properties",
                    "./configuration.properties"
            };

            FileInputStream fis = null;
            for (String path : possiblePaths) {
                try {
                    fis = new FileInputStream(path);
                    properties = new Properties();
                    properties.load(fis);
                    System.out.println("File loaded from: " + path);
                    break;
                } catch (IOException e) {
                    System.out.println("File not found at: " + path);
                }
            }

            if (fis != null) {
                fis.close();
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration.properties!", e);
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        System.out.println( key + " = " + value);
        return value;
    }
}