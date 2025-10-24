package control;

import dao.*;
import model.*;

import java.sql.Date;
import java.util.List;

public class ControlController {

    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final EnfermeiroDAO enfermeiroDAO = new EnfermeiroDAO();
    private final LeitoDAO leitoDAO = new LeitoDAO();
    private final InternacaoDAO internacaoDAO = new InternacaoDAO();
    private final ExameDAO exameDAO = new ExameDAO();

    // ===================== LOGIN =====================
    public boolean login(String email, String senha) {
        Pessoa pessoa = pessoaDAO.login(email, senha);
        return pessoa != null;
    }

    // ===================== PESSOA =====================
    public Pessoa buscarPessoaPorCpf(String cpf) {
        return pessoaDAO.buscarPorCpf(cpf);
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        Pessoa p = pessoaDAO.buscarPorCpf(cpf);
        if (p instanceof Paciente) {
            return (Paciente) p;
        }
        return null;
    }

    public Medico buscarMedicoPorCpf(String cpf) {
        Pessoa p = pessoaDAO.buscarPorCpf(cpf);
        if (p instanceof Medico) {
            return (Medico) p;
        }
        return null;
    }

    public Enfermeiro buscarEnfermeiroPorCpf(String cpf) {
        Pessoa p = pessoaDAO.buscarPorCpf(cpf);
        if (p instanceof Enfermeiro) {
            return (Enfermeiro) p;
        }
        return null;
    }

    // ===================== PACIENTE =====================
    public void cadastrarPaciente(Paciente paciente) {
        if (pacienteDAO.buscarPorCpf(paciente.getCpf()) != null) {
            System.out.println("Paciente já cadastrado.");
            return;
        }
        pacienteDAO.cadastrar(paciente);
        System.out.println("Paciente cadastrado com sucesso.");
    }

    public void atualizarPaciente(Paciente paciente) {
        if (pacienteDAO.buscarPorCpf(paciente.getCpf()) == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }
        pacienteDAO.atualizar(paciente);
        System.out.println("Paciente atualizado com sucesso.");
    }

    public boolean excluirPaciente(String cpf) {
        Paciente p = buscarPacientePorCpf(cpf);
        if (p == null)
            return false;
        pacienteDAO.excluir(cpf);
        return true;
    }

    public List<Paciente> buscarPacientePorNome(String nome) {
        return pacienteDAO.buscarPorNome(nome);
    }

    public int calcularTempoInternacao(String cpfPaciente) {
        return internacaoDAO.calcularDiasInternacao(cpfPaciente);
    }

    // ===================== MÉDICO =====================
    public void cadastrarMedico(Medico medico) {
        if (medicoDAO.buscarPorCpf(medico.getCpf()) != null) {
            System.out.println("Médico já cadastrado.");
            return;
        }
        medicoDAO.cadastrar(medico);
        System.out.println("Médico cadastrado com sucesso.");
    }

    public void atualizarMedico(Medico medico) {
        if (medicoDAO.buscarPorCpf(medico.getCpf()) == null) {
            System.out.println("Médico não encontrado.");
            return;
        }
        medicoDAO.atualizar(medico);
        System.out.println("Médico atualizado com sucesso.");
    }

    public void excluirMedico(String cpf) {
        medicoDAO.excluir(cpf);
        System.out.println("Médico excluído com sucesso.");
    }

    public List<Internacao> buscarInternacoesMedico(String cpfMedico) {
        return internacaoDAO.buscarPorMedico(cpfMedico);
    }

    // ===================== ENFERMEIRO =====================
    public void cadastrarEnfermeiro(Enfermeiro enfermeiro) {
        if (enfermeiroDAO.buscarPorCpf(enfermeiro.getCpf()) != null) {
            System.out.println("Enfermeiro já cadastrado.");
            return;
        }
        enfermeiroDAO.cadastrar(enfermeiro);
        System.out.println("Enfermeiro cadastrado com sucesso.");
    }

    public void atualizarEnfermeiro(Enfermeiro enfermeiro) {
        if (enfermeiroDAO.buscarPorCpf(enfermeiro.getCpf()) == null) {
            System.out.println("Enfermeiro não encontrado.");
            return;
        }
        enfermeiroDAO.atualizar(enfermeiro);
        System.out.println("Enfermeiro atualizado com sucesso.");
    }

    public void excluirEnfermeiro(String cpf) {
        enfermeiroDAO.excluir(cpf);
        System.out.println("Enfermeiro excluído com sucesso.");
    }

    public List<Internacao> buscarInternacoesEnfermeiro(String cpfEnfermeiro) {
        return internacaoDAO.buscarPorEnfermeiro(cpfEnfermeiro);
    }

    // ===================== LEITO =====================
    public void cadastrarLeito(Leito leito) {
        leito.setStatus("Disponível");
        leitoDAO.cadastrar(leito);
        System.out.println("Leito cadastrado com sucesso.");
    }

    public Leito buscarLeitoPorId(int idLeito) {
        return leitoDAO.buscarPorId(idLeito);
    }

    public void atualizarLeito(Leito leito) {
        if (leitoDAO.buscarPorId(leito.getIdLeito()) == null) {
            System.out.println("Leito não encontrado.");
            return;
        }
        leitoDAO.atualizar(leito);
        System.out.println("Leito atualizado com sucesso.");
    }

    public void excluirLeito(int idLeito) {
        leitoDAO.excluir(idLeito);
        System.out.println("Leito excluído com sucesso.");
    }

    public void listarLeitosDisponiveis() {
        List<Leito> leitos = leitoDAO.listarDisponiveis();
        System.out.println("\nLeitos disponíveis:");
        for (Leito l : leitos) {
            System.out.println("ID: " + l.getIdLeito() + " | Tipo: " + l.getTipo() + " | Número: " + l.getNumero());
        }
    }

    // ===================== INTERNAÇÃO =====================
    public void cadastrarInternacao(Internacao internacao) {
        internacaoDAO.cadastrar(internacao);
        System.out.println("Internação cadastrada com sucesso.");
    }

    public Internacao buscarInternacaoPorId(int idInternacao) {
        return internacaoDAO.buscarPorId(idInternacao);
    }

    public void atualizarAlta(int idInternacao) {
        internacaoDAO.atualizarAlta(idInternacao);
        System.out.println("Alta atualizada com sucesso.");
    }

    // ===================== EXAME =====================
    public void cadastrarExame(Exame exame) {
        exame.setDataPedido(new java.util.Date());
        exameDAO.cadastrar(exame);
        System.out.println("Exame cadastrado com sucesso.");
    }

    public void atualizarResultadoExame(int idExame, java.util.Date dataResultado) {
        Date sqlDate = new Date(dataResultado.getTime());
        exameDAO.atualizarResultado(idExame, sqlDate);
        System.out.println("Resultado do exame atualizado com sucesso.");
    }

    public void listarExames() {
        List<Exame> exames = exameDAO.listar();
        System.out.println("\nLista de exames:");
        for (Exame e : exames) {
            System.out.println(
                    "ID: " + e.getIdExame() + " | Nome: " + e.getNome() + " | Paciente: " + e.getPaciente().getCpf());
        }
    }

    public void excluirExame(int idExame) {
        exameDAO.excluir(idExame);
        System.out.println("Exame excluído com sucesso.");
    }

    // ===================== PRONTUÁRIO =====================
    public void exibirProntuario(String cpfPaciente) {
        Paciente pac = buscarPacientePorCpf(cpfPaciente);
        if (pac == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("\nProntuário do paciente:");
        System.out.println("Nome: " + pac.getNome());
        System.out.println("CPF: " + pac.getCpf());
        System.out.println("Email: " + pac.getEmail());
        System.out.println("Data de Nascimento: " + pac.getDataNascimento());
        System.out.println("Histórico Clínico: " + pac.getHistoricoClinico());

        List<Internacao> internacoes = internacaoDAO.buscarPorPaciente(cpfPaciente);
        for (Internacao i : internacoes) {
            System.out.println("\nInternação ID: " + i.getIdInternacao());
            System.out.println("Diagnóstico: " + i.getDiagnostico());
            System.out.println("Data de entrada: " + i.getDataEntrada());
            System.out.println("Data de alta: " + i.getDataAlta());
        }

        List<Exame> exames = exameDAO.buscarPorPaciente(cpfPaciente);
        for (Exame e : exames) {
            System.out.println("\nExame: " + e.getNome());
            System.out.println("Descrição: " + e.getDescricao());
            System.out.println("Data Pedido: " + e.getDataPedido());
            System.out.println("Data Resultado: " + e.getDataResultado());
        }
    }
}
