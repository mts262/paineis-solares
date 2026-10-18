package br.edu.ifba.paineis.operacoes;

import java.util.List;
import java.util.Map;

public interface Operacoes <P, L>{

    // d.1
    public void imprimir(List<P> monitorados);

    // d.2
    public void imprimirLeituras(Map<P, List<L>> leiturasPorMonitorado);

    // d.3
    public Map<P, List<L>> ordenar(Map<P, List<L>> leiturasPorMonitorado);

    // d.4
    public List<P> compararDesempenho(Map<P, List<L>> leiturasPorMonitorado);
}
