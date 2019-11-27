package edu.cscc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Config: Process webserver configuration
 *
 * @author Kate Stifle; Mark Martin
 * @version 1.0
 * @since 2019-11-25
 */
public class Config {
    public static final String PORT = "port";
    public static final String DEFAULTPAGE = "defaultPage";
    public static final String DEFAULTFOLDER = "defaultFolder";

    private static final String CONFIG_FILE = "./TinyWS.xml";
    private static Properties properties;

    /**
     * Constructor
     */
    public Config() {
        try {
            readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will read the properties XML file
     *
     * @throws IOException on read error
     * @throws FileNotFoundException on missing properties file.
     */
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

    /**
     * This method read the properties file using a key and returns the value associated with the key.
     *
     * @param key properties key
     * @return properties value associated with the key
     */
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

    /**
     * This method with print all of the property values to the console for debugging purposes.
     *
     */
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


