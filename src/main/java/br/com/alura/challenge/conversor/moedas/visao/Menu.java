package br.com.alura.challenge.conversor.moedas.visao;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;
import br.com.alura.challenge.conversor.moedas.servico.Conversor;
import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;
import br.com.alura.challenge.conversor.moedas.utils.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final ApiClient apiClient = new ApiClient();
    private final Conversor conversor = new Conversor();
    private final Logger logger = new Logger();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nBem-vindo ao Conversor de Moedas!");

                // Validação da moeda base
                System.out.print("Digite a moeda base (ex: USD, EUR, BRL): ");
                String moedaBase = scanner.next().toUpperCase();

                // Validação da moeda de destino
                System.out.print("Digite a moeda para conversão (ex: BRL, EUR, JPY): ");
                String moedaDestino = scanner.next().toUpperCase();

                // Validação do valor a ser convertido
                double valor = obterValorValido();

                // Requisição à API
                ExchangeRateResponse resposta = apiClient.obterTaxasCambio(moedaBase);

                // Verifica se a moeda de destino existe na resposta
                Double taxa = resposta.getConversion_rates().get(moedaDestino);

                if (taxa != null) {
                    // Realiza o cálculo
                    double valorConvertido = conversor.converter(valor, taxa);

                    // Formatação e exibição
                    String entrada = String.format("%.2f %s", valor, moedaBase);
                    String resultado = String.format("%.2f %s", valorConvertido, moedaDestino);

                    System.out.printf("Resultado: %s equivale a %s%n", entrada, resultado);

                    // Registra a conversão no log
                    logger.registrar(entrada, resultado);
                } else {
                    System.out.println("Erro: Moeda de destino inválida ou não suportada.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inválido. Certifique-se de inserir um número.");
                scanner.next();  // Limpa a entrada inválida
            } catch (Exception e) {
                System.out.println("Erro ao conectar com a API: " + e.getMessage());
            }

            // Pergunta ao usuário se deseja continuar
            System.out.print("\nDeseja realizar outra conversão? (s/n): ");
            continuar = scanner.next().equalsIgnoreCase("s");

            if (!continuar) {
                System.out.println("Obrigado por utilizar o Conversor de Moedas!");
            }
        }
    }

    /**
     * Solicita e valida um valor a ser convertido, garantindo que seja um número positivo maior que zero.
     *
     * - Se o valor inserido for negativo ou zero, o usuário será orientado a tentar novamente.
     * - Se a entrada não for um número (ex: letras ou símbolos), uma mensagem de erro será exibida,
     * e o programa continuará solicitando até que um valor válido seja inserido.
     *
     * @return Valor positivo maior que zero inserido pelo usuário.
     */
    private double obterValorValido() {
        double valor = -1;

        while (valor <= 0) {
            System.out.print("Digite o valor a ser convertido (deve ser maior que zero): ");

            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();

                if (valor <= 0) {
                    System.out.println("O valor precisa ser maior que zero. Tente novamente.\n");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira apenas números.\n");
                scanner.next();  // Limpa a entrada inválida para evitar loop infinito
            }
        }
        return valor;
    }
}