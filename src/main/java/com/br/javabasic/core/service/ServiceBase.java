package com.br.javabasic.core.service;

import java.util.List;

//Inteface basica para uso nas outras services
//Todas as funcoes devem ser instanciadas
public interface ServiceBase<T> {

    void populateElements();

    T getElementById(Long id);

    T getElementByDocument(String document);

    List<T> getElements();

    void changeName(T object, String name);

    boolean existsSomeObjet(String value);
    
}
