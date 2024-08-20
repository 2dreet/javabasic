package com.br.javabasic.core.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.br.javabasic.core.config.DataBaseConfiguration;
import com.br.javabasic.core.utils.DateUtils;

public abstract class DataBaseService<C> {
    
    public Connection getConnection() throws SQLException {
        String url = DataBaseConfiguration.getDataBaseConfiguration().getUrlJDBC();
        String user = DataBaseConfiguration.getDataBaseConfiguration().getUser();
        String password = DataBaseConfiguration.getDataBaseConfiguration().getPassword();

        try {
            Class.forName(DataBaseConfiguration.getDataBaseConfiguration().getDrivername());
            return DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            throw new SQLException("Erro ao realizar a conexão!");
        } catch(ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado!");
        }
    }

    public void insert(String table, C object) throws SQLException {
        StringBuilder sbQuery = new StringBuilder("INSERT INTO ");
        sbQuery.append(table.concat(" "));
        sbQuery.append(this.getInsertQueryFromClass(object));
        try(Connection connection = this.getConnection()) {
            try(PreparedStatement pstmt = connection.prepareStatement(sbQuery.toString())) {
                pstmt.executeUpdate();
                pstmt.close();
            } 
            connection.close();
        }
    }

    public void update(String table, HashMap<String, Object> fields, String operation) throws SQLException {
        StringBuilder sbQuery = new StringBuilder("UPDATE ");
        sbQuery.append(table.concat(" SET "));
        for (String key : fields.keySet()) {
            sbQuery.append(key.concat("="));
            sbQuery.append("'".concat(fields.get(key).toString()).concat("',"));
        }
        sbQuery.append(" WHERE ".concat(operation));

        try(Connection connection = this.getConnection()) {
            try(PreparedStatement pstmt = connection.prepareStatement(sbQuery.toString().replace(", WHERE", " WHERE "))) {
                pstmt.executeUpdate();
                pstmt.close();
            } 
            connection.close();
        }
    }

    public void delete(String table, String operation) throws SQLException {
        StringBuilder sbQuery = new StringBuilder("DELETE FROM ");
        sbQuery.append(table);
        sbQuery.append(" WHERE ".concat(operation));
        try(Connection connection = this.getConnection()) {
            try(PreparedStatement pstmt = connection.prepareStatement(sbQuery.toString())) {
                pstmt.executeUpdate();
                pstmt.close();
            } 
            connection.close();
        }
    }

    public C getBySomeField(String table, Object operation, Class<C> objClass) throws SQLException {
        StringBuilder sbQuery = new StringBuilder("SELECT * FROM " + table);
        if(operation != null) {
            sbQuery.append(" WHERE ");
            sbQuery.append(operation);
            sbQuery.append(" ");
        }
        
        C object = null;
        try(Connection connection = this.getConnection()) {
            try(PreparedStatement pstmt = connection.prepareStatement(sbQuery.toString())) {
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    try {
                        while (resultSet.next()) {
                            object = objClass.getDeclaredConstructor().newInstance();
                            Class<?> clazz = object.getClass();
                            while (clazz != null) {
                                Field[] fields = clazz.getDeclaredFields();
                                            
                                for (Field field : fields) {
                                    field.setAccessible(true);
                                    field.set(object, resultSet.getObject(field.getName()));
                                }
                                clazz = clazz.getSuperclass();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } 
        }
        return object;
    }

    public List<C> getListBySomeField(String table, String operation, Class<C> objClass) throws SQLException {
        StringBuilder sbQuery = new StringBuilder("SELECT * FROM " + table);
        if(operation != null) {
            sbQuery.append(" WHERE ");
            sbQuery.append(operation);
            sbQuery.append(" ");
        }
        
        List<C> objects = new ArrayList<>();
        try(Connection connection = this.getConnection()) {
            try(PreparedStatement pstmt = connection.prepareStatement(sbQuery.toString())) {
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    try {
                        while (resultSet.next()) {
                            C object = objClass.getDeclaredConstructor().newInstance();
                            Class<?> clazz = object.getClass();
                            while (clazz != null) {
                                Field[] fields = clazz.getDeclaredFields();
                                            
                                for (Field field : fields) {
                                    field.setAccessible(true);
                                    field.set(object, resultSet.getObject(field.getName()));
                                }
                                clazz = clazz.getSuperclass();
                            }
                            objects.add(object);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } 
        }

        return objects;
    }

    private String getInsertQueryFromClass(C object) throws SQLException {
        if (object == null) {
            throw new SQLException("Erro ao montar query de insert!");
        }

        StringBuilder fieldsString = new StringBuilder();
        StringBuilder valuesString = new StringBuilder();
        
        try {
            Class<?> clazz = object.getClass();
            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true); // Permite acesso a campos privados
                    Object value = field.get(object);
                    if (value != null) {
                        if (field.getName().equals("id")) {
                            continue;
                        }

                        fieldsString.append(field.getName()).append(",");
                        if (value instanceof Date valueDate) {
                            valuesString.append("'").append(DateUtils.getStringFromDate(valueDate)).append("',");
                        } else {
                            valuesString.append("'").append(value.toString()).append("',");
                        }
                    }
                }
                clazz = clazz.getSuperclass();
            }
            
            if (fieldsString.length() == 0 || valuesString.length() == 0) {
                throw new SQLException("Erro ao montar query de insert! Campos ou valores vazios.");
            }

            fieldsString.setLength(fieldsString.length() - 1);
            valuesString.setLength(valuesString.length() - 1);

            return " (" + fieldsString + ") VALUES (" + valuesString + ")";

        } catch(Exception e){
            e.printStackTrace();
            throw new SQLException("Erro ao montar query de insert!");
        }
    }

    public void executeQuery(String query) throws SQLException  {
        try (Connection connection = this.getConnection()) {
            try(PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.executeUpdate();
                pstmt.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao executar a query!", e);
        }
        
    }

}
