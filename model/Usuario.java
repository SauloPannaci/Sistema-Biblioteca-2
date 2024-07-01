package model;

public class Usuario {
    private int id;
    private String nome;
    private String email;

    public Usuario(String nome) {
        this.nome = nome;
    }

    // Getters e setters

    public Usuario(String nome2, String email2) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean isDisponivel() {
        
        throw new UnsupportedOperationException("Unimplemented method 'isDisponivel'");
    }
}
