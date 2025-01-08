package br.com.alura.challenge.conversor.moedas.servico;

import br.com.alura.challenge.conversor.moedas.modelo.ExchangeRateResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    // Chave da API para autenticação e acesso às taxas de câmbio
    private static final String API_KEY = "0def4c5237a1a59177ccc8e3";

    /**
     * Metodo que realiza a requisição HTTP para obter as taxas de câmbio
     * com base na moeda informada.
     *
     * @param moedaBase Código da moeda base (ex: USD, BRL)
     * @return Objeto ExchangeRateResponse contendo as taxas de câmbio
     * @throws Exception Em caso de erro na conexão ou leitura dos dados
     */
    public ExchangeRateResponse obterTaxasCambio(String moedaBase) throws Exception {
        // Monta a URL da requisição utilizando a chave da API e a moeda base
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + moedaBase);

        // Abre uma conexão HTTP com a URL especificada
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET"); // Define o metodo HTTP como GET

        // Lê a resposta da API através de um BufferedReader
        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;

        // Lê cada linha da resposta e adiciona ao StringBuilder
        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }
        leitor.close();  // Fecha o leitor após a leitura completa

        // Converte o JSON da resposta para um objeto ExchangeRateResponse usando Gson
        Gson gson = new Gson();
        return gson.fromJson(resposta.toString(), ExchangeRateResponse.class);
    }
}