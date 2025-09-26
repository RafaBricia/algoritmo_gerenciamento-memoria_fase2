import java.util.ArrayList;
import java.util.List;

public class Metricas {
    private List<Double> tamanhosMedios = new ArrayList<>();
    private List<Double> ocupacoesMedias = new ArrayList<>(); 
    private List<Double> taxasDescarte = new ArrayList<>();

    public void adicionar(double tamanhoMedio, double ocupacaoMedia, double taxaDescarte) {
        tamanhosMedios.add(tamanhoMedio);
        ocupacoesMedias.add(ocupacaoMedia);
        taxasDescarte.add(taxaDescarte);
    }

    public void imprimir(String algoritmo) {
        double mediaTamanho = tamanhosMedios.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double mediaOcupacao = ocupacoesMedias.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double mediaDescarte = taxasDescarte.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        System.out.printf("\n=== %s - RESULTADOS ===\n", algoritmo);
        System.out.printf("1. Tamanho médio dos processos: %.2f KB\n", mediaTamanho);
        System.out.printf("2. Ocupação média da memória: %.2f%%\n", mediaOcupacao);
        System.out.printf("3. Taxa de descarte: %.2f%%\n", mediaDescarte);
    }
}
