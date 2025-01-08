package br.com.alura.challenge.conversor.moedas.servico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static final String API_KEY = "0def4c5237a1a59177ccc8e3";

    // Metodo para buscar taxas de câmbio com base na moeda
    public String obterTaxasCambio(String moedaBase) throws Exception {
        // Monta a URL da requisição
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + moedaBase);

        // Abre a conexão HTTP
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        // Lê a resposta da API
        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;

        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }
        leitor.close();

        // Retorna o JSON como String
        return resposta.toString();
    }
}
