package Estruturas;

import Classes.Palavra;

public class ArvoreBinariaBusca {

    class Nodo {
        public Palavra palavra;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(Palavra p) {
            this.palavra = p;
        }
    }

    private Nodo raiz;
    private int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public boolean inserirOuAtualizar(String original, String normalizada, int linha) {

        int antes = nElementos;
        raiz = inserirRec(raiz, original, normalizada, linha);
        return nElementos > antes;
    }

    private Nodo inserirRec(Nodo nodo, String original, String normalizada, int linha) {

        if (nodo == null) {
            Palavra p = new Palavra(original, normalizada);
            p.adicionarOcorrencia(linha);
            nElementos++;
            return new Nodo(p);
        }

        int cmp = normalizada.compareTo(nodo.palavra.getNormalizada());

        if (cmp == 0) {
            nodo.palavra.atualizarOriginal(original);

            nodo.palavra.adicionarOcorrencia(linha);
        }
        else if (cmp < 0) {
            nodo.esquerdo = inserirRec(nodo.esquerdo, original, normalizada, linha);
        }
        else {
            nodo.direito = inserirRec(nodo.direito, original, normalizada, linha);
        }

        return nodo;
    }

    public Palavra buscar(String normalizada) {
        return buscarRec(raiz, normalizada);
    }

    private Palavra buscarRec(Nodo nodo, String normalizada) {
        if (nodo == null) return null;

        int cmp = normalizada.compareTo(nodo.palavra.getNormalizada());

        if (cmp == 0) return nodo.palavra;
        if (cmp < 0) return buscarRec(nodo.esquerdo, normalizada);
        return buscarRec(nodo.direito, normalizada);
    }

    public void imprimirOrdenado(StringBuilder sb) {
        imprimirRec(raiz, sb);
    }

    private void imprimirRec(Nodo nodo, StringBuilder sb) {
        if (nodo == null) return;

        imprimirRec(nodo.esquerdo, sb);

        sb.append(nodo.palavra.getOriginal())
                .append(" — ")
                .append(nodo.palavra.ocorrencias.toString())
                .append("\n");

        imprimirRec(nodo.direito, sb);
    }
}
