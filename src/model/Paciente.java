package model;

// Classe Paciente. Como um Médico ou Enfermeiro, o Paciente TAMBÉM é uma Pessoa.
// Por isso, a gente estende ('extends') a classe Pessoa, herdando todos os dados básicos (CPF, nome, etc.).
public class Paciente extends Pessoa {
    // Atributo exclusivo do Paciente.
    private String historicoClinico; // O histórico de saúde, doenças prévias, alergias, etc. (Deixei como String pra ser simples).

    // Construtor vazio. Pra criar o objeto e ir setando os dados depois.
    public Paciente() {}

    // Construtor completo. Pra criar o Paciente com todas as informações de uma vez.
    public Paciente(String cpf, String nome, String email, java.util.Date dataNascimento, Endereco endereco, String perfil, String senha, String historicoClinico) {
        // Chamo o 'super(...)' pra inicializar todos os dados da Pessoa.
        super(cpf, nome, email, dataNascimento, endereco, perfil, senha);
        // E seto o atributo que é só dele.
        this.historicoClinico = historicoClinico;
    }

    // Getter para pegar o histórico clínico.
    public String getHistoricoClinico() {
        return historicoClinico;
    }

    // Setter para alterar o histórico clínico.
    public void setHistoricoClinico(String historicoClinico) {
        this.historicoClinico = historicoClinico;
    }

    // Sobrescrevi o toString().
    // Assim, quando eu printar o objeto Paciente, ele mostra o Nome e o Histórico.
    @Override
    public String toString() {
        // Uso getNome() porque 'nome' tá privado lá na classe Pessoa, mas é herdado.
        return getNome() + " - " + historicoClinico;
    }
}