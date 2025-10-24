package dao;

import model.Medico;
import model.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private Connection connection;

    public MedicoDAO() {
        // Assumindo que há uma classe de conexão, como ConnectionFactory
        // this.connection = ConnectionFactory.getConnection();
        // Para este exemplo, vou deixar como comentário, pois não foi fornecida.
    }

    public void cadastrar(Medico medico) {
        String sqlPessoa = "INSERT INTO PESSOA (CPF, Nome, Email, Data_Nascimento, ID_Endereco, Perfil, Senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlMedico = "INSERT INTO MEDICO (CPF_Medico, CRM, Especialidade) VALUES (?, ?, ?)";

        try (PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico)) {

            // Inserir na tabela PESSOA
            stmtPessoa.setString(1, medico.getCpf());
            stmtPessoa.setString(2, medico.getNome());
            stmtPessoa.setString(3, medico.getEmail());
            stmtPessoa.setDate(4, new java.sql.Date(medico.getDataNascimento().getTime()));
            stmtPessoa.setInt(5, medico.getEndereco().getIdEndereco());
            stmtPessoa.setString(6, medico.getPerfil());
            stmtPessoa.setString(7, medico.getSenha());
            stmtPessoa.executeUpdate();

            // Inserir na tabela MEDICO
            stmtMedico.setString(1, medico.getCpf());
            stmtMedico.setString(2, medico.getCrm());
            stmtMedico.setString(3, medico.getEspecialidade());
            stmtMedico.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Medico medico) {
        String sqlPessoa = "UPDATE PESSOA SET Nome = ?, Email = ?, Data_Nascimento = ?, ID_Endereco = ?, Perfil = ?, Senha = ? WHERE CPF = ?";
        String sqlMedico = "UPDATE MEDICO SET CRM = ?, Especialidade = ? WHERE CPF_Medico = ?";

        try (PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico)) {

            // Atualizar PESSOA
            stmtPessoa.setString(1, medico.getNome());
            stmtPessoa.setString(2, medico.getEmail());
            stmtPessoa.setDate(3, new java.sql.Date(medico.getDataNascimento().getTime()));
            stmtPessoa.setInt(4, medico.getEndereco().getIdEndereco());
            stmtPessoa.setString(5, medico.getPerfil());
            stmtPessoa.setString(6, medico.getSenha());
            stmtPessoa.setString(7, medico.getCpf());
            stmtPessoa.executeUpdate();

            // Atualizar MEDICO
            stmtMedico.setString(1, medico.getCrm());
            stmtMedico.setString(2, medico.getEspecialidade());
            stmtMedico.setString(3, medico.getCpf());
            stmtMedico.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String cpf) {
        String sqlMedico = "DELETE FROM MEDICO WHERE CPF_Medico = ?";
        String sqlPessoa = "DELETE FROM PESSOA WHERE CPF = ?";

        try (PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico);
             PreparedStatement stmtPessoa = connection.prepareStatement(sqlPessoa)) {

            // Excluir de MEDICO primeiro (devido à FK)
            stmtMedico.setString(1, cpf);
            stmtMedico.executeUpdate();

            // Excluir de PESSOA
            stmtPessoa.setString(1, cpf);
            stmtPessoa.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medico buscarPorCpf(String cpf) {
        String sql = "SELECT p.CPF, p.Nome, p.Email, p.Data_Nascimento, p.ID_Endereco, p.Perfil, p.Senha, m.CRM, m.Especialidade " +
                     "FROM PESSOA p JOIN MEDICO m ON p.CPF = m.CPF_Medico WHERE p.CPF = ?";
        Medico medico = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Endereco endereco = new Endereco(); // Assumindo que há um método para buscar Endereco por ID
                endereco.setIdEndereco(rs.getInt("ID_Endereco"));
                // Preencher outros campos do Endereco se necessário

                medico = new Medico(
                    rs.getString("CPF"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getDate("Data_Nascimento"),
                    endereco,
                    rs.getString("Perfil"),
                    rs.getString("Senha"),
                    rs.getString("CRM"),
                    rs.getString("Especialidade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medico;
    }

    // Método adicional para buscar por nome, se necessário
    public List<Medico> buscarPorNome(String nome) {
        String sql = "SELECT p.CPF, p.Nome, p.Email, p.Data_Nascimento, p.ID_Endereco, p.Perfil, p.Senha, m.CRM, m.Especialidade " +
                     "FROM PESSOA p JOIN MEDICO m ON p.CPF = m.CPF_Medico WHERE p.Nome LIKE ?";
        List<Medico> medicos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("ID_Endereco"));
                // Preencher outros campos

                Medico medico = new Medico(
                    rs.getString("CPF"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getDate("Data_Nascimento"),
                    endereco,
                    rs.getString("Perfil"),
                    rs.getString("Senha"),
                    rs.getString("CRM"),
                    rs.getString("Especialidade")
                );
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
    }
}