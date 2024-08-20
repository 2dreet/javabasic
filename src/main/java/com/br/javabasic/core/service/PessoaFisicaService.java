package com.br.javabasic.core.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.br.javabasic.core.model.PessoaFisica;
import com.br.javabasic.core.repository.PessoaFisicaRepository;

// É extendido a classe ServiceUtils e implementada a interface ServiceBase passando um tipo de objeto
public class PessoaFisicaService extends ServiceUtils implements ServiceBase<PessoaFisica> {

    private final List<PessoaFisica> elements = new ArrayList<>();
    private final PessoaFisicaRepository repository = new PessoaFisicaRepository();

    @Override
    public void populateElements() {
        this.log("Populando array pessoa fisica");
        elements.add(new PessoaFisica(1L, "José", "14984101032", new Date(), "Maria"));
        elements.add(new PessoaFisica(2L, "João", "27907277086", new Date(), "Joana"));
        elements.add(new PessoaFisica(3L, "Pedro", "78187179007", new Date(), "Selia"));
        elements.stream().forEachOrdered(p -> {
            try {
                repository.insert(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public PessoaFisica getElementById(Long id) {
        this.log("Obtendo pessoa fisica por id");
        if(this.elements.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia");
        }

        try {
            return this.repository.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PessoaFisica> getElements() {
        this.log("Obtendo arrays de pessoa fisica");
        // if(this.elements.isEmpty()) {
        //     throw new IllegalArgumentException("A lista está vazia");
        // }

        try {
            return repository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void changeName(PessoaFisica pessoaFisica, String name) {
        this.log("Alterando nome da pessoa fisica");
        pessoaFisica.setName(name);
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("name", name);
        
        try {
            this.repository.update(fields, pessoaFisica.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsSomeObjet(String document) {
        try {
            return this.repository.getByCPF(document) != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PessoaFisica getElementByDocument(String document) {
        try {
            return this.repository.getByCPF(document);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
