package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        AppConfig.getInstance().load();
        Menu menu = new Menu();
        menu.start();
    }
}
