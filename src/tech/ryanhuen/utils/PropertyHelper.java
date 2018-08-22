package tech.ryanhuen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertyHelper {

    public static Map<String, String> getPropertiesEnum(String propertiesPath) {
        Map<String, String> configs = new HashMap<>();
        try {
            InputStream inputStream = new FileInputStream(new File(propertiesPath));
            final Properties ignorePro = new Properties();
            ignorePro.load(inputStream);
            final Enumeration<Object> e = ignorePro.keys();
            while (e.hasMoreElements()) {
                final String key = e.nextElement().toString().trim();
                final String value = ignorePro.getProperty(key);
                configs.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configs;
    }

    public static List<String> parseProperties2List(String propertiesPath) {
        List<String> keys = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(new File(propertiesPath));
            final Properties ignorePro = new Properties();
            ignorePro.load(inputStream);
            final Enumeration<Object> e = ignorePro.keys();
            while (e.hasMoreElements()) {
                final String value = e.nextElement().toString().trim();
                keys.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keys;
    }
}
