package br.com.alura.challenge.conversor.moedas.utils;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class TesteGson {

    public static void main(String[] args) {
        // Simulando um mapa de dados para converter em JSON
        Map<String, Object> dados = new HashMap<>();
        dados.put("moeda", "USD");
        dados.put("valor", 100.0);

        // Criando um objeto Gson
        Gson gson = new Gson();

        // Convertendo o mapa em JSON
        String json = gson.toJson(dados);
        System.out.println("JSON gerado: " + json);

        // Verificando se é possível desserializar o JSON
        Map<String, Object> resultado = gson.fromJson(json, HashMap.class);
        System.out.println("Valor desserializado: " + resultado.get("valor"));
    }
}