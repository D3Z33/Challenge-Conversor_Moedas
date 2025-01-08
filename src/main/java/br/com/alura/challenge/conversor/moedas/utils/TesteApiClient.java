package br.com.alura.challenge.conversor.moedas.utils;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;
import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;

public class TesteApiClient {

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();

        try {
            ExchangeRateResponse resposta = apiClient.obterTaxasCambio("USD");
            System.out.println("Moeda Base: " + resposta.getBase_code());
            System.out.println("Taxa de câmbio para BRL: " + resposta.getConversion_rates().get("BRL"));
            System.out.println("Taxa de câmbio para EUR: " + resposta.getConversion_rates().get("EUR"));
            System.out.println("Taxa de câmbio para JPY: " + resposta.getConversion_rates().get("JPY"));
        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }
}