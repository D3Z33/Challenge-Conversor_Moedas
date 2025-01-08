package br.com.alura.challenge.conversor.moedas.visao;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;
import br.com.alura.challenge.conversor.moedas.servico.Conversor;
import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;

import java.util.Scanner;

public class Menu {

    private final ApiClient apiClient = new ApiClient();
    private final Conversor conversor = new Conversor();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("Bem-vindo ao Conversor de Moedas!");
        System.out.print("Digite a moeda base (ex: USD, EUR, BRL): ");
        String moedaBase = scanner.next().toUpperCase();

        System.out.print("Digite a moeda para conversão (ex: BRL, EUR, JPY): ");
        String moedaDestino = scanner.next().toUpperCase();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        try {
            // Obtem taxas de câmbio para a moeda base
            ExchangeRateResponse resposta = apiClient.obterTaxasCambio(moedaBase);
            Double taxa = resposta.getConversion_rates().get(moedaDestino);

            if (taxa != null) {
                // Realiza a conversão
                double valorConvertido = conversor.converter(valor, taxa);
                System.out.printf("Resultado: %.2f %s equivale a %.2f %s%n",
                        valor, moedaBase, valorConvertido, moedaDestino);
            } else {
                System.out.println("Moeda de destino não encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }
}