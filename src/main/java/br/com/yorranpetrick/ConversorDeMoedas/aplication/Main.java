package br.com.yorranpetrick.ConversorDeMoedas.aplication;

import br.com.yorranpetrick.ConversorDeMoedas.controller.ConexaoApi;
import br.com.yorranpetrick.ConversorDeMoedas.models.ErrorMoeda;
import br.com.yorranpetrick.ConversorDeMoedas.models.Moedas;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String menuPrincipal = """
                |------------------------------------------------------|
                | Bem vindo, Escolha uma de nossas ferramentas         |
                | 1 -> Conversão de Moedas                             |
                | 2 -> Historico de Valor da Moeda nos ultimos 5 dias  |
                |------------------------------------------------------|
                """;
        String moedasExistentes = """
                |----------------------------------------|
                | Digite a Sigla de duas Moedas Desejada |
                | BRL -> Real Brasileiro                 |
                | DZD -> Dinar Argelino                  |
                | USD -> Dólar Americano                 |
                | CAD -> Dólar Canadense                 |
                | EUR -> Euro                            |
                |----------------------------------------|
                """;
        boolean iteracao = true;
        String primeiraMoeda, segundaMoeda, respostaApi;
        ConexaoApi conexao;
        Moedas moeda;

        while (iteracao){
            System.out.println(menuPrincipal);
            int escolhaMenu = scanner.nextInt();
            scanner.nextLine(); // Recolher a quebra de linha do buffer

            switch (escolhaMenu){
                case 1:
                    System.out.println(moedasExistentes);
                    primeiraMoeda = scanner.nextLine();
                    segundaMoeda = scanner.nextLine();
                    conexao = new ConexaoApi(primeiraMoeda.toUpperCase()+"-"+segundaMoeda.toUpperCase());

                    respostaApi = conexao.acessoApi(1); // chamada única
                    moeda = conexao.descerializacaoJson(respostaApi);

                    if (moeda != null) {
                        System.out.println(moeda);
                    } else {
                        ErrorMoeda erro = conexao.descerializacaoErrorMoeda(respostaApi);
                        System.out.println(erro);
                    }
                    break;

                case 2:
                    System.out.println(moedasExistentes);
                    primeiraMoeda = scanner.nextLine();
                    segundaMoeda = scanner.nextLine();
                    conexao = new ConexaoApi(primeiraMoeda.toUpperCase()+"-"+segundaMoeda.toUpperCase());
                    respostaApi = conexao.acessoApi(2);
                    List<Moedas> moedas = conexao.valorHistoricoCotacao(respostaApi);

                    if (moedas != null){
                        int diasCotacao = 5;
                        for(Moedas moedasList: moedas){
                            System.out.println("Dia inical da contação " + moedasList.getDataAtualCotacao() + "\n"+
                                    "Valor no Dia "+diasCotacao +" -> " + moedasList.getValorDaConversao());
                            diasCotacao--;
                        }
                    }else {
                        ErrorMoeda erro = conexao.descerializacaoErrorMoeda(respostaApi);
                        System.out.println(erro);
                    }
                    break;

                case 0:
                    iteracao = false;
                    break;

                default:
                    iteracao = false;
            }
        }
    }
}