package br.com.alura.challenge.conversor.moedas.visao;

import br.com.alura.challenge.conversor.moedas.servico.ApiClient;
import br.com.alura.challenge.conversor.moedas.servico.Conversor;
import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;
import br.com.alura.challenge.conversor.moedas.utils.Logger;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Menu {

    private final ApiClient apiClient = new ApiClient();
    private final Conversor conversor = new Conversor();
    private final Logger logger = new Logger();
    private final Scanner scanner = new Scanner(System.in);

    // Lista de moedas suportadas
    private final Set<String> moedasSuportadas = new HashSet<>(Arrays.asList("USD", "BRL", "EUR", "JPY", "GBP"));

    public void exibirMenu() {
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("          Conversor de Moedas        ");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

                // Solicita e valida a moeda base
                String moedaBase = obterMoedaValida("Digite a moeda base (ex: USD, EUR, BRL): ");

                // Solicita e valida a moeda de destino
                String moedaDestino = obterMoedaValida("Digite a moeda para conversão (ex: BRL, EUR, JPY): ");

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
                    String entradaFormatada = formatarMoeda(valor, moedaBase);
                    String resultadoFormatado = formatarMoeda(valorConvertido, moedaDestino);

                    // Exibe resultado no console com timestamp
                    String resultadoFinal = String.format(
                            "[%s] %s ➝ %s",
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                            entradaFormatada, resultadoFormatado
                    );
                    System.out.println("Resultado: " + resultadoFinal);

                    // Registra no log
                    logger.registrar(entradaFormatada, resultadoFormatado);

                    // **Adição da conversão reversa após exibir o resultado principal**
                    System.out.print("\nDeseja realizar a conversão inversa? (s/n): ");
                    if (scanner.next().equalsIgnoreCase("s")) {
                        realizarConversaoReversa(moedaDestino, moedaBase, valorConvertido);
                    }

                } else {
                    System.out.println("Erro: Moeda de destino inválida ou não suportada.");
                }

            } catch (Exception e) {
                System.out.println("Erro ao conectar com a API: " + e.getMessage());
            }

            // Pergunta ao usuário se deseja continuar com outra conversão
            System.out.print("\nDeseja realizar outra conversão? (s/n): ");
            continuar = scanner.next().equalsIgnoreCase("s");

            // Mensagem de despedida ao finalizar
            if (!continuar) {
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("   Obrigado por utilizar o Conversor de Moedas!");
                System.out.println("            Tenha um ótimo dia! 🚀");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        }
    }

    /**
     * Realiza a conversão reversa utilizando o valor convertido anteriormente.
     *
     * @param moedaBase Moeda de destino da conversão original (agora será a moeda base).
     * @param moedaDestino Moeda base da conversão original (agora será a moeda de destino).
     * @param valor Valor já convertido, a ser usado como base para a conversão reversa.
     */
    private void realizarConversaoReversa(String moedaBase, String moedaDestino, double valor) {
        try {
            ExchangeRateResponse resposta = apiClient.obterTaxasCambio(moedaBase);
            Double taxaReversa = resposta.getConversion_rates().get(moedaDestino);

            if (taxaReversa != null) {
                double valorReverso = conversor.converter(valor, taxaReversa);
                String entradaFormatada = formatarMoeda(valor, moedaBase);
                String resultadoFormatado = formatarMoeda(valorReverso, moedaDestino);

                System.out.printf("Conversão Reversa: %s equivale a %s%n", entradaFormatada, resultadoFormatado);
                logger.registrar(entradaFormatada, resultadoFormatado);
            } else {
                System.out.println("Erro: Moeda de destino não encontrada na conversão reversa.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API para conversão reversa: " + e.getMessage());
        }
    }

    /**
     * Solicita e valida um valor a ser convertido, garantindo que seja um número positivo maior que zero.
     *
     * - A primeira solicitação será simples, sem a mensagem de restrição.
     * - Se o valor inserido for negativo ou zero, a mensagem com a restrição aparecerá.
     * - Se a entrada não for um número (ex: letras ou símbolos), o usuário será orientado a inserir apenas números.
     *
     * @return Valor positivo maior que zero inserido pelo usuário.
     */
    private double obterValorValido() {
        double valor = -1;
        boolean primeiraTentativa = true;

        while (valor <= 0) {
            // Mensagem muda após erro
            if (primeiraTentativa) {
                System.out.print("Digite o valor a ser convertido: ");
                primeiraTentativa = false;
            } else {
                System.out.print("Digite o valor a ser convertido (deve ser maior que zero): ");
            }

            // Verifica se a entrada é um número
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();

                // Valida se o valor é maior que zero
                if (valor <= 0) {
                    System.out.println("O valor precisa ser maior que zero. Tente novamente.\n");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira apenas números.\n");
                scanner.next();  // Limpa a entrada inválida
            }
        }
        return valor;
    }

    /**
     * Solicita ao usuário uma moeda e valida se ela está na lista de moedas suportadas.
     *
     * @param mensagem Mensagem exibida para solicitar a moeda.
     * @return Moeda válida inserida pelo usuário.
     */
    private String obterMoedaValida(String mensagem) {
        String moeda;

        while (true) {
            System.out.print(mensagem);
            moeda = scanner.next().toUpperCase();

            if (moedasSuportadas.contains(moeda)) {
                break;
            } else {
                System.out.println("Moeda inválida! Moedas suportadas: " + moedasSuportadas + "\n");
            }
        }
        return moeda;
    }

    /**
     * Formata um valor monetário de acordo com a moeda informada.
     *
     * @param valor Valor a ser formatado.
     * @param moeda Código da moeda (ex: USD, BRL).
     * @return Valor formatado como string.
     */
    private String formatarMoeda(double valor, String moeda) {
        Locale locale = obterLocalePorMoeda(moeda);
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoeda.format(valor);
    }

    /**
     * Define o Locale (localização) com base no código da moeda.
     *
     * @param moeda Código da moeda (ex: USD, BRL).
     * @return Locale correspondente à moeda.
     */
    private Locale obterLocalePorMoeda(String moeda) {
        return switch (moeda) {
            case "USD" -> Locale.US;
            case "BRL" -> new Locale("pt", "BR");
            case "EUR" -> Locale.FRANCE;
            case "JPY" -> Locale.JAPAN;
            case "GBP" -> Locale.UK;
            default -> Locale.US;
        };
    }
}