package br.com.alura.challenge.conversor.moedas.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String ARQUIVO_LOG = "historico_conversoes.txt";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Registra uma conversão no arquivo de log
     *
     * @param entrada Valor e moeda de entrada
     * @param resultado Valor e moeda de saída
     */
    public void registrar(String entrada, String resultado) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO_LOG, true))) {
            String dataHora = LocalDateTime.now().format(FORMATO_DATA);
            escritor.write(String.format("[%s] %s ➝ %s%n", dataHora, entrada, resultado));
        } catch (IOException e) {
            System.err.println("Erro ao salvar no log: " + e.getMessage());
        }
    }
}