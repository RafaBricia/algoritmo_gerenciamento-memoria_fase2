# Simulador de Gerenciamento de Memória em Java

## Descrição

Este projeto simula a **alocação de processos na memória** utilizando diferentes **algoritmos de alocação**:

- **Best-Fit (BF)** – escolhe o **menor espaço livre** que seja suficiente para o processo.  
- **First-Fit (FF)** – escolhe o **primeiro espaço livre** encontrado que seja suficiente.  
- **Worst-Fit (WF)** – escolhe o **maior espaço livre** disponível.  
- **Circular-Fit (CF / Next-Fit)** – similar ao *First-Fit*, mas continua a busca a partir da **última posição utilizada**, circulando pela memória se necessário.  

Cada algoritmo mostra como os processos são inseridos na memória e permite comparar as diferenças de fragmentação e ocupação.  

---

## Estrutura do Projeto

- **Main.java** – ponto de entrada do programa; cria cenários de teste e executa os 4 algoritmos de alocação.  
- **Processo.java** – representa um processo com `id` e `tamanho` em unidades de memória.  
- **GerenciadorDeMemoria.java** – gerencia a memória como um vetor de inteiros e implementa os algoritmos:
  - `executarBF()`  
  - `executarFF()`  
  - `executarWF()`  
  - `executarCF()`  
  - `mostrarMemoria()` para exibir o estado da memória.  

---

## Como funciona

1. Crie um **GerenciadorDeMemoria**, informando o tamanho da memória e o algoritmo desejado:  

```
GerenciadorDeMemoria gm = new GerenciadorDeMemoria(20, "BF");
```

```
gm.adicionarProcesso(new Processo(1, 5));
```

```
gm.gerenciar();
```

```
gm.mostrarMemoria();
```

```
=== BEST-FIT ===
Processo 1 (tam=5) alocado em [0-4]
Processo 2 (tam=3) alocado em [5-7]
Processo 3 (tam=7) alocado em [8-14]
Memória final:
1 1 1 1 1 2 2 2 3 3 3 3 3 3 3 0 0 0 0 0 

=== FIRST-FIT ===
Processo 4 (tam=6) alocado em [0-5]
Processo 5 (tam=4) alocado em [6-9]
Processo 6 (tam=5) alocado em [10-14]
Memória final:
4 4 4 4 4 4 5 5 5 5 6 6 6 6 6 0 0 0 0 0 

=== WORST-FIT ===
Processo 7 (tam=4) alocado em [0-3]
Processo 8 (tam=6) alocado em [4-9]
Processo 9 (tam=5) alocado em [10-14]
Memória final:
7 7 7 7 8 8 8 8 8 8 9 9 9 9 9 0 0 0 0 0 

=== CIRCULAR-FIT ===
Processo 10 (tam=5) alocado em [0-4]
Processo 11 (tam=7) alocado em [5-11]
Processo 12 (tam=6) alocado em [12-17]
Memória final:
10 10 10 10 10 11 11 11 11 11 11 12 12 12 12 12 12 0 0 0 

```

---

## 💡 Funcionalidades Extras

Impressão detalhada da alocação de cada processo (posição inicial e final).

Estado final da memória após cada algoritmo.

Comparação clara entre os diferentes métodos de alocação.

Fácil adaptação para testar cenários de fragmentação interna e externa.

---

## 🚀 Como Rodar

1) Compile todos os arquivos Java:

```
javac *.java

```
2) Execute o programa:

```
java Main
```