package control;

import java.util.ArrayList;
import java.util.Date;

import model.Paciente;
import model.Medico;
import model.Leito;
import model.Internacao;

/**
 * Fachada unificada chamada pela view.Main (mantém as assinaturas pedidas).
 */
public class Control {

    private final PacienteController   pacientes   = new PacienteController();
    private final MedicoController     medicos     = new MedicoController();
    private final LeitoController      leitos      = new LeitoController();
    private final InternacaoController internacoes = new InternacaoController();

    // -------------------- PACIENTES --------------------

    public boolean cadastrarPaciente(String nome, String cpf, String email,
                                     Date dataNasc, String endereco, String historicoClinico) {
        return pacientes.cadastrar(nome, cpf, email, dataNasc, endereco, historicoClinico);
    }

    public ArrayList<Paciente> listarPacientes() {
        return pacientes.listar();
    }

    // -------------------- MÉDICOS ----------------------

    public ArrayList<Medico> listarMedicos() {
        return medicos.listar();
    }

    // -------------------- LEITOS -----------------------

    public ArrayList<Leito> listarLeitos() {
        return leitos.listarTodos();
    }

    public ArrayList<Leito> listarLeitosLivres() {
        return leitos.listarLivres();
    }

    // -------------------- INTERNAÇÕES ------------------

    /**
     * A view.Main passa índices (paciente, médico, leito-livre),
     * então aqui reconstituímos os objetos para registrar a internação.
     */
    public boolean registrarInternacao(int idxPaciente, int idxMedico, int idxLeitoLivre,
                                       Date dataEntrada, String diagnostico) {
        // Valida paciente e médico
        Paciente p = pacientes.getByIndex(idxPaciente);
        Medico   m = medicos.getByIndex(idxMedico);
        if (p == null || m == null) return false;

        // Leito livre selecionado na lista de livres
        Leito l = leitos.getLivreByIndex(idxLeitoLivre);
        if (l == null) return false;

        // Registra a internação (isso marca o leito como OCUPADO pelo seu Model)
        return internacoes.registrar(p, m, l, dataEntrada, diagnostico);
    }

    public ArrayList<Internacao> listarInternacoes() {
        return internacoes.listar();
    }

    // Extensões futuras:
    // public boolean darAlta(int idxInternacao, Date dataAlta) { ... }
}