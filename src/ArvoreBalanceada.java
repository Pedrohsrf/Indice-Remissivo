public class ArvoreBalanceada {

    class Nodo {

        public int elemento;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(int elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    public Nodo raiz;
    public int nElementos;

    public ArvoreBalanceada() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    public void imprime() {
        if (this.raiz != null)
            this.imprime(this.raiz, 0);
    }

    private void imprime(Nodo nodo, int level) {

        System.out.println("[" + nodo.elemento + " | fb=" + this.fatorBalanco(nodo) + "]");

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        if (nodo.esquerdo != null) {
            System.out.print("# esq: ");
            this.imprime(nodo.esquerdo, level + 1);
        } else {
            System.out.print("# esq: NULO");
        }
        System.out.println();

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        if (nodo.direito != null) {
            System.out.print("# dir: ");
            this.imprime(nodo.direito, level + 1);
        } else {
            System.out.print("# dir: NULO");
        }
        System.out.println();
    }

    public void imprimePreOrdem() {
        this.preOrdem(this.raiz);
        System.out.println();
    }

    public void imprimePosOrdem() {
        this.posOrdem(this.raiz);
        System.out.println();
    }

    public void imprimeEmOrdem() {
        this.emOrdem(this.raiz);
        System.out.println();
    }

    public void preOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        System.out.print(nodo.elemento + " ");
        this.preOrdem(nodo.esquerdo);
        this.preOrdem(nodo.direito);
    }

    public void posOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.posOrdem(nodo.esquerdo);
        this.posOrdem(nodo.direito);
        System.out.print(nodo.elemento + " ");
    }

    public void emOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.emOrdem(nodo.esquerdo);
        System.out.print(nodo.elemento + " ");
        this.emOrdem(nodo.direito);
    }

    public void insere(int elemento) {
        this.insereABB(elemento, this.raiz);
        this.rebalanceia(elemento);
    }

    public void remove(int elemento) {
        this.removeABB(elemento, this.raiz);
        this.rebalanceia(elemento);
    }

    private void insereABB(int elemento, Nodo nodo) {

        Nodo novo = new Nodo(elemento);

        if (nodo == null) {
            this.raiz = novo;
            this.nElementos++;
            return;
        }

        if (elemento < nodo.elemento) {
            if (nodo.esquerdo == null) {
                nodo.esquerdo = novo;
                this.nElementos++;
                return;
            } else {
                this.insereABB(elemento, nodo.esquerdo);
            }
        }

        if (elemento > nodo.elemento) {
            if (nodo.direito == null) {
                nodo.direito = novo;
                this.nElementos++;
                return;
            } else {
                this.insereABB(elemento, nodo.direito);
            }
        }
    }

    private Nodo menorElemento(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }

    public boolean removeABB(int elemento) {
        return this.removeABB(elemento, this.raiz) != null;
    }

    private Nodo removeABB(int elemento, Nodo nodo) {

        if (nodo == null) {
            System.out.println("Valor não encontrado");
            return null;
        }

        if (elemento < nodo.elemento) {
            nodo.esquerdo = this.removeABB(elemento, nodo.esquerdo);
        } else if (elemento > nodo.elemento) {
            nodo.direito = this.removeABB(elemento, nodo.direito);
        } else {

//	    	if(nodo.esquerdo == null && nodo.direito == null) {
//	    		return null;
//	    	}

            if (nodo.esquerdo == null) {
                this.nElementos--;
                return nodo.direito;
            } else if (nodo.direito == null) {
                this.nElementos--;
                return nodo.esquerdo;
            } else {
                Nodo substituto = this.menorElemento(nodo.direito);
                nodo.elemento = substituto.elemento;
                this.removeABB(substituto.elemento, nodo.direito);
            }
        }

        return nodo;
    }

    public void rebalanceia(int elemento) {
        this.raiz = this.rebalanceia(elemento, this.raiz);
    }

    public Nodo rebalanceia(int elemento, Nodo nodo) {

        if (nodo == null) {
            return null;
        }

        int fb = this.fatorBalanco(nodo);
        if (Math.abs(fb) >= 2) {
            return this.rotaciona(nodo);
        }

        if (elemento < nodo.elemento) {
            nodo.esquerdo = this.rebalanceia(elemento, nodo.esquerdo);
        } else if (elemento > nodo.elemento) {
            nodo.direito = this.rebalanceia(elemento, nodo.direito);
        }

        return nodo;
    }

    private int altura(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
        int alturaDireita = this.altura(nodo.direito) + 1;

        int altura = alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita;

        return altura;

    }

    public int altura() {
        return this.altura(this.raiz);
    }

    public int fatorBalanco(Nodo nodo) {
        int alturaEsq = this.altura(nodo.esquerdo);
        int alturaDir = this.altura(nodo.direito);

        return alturaDir - alturaEsq;
    }

    private Nodo rotaciona(Nodo nodo) {

        if (this.fatorBalanco(nodo) <= -2) { // rotação para direita

            int balancoFilho = (int) Math.signum(this.fatorBalanco(nodo.esquerdo));

            if (balancoFilho == -1 || balancoFilho == 0) {
                System.out.println("Rotação simples à direita");
                return this.rotacaoDireita(nodo);
            } else {
                System.out.println("Rotação dupla à direita");
                nodo.esquerdo = this.rotacaoEsquerda(nodo.esquerdo);
                return this.rotacaoDireita(nodo);
            }

        } else if (this.fatorBalanco(nodo) >= 2) { // rotação para esquerda

            int balancoFilho = (int) Math.signum(this.fatorBalanco(nodo.direito));

            if (balancoFilho == 1 || balancoFilho == 0) {
                System.out.println("Rotação simples à esquerda");
                return this.rotacaoEsquerda(nodo);
            } else {
                System.out.println("Rotação dupla à esquerda");
                nodo.direito = this.rotacaoDireita(nodo.direito);
                return this.rotacaoEsquerda(nodo);
            }

        }

        return nodo;
    }

    private Nodo rotacaoDireita(Nodo x) {

        Nodo y = x.esquerdo;

        Nodo b = y.direito;

        y.direito = x;
        x.esquerdo = b;

        return y;
    }

    private Nodo rotacaoEsquerda(Nodo x) {

        Nodo y = x.direito;

        Nodo b = y.esquerdo;

        y.esquerdo = x;
        x.direito = b;

        return y;
    }
}