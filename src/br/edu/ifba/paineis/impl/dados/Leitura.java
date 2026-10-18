package br.edu.ifba.paineis.impl.dados;

public class Leitura {

    private double potencia;
    private  double tensao;
    private double corrente;
    private  double temperatura;

    // a complexidade é O(1), pois realiza apenas quatro atribuições de valores,
    // independentemente da quantidade de leituras existentes no sistema.
    // O tempo de execução permanece constante, mesmo que o sistema monitore muitos painéis solares.
    public Leitura(double potencia, double tensao, double corrente, double temperatura) {
        this.potencia = potencia;
        this.tensao = tensao;
        this.corrente = corrente;
        this.temperatura = temperatura;
    }

    // a complexidade é O(1), pois retorna diretamente o valor de um atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public double getPotencia() {
        return potencia;
    }

    // a complexidade é O(1), pois realiza uma atribuição ao atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    // a complexidade é O(1), pois retorna diretamente o valor de um atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public double getTensao() {
        return tensao;
    }

    // a complexidade é O(1), pois realiza uma atribuição ao atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public void setTensao(double tensao) {
        this.tensao = tensao;
    }

    // a complexidade é O(1), pois retorna diretamente o valor de um atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public double getCorrente() {
        return corrente;
    }

    // a complexidade é O(1), pois realiza uma atribuição ao atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public void setCorrente(double corrente) {
        this.corrente = corrente;
    }

    // a complexidade é O(1), pois retorna diretamente o valor de um atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public double getTemperatura() {
        return temperatura;
    }

    // a complexidade é O(1), pois realiza uma atribuição ao atributo.
    // A quantidade de painéis e leituras armazenadas não aumenta o tempo de execução desse método.
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    // a complexidade é O(1), pois monta uma representação textual de quatro valores.
    // O custo não cresce com a quantidade de painéis ou de leituras armazenadas no sistema.
    @Override
    public String toString() {
        return "Potência: " +potencia+ "W | " +
                "Tensão: " +tensao+ "V | " +
                "Corrente: " +corrente+ "A | " +
                "Temperatura: " +temperatura+ "ºC ";
    }
}
