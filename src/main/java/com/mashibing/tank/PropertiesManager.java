package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author : shiwp
 * @Date : 2021/1/24 10:17 上午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class PropertiesManager {

    private static PropertiesManager INSTANCE;
    private Properties properties;

    private PropertiesManager() {
        properties = new Properties();
        try {
            properties.load(PropertiesManager.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesManager getInstance() {
        if (INSTANCE == null) {
            synchronized (PropertiesManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PropertiesManager();
                }
            }
        }
        return INSTANCE;
    }

    public String getString(String key) {
        return (String)get(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public Object get(String key) {
        return properties.get(key);
    }

}