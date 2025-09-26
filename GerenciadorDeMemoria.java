import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GerenciadorDeMemoria {
    private int[] memoria; 
    private String algoritmo;
    private int ultimoIndice = 0;
    private Random random = new Random();

    public GerenciadorDeMemoria(String algoritmo) {
        this.memoria = new int[1000]; 
        this.algoritmo = algoritmo;
    }


    public void timeExecucao(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            if (memoria[i] == 0) {
                int inicio = i;
                int tamanho = 0;
                while (i < memoria.length && memoria[i] == 0) {
                    tamanho++;
                    i++;
                }
                if (tamanho >= p.getTamanho() && tamanho < melhorTamanho) {
                    melhorTamanho = tamanho;
                    melhorInicio = inicio;
                }
            }
            i++;
        }

        if (melhorInicio != -1) {
            for (int j = melhorInicio; j < melhorInicio + p.getTamanho(); j++) {
                memoria[j] = p.getId();
                
            }
            return true;
        }
        return false;
    }

    private boolean executarFF(Processo p) {
        for (int i = 0; i <= memoria.length - p.getTamanho(); i++) {
            boolean livre = true;
            for (int j = 0; j < p.getTamanho(); j++) {
                if (memoria[i + j] != 0) {
                    livre = false;
                    break;
                }
            }
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
            if (memoria[i] == 0) {
                int inicio = i;
                int tamanho = 0;
                while (i < memoria.length && memoria[i] == 0) {
                    tamanho++;
                    i++;
                }
                if (tamanho >= p.getTamanho() && tamanho > piorTamanho) {
                    piorTamanho = tamanho;
                    piorInicio = inicio;
                }
            }
            i++;
        }

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
            if (i + p.getTamanho() <= memoria.length) {
                boolean livre = true;
                for (int j = 0; j < p.getTamanho(); j++) {
                    if (memoria[i + j] != 0) {
                        livre = false;
                        break;
                    }
                }
                if (livre) {
                    for (int j = 0; j < p.getTamanho(); j++) {
                        memoria[i + j] = p.getId();
                        
                    }
                    ultimoIndice = (i + p.getTamanho()) % memoria.length;
                    return true;
                }
            }
            i = (i + 1) % memoria.length;
        } while (i != start);

        return false;
    }

    public void removerProcessoAleatorio() {
        List<Integer> processosAtivos = new ArrayList<>();
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != 0 && !processosAtivos.contains(memoria[i])) {
                processosAtivos.add(memoria[i]);
            }
        }

        if (!processosAtivos.isEmpty()) {
            int processoParaRemover = processosAtivos.get(random.nextInt(processosAtivos.size()));
            for (int i = 0; i < memoria.length; i++) {
                if (memoria[i] == processoParaRemover) {
                    memoria[i] = 0;
                }
            }
        }
    }

    public double calcularOcupacao() {
        int ocupado = 0;
        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] != 0) ocupado++;
        }
        return (ocupado * 100.0) / memoria.length;
    }
}