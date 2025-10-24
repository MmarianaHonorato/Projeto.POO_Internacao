package model;

import java.util.Date; // Pra poder usar a data de nascimento.

// Classe Pessoa. Essa é a base! Ela guarda as informações que são comuns a
// todo mundo no sistema: Paciente, Médico, Enfermeiro, etc.
public class Pessoa {
    // Atributos básicos que toda pessoa tem.
    private String cpf; // O identificador único.
    private String nome;
    private String email;
    private Date dataNascimento;
    private Endereco endereco; // Referência à classe Endereco (tem um Endereco dentro de Pessoa).
    private String perfil; // O tipo de usuário no sistema: "Paciente", "Médico", "Admin", etc.
    private String senha; // Pra fazer login.

    // Construtor vazio. Padrão pra inicializar sem passar nada.
    public Pessoa() {}

    // Construtor completo. Pra inicializar todos os dados de uma vez.
    public Pessoa(String cpf, String nome, String email, Date dataNascimento, Endereco endereco, String perfil, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.perfil = perfil;
        this.senha = senha;
    }

    // ATENÇÃO: Construtor estranho!
    // Esse construtor aqui parece que foi gerado automaticamente e não tá sendo usado.
    // O 'throw new UnsupportedOperationException' diz que se alguém tentar usar, o sistema vai quebrar.
    // Eu deixaria ele de lado ou apagaria, a não ser que eu precise muito dele.
    public Pessoa(String string, String string0, java.sql.Date date, String string1, String string2, int aInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Getters e Setters para todos os atributos. É o que permite acessar os dados
    // que são 'private' (protegidos) de fora da classe.

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    // ... (Os outros Getters e Setters seguem o mesmo padrão) ...

    public Endereco getEndereco() { // Retorna o objeto Endereco inteiro.
        return endereco;
    }

    public void setEndereco(Endereco endereco) { // Seta um novo objeto Endereco.
        this.endereco = endereco;
    }
    
    // Sobrescrevi o toString().
    // Pra quando eu quiser imprimir qualquer pessoa, ele mostra o Nome e o Perfil (tipo: "João da Silva (Paciente)").
    @Override
    public String toString() {
        return nome + " (" + perfil + ")";
    }
}