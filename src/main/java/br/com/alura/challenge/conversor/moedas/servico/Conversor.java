package br.com.alura.challenge.conversor.moedas.servico;

public class Conversor {

    /**
     * Realiza a conversão de um valor com base na taxa informada
     *
     * @param valor Valor a ser convertido
     * @param taxa Taxa de conversão
     * @return Valor convertido
     */
    public double converter(double valor, double taxa) {
        return valor * taxa;
    }
}