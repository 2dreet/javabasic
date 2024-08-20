package com.br.javabasic;

import com.br.javabasic.core.model.Pessoa;
import com.br.javabasic.core.model.PessoaFisica;
import com.br.javabasic.core.service.MigrationService;
import com.br.javabasic.core.service.PessoaFisicaService;
import com.br.javabasic.core.service.PessoaJuridicaService;
import com.br.javabasic.core.service.ServiceUtils;

public class App 
{
    public static void main( String[] args )
    {
        new MigrationService().init();

        ServiceUtils serviceUtils = new ServiceUtils();

        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
        // pessoaFisicaService.populateElements();
        PessoaFisica pessoaPF = pessoaFisicaService.getElementByDocument("14984101032");
        System.out.println(pessoaPF.getId());
        pessoaFisicaService.getElements().stream().forEachOrdered(pessoa -> System.out.println(pessoa.getName()));

        System.out.println("Achou CPF = " + pessoaFisicaService.existsSomeObjet("27907277086"));
        System.out.println("Achou CPF = " + pessoaFisicaService.existsSomeObjet("37907277086"));
        

        PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService();
        // pessoaJuridicaService.populateElements();
        Pessoa pessoaPJ = pessoaJuridicaService.getElementByDocument("62456845000143");
        System.out.println(pessoaPJ);
        pessoaJuridicaService.getElements().stream().forEachOrdered(pessoa -> System.out.println(pessoa.getName()));

        System.out.println("Achou CNPJ = " + pessoaJuridicaService.existsSomeObjet("62456845000143"));
        System.out.println("Achou CNPJ = " + pessoaJuridicaService.existsSomeObjet("92456845000143"));

    }
}
