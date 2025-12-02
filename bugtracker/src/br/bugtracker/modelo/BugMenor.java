package br.bugtracker.modelo;

public class BugMenor extends Bug {

    public BugMenor(int id,
                    String titulo,
                    String descricao,
                    Prioridade prioridade,
                    StatusBug status,
                    Desenvolvedor desenvolvedorResponsavel) {

        super(id, titulo, descricao, prioridade, status, desenvolvedorResponsavel);
    }

    @Override
    public double calcularImpacto() {
        // Exemplo: impacto menor
        return 2.0;
    }
}
