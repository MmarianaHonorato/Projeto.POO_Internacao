package model;

import java.util.Date;

// "Paciente" herda de "Pessoa"
public class Paciente extends Pessoa {
    
    // O RF003 pede "histórico clínico"
    private String historicoClinico;

    // Construtor que chama o construtor da classe "Pai" (Pessoa)
    public Paciente(String nome, String cpf, String email, Date dataNascimento, String endereco, String historicoClinico) {
        super(nome, cpf, email, dataNascimento, endereco);
        this.historicoClinico = historicoClinico;
    }

    // Getters e Setters específicos
    public String getHistoricoClinico() {
        return historicoClinico;
    }

    public void setHistoricoClinico(String historicoClinico) {
        this.historicoClinico = historicoClinico;
    }

    // sobrescrevendo o método toString() para facilitar a exibicao
    @Override
    public String toString() {
        return "Paciente: " + getNome() + " (CPF: " + getCpf() + ")";
    }
}