package edu.cscc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Process webserver configuration
 * @author student name
 */
public class Config {
    public static final String PORT = "port";
    public static final String DEFAULTPAGE = "defaultPage";
    public static final String DEFAULTFOLDER = "defaultFolder";

    private static final String CONFIG_FILE = "./TinyWS.xml";
    private static Properties properties;


    public Config() {
        try {
            readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readProperties() throws IOException {
            try {
                File file = new File(CONFIG_FILE);
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.loadFromXML(fileInput);
                fileInput.close();

                Enumeration enuKeys = properties.keys();


                while (enuKeys.hasMoreElements()) {
                    String key = (String) enuKeys.nextElement();
                    String value = properties.getProperty(key);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public String getProperty(String key) {
        try {
            File file = new File(CONFIG_FILE);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();


            while (enuKeys.hasMoreElements()) {
                String element = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                if (element.equals(key)) {
                    return value;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public void dumpProperties() {
        try {
            File file = new File(CONFIG_FILE);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


