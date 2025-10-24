package model;

// Classe Medico. Ela herda tudo da classe Pessoa (cpf, nome, endereço, etc.).
public class Medico extends Pessoa {
    // Atributos específicos de um médico.
    private String crm; // O registro do médico (Conselho Regional de Medicina).
    private String especialidade; // A área dele, tipo "Cardiologia", "Pediatria", etc.

    // Construtor vazio. Padrão pra criar o objeto sem dados e preencher depois.
    public Medico() {}

    // Construtor completo.
    // Chamo o 'super(...)' primeiro pra inicializar os campos que vêm da Pessoa.
    public Medico(String cpf, String nome, String email, java.util.Date dataNascimento, Endereco endereco, String perfil, String senha, String crm, String especialidade) {
        // Inicializa a parte 'Pessoa' do objeto.
        super(cpf, nome, email, dataNascimento, endereco, perfil, senha);
        // Inicializa os atributos exclusivos do Medico.
        this.crm = crm;
        this.especialidade = especialidade;
    }

    // Getters e Setters para o CRM.

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    // Getters e Setters para a Especialidade.

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    // Sobrescrevi o toString().
    // Quando eu printar um objeto Medico, ele vai mostrar o Nome e a Especialidade.
    // Uso getNome() porque 'nome' é privado na classe Pessoa.
    @Override
    public String toString() {
        return getNome() + " - " + especialidade;
    }
}