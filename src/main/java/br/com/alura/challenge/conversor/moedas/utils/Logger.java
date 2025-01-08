package br.com.alura.challenge.conversor.moedas.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    // Nome do arquivo de log onde o histórico será salvo
    private static final String ARQUIVO_LOG = "historico_conversoes.txt";

    // Formato para data e hora (dd/MM/yyyy HH:mm:ss)
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Registra uma conversão de moeda no arquivo de log.
     * O registro inclui data, hora, valor de entrada e o valor convertido.
     *
     * @param entrada   Valor original e moeda (ex: "100.00 USD")
     * @param resultado Valor convertido e moeda (ex: "500.00 BRL")
     */
    public void registrar(String entrada, String resultado) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO_LOG, true))) {
            // Obtém a data e hora atuais e formata
            String dataHora = LocalDateTime.now().format(FORMATO_DATA);

            // Escreve no arquivo o registro da conversão
            escritor.write(String.format("[%s] %s ➝ %s%n", dataHora, entrada, resultado));

        } catch (IOException e) {
            // Caso ocorra erro, exibe mensagem no console
            System.err.println("Erro ao salvar no log: " + e.getMessage());
        }
    }
}