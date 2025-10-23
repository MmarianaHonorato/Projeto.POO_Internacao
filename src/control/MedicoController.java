package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Medico; // Medico extends Pessoa; tem crm e especialidade

/**
 * Regras/ações para Médico.
 */
public class MedicoController {

    private final List<Medico> medicos = new ArrayList<>();

    public MedicoController() {
        // Dados de exemplo para a View já listar algo
        medicos.add(new Medico("Dra. Ana", "00011122233", "ana@hosp.com",
                new Date(), "Asa Norte", "CRM-1234", "Clínica Médica"));
        medicos.add(new Medico("Dr. Bruno", "99988877766", "bruno@hosp.com",
                new Date(), "Asa Sul", "CRM-5678", "Cirurgia"));
    }

    public boolean cadastrar(String nome, String cpf, String email, Date dataNasc,
                             String endereco, String crm, String especialidade) {
        try {
            medicos.add(new Medico(nome, cpf, email, dataNasc, endereco, crm, especialidade));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Medico> listar() {
        return new ArrayList<>(medicos);
    }

    public Medico getByIndex(int idx) {
        if (idx < 0 || idx >= medicos.size()) return null;
        return medicos.get(idx);
    }

    public int size() { return medicos.size(); }
}