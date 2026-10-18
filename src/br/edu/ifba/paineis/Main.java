package br.edu.ifba.paineis;

import br.edu.ifba.paineis.impl.OperacoesImpl;
import br.edu.ifba.paineis.impl.SensoriamentoImpl;
import br.edu.ifba.paineis.impl.dados.Leitura;
import br.edu.ifba.paineis.impl.dados.PainelSolar;
import br.edu.ifba.paineis.operacoes.Operacoes;
import br.edu.ifba.paineis.sensoriamento.Sensoriamento;

import java.util.*;

public class Main {

    private static int TOTAL_PAINEIS = 10;
    private static int TOTAL_LEITURAS = 100;

    public static void main(String[] args) {
        Sensoriamento<Leitura> sensoriamento = new SensoriamentoImpl();

        // Gera leituras para N painéis solares
        Map<PainelSolar, List<Leitura>> leiturasPorPainel = new TreeMap<>();
        for (int i = 0; i < TOTAL_PAINEIS; i++) {
            leiturasPorPainel.put(new PainelSolar("PS_" +String.format("%03d", i+1)),
                    sensoriamento.capturarLeituras(TOTAL_LEITURAS));
        }

        Operacoes<PainelSolar, Leitura> operacoes = new OperacoesImpl();

        // d.1
        operacoes.imprimir(new ArrayList<PainelSolar>(leiturasPorPainel.keySet()));

        //d.2
        operacoes.imprimirLeituras(leiturasPorPainel);

        //d.3
        Map<PainelSolar, List<Leitura>> ordenado = operacoes.ordenar(leiturasPorPainel);
        operacoes.imprimirLeituras(ordenado);

        // d.4
        operacoes.analisarLeiturasDiscrepantes(leiturasPorPainel);
    }
}
