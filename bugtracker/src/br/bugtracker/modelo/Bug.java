package br.bugtracker.modelo;

public abstract class Bug {

    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private StatusBug status;
    private Desenvolvedor desenvolvedorResponsavel;

    public Bug(int id,
               String titulo,
               String descricao,
               Prioridade prioridade,
               StatusBug status,
               Desenvolvedor desenvolvedorResponsavel) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.desenvolvedorResponsavel = desenvolvedorResponsavel;
    }

    public int getId() {
        return id;
    }

    // usado pelo repositório ao carregar do arquivo, se necessário
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public StatusBug getStatus() {
        return status;
    }

    public void atualizarStatus(StatusBug novoStatus) {
        this.status = novoStatus;
    }

    public Desenvolvedor getDesenvolvedorResponsavel() {
        return desenvolvedorResponsavel;
    }

    public void atribuirDesenvolvedor(Desenvolvedor desenvolvedorResponsavel) {
        this.desenvolvedorResponsavel = desenvolvedorResponsavel;
    }

    // método abstrato para polimorfismo
    public abstract double calcularImpacto();

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", prioridade=" + prioridade +
                ", status=" + status +
                ", dev=" + (desenvolvedorResponsavel != null ? desenvolvedorResponsavel.getNome() : "Nenhum") +
                '}';
    }
}
