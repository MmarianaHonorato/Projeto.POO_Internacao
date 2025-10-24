package model;

// A classe Enfermeiro, que É UMA Pessoa (por isso o 'extends Pessoa').
// Ela herda todos os atributos e métodos que eu defini em Pessoa (cpf, nome, etc.).
public class Enfermeiro extends Pessoa {
    // Atributo específico do Enfermeiro: o COREN (registro profissional).
    private String coren;

    // Construtor vazio. Sempre útil!
    public Enfermeiro() {}

    // Construtor completo. Repara que eu chamo o 'super(...)' pra passar
    // os dados da Pessoa pra classe mãe, e só depois defino o atributo 'coren'
    // que é exclusivo dessa classe.
    public Enfermeiro(String cpf, String nome, String email, java.util.Date dataNascimento, Endereco endereco, String perfil, String senha, String coren) {
        // Manda os dados de Pessoa pra quem tá lá em cima (a classe Pessoa).
        super(cpf, nome, email, dataNascimento, endereco, perfil, senha);
        // E aqui eu defino o que é meu.
        this.coren = coren;
    }

    // Sobrescrevi o método toString().
    // Assim, quando eu pedir pra imprimir um Enfermeiro, vai sair o nome dele e o COREN.
    // Usei o getNome() e getCoren() pra acessar os dados, já que o 'nome' é private lá na classe Pessoa.
    @Override
    public String toString() {
        return getNome() + " (COREN: " + getCoren() + ")";
    }

    // O Getter pra pegar o COREN
    public String getCoren() {
        return coren;
    }

    // O Setter pra mudar o COREN
    public void setCoren(String coren) {
        this.coren = coren;
    }
}