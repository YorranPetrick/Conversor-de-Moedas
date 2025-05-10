package br.com.yorranpetrick.ConversorDeMoedas.models;

import com.google.gson.annotations.SerializedName;

public class ErrorMoeda {
    @SerializedName("status")
    private int statusError;
    @SerializedName("code")
    private String codigoError;
    @SerializedName("message")
    private String mensagemError;


    @Override
    public String toString() {
        return "Erro Detectado {" +
                "Status do Erro =" + statusError +
                ", Codigo do Error ='" + codigoError + '\'' +
                ", Mensagem do Erro ='" + mensagemError + '\'' +
                '}';
    }
}
