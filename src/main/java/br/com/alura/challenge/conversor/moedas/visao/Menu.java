package br.com.alura.challenge.conversor.moedas.visao;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;
import br.com.alura.challenge.conversor.moedas.servico.Conversor;
import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;
import br.com.alura.challenge.conversor.moedas.utils.Logger;

import java.util.Scanner;

public class Menu {

    private final ApiClient apiClient = new ApiClient();
    private final Conversor conversor = new Conversor();
    private final Logger logger = new Logger();
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
            ExchangeRateResponse resposta = apiClient.obterTaxasCambio(moedaBase);
            Double taxa = resposta.getConversion_rates().get(moedaDestino);

            if (taxa != null) {
                double valorConvertido = conversor.converter(valor, taxa);
                String entrada = String.format("%.2f %s", valor, moedaBase);
                String resultado = String.format("%.2f %s", valorConvertido, moedaDestino);

                // Exibe resultado no console
                System.out.printf("Resultado: %s equivale a %s%n", entrada, resultado);

                // Registra a conversão no log
                logger.registrar(entrada, resultado);
            } else {
                System.out.println("Moeda de destino não encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }
}