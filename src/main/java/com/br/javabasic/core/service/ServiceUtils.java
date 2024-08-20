package com.br.javabasic.core.service;

import com.br.javabasic.core.model.Pessoa;
import com.br.javabasic.core.model.PessoaFisica;
import com.br.javabasic.core.model.PessoaJuridica;

public class ServiceUtils {

    protected void log(String action) {
        System.out.println(action);
    }

    //Exemplo de polimorfismo (Overloading)
    protected void log(String action, Long id) {
        this.log(action.concat(String.valueOf(id)));
    }

    //Exemplo de polimorfismo (Overriding)
    public void printName(Pessoa pessoa) {
        pessoa.printName();
    }

    //Exemplo de polimorfismo (Overriding)
    public void printName(PessoaFisica pessoa) {
        pessoa.printName();
    }

    //Exemplo de polimorfismo (Overriding)
    public void printName(PessoaJuridica pessoa) {
        this.printName((Pessoa) pessoa);
    }
    
}
