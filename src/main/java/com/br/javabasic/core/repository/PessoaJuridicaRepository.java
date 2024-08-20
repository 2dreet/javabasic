package com.br.javabasic.core.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.br.javabasic.core.model.PessoaJuridica;
import com.br.javabasic.core.service.DataBaseService;

public class PessoaJuridicaRepository extends DataBaseService<PessoaJuridica> {

    private final String tableName = "pessoa";

    public void insert(PessoaJuridica pessoaJuridica) throws SQLException {
        this.insert(tableName, pessoaJuridica);
    }

    public void update(HashMap<String, Object> fields, Long id) throws SQLException {
        this.update(tableName, fields, " id = " + id);
    }

    public void delete(Long id) throws SQLException {
        this.delete(tableName, " id = " + id);
    }

    public PessoaJuridica getById(Long id) throws SQLException {
        return this.getBySomeField(tableName, "id = " + id, PessoaJuridica.class);
    }

    public PessoaJuridica getByCNPJ(String cnpj) throws SQLException {
        return this.getBySomeField(tableName, "cnpj = '"+ cnpj+ "'", PessoaJuridica.class);
    }

    public List<PessoaJuridica> getAll() throws SQLException {
        return this.getListBySomeField(tableName, "cnpj is not null", PessoaJuridica.class);
    }
}
