package Classes;

import Estruturas.ListaEncadeada;

public class Palavra implements Comparable<Palavra> {

    private String original;
    private String normalizada;
    public ListaEncadeada ocorrencias;

    public Palavra(String original, String normalizada) {
        this.original = limparPontuacaoFinal(original);
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
        if (!ocorrencias.contem(linha))
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

    public void atualizarOriginal(String novoOriginal) {
        String limpo = limparPontuacaoFinal(novoOriginal);

        if (limpo.length() < this.original.length()) {
            this.original = limpo;
        }
    }
    private String limparPontuacaoFinal(String s) {

        if (s == null || s.isEmpty()) return s;

        while (s.length() > 0) {
            char ultimo = s.charAt(s.length() - 1);

            if (Character.isLetter(ultimo) || ultimo == '-') {
                break;
            }
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }
}
