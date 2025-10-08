package com.demo.swaglabs.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class GetProperty {

    static Properties properties = new Properties();

    public static String getProperties(String property) {
        String file = "src/test/resources/application.properties";
        try {
            FileInputStream fs = new FileInputStream(file);
            properties.load(fs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(property);
    }
}
