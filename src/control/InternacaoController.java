package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Internacao;
import model.Medico;
import model.Paciente;
import model.Leito;

/**
 * Regras/ações para Internação.
 * Cria Internacao que, pelo seu Model, já marca o Leito como OCUPADO.
 */
public class InternacaoController {

    private final List<Internacao> internacoes = new ArrayList<>();

    /**
     * Registra uma nova Internação com objetos já validados.
     */
    public boolean registrar(Paciente paciente, Medico medico, Leito leitoLivre,
                             Date dataEntrada, String diagnostico) {
        try {
            Internacao i = new Internacao(paciente, medico, leitoLivre, dataEntrada, diagnostico);
            internacoes.add(i); // o construtor já setou o leito como ocupado
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Internacao> listar() {
        return new ArrayList<>(internacoes);
    }

    /** Dá alta e libera leito (seu Model já faz setOcupado(false) no registrarAlta). */
    public boolean darAlta(int idxInternacao, Date dataAlta) {
        if (idxInternacao < 0 || idxInternacao >= internacoes.size()) return false;
        Internacao i = internacoes.get(idxInternacao);
        i.registrarAlta(dataAlta);
        return true;
    }
}