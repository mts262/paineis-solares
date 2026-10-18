package br.edu.ifba.paineis.sensoriamento;

import java.util.List;

public interface Sensoriamento<L> {

    public List<L> capturarLeituras(int totalLeituras);
}
