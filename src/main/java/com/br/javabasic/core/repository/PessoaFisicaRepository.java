package com.br.javabasic.core.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.br.javabasic.core.model.PessoaFisica;
import com.br.javabasic.core.service.DataBaseService;

public class PessoaFisicaRepository extends DataBaseService<PessoaFisica> {
    
    private final String tableName = "pessoa";

    public void insert(PessoaFisica pessoaFisica) throws SQLException {
        this.insert(tableName, pessoaFisica);
    }

    public void update(HashMap<String, Object> fields, Long id) throws SQLException {
        this.update(tableName, fields, " id = " + id);
    }

    public void delete(Long id) throws SQLException {
        this.delete(tableName, " id = " + id);
    }

    public PessoaFisica getById(Long id) throws SQLException {
        return this.getBySomeField(tableName, "id = " + id, PessoaFisica.class);
    }

    public PessoaFisica getByCPF(String cpf) throws SQLException {
        return this.getBySomeField(tableName, "cpf = '" + cpf + "'", PessoaFisica.class);
    }

    public List<PessoaFisica> getAll() throws SQLException {
        return this.getListBySomeField(tableName, "cpf is not null", PessoaFisica.class);
    }
}
