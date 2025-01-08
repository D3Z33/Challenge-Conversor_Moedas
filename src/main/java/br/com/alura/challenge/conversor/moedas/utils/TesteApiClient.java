package br.com.alura.challenge.conversor.moedas.utils;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;

public class TesteApiClient {

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();

        try {
            String resposta = apiClient.obterTaxasCambio("USD");
            System.out.println("Resposta da API:\n" + resposta);
        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }
}