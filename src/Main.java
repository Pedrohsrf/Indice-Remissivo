import java.nio.file.Paths;
import java.util.List;

/*
 0. Gerar dois vetores de strings
    - Texto: cada linha é um elemento
    - Palavras-chave: cada palavra é um elemento REGEX DE SPLIT: [\\s.,;:]+
 1. Criar uma tabela hash com chave 
    (letras do alfabeto) e valor
    (arvore binaria de busca vazia)
 2. Percorrer o vetor texto e 
    criar um objeto Palavra ou 
    adicionar mais uma ocorrencia 
    (linha = indice na lista encadeada)
    com todas as palavras 
    (separado por espaco e 
    tratar viegulas e ponto) e ao mesmo
    adicionar palavra à arvore 
    correspondente na tabela Hash.
 3. Percorrer tabela hash buscando cada
    palavra chave em ordem alfabetica
    na tabela hash e incrementando
    ao payload do arquivo de saida.
 */

public class Main {
    
    public static void main(String[] args) {
        System.out.println("------ ENTRADAS ------");
        
        List<String> texto = GerenciadorArquivos.lerArquivo(Paths.get("texto.txt"));
        System.out.println("Texto:");
        System.out.println(texto);
        System.out.println();
        
        List<String> palavrasChave = GerenciadorArquivos.lerArquivo(Paths.get("palavras-chave.txt"));
        System.out.println("Palavras-chave:");
        System.out.println(palavrasChave);
        System.out.println();
        
        System.out.println("------ SAÍDA ------");
        
        String indiceRemissivo = IndiceRemissivo.indiceRemissivo(texto, palavrasChave);
        GerenciadorArquivos.escreverArquivo(Paths.get("indice-remissivo.txt"), indiceRemissivo);
        System.out.println("Índice remissivo:");
        System.out.println(indiceRemissivo);
    }
    
}