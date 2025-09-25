package edu.ccrm.config;

public class AppConfig {
    private static AppConfig instance;
    private String dataPath = "data/";

    private AppConfig() {}

    public static synchronized AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public void load() {
        System.out.println("AppConfig loaded. Data path: " + dataPath);
    }

    public String getDataPath() {
        return dataPath;
    }
}
