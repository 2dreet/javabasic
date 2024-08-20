package com.br.javabasic.core.config;

public class DataBaseConfiguration extends ConfigClass {
    
    private static DataBaseConfiguration dataBaseConfiguration;

    private String urlJDBC;
    private String driverName;
    private String user;
    private String password;

    private DataBaseConfiguration() {
        this.urlJDBC = this.getProperty("database.url.jdbc");
        this.driverName = this.getProperty("database.drivername");
        this.user = this.getProperty("database.user");
        this.password = this.getProperty("database.password");
    }

    public static DataBaseConfiguration getDataBaseConfiguration() {
        if (dataBaseConfiguration == null) {
            return new DataBaseConfiguration();
        }
        return dataBaseConfiguration;
    }

    public String getUrlJDBC() {
        return this.urlJDBC;
    }

    public String getUser() {
        return this.urlJDBC;
    }

    public String getPassword() {
        return this.urlJDBC;
    }

    public String getDrivername() {
        return this.driverName;
    }
}
