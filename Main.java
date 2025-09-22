public class Main {
    public static void main(String[] args) {
        // ===== BEST-FIT =====
        GerenciadorDeMemoria gm1 = new GerenciadorDeMemoria(20, "BF");
        System.out.println("\n");

        gm1.adicionarProcesso(new Processo(1, 5));
        gm1.adicionarProcesso(new Processo(2, 3));
        gm1.adicionarProcesso(new Processo(3, 7));
        System.out.println("BF -> " + gm1.gerenciar());
        gm1.mostrarMemoria();

        // ===== FIRST-FIT =====
        GerenciadorDeMemoria gm2 = new GerenciadorDeMemoria(20, "FF");
        System.out.println("\n");

        gm2.adicionarProcesso(new Processo(4, 6));
        gm2.adicionarProcesso(new Processo(5, 4));
        gm2.adicionarProcesso(new Processo(6, 5));
        System.out.println("FF -> " + gm2.gerenciar());
        gm2.mostrarMemoria();
        System.out.println(("========================================================================="));

        // ===== WORST-FIT =====
        GerenciadorDeMemoria gm3 = new GerenciadorDeMemoria(20, "WF");
        System.out.println("\n");

        gm3.adicionarProcesso(new Processo(7, 4));
        gm3.adicionarProcesso(new Processo(8, 6));
        gm3.adicionarProcesso(new Processo(9, 5));
        System.out.println("WF -> " + gm3.gerenciar());
        gm3.mostrarMemoria();
        System.out.println(("========================================================================="));


        // ===== NEXT-FIT =====
        GerenciadorDeMemoria gm4 = new GerenciadorDeMemoria(20, "NF");
        System.out.println("\n");

        gm4.adicionarProcesso(new Processo(10, 5));
        gm4.adicionarProcesso(new Processo(11, 7));
        gm4.adicionarProcesso(new Processo(12, 6));
        System.out.println("NF -> " + gm4.gerenciar());
        gm4.mostrarMemoria();
        System.out.println(("========================================================================="));

    }
}
