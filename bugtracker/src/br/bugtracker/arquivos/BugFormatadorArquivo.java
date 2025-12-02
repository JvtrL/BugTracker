package br.bugtracker.arquivos;

import br.bugtracker.modelo.*;

public class BugFormatadorArquivo {

    // Formato:
    // tipo;id;titulo;descricao;prioridade;status;devId;devNome;devEmail
    // tipo = CRITICO ou MENOR

    public String bugParaLinha(Bug bug) {
        String tipo = (bug instanceof BugCritico) ? "CRITICO" : "MENOR";

        Desenvolvedor dev = bug.getDesenvolvedorResponsavel();
        int devId = dev != null ? dev.getId() : -1;
        String devNome = dev != null ? dev.getNome() : "";
        String devEmail = dev != null ? dev.getEmail() : "";

        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append(";");
        sb.append(bug.getId()).append(";");
        sb.append(escape(bug.getTitulo())).append(";");
        sb.append(escape(bug.getDescricao())).append(";");
        sb.append(bug.getPrioridade().name()).append(";");
        sb.append(bug.getStatus().name()).append(";");
        sb.append(devId).append(";");
        sb.append(escape(devNome)).append(";");
        sb.append(escape(devEmail));

        return sb.toString();
    }

    public Bug linhaParaBug(String linha) {
        String[] partes = linha.split(";", -1);
        if (partes.length < 9) {
            throw new IllegalArgumentException("Linha de bug inválida: " + linha);
        }

        String tipo = partes[0];
        int id = Integer.parseInt(partes[1]);
        String titulo = unescape(partes[2]);
        String descricao = unescape(partes[3]);
        Prioridade prioridade = Prioridade.valueOf(partes[4]);
        StatusBug status = StatusBug.valueOf(partes[5]);

        int devId = Integer.parseInt(partes[6]);
        String devNome = unescape(partes[7]);
        String devEmail = unescape(partes[8]);
        Desenvolvedor dev = null;
        if (devId != -1) {
            dev = new Desenvolvedor(devId, devNome, devEmail);
        }

        if ("CRITICO".equalsIgnoreCase(tipo)) {
            return new BugCritico(id, titulo, descricao, prioridade, status, dev);
        } else {
            return new BugMenor(id, titulo, descricao, prioridade, status, dev);
        }
    }

    private String escape(String texto) {
        if (texto == null) return "";
        return texto.replace(";", "\\;");
    }

    private String unescape(String texto) {
        if (texto == null) return "";
        return texto.replace("\\;", ";");
    }
}
