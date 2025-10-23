package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import model.Paciente; // Paciente já existe e herda Pessoa (com histórico clínico)  [citação no texto]
                      // Paciente extends Pessoa: nome, cpf, email, dataNascimento, endereco, historicoClinico

/**
 * Regras/ações para Paciente.
 * Agora usa lista em memória; depois trocamos por DAO sem mudar a assinatura.
 */
public class PacienteController {

    private final List<Paciente> pacientes = new ArrayList<>();

    /** Cadastra um novo paciente (dados vêm da View). */
    public boolean cadastrar(String nome, String cpf, String email,
                             Date dataNasc, String endereco, String historicoClinico) {
        try {
            Paciente p = new Paciente(nome, cpf, email, dataNasc, endereco, historicoClinico);
            pacientes.add(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Devolve a lista inteira para a View imprimir. */
    public ArrayList<Paciente> listar() {
        return new ArrayList<>(pacientes);
    }

    /** Acesso por índice (a View usa índices ao registrar internação). */
    public Paciente getByIndex(int idx) {
        if (idx < 0 || idx >= pacientes.size()) return null;
        return pacientes.get(idx);
    }

    /** Tamanho (útil para validações na fachada). */
    public int size() { return pacientes.size(); }
}