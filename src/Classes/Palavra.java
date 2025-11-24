package Classes;

import Estruturas.ListaEncadeada;

public class Palavra implements Comparable<Palavra> {

    private String original;
    private String normalizada;
    public ListaEncadeada ocorrencias;

    public Palavra(String original, String normalizada) {
        this.original = original;
        this.normalizada = normalizada;
        this.ocorrencias = new ListaEncadeada();
    }

    public String getOriginal() {
        return original;
    }

    public String getNormalizada() {
        return normalizada;
    }

    public void adicionarOcorrencia(int linha) {
        if(!ocorrencias.contem(linha))
            ocorrencias.insereFinal(linha);
    }

    @Override
    public int compareTo(Palavra outra) {
        return this.normalizada.compareTo(outra.normalizada);
    }

    @Override
    public String toString() {
        return original + " -> " + ocorrencias.toString();
    }
}
