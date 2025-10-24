package dao;

import model.Leito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeitoDAO {
    private Connection connection;

    public LeitoDAO() {
        // Assumindo ConnectionFactory.getConnection();
    }

    public void cadastrar(Leito leito) {
        String sql = "INSERT INTO LEITO (Numero, Tipo, Status) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, String.valueOf(leito.getNumero()));  // Converte int para String
            stmt.setString(2, leito.getTipo());
            stmt.setString(3, leito.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                leito.setIdLeito(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Leito leito) {
        String sql = "UPDATE LEITO SET Numero = ?, Tipo = ?, Status = ? WHERE ID_Leito = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(leito.getNumero()));  // Converte int para String
            stmt.setString(2, leito.getTipo());
            stmt.setString(3, leito.getStatus());
            stmt.setInt(4, leito.getIdLeito());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idLeito) {
        String sql = "DELETE FROM LEITO WHERE ID_Leito = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLeito);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Leito buscarPorId(int idLeito) {
        String sql = "SELECT * FROM LEITO WHERE ID_Leito = ?";
        Leito leito = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLeito);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                leito = new Leito();
                leito.setIdLeito(rs.getInt("ID_Leito"));
                leito.setNumero(Integer.parseInt(rs.getString("Numero")));  // Converte String para int
                leito.setTipo(rs.getString("Tipo"));
                leito.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leito;
    }

    public List<Leito> listarDisponiveis() {
        String sql = "SELECT * FROM LEITO WHERE Status = 'Disponível'";
        List<Leito> leitos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Leito leito = new Leito();
                leito.setIdLeito(rs.getInt("ID_Leito"));
                leito.setNumero(Integer.parseInt(rs.getString("Numero")));  // Converte String para int
                leito.setTipo(rs.getString("Tipo"));
                leito.setStatus(rs.getString("Status"));
                leitos.add(leito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leitos;
    }

    public List<Leito> listarTodos() {
        String sql = "SELECT * FROM LEITO";
        List<Leito> leitos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Leito leito = new Leito();
                leito.setIdLeito(rs.getInt("ID_Leito"));
                leito.setNumero(Integer.parseInt(rs.getString("Numero")));  // Converte String para int
                leito.setTipo(rs.getString("Tipo"));
                leito.setStatus(rs.getString("Status"));
                leitos.add(leito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leitos;
    }
}