package br.bugtracker.arquivos;

import br.bugtracker.modelo.Bug;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class BugRepositorio {

    private final Path caminhoArquivo;
    private final BugFormatadorArquivo formatador;

    public BugRepositorio(String caminhoArquivo) {
        this.caminhoArquivo = Paths.get(caminhoArquivo);
        this.formatador = new BugFormatadorArquivo();
    }

    public List<Bug> carregar() {
        List<Bug> bugs = new ArrayList<>();

        if (!Files.exists(caminhoArquivo)) {
            return bugs;
        }

        try {
            List<String> linhas = Files.readAllLines(caminhoArquivo, StandardCharsets.UTF_8);
            for (String linha : linhas) {
                if (linha.isBlank()) continue;
                Bug bug = formatador.linhaParaBug(linha);
                bugs.add(bug);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de bugs: " + e.getMessage());
        }

        return bugs;
    }

    public void salvar(List<Bug> bugs) {
        List<String> linhas = new ArrayList<>();
        for (Bug bug : bugs) {
            linhas.add(formatador.bugParaLinha(bug));
        }

        try {
            if (caminhoArquivo.getParent() != null) {
                Files.createDirectories(caminhoArquivo.getParent());
            }
            Files.write(caminhoArquivo, linhas, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de bugs: " + e.getMessage());
        }
    }
}
