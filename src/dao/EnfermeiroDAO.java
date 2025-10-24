package dao;

import model.Enfermeiro;
import model.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnfermeiroDAO {
    private Connection connection;

    public EnfermeiroDAO() {
        // Assumindo ConnectionFactory.getConnection();
    }

    public void cadastrar(Enfermeiro enfermeiro) {
        String sqlPessoa = "INSERT INTO PESSOA (CPF, Nome, Email, Data_Nascimento, ID_Endereco, Perfil, Senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlEnfermeiro = "INSERT INTO ENFERMEIRO (CPF_Enfermeiro, Coren) VALUES (?, ?)";

        try (PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement stmtEnfermeiro = connection.prepareStatement(sqlEnfermeiro)) {

            // Inserir na tabela PESSOA
            stmtPessoa.setString(1, enfermeiro.getCpf());
            stmtPessoa.setString(2, enfermeiro.getNome());
            stmtPessoa.setString(3, enfermeiro.getEmail());
            stmtPessoa.setDate(4, new java.sql.Date(enfermeiro.getDataNascimento().getTime()));
            stmtPessoa.setInt(5, enfermeiro.getEndereco().getIdEndereco());
            stmtPessoa.setString(6, enfermeiro.getPerfil());
            stmtPessoa.setString(7, enfermeiro.getSenha());
            stmtPessoa.executeUpdate();

            // Inserir na tabela ENFERMEIRO
            stmtEnfermeiro.setString(1, enfermeiro.getCpf());
            stmtEnfermeiro.setString(2, enfermeiro.getCoren());
            stmtEnfermeiro.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Enfermeiro enfermeiro) {
        String sqlPessoa = "UPDATE PESSOA SET Nome = ?, Email = ?, Data_Nascimento = ?, ID_Endereco = ?, Perfil = ?, Senha = ? WHERE CPF = ?";
        String sqlEnfermeiro = "UPDATE ENFERMEIRO SET Coren = ? WHERE CPF_Enfermeiro = ?";

        try (PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement stmtEnfermeiro = connection.prepareStatement(sqlEnfermeiro)) {

            // Atualizar PESSOA
            stmtPessoa.setString(1, enfermeiro.getNome());
            stmtPessoa.setString(2, enfermeiro.getEmail());
            stmtPessoa.setDate(3, new java.sql.Date(enfermeiro.getDataNascimento().getTime()));
            stmtPessoa.setInt(4, enfermeiro.getEndereco().getIdEndereco());
            stmtPessoa.setString(5, enfermeiro.getPerfil());
            stmtPessoa.setString(6, enfermeiro.getSenha());
            stmtPessoa.setString(7, enfermeiro.getCpf());
            stmtPessoa.executeUpdate();

            // Atualizar ENFERMEIRO
            stmtEnfermeiro.setString(1, enfermeiro.getCoren());
            stmtEnfermeiro.setString(2, enfermeiro.getCpf());
            stmtEnfermeiro.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String cpf) {
        String sqlEnfermeiro = "DELETE FROM ENFERMEIRO WHERE CPF_Enfermeiro = ?";
        String sqlPessoa = "DELETE FROM PESSOA WHERE CPF = ?";

        try (PreparedStatement stmtEnfermeiro = connection.prepareStatement(sqlEnfermeiro);
             PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa)) {

            // Excluir de ENFERMEIRO primeiro (devido à FK)
            stmtEnfermeiro.setString(1, cpf);
            stmtEnfermeiro.executeUpdate();

            // Excluir de PESSOA
            stmtPessoa.setString(1, cpf);
            stmtPessoa.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Enfermeiro buscarPorCpf(String cpf) {
        String sql = "SELECT p.CPF, p.Nome, p.Email, p.Data_Nascimento, p.ID_Endereco, p.Perfil, p.Senha, e.Coren " +
                     "FROM PESSOA p JOIN ENFERMEIRO e ON p.CPF = e.CPF_Enfermeiro WHERE p.CPF = ?";
        Enfermeiro enfermeiro = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("ID_Endereco"));
                // Adicione busca completa do Endereco se necessário (ex.: via EnderecoDAO)

                enfermeiro = new Enfermeiro(
                    rs.getString("CPF"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getDate("Data_Nascimento"),
                    endereco,
                    rs.getString("Perfil"),
                    rs.getString("Senha"),
                    rs.getString("Coren")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enfermeiro;
    }

    public List<Enfermeiro> buscarPorNome(String nome) {
        String sql = "SELECT p.CPF, p.Nome, p.Email, p.Data_Nascimento, p.ID_Endereco, p.Perfil, p.Senha, e.Coren " +
                     "FROM PESSOA p JOIN ENFERMEIRO e ON p.CPF = e.CPF_Enfermeiro WHERE p.Nome LIKE ?";
        List<Enfermeiro> enfermeiros = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("ID_Endereco"));

                Enfermeiro enfermeiro = new Enfermeiro(
                    rs.getString("CPF"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getDate("Data_Nascimento"),
                    endereco,
                    rs.getString("Perfil"),
                    rs.getString("Senha"),
                    rs.getString("Coren")
                );
                enfermeiros.add(enfermeiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enfermeiros;
    }
}
