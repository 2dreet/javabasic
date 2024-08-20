package com.br.javabasic.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PessoaJuridica extends Pessoa {

    String cnpj;
    
    public PessoaJuridica(Long id, String name, String cnpj) {
        super(id, name);
        this.cnpj = cnpj;
    }

    @Override
    public void printName() {
        System.out.println("Meu nome PJ é " + this.name);
    }

    @Override
    protected String getDocument() {
        return this.cnpj;
    }

    @Override
    public boolean existsThisDocument(String document) {
        return this.getDocument().equals(document);
    }
}
