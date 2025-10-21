package model;

import java.util.Date;

// Classe para a entidade "Internacao"
public class Internacao {
    
    // Relações do ERD 
    private Paciente paciente;
    private Medico medico;
    private Leito leito;
    
    // Atributos do ERD
    private Date dataEntrada;
    private Date dataAlta;
    private String diagnostico;

    public Internacao(Paciente paciente, Medico medico, Leito leito, Date dataEntrada, String diagnostico) {
        this.paciente = paciente;
        this.medico = medico;
        this.leito = leito;
        this.dataEntrada = dataEntrada;
        this.diagnostico = diagnostico;
        
        // Regra de negócio simples: ao internar, o leito fica ocupado
        this.leito.setOcupado(true);
    }
    
    // Método para finalizar a internação
    public void registrarAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
        // Regra de negócio: ao dar alta, o leito fica livre
        this.leito.setOcupado(false);
    }

    // Getters e Setters
    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Leito getLeito() {
        return leito;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public Date getDataAlta() {
        return dataAlta;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }
}
