package br.com.yorranpetrick.ConversorDeMoedas.controller;

import br.com.yorranpetrick.ConversorDeMoedas.models.ErrorMoeda;
import br.com.yorranpetrick.ConversorDeMoedas.models.Moedas;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.List;
import java.util.Map;


public class ConexaoApi {
    private String uri;
    private Gson gson = new Gson();
    public ConexaoApi(String uri) {
        this.uri = uri;
    }

    public String acessoApi(int tipoVerificacao) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); //Criando um cliente
        HttpRequest request;

            if (tipoVerificacao == 1){
                request = HttpRequest.newBuilder()
                        .uri(URI.create("https://economia.awesomeapi.com.br/json/last/"+this.uri))
                        .build();
            }else {
                request = HttpRequest.newBuilder()
                        .uri(URI.create("https://economia.awesomeapi.com.br/json/daily/"+this.uri+"/5"))
                        .build();
            }

            // Envia a requisição de forma síncrona e recebe a resposta
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString() // Converte o corpo da resposta para String
            );

            return response.body();

    }

    public Moedas descerializacaoJson(String json) {
        try {
            // Tentando deserializar o JSON da moeda
            Type type = new TypeToken<Map<String, Moedas>>() {}.getType();
            Map<String, Moedas> moedasMap = gson.fromJson(json, type);

            Moedas moeda = moedasMap.values().iterator().next();

            return moeda;
        } catch (JsonSyntaxException e) {
            // Se o JSON de erro for detectado, tentamos deserializar o erro
            System.out.println("Tentando detectar o erro..... ");
        }
        return null;
    }

    public  List<Moedas> valorHistoricoCotacao(String json){
        try {
            Type listType = new TypeToken<List<Moedas>>(){}.getType();
            List<Moedas> moedasList = gson.fromJson(json.toString(), listType);
            return moedasList;

        }catch (JsonSyntaxException e){
            // Se o JSON de erro for detectado, tentamos deserializar o erro
            System.out.println("Tentando detectar o erro..... ");
        }
        return null;
    }

    public ErrorMoeda descerializacaoErrorMoeda(String json){
        ErrorMoeda errorMoeda = gson.fromJson(json, ErrorMoeda.class);
        return errorMoeda;
    }

}
