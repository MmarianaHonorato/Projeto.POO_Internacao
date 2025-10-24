package model;

import java.util.Date; // Precisa pra trabalhar com as datas de entrada e alta.

// Classe Internacao. Essa aqui é o registro de quando um Paciente tá ocupando um Leito.
public class Internacao {
    private int idInternacao; // ID único da internação (chave no banco).
    private Date dataEntrada; // Quando o paciente deu entrada.
    private Date dataAlta; // Quando o paciente teve alta (pode ser null se ainda estiver internado).
    private String diagnostico; // O motivo da internação, o que o paciente tem.

    // Aqui estão os relacionamentos, que ligam a internação a outros objetos importantes.
    private Paciente paciente; // O paciente que está internado.
    private Medico medico; // O médico responsável pela internação.
    private Leito leito; // O leito que o paciente está ocupando.

    // Construtor vazio. Pra iniciar o objeto e preencher os dados depois.
    public Internacao() {}

    // Construtor completo. Pra criar a internação de uma vez só, com todos os dados e referências.
    public Internacao(int idInternacao, Date dataEntrada, Date dataAlta, String diagnostico, Paciente paciente, Medico medico, Leito leito) {
        this.idInternacao = idInternacao;
        this.dataEntrada = dataEntrada;
        this.dataAlta = dataAlta;
        this.diagnostico = diagnostico;
        this.paciente = paciente;
        this.medico = medico;
        this.leito = leito;
    }

    // Getters e Setters, o feijão com arroz pra acessar e modificar as informações privadas.

    public int getIdInternacao() {
        return idInternacao;
    }
    // ... (todos os outros Getters e Setters seguem aqui, padrão) ...
    // Note que os getters dos objetos (Paciente, Medico, Leito) retornam o objeto em si,
    // o que permite acessar as informações deles (tipo paciente.getNome()).

    public Paciente getPaciente() {
        return paciente;
    }
    
    public Medico getMedico() {
        return medico;
    }
    
    public Leito getLeito() {
        return leito;
    }

    // Método toString() sobrescrito.
    // Pra quando for exibir no sistema ou debuggar, ele mostra o ID da internação e o nome do paciente.
    @Override
    public String toString() {
        // Uso paciente.getNome() pra pegar o nome do objeto Paciente relacionado.
        return "Internação " + idInternacao + " - Paciente: " + paciente.getNome();
    }
}