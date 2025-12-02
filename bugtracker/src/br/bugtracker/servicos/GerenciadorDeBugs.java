package br.bugtracker.servicos;

import br.bugtracker.arquivos.BugRepositorio;
import br.bugtracker.modelo.*;

import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeBugs {

    private final List<Bug> bugs = new ArrayList<>();
    private final Map<Integer, Bug> bugsPorId = new HashMap<>();
    private final BugRepositorio repositorio;
    private int proximoId = 1;

    public GerenciadorDeBugs(BugRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public synchronized int gerarNovoId() {
        return proximoId++;
    }

    public synchronized void registrarBug(Bug bug) {
        bugs.add(bug);
        bugsPorId.put(bug.getId(), bug);
        ajustarProximoId(bug.getId());
        if (bug instanceof Notificavel notificavel) {
            notificavel.notificarCriacao();
        }
    }

    public synchronized List<Bug> listarTodos() {
        return new ArrayList<>(bugs);
    }

    public synchronized Bug buscarPorId(int id) {
        return bugsPorId.get(id);
    }

    public synchronized void atualizarStatus(int idBug, StatusBug novoStatus) {
        Bug bug = bugsPorId.get(idBug);
        if (bug == null) {
            System.out.println("Bug com id " + idBug + " não encontrado.");
            return;
        }
        bug.atualizarStatus(novoStatus);
        if (bug instanceof Notificavel notificavel) {
            notificavel.notificarAtualizacaoStatus();
        }
    }

    public synchronized void removerBug(int idBug) {
        Bug bug = bugsPorId.remove(idBug);
        if (bug != null) {
            bugs.remove(bug);
            System.out.println("Bug removido com sucesso: " + idBug);
        } else {
            System.out.println("Bug com id " + idBug + " não encontrado.");
        }
    }

    public synchronized List<Bug> listarPorStatus(StatusBug status) {
        return bugs.stream()
                .filter(b -> b.getStatus() == status)
                .collect(Collectors.toList());
    }

    public synchronized List<Bug> listarPorPrioridade(Prioridade prioridade) {
        return bugs.stream()
                .filter(b -> b.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    public synchronized List<Bug> listarPorDesenvolvedor(Desenvolvedor dev) {
        return bugs.stream()
                .filter(b -> b.getDesenvolvedorResponsavel() != null &&
                        b.getDesenvolvedorResponsavel().getId() == dev.getId())
                .collect(Collectors.toList());
    }

    public synchronized void carregar() {
        List<Bug> carregados = repositorio.carregar();
        bugs.clear();
        bugsPorId.clear();
        bugs.addAll(carregados);
        for (Bug bug : carregados) {
            bugsPorId.put(bug.getId(), bug);
            ajustarProximoId(bug.getId());
        }
        System.out.println("Bugs carregados do arquivo: " + bugs.size());
    }

    public synchronized void salvar() {
        repositorio.salvar(bugs);
        System.out.println("Bugs salvos em arquivo.");
    }

    private void ajustarProximoId(int idExistente) {
        if (idExistente >= proximoId) {
            proximoId = idExistente + 1;
        }
    }
}
