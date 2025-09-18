import java.util.Random;

public class GeradorDeProcesso {
    private static int contadorId = 0;
    private static Random gerador = new Random();

    public static Processo gerarProcesso() {
        int id = ++contadorId;
        int qtd = gerador.nextInt(41) + 10; // de 10 a 50
        return new Processo(id, qtd);
    }
}
