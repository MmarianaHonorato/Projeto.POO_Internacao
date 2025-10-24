package model;

import java.util.Date; // Preciso importar isso pra conseguir usar datas!

// Classe Exame. Ela guarda todas as informações de um exame médico.
public class Exame {
    private int idExame; // ID único do exame (chave primária no banco).
    private String nome; // Nome do exame, tipo "Hemograma", "Raio-X", etc.
    private String descricao; // O resultado do exame, ou uma descrição dele.
    private Date dataPedido; // Quando o exame foi solicitado.
    private Date dataResultado; // Quando o resultado ficou pronto.

    // Aqui entram os relacionamentos: quem está envolvido no exame.
    private Paciente paciente; // Quem fez o exame (referência pra classe Paciente).
    private Medico medico; // Quem pediu o exame (referência pra classe Medico).
    private Internacao internacao; // Se o exame está ligado a uma internação específica.

    // Construtor vazio. Pra criar o objeto e preencher os campos depois com os setters.
    public Exame() {}

    // Construtor completo. Pra criar o objeto de uma vez só, geralmente quando leio do banco de dados.
    public Exame(int idExame, String nome, String descricao, Date dataPedido, Date dataResultado, Paciente paciente, Medico medico, Internacao internacao) {
        this.idExame = idExame;
        this.nome = nome;
        this.descricao = descricao;
        this.dataPedido = dataPedido;
        this.dataResultado = dataResultado;
        this.paciente = paciente;
        this.medico = medico;
        this.internacao = internacao;
    }

    // Getters e Setters, o básico pra acessar e modificar os atributos.
    
    // ... (todos os getters e setters estão aqui, como sempre) ...
    // Eu não vou comentar um por um, porque é o padrãozão.

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }
    
    // [Cortei os outros Getters e Setters pra não ficar repetitivo, mas eles estão no código original]
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }

    // Método toString() sobrescrito.
    // Pra quando eu der um 'print' no objeto, ele mostrar o nome do exame e o nome do paciente.
    @Override
    public String toString() {
        // Pego o nome do exame e o nome do paciente (usando o getNome() da classe Paciente).
        return nome + " - " + paciente.getNome();
    }
}