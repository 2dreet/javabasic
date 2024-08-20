package com.br.javabasic.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {

    Long id;
    String name;

    public abstract void printName();

    protected abstract String getDocument();

    public abstract boolean existsThisDocument(String document);

}
