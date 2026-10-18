package br.edu.ifba.paineis.impl;

import br.edu.ifba.paineis.impl.dados.Leitura;
import br.edu.ifba.paineis.sensoriamento.Sensoriamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensoriamentoImpl implements Sensoriamento<Leitura> {

    private static final double TENSAO_NORMAL = 32;
    private static final double OCILACAO_MAXIMA_TENSAO = 10;

    private static final double CORRENTE_NORMAL = 8;
    private static final double OCILACAO_MAXIMA_CORRENTE = 12;

    private static final double TEMPERATURA_NORMAL = 30;
    private static final double OCILACAO_MAXIMA_TEMPERATURA = 15;

    // A complexidade é O(N), pois o laço é executado N vezes e, em cada repetição, realiza uma
    // quantidade constante de operações para gerar os valores dos sensores e adicionar uma nova leitura à lista.
    // O tempo de execução e a quantidade de memória utilizada aumentam linearmente conforme aumenta o total de leituras.
    @Override
    public List<Leitura> capturarLeituras(int totalLeituras) {
        List<Leitura> leituras = new ArrayList<>();

        Random randomizador = new Random();

        for (int i = 0; i < totalLeituras; i++) {
            double ocilacaoTensao = TENSAO_NORMAL * randomizador.nextDouble(OCILACAO_MAXIMA_TENSAO) / 100;
            ocilacaoTensao = randomizador.nextBoolean()? TENSAO_NORMAL + ocilacaoTensao: TENSAO_NORMAL - ocilacaoTensao;

            double ocilacaoCorrente = CORRENTE_NORMAL * randomizador.nextDouble(OCILACAO_MAXIMA_CORRENTE) / 100;
            ocilacaoCorrente = randomizador.nextBoolean()? CORRENTE_NORMAL + ocilacaoCorrente: CORRENTE_NORMAL - ocilacaoCorrente;

            double ocilacaoTemperatura = TEMPERATURA_NORMAL * randomizador.nextDouble(OCILACAO_MAXIMA_TEMPERATURA) / 100;
            ocilacaoTemperatura = randomizador.nextBoolean()? TEMPERATURA_NORMAL + ocilacaoTemperatura: TEMPERATURA_NORMAL - ocilacaoTemperatura;


            if (ocilacaoTensao < 0) {
                ocilacaoTensao = 0;
            }
            if (ocilacaoCorrente < 0) {
                ocilacaoCorrente = 0;
            }

            ocilacaoTensao = Math.round(ocilacaoTensao * 100.0) / 100.0;
            ocilacaoCorrente = Math.round(ocilacaoCorrente * 100.0) / 100.0;
            ocilacaoTemperatura = Math.round(ocilacaoTemperatura * 100.0) / 100.0;

            double ocilacaoPotencia = ocilacaoTensao * ocilacaoCorrente;

            ocilacaoPotencia = Math.round(ocilacaoPotencia * 100.0) / 100.0;

            leituras.add(new Leitura(ocilacaoPotencia, ocilacaoTensao, ocilacaoCorrente, ocilacaoTemperatura));
        }

        return leituras;
    }
}
