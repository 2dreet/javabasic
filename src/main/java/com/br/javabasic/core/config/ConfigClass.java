package com.br.javabasic.core.config;

import java.util.ResourceBundle;

public class ConfigClass {
    private static ResourceBundle resourceBundle;

    public String getProperty(String key) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("application");
        }

        return resourceBundle.getString(key);
    }

}
