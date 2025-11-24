package Classes;

import Estruturas.HashColisaoExterior;
import Estruturas.ListaEncadeada;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class IndiceRemissivo {

    public static String gerar(Path arquivoTexto, Path arquivoChaves) throws Exception {

        List<String> linhas = Files.readAllLines(arquivoTexto);

        List<Palavra> listaPalavras = new ArrayList<>();

        HashColisaoExterior hash = new HashColisaoExterior();

        for (int i = 0; i < linhas.size(); i++) {

            String[] palavras = linhas.get(i)
                    .split("\\s+");

            for (String p : palavras) {

                String limpa = limpar(p);
                if (limpa.isEmpty()) continue;

                listaPalavras.add(new Palavra(p, p));
                hash.inserirOuAtualizar(p, i + 1);
            }
        }

        String conteudoChaves = Files.readString(arquivoChaves);

        String[] chaves = Arrays.stream(conteudoChaves.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        Arrays.sort(chaves, String.CASE_INSENSITIVE_ORDER);

        StringBuilder sb = new StringBuilder();

        for (String chave : chaves) {

            Palavra encontrada = hash.buscar(chave);

            if (encontrada == null) continue;

            sb.append(encontrada.getOriginal())
                    .append(" — ")
                    .append(encontrada.ocorrencias.toString())
                    .append("\n");
        }

        return sb.toString();
    }

    private static String limpar(String p) {
        if (p == null || p.isEmpty()) return "";
        p = p.replaceAll("^[^\\p{L}]+", "");
        p = p.replaceAll("[^\\p{L}]+$", "");
        return p.trim();
    }
}
