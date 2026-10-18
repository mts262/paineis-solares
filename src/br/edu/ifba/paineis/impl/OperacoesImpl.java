package br.edu.ifba.paineis.impl;

import br.edu.ifba.paineis.impl.dados.Leitura;
import br.edu.ifba.paineis.impl.dados.PainelSolar;
import br.edu.ifba.paineis.operacoes.Operacoes;
import br.edu.ifba.paineis.ordenacao.Ordenador;

import java.util.*;

public class OperacoesImpl implements Operacoes<PainelSolar, Leitura> {

    // A complexidade é O(N), pois percorre a lista de painéis solares uma única vez,
    // imprimindo cada painel. Assim, o número de operações cresce proporcionalmente à quantidade de painéis.
    // Se a lista tiver muitos painéis, o tempo de execução aumentará linearmente, e a impressão
    // de todos eles poderá demorar mais.
    @Override
    public void imprimir(List<PainelSolar> monitorados) {
        System.out.println("=== IMPRIMINDO LISTA DE PAINÉIS SOLARES ===");
        for (PainelSolar painel: monitorados) {
            System.out.println(painel);
        }
        System.out.println();
    }

    // A complexidade é O(NM), pois o laço externo percorre os N painéis solares, enquanto o laço
    // interno percorre as leituras de cada painel, considerando M leituras por painel.
    // Se a quantidade de painéis ou de leituras por painel aumentar, o tempo de execução também
    // aumentará. Com muitos painéis e muitas leituras, a impressão poderá demorar mais tempo.
    @Override
    public void imprimirLeituras(Map<PainelSolar, List<Leitura>> leiturasPorMonitorado) {
        System.out.println("===  LISTA DE LEITURAS POR PAINEL SOLAR ===");
        for (PainelSolar painel: leiturasPorMonitorado.keySet()) {
            System.out.println("Leituras por painel solar (" +painel.getIdPainel()+ ") :");
            for (Leitura leitura: leiturasPorMonitorado.get(painel)) {
                System.out.println(leitura);
            }
            System.out.println();
        }
    }

    // A omplexidade é O(MNlogN), pois percorre os M painéis solares e ordena a lista
    // de até N leituras de cada painel, cuja ordenação tem complexidade. A cópia das
    // listas também exige tempo proporcional à quantidade de leituras.
    // Com o aumento da quantidade de painéis e leituras, o tempo de execução aumenta.
    // Painéis com muitas leituras exigem mais tempo para ordenar os seus dados.
    @Override
    public Map<PainelSolar, List<Leitura>> ordenar(Map<PainelSolar, List<Leitura>> leiturasPorMonitorado) {
        Map<PainelSolar, List<Leitura>> ordenadas = new TreeMap<>();

        System.out.println("ORDENANDO AS LEITURAS POR POTÊNCIA DE CADA PAINEL SOLAR...");
        for (PainelSolar painel: leiturasPorMonitorado.keySet()) {

            List<Leitura> leituras = new ArrayList<>(leiturasPorMonitorado.get(painel));
            Ordenador<Leitura> ordenador = new OrdenarLeituras(leituras);

            ordenador.ordenar();

            ordenadas.put(painel, leituras);
        }

        return ordenadas;
    }

    // A complexidade é O(MN^2), onde M é o número de painéis solares e N é a quantidade de leituras por painel.
    // Para cada painel, dois loops aninhados comparam as potências das leituras entre si, resultando em O(N^2) por painel.
    // O tempo de execução cresce quadraticamente com o número de leituras e linearmente com o número de painéis.
    @Override
    public void analisarLeiturasDiscrepantes(Map<PainelSolar, List<Leitura>> leiturasPorMonitorado) {

        double diferencaMinima = 30.0;
        double percentualMinimo = 0.75;

        System.out.println("ANALISANDO LEITURAS DISCREPANTES DE POTÊNCIA DOS PAINÉIS SOLARES...");
        for (Map.Entry<PainelSolar, List<Leitura>> entrada : leiturasPorMonitorado.entrySet()) {

            PainelSolar painel = entrada.getKey();
            List<Leitura> leituras = entrada.getValue();

            int totalDiscrepantes = 0;

            for (int i = 0; i < leituras.size(); i++) {
                double potenciaAtual = leituras.get(i).getPotencia();
                int diferencas = 0;

                for (int j = 0; j < leituras.size(); j++) {

                    if (i == j) {
                        continue;
                    }

                    double potenciaComparada = leituras.get(j).getPotencia();

                    if (Math.abs(potenciaAtual - potenciaComparada)
                            > diferencaMinima) {
                        diferencas++;
                    }
                }

                int totalComparacoes = leituras.size() - 1;

                if (totalComparacoes > 0
                        && diferencas >= totalComparacoes * percentualMinimo) {

                    System.out.printf(
                            "Painel %s - Leitura %d com potência discrepante: %.2fW%n",
                            painel.getIdPainel(),
                            i + 1,
                            potenciaAtual
                    );

                    totalDiscrepantes++;
                }
            }

            System.out.printf(
                    "%s: %d de %d leituras discrepantes.%n",
                    painel.getIdPainel(),
                    totalDiscrepantes,
                    leituras.size()
            );
            System.out.println();
        }
    }
}
