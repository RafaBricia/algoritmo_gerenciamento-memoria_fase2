import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        String[] algoritmos = {"BF", "FF", "WF", "NF"};
        
        for (String algoritmo : algoritmos) {
            Metricas metricas = new Metricas();
            
            for (int execucao = 1; execucao <= 100; execucao++) {
                System.out.printf("Executando %s - %d/100\n", algoritmo, execucao);
                
                GerenciadorDeMemoria gm = new GerenciadorDeMemoria(algoritmo);
                List<Double> ocupacoesPorSegundo = new ArrayList<>();
                List<Integer> tamanhosProcessos = new ArrayList<>();
                int processosGerados = 0;
                int processosDescartados = 0;

                for (int segundo = 1; segundo <= 100; segundo++) {
                    for (int i = 0; i < 2; i++) {
                        Processo p = GeradorDeProcesso.gerarProcesso();
                        tamanhosProcessos.add(p.getTamanho());
                        processosGerados++;
                        
                        if (!gm.alocarProcesso(p)) {
                            processosDescartados++; 
                        }
                    }

                    int processosParaRemover = random.nextInt(2) + 1; 
                    for (int i = 0; i < processosParaRemover; i++) {
                        gm.removerProcessoAleatorio();
                    }

                    ocupacoesPorSegundo.add(gm.calcularOcupacao());
                }

                double tamanhoMedio = tamanhosProcessos.stream().mapToInt(Integer::intValue).average().orElse(0);
                double ocupacaoMedia = ocupacoesPorSegundo.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                double taxaDescarte = (processosDescartados * 100.0) / processosGerados;

                metricas.adicionar(tamanhoMedio, ocupacaoMedia, taxaDescarte);
            }

            metricas.imprimir(algoritmo);
        }
    }
}