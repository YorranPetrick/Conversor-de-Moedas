# 💱 Conversor de Moedas

Este projeto é um **conversor de moedas** que utiliza a [API Economia Awesome](https://economia.awesomeapi.com.br) para obter taxas de câmbio em tempo real. Desenvolvido em **Java**, o projeto segue uma arquitetura organizada em camadas, separando responsabilidades em `application`, `models` e `controller`.

## 🚀 Tecnologias Utilizadas

- **Java**
- **Gson** – para desserialização de JSON
- **HTTP (HttpURLConnection)** – para realizar requisições à API


## ⚙️ Funcionamento

1. O usuário informa a moeda de origem, a moeda de destino.
2. O sistema realiza uma requisição HTTP à API.
3. Os dados recebidos em JSON são convertidos para objetos Java usando **Gson**.
4. O valor de conversão é exibido ao usuário.
5. Se houver erro, a classe ErrorMoeda é utilizada para a desserialização do JSON.
