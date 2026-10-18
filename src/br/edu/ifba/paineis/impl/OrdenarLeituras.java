
package br.edu.ifba.paineis.impl;

import br.edu.ifba.paineis.impl.dados.Leitura;
import br.edu.ifba.paineis.ordenacao.Ordenador;

import java.util.ArrayList;
import java.util.List;

public class OrdenarLeituras extends Ordenador<Leitura> {

    // A complexidade é O(1), pois apenas repassa a referência da lista de leituras para o construtor
    // da classe mãe, sem percorrer os seus elementos.
    // O tempo de execução permanece constante, independentemente da quantidade de leituras na lista.
    public OrdenarLeituras(List<Leitura> leituras) {
        super(leituras);
    }

    // A complexidade é O(NlogN), pois chama o Merge Sort, que divide a lista em partes menores
    // e intercala os elementos até obter a lista ordenada.
    // Mesmo com muitas leituras, o crescimento do número de operações é menor que o de algoritmos quadráticos.
    @Override
    public void ordenar() {
        if (leituras.size() > 1) {
            mergeSort(0, leituras.size() - 1);
        }
    }

    // A complexidade é O(NlogN), pois divide recursivamente o trecho em duas metades e intercala
    // os resultados ordenados, realizando O(N) trabalho por nível de divisão, ao longo de O(log N) níveis.
    // Listas maiores exigem mais divisões e intercalações, mas o crescimento é relativamente eficiente para
    // grandes entradas.
    private void mergeSort(int inicio, int fim) {
        if (inicio < fim) {
            int meio = inicio + (fim - inicio) / 2;

            mergeSort(inicio, meio);
            mergeSort(meio + 1, fim);

            intercalar(inicio, meio, fim);
        }
    }

    // A complexidade é O(N), pois copia os elementos das duas metades para listas auxiliares e
    // percorre essas listas para intercalar os elementos, realizando um trabalho proporcional ao
    // tamanho do trecho.
    // Quanto maior o trecho, maior o tempo de cópia e intercalação, de forma linear.
    private void intercalar(int inicio, int meio, int fim) {
        List<Leitura> esquerda = new ArrayList<>();
        List<Leitura> direita = new ArrayList<>();

        for (int i = inicio; i <= meio; i++) {
            esquerda.add(leituras.get(i));
        }

        for (int i = meio + 1; i <= fim; i++) {
            direita.add(leituras.get(i));
        }

        int i = 0;
        int j = 0;
        int k = inicio;

        while (i < esquerda.size() && j < direita.size()) {
            if (esquerda.get(i).getPotencia()
                    <= direita.get(j).getPotencia()) {
                leituras.set(k, esquerda.get(i));
                i++;
            } else {
                leituras.set(k, direita.get(j));
                j++;
            }
            k++;
        }

        while (i < esquerda.size()) {
            leituras.set(k, esquerda.get(i));
            i++;
            k++;
        }

        while (j < direita.size()) {
            leituras.set(k, direita.get(j));
            j++;
            k++;
        }
    }
}
