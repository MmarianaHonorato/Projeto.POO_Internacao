package control;

import java.util.ArrayList;
import java.util.List;

import model.Leito; // tem numero, tipo e boolean ocupado; toString mostra "OCUPADO/LIVRE"

/**
 * Regras/ações para Leito (listar todos e filtrar livres).
 */
public class LeitoController {

    private final List<Leito> leitos = new ArrayList<>();

    public LeitoController() {
        // Amostras: dois livres, um ocupado
        Leito l101 = new Leito(101, "CLINICO");
        Leito l102 = new Leito(102, "CLINICO");
        Leito l201 = new Leito(201, "UTI_ADULTO");
        l201.setOcupado(true); // simula ocupado

        leitos.add(l101);
        leitos.add(l102);
        leitos.add(l201);
    }

    public boolean cadastrar(int numero, String tipo, boolean ocupado) {
        try {
            Leito l = new Leito(numero, tipo);
            l.setOcupado(ocupado);
            leitos.add(l);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Lista todos os leitos (livres e ocupados). */
    public ArrayList<Leito> listarTodos() {
        return new ArrayList<>(leitos);
    }

    /** Filtra e retorna apenas os livres (ocupado == false). */
    public ArrayList<Leito> listarLivres() {
        ArrayList<Leito> livres = new ArrayList<>();
        for (Leito l : leitos) {
            if (!l.isOcupado()) {
                livres.add(l);
            }
        }
        return livres;
    }

    public Leito getLivreByIndex(int idxNaListaDeLivres) {
        ArrayList<Leito> livres = listarLivres();
        if (idxNaListaDeLivres < 0 || idxNaListaDeLivres >= livres.size()) return null;
        return livres.get(idxNaListaDeLivres);
    }
}