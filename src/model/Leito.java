package model;

// Classe para a entidade "Leito" 
public class Leito {
    private int numero;
    private String tipo; // Ex: UTI, Quarto 
    private boolean ocupado; // status livre ou ocupado

    public Leito(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ocupado = false; // Todo leito começa livre
    }

    // Getters e Setters
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

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        // Operador ternário um if ou else simples
        String status = ocupado ? "OCUPADO" : "LIVRE"; 
        return "Leito nº: " + numero + " (Tipo: " + tipo + ") - Status: " + status;
    }
}