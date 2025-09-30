import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GerenciadorDeMemoria {
    private int[] memoria; 
    private String algoritmo;
    private int ultimoIndice = 0; //usado pelo Next-Fit
    private Random random = new Random();

    public GerenciadorDeMemoria(String algoritmo) {
        this.memoria = new int[1000]; 
        this.algoritmo = algoritmo;
    }


    public boolean alocarProcesso(Processo p) {
        if (algoritmo.equals("BF")) {
            return executarBF(p);
        } else if (algoritmo.equals("FF")) {
            return executarFF(p);
        } else if (algoritmo.equals("WF")) {
            return executarWF(p);
        } else if (algoritmo.equals("NF")) {
            return executarNF(p);
        }
        return false;
    }

    private boolean executarBF(Processo p) {
        int melhorInicio = -1;
        int melhorTamanho = Integer.MAX_VALUE;
        int i = 0;

        while (i < memoria.length) {

            // Encontrando o início de um bloco livre.
            if (memoria[i] == 0) {
                int inicio = i;
                int tamanho = 0;
                
                // Encontra o tamanho do bloco livre atual.
                while (i < memoria.length && memoria[i] == 0) {
                    tamanho++;
                    i++;
                }

                // Compara com o melhor bloco encontrado até agora.
                if (tamanho >= p.getTamanho() && tamanho < melhorTamanho) {
                    melhorTamanho = tamanho;
                    melhorInicio = inicio;
                }
            }
            i++;
        }

        // Aloca o processo no melhor bloco encontrado.
        if (melhorInicio != -1) {
            for (int j = melhorInicio; j < melhorInicio + p.getTamanho(); j++) {
                memoria[j] = p.getId();
                
            }
            return true;
        }

        return false;
    }

    private boolean executarFF(Processo p) {

        // Verifica todos os possíveis índices livres da memória, levando em consideração o limite do array para aquele processo.
        for (int i = 0; i <= memoria.length - p.getTamanho(); i++) {
            boolean livre = true;

            // Verifica se o bloco de memória de tamanho do processo está livre.
            for (int j = 0; j < p.getTamanho(); j++) {
                if (memoria[i + j] != 0) {
                    livre = false;
                    break;
                }
            }
            
            // Caso a área esteja livre, aloca-se e retorna.
            if (livre) {
                for (int j = 0; j < p.getTamanho(); j++) {
                    memoria[i + j] = p.getId();
                    
                }
                return true;
            }
        }
        return false;
    }

    private boolean executarWF(Processo p) {
        int piorInicio = -1;
        int piorTamanho = -1;
        int i = 0;

        while (i < memoria.length) {

            // Encontrando o início de um bloco livre.
            if (memoria[i] == 0) {
                int inicio = i;
                int tamanho = 0;

                // Encontra o tamanho do bloco livre atual.
                while (i < memoria.length && memoria[i] == 0) {
                    tamanho++;
                    i++;
                }

                // Compara com o pior (maior) bloco encontrado até agora.
                if (tamanho >= p.getTamanho() && tamanho > piorTamanho) {
                    piorTamanho = tamanho;
                    piorInicio = inicio;
                }
            }
            i++;
        }

        // tentando alocar o processo no maior bloco encontrado.
        if (piorInicio != -1) {
            for (int j = piorInicio; j < piorInicio + p.getTamanho(); j++) {
                memoria[j] = p.getId();
                
            }
            return true;
        }
        return false;
    }

    private boolean executarNF(Processo p) {
        int start = ultimoIndice;
        int i = start;

        do {
             // Verifica se o processo é cabível, considerando o final do array.
            if (i + p.getTamanho() <= memoria.length) {
                boolean livre = true;

                // Verifica se o bloco está livre.
                for (int j = 0; j < p.getTamanho(); j++) {
                    if (memoria[i + j] != 0) {
                        livre = false;
                        break;
                    }
                }

                // Se estiver livre, aloca o processo.
                if (livre) {
                    for (int j = 0; j < p.getTamanho(); j++) {
                        memoria[i + j] = p.getId();
                        
                    }
                    
                    // ATUALIZA: Define o novo 'ultimoIndice' como o próximo espaço livre após esta alocação.
                    ultimoIndice = (i + p.getTamanho()) % memoria.length;
                    return true;
                }
            }
            
            // Avança para o próximo índice de forma circular.
            i = (i + 1) % memoria.length;
        } while (i != start); // Continua buscando até voltarmos para o ponto que iniciamos.

        return false;
    }

    public void removerProcessoAleatorio() {

        List<Integer> processosAtivos = new ArrayList<>(); 
    
        // Percorre a memória e adiciona à lista os IDs únicos dos processos ativos
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != 0 && !processosAtivos.contains(memoria[i])) {
                processosAtivos.add(memoria[i]);
            }
        }
    
        if (!processosAtivos.isEmpty()) {

            // Escolhe aleatoriamente um processo da lista para remover
            int processoParaRemover = processosAtivos.get(random.nextInt(processosAtivos.size()));
    
            // Percorre a memória e libera todas as posições desse processo
            for (int i = 0; i < memoria.length; i++) {
                if (memoria[i] == processoParaRemover) {
                    memoria[i] = 0; 
                }
            }
        }
    }
    
    public double calcularOcupacao() {
        int ocupado = 0; 
    
        // Conta todas as posições da memória que estão ocupadas
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != 0) ocupado++;
        }
    
        // Calcula a porcentagem de ocupação da memória
        return (ocupado * 100.0) / memoria.length;
    }
    
}