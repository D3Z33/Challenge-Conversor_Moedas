# Challenge - Conversor de Moedas 💱

Bem-vindo ao **Conversor de Moedas**! Este projeto foi desenvolvido para oferecer uma experiência realista de conversão de moedas em tempo real, utilizando a **API de taxas de câmbio Exchange Rate API**.  

O conversor foi projetado para ser uma ferramenta funcional que não só realiza cálculos de forma rápida, mas também traz recursos extras que facilitam o dia a dia de quem precisa lidar com diferentes moedas.

---

## 🚀 Funcionalidades

🔸 **Conversão de Moedas** – Realize conversões de valores em tempo real entre **USD, BRL, EUR, JPY e GBP**.

🔸 **Conversão Inversa** – Após uma conversão, realize a operação inversa com um único clique.

🔸 **Validação de Dados** – Entradas inválidas são tratadas de forma amigável, evitando travamentos e garantindo que o usuário insira apenas valores numéricos ou moedas suportadas.

🔸 **Estilização do Console** – Saídas no terminal com divisores e mensagens de feedback.

---

## 📁 Estrutura do Projeto

```bash
Challenge-Conversor_Moedas
│
├── src
│   └── main
│       └── java
│           └── br
│               └── com
│                   └── alura
│                       └── challenge
│                           └── conversor
│                               └── moedas
│                                   ├── aplicacao
│                                   │   ├── Main.java
│                                   │   └── aplicacao.java
│                                   │
│                                   ├── demo
│                                   │   └── demo.mp4
│                                   │
│                                   ├── modelo
│                                   │   └── ExchangeRateResponse.java
│                                   │
│                                   ├── servico
│                                   │   ├── ApiClient.java
│                                   │   └── Conversor.java
│                                   │
│                                   ├── utils
│                                   │   ├── Logger.java
│                                   │   ├── TesteApiClient.java
│                                   │   └── TesteGson.java
│                                   │
│                                   └── visao
│                                       └── Menu.java
│
├── .gitignore
├── historico_conversoes.txt
└── README.md
```

---

## 📚 Cursos Inspiradores

Este projeto foi inspirado e desenvolvido com base nos cursos da **Alura** para consolidação dos seguintes conhecimentos:

🔹 **1° Curso:** Java - Criando sua primeira aplicação  
🔹 **2° Curso:** Java - Aplicando Orientação a Objetos  
🔹 **3° Curso:** Java - Trabalhando com Listas e Coleções de Dados  
🔹 **4° Curso:** Java - Consumindo API, Gravando Arquivos e Lidando com Erros

---

## ⚙️ Tecnologias Utilizadas

🔹 **Java 11** – Linguagem principal utilizada no desenvolvimento do projeto.  
🔹 **IntelliJ IDEA** – Ambiente de desenvolvimento integrado (IDE).  
🔹 **Biblioteca Gson** – Utilizada para manipulação de dados em formato JSON.  
🔹 **API Exchange Rate** – API responsável por fornecer as taxas de câmbio em tempo real.

---

## 🛠️ Executando o Projeto

Se você nunca programou em Java, não se preocupe! Este guia foi feito para que qualquer pessoa consiga rodar o projeto, mesmo sem experiência prévia.

### 📥 Clonar o Repositório:
```bash
git clone https://github.com/D3Z33/Challenge-Conversor_Moedas.git
```

---

## 📂 Importar o Projeto no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Clique em **File > Open**.
3. Selecione a pasta do projeto clonado.

---

## ▶️ Executar o Projeto

1. Localize o arquivo **Main.java**.
2. Clique com o botão direito sobre ele.
3. Selecione **Run 'Main'**.
4. 🎉 O conversor será executado diretamente no console!

---

## ⚠️ Tratativas de Erro Implementadas

- **Moeda inválida:**
    - Se uma moeda não suportada for inserida, o sistema exibe um aviso e pede nova entrada.
- **Valor não numérico:**
    - Ao inserir letras ou símbolos em campos numéricos, o sistema solicita um novo valor válido.
- **Loop de Validação:**
    - O menu permanece em execução até que o usuário digite **"n"** explicitamente para encerrar.

---

## 🎯 Por que este projeto é útil?

O **Conversor de Moedas** oferece mais do que um simples cálculo de câmbio:

- **Conversão Inversa e Múltipla:**
    - Automatiza processos, economizando tempo e evitando múltiplas consultas manuais.
- **Profissionais e Viajantes:**
    - Ideal para quem realiza transações internacionais ou viaja frequentemente.
- **Estudantes e Programadores:**
    - Excelente projeto prático para desenvolver habilidades em Java e consumo de APIs.

A automação e praticidade no processo de conversão tornam o fluxo de trabalho mais ágil e eficiente, proporcionando precisão e rapidez em operações financeiras.

---

## 🎥 Demonstração do Projeto

<div align="center">
  <strong>⚠️ Atenção:</strong>  
  Clique no vídeo abaixo para assistir à demonstração.  
  <br>
  Você será redirecionado para o Imgur pois o GitHub não suporta vídeos diretamente.
</div>

<br>

<div align="center">
  <a href="https://imgur.com/sQ2zuc0">
    <img src="https://i.imgur.com/sQ2zuc0.png" alt="Assista à demonstração no Imgur">
  </a>
</div>

---

## 🔗 Contato e Redes

- [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github)](https://github.com/D3Z33)
- [![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)](https://discord.com/users/deze_e)

---

**Espero que este projeto ajude você a entender um pouco mais sobre Java, consumo de API e boas práticas no desenvolvimento de aplicações em console!** 😃