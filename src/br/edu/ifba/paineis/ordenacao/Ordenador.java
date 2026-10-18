package br.edu.ifba.paineis.ordenacao;

import java.util.List;

public abstract class Ordenador<T> {

    protected List<T> leituras = null;

    // a complexidade é O(1), pois realiza apenas a atribuição da referência da lista ao atributo da classe,
    // sem percorrer os seus elementos.
    // O tempo de execução permanece constante independente da quantidade de leituras armazenadas.
    public Ordenador(List<T> leituras) {
        this.leituras = leituras;
    }

    public abstract void ordenar();

    // a complexidade é O(1), pois retorna diretamente a referência da lista armazenada, sem percorrer
    // ou modificar os seus elementos.
    // O tempo de execução não depende da quantidade de leituras armazenadas.
    public List<T> getLeiturasOrdenadas() {
        return leituras;
    }
}
