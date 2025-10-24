package dao;

import model.Pessoa;
import java.sql.*;

public class PessoaDAO {

    public Pessoa login(String email, String senha) {
        String sql = "SELECT * FROM PESSOA WHERE Email = ? AND Senha = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setCpf(rs.getString("CPF"));
                p.setNome(rs.getString("Nome"));
                p.setEmail(rs.getString("Email"));
                p.setPerfil(rs.getString("Perfil"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
        return null;
    }

    public Pessoa buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM PESSOA WHERE CPF = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setCpf(rs.getString("CPF"));
                p.setNome(rs.getString("Nome"));
                p.setEmail(rs.getString("Email"));
                p.setPerfil(rs.getString("Perfil"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa: " + e.getMessage());
        }
        return null;
    }
}
