package model;

import java.util.Date;

// "Medico" também herda de "Pessoa"
public class Medico extends Pessoa {

    private String crm; // Atributo do ERD
    private String especialidade; // Atributo do ERD 

    public Medico(String nome, String cpf, String email, Date dataNascimento, String endereco, String crm, String especialidade) {
        super(nome, cpf, email, dataNascimento, endereco);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    // Getters e Setters específicos
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    @Override
    public String toString() {
        return "Médico: " + getNome() + " (CRM: " + crm + " - " + especialidade + ")";
    }
}