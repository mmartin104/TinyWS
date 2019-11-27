package edu.cscc;

import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

    Config configTest = new Config();

    @Test
    public void propertiesWillPopulateFromConfigFile() {
        Assert.assertEquals("index.html", configTest.getProperty("defaultPage"));
        Assert.assertEquals("./html", configTest.getProperty("defaultFolder"));
        Assert.assertEquals("80", configTest.getProperty("port"));
    }



}


