import java.util.ArrayList;

public class GerenciadorDeMemoria {
    // Lista de processos que serão gerenciados/alocados
    private ArrayList<Processo> filaDeProcessos = new ArrayList<>();
    
    // Vetor que representa a memória principal
    // Cada posição armazena o ID de um processo (ou 0 se estiver livre)
    private int[] memoria; 
    
    // Define qual algoritmo de alocação será usado (BF, FF, WF, NF)
    private String algoritmo;
    
    // Último índice utilizado (para o algoritmo Next-Fit)
    private int ultimoIndice = 0; 

    // Construtor: inicializa a memória com o tamanho especificado
    // e define o algoritmo de alocação a ser usado
    public GerenciadorDeMemoria(int tamanhoMemoria, String algoritmo) {
        this.memoria = new int[tamanhoMemoria]; 
        this.algoritmo = algoritmo;
    }

    // Adiciona um processo à fila de espera
    public void adicionarProcesso(Processo p) {
        System.out.println("Adicionando processo: " + p);  
        filaDeProcessos.add(p);
    }
    
    // Simula tempo de execução (apenas para deixar mais visual e didático)
    public void timeExecucao(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Exibe mensagem informando qual algoritmo está em execução
    public void algoritmoExecutando(String nomeAlgoritmo) {
        System.out.println("\n");
        System.out.println("\n================== Executando " + nomeAlgoritmo + " ==================");
    }

    // Método principal que decide qual algoritmo executar
    public String gerenciar(){
        if (algoritmo.equals("BF")) {
            return executarBF();
        } else if (algoritmo.equals("FF")) {
            return executarFF();
        } else if (algoritmo.equals("WF")) {
            return executarWF();
        } else if (algoritmo.equals("NF")) {
            return executarNF();
        } else {
            return "Algoritmo inválido.";
        }
    }

    // Mostra o estado atual da memória (quais processos ocupam quais posições)
    public void mostrarMemoria() {
        System.out.print("Memória: ");
        for (int i = 0; i < memoria.length; i++) {
            System.out.print(memoria[i] + " ");
        }
        System.out.println();
    }

    // -----------------------------
    // Algoritmo Best-Fit (melhor ajuste)
    // -----------------------------
    // Procura o menor bloco livre capaz de armazenar o processo
    public String executarBF() {
        algoritmoExecutando("Best-Fit");

        for (Processo p : filaDeProcessos) {
            timeExecucao(3);

            System.out.println("Tentando alocar " + p);
            int melhorInicio = -1; // índice inicial do melhor bloco encontrado
            int melhorTamanho = Integer.MAX_VALUE; // tamanho do menor bloco suficiente
            int i = 0;

            // Percorre toda a memória procurando blocos livres
            while (i < memoria.length) {

                if (memoria[i] == 0) { // início de um bloco livre
                    int inicio = i;
                    int tamanho = 0;

                    // Conta o tamanho do bloco contínuo de memória livre
                    while (i < memoria.length && memoria[i] == 0) {
                        tamanho++;
                        i++;
                    }

                    System.out.println("  Bloco livre encontrado (início=" + inicio + ", tamanho=" + tamanho + ")");
                    
                    // Atualiza se for o menor bloco possível que comporte o processo
                    if (tamanho >= p.getTamanho() && tamanho < melhorTamanho) {
                        melhorTamanho = tamanho;
                        melhorInicio = inicio;
                    }
                }
                i++;
            }

            // Se encontrou espaço, aloca
            if (melhorInicio != -1) {
                System.out.println("→ Alocando " + p + " no índice " + melhorInicio);
                for (int j = melhorInicio; j < melhorInicio + p.getTamanho(); j++) {
                    memoria[j] = p.getId();
                }
                mostrarMemoria();
            } else {
                return "Sem espaço para " + p;
            }
        }
        return "Best-Fit concluído.";
    }

    // -----------------------------
    // Algoritmo First-Fit (primeiro ajuste)
    // -----------------------------
    // Procura o primeiro bloco livre grande o suficiente
    public String executarFF() {
        algoritmoExecutando("First-Fit");

        for (Processo p : filaDeProcessos) {
            timeExecucao(3);

            System.out.println("Tentando alocar " + p);
            boolean alocado = false;

            // Percorre a memória do início ao fim
            for (int i = 0; i <= memoria.length - p.getTamanho(); i++) {
                boolean livre = true;

                // Verifica se o espaço é suficiente e contínuo
                for (int j = 0; j < p.getTamanho(); j++) {
                    if (memoria[i + j] != 0) {
                        livre = false;
                        break;
                    }
                }

                // Se encontrou espaço, aloca
                if (livre) {
                    System.out.println("→ Alocando " + p + " no índice " + i);
                    for (int j = 0; j < p.getTamanho(); j++) {
                        memoria[i + j] = p.getId();
                    }
                    alocado = true;
                    mostrarMemoria();
                    break;
                }
            }

            if (!alocado) {
                return "Sem espaço para " + p;
            }
        }
        return "First-Fit concluído.";
    }

    // -----------------------------
    // Algoritmo Worst-Fit (pior ajuste)
    // -----------------------------
    // Procura o maior bloco livre disponível
    public String executarWF() {
        algoritmoExecutando("Worst-Fit");
 
        for (Processo p : filaDeProcessos) {
            timeExecucao(3);

            System.out.println("Tentando alocar " + p);
            int piorInicio = -1;   // índice inicial do maior bloco
            int piorTamanho = -1;  // tamanho do maior bloco
            int i = 0;

            // Percorre toda a memória procurando blocos livres
            while (i < memoria.length) {
                if (memoria[i] == 0) {
                    int inicio = i;
                    int tamanho = 0;

                    // Conta o tamanho do bloco livre
                    while (i < memoria.length && memoria[i] == 0) {
                        tamanho++;
                        i++;
                    }

                    System.out.println("  Bloco livre encontrado (início=" + inicio + ", tamanho=" + tamanho + ")");
                    
                    // Atualiza se for o maior bloco encontrado
                    if (tamanho >= p.getTamanho() && tamanho > piorTamanho) {
                        piorTamanho = tamanho;
                        piorInicio = inicio;
                    }
                }
                i++;
            }

            // Se encontrou espaço, aloca
            if (piorInicio != -1) {
                System.out.println("→ Alocando " + p + " no índice " + piorInicio);
                for (int j = piorInicio; j < piorInicio + p.getTamanho(); j++) {
                    memoria[j] = p.getId();
                }
                mostrarMemoria();
            } else {
                return "Sem espaço para " + p;
            }
        }
        return "Worst-Fit concluído.";
    }

    // -----------------------------
    // Algoritmo Next-Fit
    // -----------------------------
    // Parecido com o First-Fit, mas começa a busca a partir da última posição usada
    public String executarNF() {
        algoritmoExecutando("Next-Fit");
    
        for (Processo p : filaDeProcessos) {
            timeExecucao(3);

            System.out.println("Tentando alocar " + p);
            boolean alocado = false;

            // Ponto de partida é o último índice utilizado
            int start = ultimoIndice;
            int i = start;
    
            // Continua procurando até dar uma volta completa na memória
            do {
                // Verifica se o processo cabe a partir da posição atual
                if (i + p.getTamanho() <= memoria.length) {
                    boolean livre = true;

                    // Confere se o espaço está realmente livre
                    for (int j = 0; j < p.getTamanho(); j++) {
                        if (memoria[i + j] != 0) {
                            livre = false;
                            break;
                        }
                    }

                    // Se encontrou espaço, aloca
                    if (livre) {
                        System.out.println("→ Alocando " + p + " no índice " + i);
                        for (int j = 0; j < p.getTamanho(); j++) {
                            memoria[i + j] = p.getId();
                        }
                        // Atualiza o último índice utilizado
                        ultimoIndice = (i + p.getTamanho()) % memoria.length;
                        alocado = true;
                        mostrarMemoria();
                        break;
                    }
                }

                // Avança uma posição (com wrap-around no fim da memória)
                i = (i + 1) % memoria.length;

            } while (i != start); // para quando tiver dado a volta completa
    
            if (!alocado) {
                return "Sem espaço para " + p;
            }
        }
        return "Next-Fit concluído.";
    }
    
}
