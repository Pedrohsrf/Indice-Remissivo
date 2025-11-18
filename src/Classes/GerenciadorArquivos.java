package Classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class GerenciadorArquivos {

    public static void escreverArquivo(Path caminho, String conteudo) {
        try {
            Files.writeString(caminho, conteudo);
            System.out.println("Escrita concluída com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<String> lerArquivo(Path caminho){
        List<String> linhas = null;
        if (Files.exists(caminho)) {
            try {
                linhas = Files.readAllLines(caminho);

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo não foi encontrado no caminho especificado.");
        }
        
        return linhas;
    }
}