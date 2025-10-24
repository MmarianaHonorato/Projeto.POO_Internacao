package model;

// Classe Leito. Ela representa uma cama/leito no hospital.
public class Leito {
    private int idLeito; // ID único do leito (chave primária no banco).
    private int numero; // O número real do leito (tipo 101, 2B, etc. Mas aqui tá como int).
    private String tipo; // Tipo de leito, como "Enfermaria", "UTI", "Particular".
    private String status; // Status do leito: "Ocupado", "Vago", "Em manutenção".

    // Construtor vazio. Pra quando eu crio o objeto e seto as coisas depois.
    public Leito() {}

    // Construtor completo. Pra criar o leito com todos os dados de uma vez.
    public Leito(int idLeito, int numero, String tipo, String status) {
        this.idLeito = idLeito;
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
    }

    // Getters e Setters, pra acessar e modificar os dados de forma controlada.

    public int getIdLeito() {
        return idLeito;
    }

    public void setIdLeito(int idLeito) {
        this.idLeito = idLeito;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Sobrescrevi o toString().
    // Ele retorna uma string bem completa pra eu saber qual leito é, o tipo e se tá livre ou não.
    // Exemplo: "Leito 105 (UTI) - Ocupado"
    @Override
    public String toString() {
        return "Leito " + numero + " (" + tipo + ") - " + status;
    }
}