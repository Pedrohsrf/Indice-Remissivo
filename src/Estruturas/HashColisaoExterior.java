package Estruturas;

import Classes.Palavra;
import java.text.Normalizer;

public class HashColisaoExterior {

    private ArvoreBinariaBusca[] tabela;
    private int nElementos;

    public HashColisaoExterior() {
        tabela = new ArvoreBinariaBusca[26];
        for (int i = 0; i < 26; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
        nElementos = 0;
    }

    private String normalizar(String p) {
        if (p == null) return "";

        String s = Normalizer.normalize(p.toLowerCase(), Normalizer.Form.NFD);
        s = s.replaceAll("\\p{M}", "");

        s = s.replaceAll("[^a-z\\-]", "");

        return s;
    }

    private int hash(String normalizada) {
            normalizada = normalizar(normalizada);
        if (normalizada.isEmpty()) return -1;

        char c = normalizada.charAt(0);

        if (c < 'a' || c > 'z') return -1;

        return c - 'a';
    }

    public void inserirOuAtualizar(String palavraOriginal, int linha) {

        String normalizada = normalizar(palavraOriginal);

        int pos = hash(normalizada);
        if (pos == -1) return;

        tabela[pos].inserirOuAtualizar(palavraOriginal, normalizada, linha);
    }

    public Palavra buscar(String palavraOriginal) {

        String normalizada = normalizar(palavraOriginal);

        int pos = hash(normalizada);
        if (pos == -1) return null;

        return tabela[pos].buscar(normalizada);
    }

    public void imprimir(StringBuilder sb) {
        for (int i = 0; i < 26; i++) {
            tabela[i].imprimirOrdenado(sb);
        }
    }
}
