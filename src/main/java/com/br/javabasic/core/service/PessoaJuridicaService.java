package com.br.javabasic.core.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.br.javabasic.core.model.PessoaJuridica;
import com.br.javabasic.core.repository.PessoaJuridicaRepository;

// É extendido a classe ServiceUtils e implementada a interface ServiceBase passando um tipo de objeto
public class PessoaJuridicaService extends ServiceUtils implements ServiceBase<PessoaJuridica> {

    private final PessoaJuridicaRepository repository = new PessoaJuridicaRepository();

    private final List<PessoaJuridica> elements = new ArrayList<>();

    @Override
    public void populateElements() {
        this.log("Populando array de pessoa juridica");
        elements.add(new PessoaJuridica(4L, "Empresa 1", "62456845000143"));
        elements.add(new PessoaJuridica(5L, "Empresa 2", "26855484000144"));
        elements.add(new PessoaJuridica(6L, "Empresa 3", "88670853000181"));
        elements.stream().forEachOrdered(p -> {
            try {
                repository.insert(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public PessoaJuridica getElementById(Long id) {
        this.log("Obtendo pessoa juridica pelo id");
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
    public List<PessoaJuridica> getElements() {
        this.log("Obtendo lista de pessoa juridica");
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
    public void changeName(PessoaJuridica pessoaJuridica, String name) {
        this.log("Alterando nome da pessoa juridica");
        pessoaJuridica.setName(name);
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("name", name);

        try {
            this.repository.update(fields, pessoaJuridica.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsSomeObjet(String document) {
        try {
            return this.repository.getByCNPJ(document) != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PessoaJuridica getElementByDocument(String document) {
        try {
            return this.repository.getByCNPJ(document);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
