package br.bugtracker.tarefas;

import br.bugtracker.servicos.GerenciadorDeBugs;

public class TarefaAutoSalvar implements Runnable {

    private final GerenciadorDeBugs gerenciador;
    private final long intervaloMs;
    private volatile boolean executando = true;

    public TarefaAutoSalvar(GerenciadorDeBugs gerenciador, long intervaloMs) {
        this.gerenciador = gerenciador;
        this.intervaloMs = intervaloMs;
    }

    public void parar() {
        executando = false;
    }

    @Override
    public void run() {
        while (executando) {
            try {
                Thread.sleep(intervaloMs);
                gerenciador.salvar();
            } catch (InterruptedException e) {
                System.out.println("Thread de auto-salvamento interrompida.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
