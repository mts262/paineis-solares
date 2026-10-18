package br.edu.ifba.paineis.impl.dados;

public class PainelSolar implements Comparable<PainelSolar> {

    private String idPainel;

    // a complexidade é O(1), pois realiza apenas uma atribuição de valor.
    // O tempo de execução permanece constante, mesmo que o sistema monitore muitos painéis solares.
    public PainelSolar(String idPainel) {
        this.idPainel = idPainel;
    }

    // a complexidade é O(1), pois retorna diretamente o valor de um atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public String getIdPainel() {
        return idPainel;
    }

    // a complexidade é O(1), pois realiza uma atribuição ao atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public void setIdPainel(String idPainel) {
        this.idPainel = idPainel;
    }

    // a complexidade é O(1), pois compara um painel solar a outro.
    // O custo não cresce com a quantidade de painéis ou de leituras armazenadas no sistema.
    @Override
    public int compareTo(PainelSolar ps) {
        return getIdPainel().compareTo(ps.getIdPainel());
    }

    // a complexidade é O(1), pois monta uma representação textual de um valor.
    // O custo não cresce com a quantidade de painéis ou de leituras armazenadas no sistema.
    @Override
    public String toString() {
        return "Id Painel Solar: " + idPainel;
    }
}
