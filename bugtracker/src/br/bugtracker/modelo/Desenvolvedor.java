package br.bugtracker.modelo;

public class Desenvolvedor {

    private int id;
    private String nome;
    private String email;

    public Desenvolvedor(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    // Se quiser impedir mudança de id, basta remover esse setter
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Desenvolvedor{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}
