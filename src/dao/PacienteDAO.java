package dao;

import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // Cadastrar novo paciente
    public void cadastrar(Paciente paciente) {
        String sqlPessoa = "INSERT INTO PESSOA (CPF, Nome, Email, Data_Nascimento, Perfil, Senha) VALUES (?, ?, ?, ?, 'Paciente', '123')";
        String sqlPaciente = "INSERT INTO PACIENTE (CPF_Paciente, Historico_Clinico) VALUES (?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
                PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
                PreparedStatement stmtPaciente = conn.prepareStatement(sqlPaciente)) {

            stmtPessoa.setString(1, paciente.getCpf());
            stmtPessoa.setString(2, paciente.getNome());
            stmtPessoa.setString(3, paciente.getEmail());
            stmtPessoa.setDate(4, new java.sql.Date(paciente.getDataNascimento().getTime()));
            stmtPessoa.executeUpdate();

            stmtPaciente.setString(1, paciente.getCpf());
            stmtPaciente.setString(2, paciente.getHistoricoClinico());
            stmtPaciente.executeUpdate();

            System.out.println("Paciente cadastrado com sucesso!");

        } catch (SQLException e) {
            // Tratar duplicata de CPF
            if (e.getErrorCode() == 1062) { // Código MySQL para chave primária duplicada
                System.out.println(" Paciente com esse CPF já existe!");
            } else {
                System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
            }
        }
    }

    // Atualizar paciente
    public void atualizar(Paciente paciente) {
        String sqlPessoa = "UPDATE PESSOA SET Nome = ?, Email = ? WHERE CPF = ?";
        String sqlPaciente = "UPDATE PACIENTE SET Historico_Clinico = ? WHERE CPF_Paciente = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
                PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
                PreparedStatement stmtPaciente = conn.prepareStatement(sqlPaciente)) {

            stmtPessoa.setString(1, paciente.getNome());
            stmtPessoa.setString(2, paciente.getEmail());
            stmtPessoa.setString(3, paciente.getCpf());
            int linhasPessoa = stmtPessoa.executeUpdate();

            stmtPaciente.setString(1, paciente.getHistoricoClinico());
            stmtPaciente.setString(2, paciente.getCpf());
            int linhasPaciente = stmtPaciente.executeUpdate();

            if (linhasPessoa > 0 || linhasPaciente > 0) {
                System.out.println(" Dados atualizados com sucesso!");
            } else {
                System.out.println(" Nenhum dado foi atualizado. CPF não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    // Excluir paciente
    public boolean excluir(String cpf) {
        String sqlPaciente = "DELETE FROM PACIENTE WHERE CPF_Paciente = ?";
        String sqlPessoa = "DELETE FROM PESSOA WHERE CPF = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
                PreparedStatement stmtPaciente = conn.prepareStatement(sqlPaciente);
                PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {

            // Executa DELETE no paciente
            int linhasPaciente = stmtPaciente.executeUpdate();

            // Se não conseguiu excluir paciente, retorna false
            if (linhasPaciente == 0) {
                return false;
            }

            // Exclui a pessoa correspondente
            stmtPessoa.setString(1, cpf);
            stmtPessoa.executeUpdate();

            // Se chegou até aqui, deu tudo certo
            return true;

        } catch (SQLException e) {
            // Se der erro (como restrição de foreign key), apenas retorna false
            return false;
        }
    }

    // Buscar paciente por CPF
    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT p.CPF, p.Nome, p.Email, p.Data_Nascimento, pa.Historico_Clinico " +
                "FROM PESSOA p JOIN PACIENTE pa ON p.CPF = pa.CPF_Paciente WHERE p.CPF = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF"));
                paciente.setNome(rs.getString("Nome"));
                paciente.setEmail(rs.getString("Email"));
                paciente.setDataNascimento(rs.getDate("Data_Nascimento"));
                paciente.setHistoricoClinico(rs.getString("Historico_Clinico"));
                return paciente;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null;
    }

    // Buscar pacientes por nome
    public List<Paciente> buscarPorNome(String nome) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT p.CPF, p.Nome, p.Email, p.Data_Nascimento, pa.Historico_Clinico " +
                "FROM PESSOA p JOIN PACIENTE pa ON p.CPF = pa.CPF_Paciente WHERE p.Nome LIKE ?";

        try (Connection conn = ConexaoMySQL.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF"));
                paciente.setNome(rs.getString("Nome"));
                paciente.setEmail(rs.getString("Email"));
                paciente.setDataNascimento(rs.getDate("Data_Nascimento"));
                paciente.setHistoricoClinico(rs.getString("Historico_Clinico"));
                lista.add(paciente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacientes por nome: " + e.getMessage());
        }
        return lista;
    }
}
