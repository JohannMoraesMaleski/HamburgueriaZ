# HamburgueriaZ

Aplicativo Android para gerenciamento de pedidos de uma hamburgueria, desenvolvido em Java utilizando Android SDK.

## Funcionalidades
- Seleção de diferentes tipos de lanches (X-Burger, X-Salada, X-Bacon)
- Adição de complementos (Bacon, Queijo, Onion Rings)
- Definição da quantidade de lanches
- Cálculo automático do valor total do pedido
- Resumo detalhado do pedido
- Envio do pedido por e-mail

## Estrutura do Projeto
- `app/src/main/java/com/example/hamburgueriaz/` — Código-fonte principal (Java)
- `app/src/main/res/` — Recursos (layouts, strings, imagens)
- `app/src/main/AndroidManifest.xml` — Configurações do aplicativo
- `build.gradle` — Configuração de build do projeto

## Tecnologias Utilizadas
- Java 11
- Android SDK (minSdk 23, targetSdk 35)
- AndroidX, Material Components

## Como Executar
1. Clone o repositório ou copie os arquivos para sua máquina.
2. Abra o projeto no Android Studio.
3. Conecte um dispositivo Android ou utilize um emulador.
4. Clique em "Run" para compilar e instalar o app.

## Configuração de Build
- O projeto utiliza Gradle para build e gerenciamento de dependências.
- As dependências principais estão em `app/build.gradle`.

## Observações
- O envio de pedidos é feito via e-mail para `estudanteads2025@gmail.com`.
- É necessário ter um cliente de e-mail instalado no dispositivo para utilizar essa funcionalidade.

## Licença
Este projeto é apenas para fins educacionais.
