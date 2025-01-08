package br.com.alura.challenge.conversor.moedas.visao;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;
import br.com.alura.challenge.conversor.moedas.servico.Conversor;
import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;
import br.com.alura.challenge.conversor.moedas.utils.Logger;

import java.util.Scanner;

public class Menu {

    // Instâncias das classes necessárias para a execução do programa
    private final ApiClient apiClient = new ApiClient();  // Cliente HTTP para comunicação com a API
    private final Conversor conversor = new Conversor();  // Lógica de conversão de valores
    private final Logger logger = new Logger();           // Registra as conversões em arquivo
    private final Scanner scanner = new Scanner(System.in); // Lê entradas do usuário pelo console

    /**
     * Exibe o menu do conversor de moedas e permite múltiplas conversões em loop.
     */
    public void exibirMenu() {
        boolean continuar = true;  // Variável de controle para o loop de conversões

        // Loop principal para permitir múltiplas conversões
        while (continuar) {
            System.out.println("\nBem-vindo ao Conversor de Moedas!");

            // Solicita a moeda base (de origem)
            System.out.print("Digite a moeda base (ex: USD, EUR, BRL): ");
            String moedaBase = scanner.next().toUpperCase();

            // Solicita a moeda de destino (para conversão)
            System.out.print("Digite a moeda para conversão (ex: BRL, EUR, JPY): ");
            String moedaDestino = scanner.next().toUpperCase();

            // Solicita o valor a ser convertido
            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            try {
                // Faz a requisição à API para obter as taxas de câmbio
                ExchangeRateResponse resposta = apiClient.obterTaxasCambio(moedaBase);

                // Obtém a taxa de câmbio para a moeda de destino
                Double taxa = resposta.getConversion_rates().get(moedaDestino);

                if (taxa != null) {
                    // Realiza o cálculo da conversão
                    double valorConvertido = conversor.converter(valor, taxa);

                    // Formata os resultados para exibição
                    String entrada = String.format("%.2f %s", valor, moedaBase);
                    String resultado = String.format("%.2f %s", valorConvertido, moedaDestino);

                    // Exibe o resultado no console
                    System.out.printf("Resultado: %s equivale a %s%n", entrada, resultado);

                    // Registra a conversão no arquivo de log
                    logger.registrar(entrada, resultado);
                } else {
                    // Exibe uma mensagem caso a moeda de destino não seja encontrada
                    System.out.println("Moeda de destino não encontrada.");
                }

            } catch (Exception e) {
                // Trata exceções de erro na conexão com a API
                System.out.println("Erro ao conectar com a API: " + e.getMessage());
            }

            // Pergunta ao usuário se deseja realizar outra conversão
            System.out.print("\nDeseja realizar outra conversão? (s/n): ");
            continuar = scanner.next().equalsIgnoreCase("s");  // Continua se o usuário digitar 's'

            // Mensagem de encerramento do programa
            if (!continuar) {
                System.out.println("Obrigado por utilizar o Conversor de Moedas!");
            }
        }
    }
}