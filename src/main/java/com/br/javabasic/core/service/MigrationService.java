package com.br.javabasic.core.service;

import java.sql.SQLException;

 public class MigrationService extends DataBaseService<Object> {

    public void init() {
        
        try {
            this.executeQuery(" CREATE TABLE IF NOT EXISTS pessoa ( "+
            " id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "+
            " name VARCHAR(255) NOT NULL, "+
            " cpf VARCHAR(14), "+
            " birthDate DATE, "+
            " motherName VARCHAR(255), "+
            " cnpj VARCHAR(18) "+
            ");");
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

}
