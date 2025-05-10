package br.com.yorranpetrick.ConversorDeMoedas.models;

import com.google.gson.annotations.SerializedName;

public class Moedas {
    @SerializedName("name")
    private String nomeDaConversao;
    @SerializedName("bid")
    private String valorDaConversao;
    @SerializedName("create_date")
    private String dataAtualCotacao;

    public String getValorDaConversao() {
        return valorDaConversao;
    }
    public String getDataAtualCotacao() {
        return dataAtualCotacao;
    }

    @Override
    public String toString() {
        return "Nome da Conversão = '" + nomeDaConversao + '\'' +
                ", Valor Da Conversao = '" + valorDaConversao + '\'' +
                ", Ultima data da Cotação = "+ dataAtualCotacao;

    }
}
