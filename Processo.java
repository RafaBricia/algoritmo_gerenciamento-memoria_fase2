import java.util.Random;

class Processo {
    private int id;
    private int tamanho;

    public Processo(int id, int tamanho) {
        this.id = id;
        this.tamanho = tamanho;
    }

    public int getId() { return id; }
    public int getTamanho() { return tamanho; }

    @Override
    public String toString() {
        return "Processo " + id + " (" + tamanho + "KB)";
    }
}

class GeradorDeProcesso {
    private static int contadorId = 0;
    private static Random gerador = new Random();

    public static Processo gerarProcesso() {
        int id = ++contadorId;
        int qtd = gerador.nextInt(41) + 10;
        return new Processo(id, qtd);
    }
}