package br.bugtracker.modelo;

public class BugCritico extends Bug implements Notificavel {

    public BugCritico(int id,
                      String titulo,
                      String descricao,
                      Prioridade prioridade,
                      StatusBug status,
                      Desenvolvedor desenvolvedorResponsavel) {

        super(id, titulo, descricao, prioridade, status, desenvolvedorResponsavel);
    }

    @Override
    public double calcularImpacto() {
        // Exemplo simples: bugs críticos têm impacto maior fixo
        return 10.0;
    }

    public boolean precisaPararDeploy() {
        return getStatus() != StatusBug.RESOLVIDO
                && getStatus() != StatusBug.FECHADO;
    }

    @Override
    public void notificarCriacao() {
        System.out.println("[NOTIFICAÇÃO] Bug CRÍTICO criado: " + getId() + " - " + getTitulo());
    }

    @Override
    public void notificarAtualizacaoStatus() {
        System.out.println("[NOTIFICAÇÃO] Status de Bug CRÍTICO alterado: " +
                getId() + " -> " + getStatus());
    }
}
