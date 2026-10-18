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

    // Complexidade: O(MNlogN), pois percorre os M painéis solares e ordena a lista
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

    // A complexidade: O(NM+N^2), pois percorre os N painéis e a suas M leituras para calcular
    // as potências médias. Depois, compara cada painel com os demais, realizando O(N^2) comparações.
    // Com muitos painéis e leituras, o tempo de execução aumenta devido ao cálculo das médias e,
    // principalmente, às comparações quadráticas entre os painéis.
    @Override
    public List<PainelSolar> compararDesempenho(Map<PainelSolar, List<Leitura>> leiturasPorMonitorado) {
        List<PainelSolar> baixoDesempenho = new ArrayList<>();

        Map<PainelSolar, Double> potenciaPainel = new HashMap<>();
        for (PainelSolar painel: leiturasPorMonitorado.keySet()) {
            double potenciaMedia = 0;

            List<Leitura> leituras = leiturasPorMonitorado.get(painel);
            for (int i = 0; i < leituras.size(); i++) {
                potenciaMedia+= leituras.get(i).getPotencia();
            }

            if (leituras.size() == 0) {
                continue;
            }

            potenciaMedia = potenciaMedia / leituras.size();

            potenciaPainel.put(painel, potenciaMedia);
        }

        System.out.println("PAINÉIS COM POTÊNCIA MÉDIA INFERIOR À DA MAIORIA:");
        List<Map.Entry<PainelSolar, Double>> paineis = new ArrayList<>(potenciaPainel.entrySet());
        for (int i = 0; i < paineis.size(); i++) {
            double desempenho = paineis.get(i).getValue();
            int paineisMelhores = 0;

            for (int j = 0; j < paineis.size(); j++) {
                if (i != j && paineis.get(j).getValue() > desempenho) {
                    paineisMelhores++;
                }
            }

            if (paineisMelhores > (paineis.size() - 1) / 2.0) {
                baixoDesempenho.add(paineis.get(i).getKey());
            }
        }

        return baixoDesempenho;
    }
}
