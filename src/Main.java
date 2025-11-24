import Classes.GerenciadorArquivos;
import Classes.IndiceRemissivo;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        try {
            Path caminhoTexto = Path.of("texto.txt");
            Path caminhoChaves = Path.of("palavras-chave.txt");

            String resultado = IndiceRemissivo.gerar(caminhoTexto, caminhoChaves);

            Path saida = Path.of("indice-remissivo.txt");
            GerenciadorArquivos.escreverArquivo(saida, resultado);

            System.out.println("Índice remissivo gerado com sucesso!");
            System.out.println("Arquivo salvo em: " + saida.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
