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