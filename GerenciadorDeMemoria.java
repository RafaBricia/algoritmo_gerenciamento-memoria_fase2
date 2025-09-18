import java.util.ArrayList;

public class GerenciadorDeMemoria {
    private ArrayList<Processo> filaDeProcessos = new ArrayList<>();
    private int[] memoria; 
    private String algoritmo;
    private int ultimoIndice = 0; 

    public GerenciadorDeMemoria(int tamanhoMemoria, String algoritmo) {
        this.memoria = new int[tamanhoMemoria]; 
        this.algoritmo = algoritmo;
    }

    public void adicionarProcesso(Processo p) {
        System.out.println("Adicionando processo: " + p);  
        filaDeProcessos.add(p);
    }
    
    public String executarBF() {
        System.out.println("\n");
        System.out.println("\n================== Executando Best-Fit ==================");
        for (Processo p : filaDeProcessos) {

            try {
                Thread.sleep(300); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Tentando alocar " + p);
            int melhorInicio = -1;
            int melhorTamanho = Integer.MAX_VALUE;
            int i = 0;

            while (i < memoria.length) {

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (memoria[i] == 0) {
                    int inicio = i;
                    int tamanho = 0;
                    while (i < memoria.length && memoria[i] == 0) {
                        tamanho++;
                        i++;
                    }
                    System.out.println("  Bloco livre encontrado (início=" + inicio + ", tamanho=" + tamanho + ")");
                    if (tamanho >= p.getTamanho() && tamanho < melhorTamanho) {
                        melhorTamanho = tamanho;
                        melhorInicio = inicio;
                    }
                }
                i++;
            }

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

    public String executarFF() {
        System.out.println("\n");
        System.out.println("\n================== Executando First-Fit ==================");
        for (Processo p : filaDeProcessos) {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Tentando alocar " + p);
            boolean alocado = false;
            for (int i = 0; i <= memoria.length - p.getTamanho(); i++) {
                boolean livre = true;
                for (int j = 0; j < p.getTamanho(); j++) {
                    if (memoria[i + j] != 0) {
                        livre = false;
                        break;
                    }
                }
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

    public String executarWF() {
        System.out.println("\n");

        System.out.println("\n================== Executando Worst-Fit ==================");
        for (Processo p : filaDeProcessos) {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Tentando alocar " + p);
            int piorInicio = -1;
            int piorTamanho = -1;
            int i = 0;

            while (i < memoria.length) {
                if (memoria[i] == 0) {
                    int inicio = i;
                    int tamanho = 0;
                    while (i < memoria.length && memoria[i] == 0) {
                        tamanho++;
                        i++;
                    }
                    System.out.println("  Bloco livre encontrado (início=" + inicio + ", tamanho=" + tamanho + ")");
                    if (tamanho >= p.getTamanho() && tamanho > piorTamanho) {
                        piorTamanho = tamanho;
                        piorInicio = inicio;
                    }
                }
                i++;
            }

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

    public String executarCF() {
        System.out.println("\n");

        System.out.println("\n================== Executando Circular-Fit ==================");
        for (Processo p : filaDeProcessos) {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Tentando alocar " + p);
            boolean alocado = false;
            int start = ultimoIndice;
            int i = start;

            do {

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i + p.getTamanho() <= memoria.length) {
                    boolean livre = true;
                    for (int j = 0; j < p.getTamanho(); j++) {
                        if (memoria[i + j] != 0) {
                            livre = false;
                            break;
                        }
                    }
                    if (livre) {
                        System.out.println("→ Alocando " + p + " no índice " + i);
                        for (int j = 0; j < p.getTamanho(); j++) {
                            memoria[i + j] = p.getId();
                        }
                        ultimoIndice = (i + p.getTamanho()) % memoria.length;
                        alocado = true;
                        mostrarMemoria();
                        break;
                    }
                }
                i = (i + 1) % memoria.length;
            } while (i != start);

            if (!alocado) {
                return "Sem espaço para " + p;
            }
        }
        return "Circular-Fit concluído.";
    }

    public String gerenciar(){
        if (algoritmo.equals("BF")) {
            return executarBF();
        } else if (algoritmo.equals("FF")) {
            return executarFF();
        } else if (algoritmo.equals("WF")) {
            return executarWF();
        } else if (algoritmo.equals("CF")) {
            return executarCF();
        } else {
            return "Algoritmo inválido.";
        }
    }

    public void mostrarMemoria() {
        System.out.print("Memória: ");
        for (int i = 0; i < memoria.length; i++) {
            System.out.print(memoria[i] + " ");
        }
        System.out.println();
        
    }
}
