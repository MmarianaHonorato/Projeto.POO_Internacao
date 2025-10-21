package model;

import java.util.Date;

// Classe "Pessoa" do diagrama ERD. 
// Usa 'abstract' porque ninguém será apenas "Pessoa", 
// mas sim Paciente ou Medico.
public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private String endereco; // Simplificado de "Endereço" do ERD

    // Construtor
    public Pessoa(String nome, String cpf, String email, Date dataNascimento, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}