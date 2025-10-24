package model;

// Classe Endereco, pra guardar os dados de um endereço, tipo CEP, cidade, etc.
public class Endereco {
    // Esses são os atributos (as "variáveis") do meu endereço.
    private int idEndereco; // O ID único no banco de dados.
    private String cep;
    private String estado;
    private String cidade;
    private String numero; // Deixei como String pra facilitar (pode ser "s/n").
    private String complemento; // Apartamento, bloco, essas coisas.

    // Construtor vazio (padrão). Bom pra quando eu for criar o objeto e setar as coisas depois.
    public Endereco() {
    }

    // Construtor completo, com o ID. Uso quando tô lendo do banco.
    public Endereco(int idEndereco, String cep, String estado, String cidade, String numero, String complemento) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
    }

    // Construtor sem o ID. Uso quando tô criando um endereço novo que ainda não tá no banco.
    public Endereco(String cep, String estado, String cidade, String numero, String complemento) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
    }

    // Getters e Setters (padrãozão do Java) pra acessar e modificar os atributos.

    public int getIdEndereco() { // Pra pegar o ID
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) { // Pra mudar o ID
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    // Sobrescrevi o método toString() pra quando eu quiser imprimir o objeto Endereco.
    // Vai sair num formato mais legível, tipo: "São Paulo - SP, 123"
    @Override
    public String toString() {
        return cidade + " - " + estado + ", " + numero;
    }
}