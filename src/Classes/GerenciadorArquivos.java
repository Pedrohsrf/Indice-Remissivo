package Classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GerenciadorArquivos {

    public static void escreverArquivo(Path caminho, String conteudo) {
        try {
            Files.writeString(caminho, conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String lerArquivo(Path caminho) {
        try {
            if (!Files.exists(caminho)) {
                System.out.println("Arquivo não encontrado no caminho especificado.");
                return "";
            }

            return Files.readString(caminho);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}