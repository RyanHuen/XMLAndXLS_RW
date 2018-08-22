package tech.ryanhuen.utils;

import java.util.HashMap;
import java.util.Map;

public class Configs {
    private Map<String, String> config = new HashMap<>();
    private static Configs sInstance = null;

    public static Configs getInstance() {
        if (sInstance == null) {
            synchronized (Configs.class) {
                if (sInstance == null) {
                    sInstance = new Configs();
                }
            }
        }
        return sInstance;
    }

    public Configs() {
        config = PropertyHelper.getPropertiesEnum("config/project_config.properties");
    }

    public String getConfig(String key) {
        return config.get(key);
    }

}
