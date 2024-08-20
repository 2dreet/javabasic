package com.br.javabasic.core.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PessoaFisica extends Pessoa {

    String cpf;
    Date birthDate;
    String motherName;
    
    public PessoaFisica(Long id, String name, String cpf, Date birthDate, String motherName) {
        super(id, name);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.motherName = motherName;
    }

    @Override
    public void printName() {
        System.out.println("Meu nome PF é " + this.name);
    }

    @Override
    protected String getDocument() {
        return this.cpf;
    }

    @Override
    public boolean existsThisDocument(String document) {
        return this.getDocument().equals(document);
    }

}
