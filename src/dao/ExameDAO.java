package dao;

import model.Exame;
import model.Paciente;
import model.Medico;
import model.Internacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {
    private Connection connection;

    public ExameDAO() {
        this.connection = ConexaoMySQL.getConnection();
    }

    public void cadastrar(Exame exame) {
        String sql = "INSERT INTO EXAME (Nome, Descricao, Data_Pedido, Data_Resultado, CPF_Paciente, CPF_Medico, ID_Internacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exame.getNome());
            stmt.setString(2, exame.getDescricao());
            stmt.setTimestamp(3, new Timestamp(exame.getDataPedido().getTime()));
            if (exame.getDataResultado() != null) {
                stmt.setTimestamp(4, new Timestamp(exame.getDataResultado().getTime()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }
            stmt.setString(5, exame.getPaciente().getCpf());
            stmt.setString(6, exame.getMedico().getCpf());
            stmt.setInt(7, exame.getInternacao().getIdInternacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                exame.setIdExame(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarResultado(int idExame, java.util.Date dataResultado) {
        String sql = "UPDATE EXAME SET Data_Resultado = ? WHERE ID_Exame = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(dataResultado.getTime()));
            stmt.setInt(2, idExame);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idExame) {
        String sql = "DELETE FROM EXAME WHERE ID_Exame = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idExame);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exame buscarPorId(int idExame) {
        String sql = "SELECT e.*, p.Nome AS PacienteNome, m.Nome AS MedicoNome, i.Diagnostico AS InternacaoDiagnostico "
                +
                "FROM EXAME e " +
                "JOIN PACIENTE pa ON e.CPF_Paciente = pa.CPF_Paciente " +
                "JOIN PESSOA p ON pa.CPF_Paciente = p.CPF " +
                "JOIN MEDICO me ON e.CPF_Medico = me.CPF_Medico " +
                "JOIN PESSOA m ON me.CPF_Medico = m.CPF " +
                "JOIN INTERNACAO i ON e.ID_Internacao = i.ID_Internacao " +
                "WHERE e.ID_Exame = ?";
        Exame exame = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idExame);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF_Paciente"));
                paciente.setNome(rs.getString("PacienteNome"));

                Medico medico = new Medico();
                medico.setCpf(rs.getString("CPF_Medico"));
                medico.setNome(rs.getString("MedicoNome"));

                Internacao internacao = new Internacao();
                internacao.setIdInternacao(rs.getInt("ID_Internacao"));
                internacao.setDiagnostico(rs.getString("InternacaoDiagnostico"));

                exame = new Exame(
                        rs.getInt("ID_Exame"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getTimestamp("Data_Pedido"),
                        rs.getTimestamp("Data_Resultado"),
                        paciente,
                        medico,
                        internacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exame;
    }

    public List<Exame> listar() {
        String sql = "SELECT e.*, p.Nome AS PacienteNome, m.Nome AS MedicoNome, i.Diagnostico AS InternacaoDiagnostico "
                +
                "FROM EXAME e " +
                "JOIN PACIENTE pa ON e.CPF_Paciente = pa.CPF_Paciente " +
                "JOIN PESSOA p ON pa.CPF_Paciente = p.CPF " +
                "JOIN MEDICO me ON e.CPF_Medico = me.CPF_Medico " +
                "JOIN PESSOA m ON me.CPF_Medico = m.CPF " +
                "JOIN INTERNACAO i ON e.ID_Internacao = i.ID_Internacao";
        List<Exame> exames = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF_Paciente"));
                paciente.setNome(rs.getString("PacienteNome"));

                Medico medico = new Medico();
                medico.setCpf(rs.getString("CPF_Medico"));
                medico.setNome(rs.getString("MedicoNome"));

                Internacao internacao = new Internacao();
                internacao.setIdInternacao(rs.getInt("ID_Internacao"));
                internacao.setDiagnostico(rs.getString("InternacaoDiagnostico"));

                Exame exame = new Exame(
                        rs.getInt("ID_Exame"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getTimestamp("Data_Pedido"),
                        rs.getTimestamp("Data_Resultado"),
                        paciente,
                        medico,
                        internacao);
                exames.add(exame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exames;
    }

    public List<Exame> buscarPorPaciente(String cpfPaciente) {
        String sql = "SELECT e.*, p.Nome AS PacienteNome, m.Nome AS MedicoNome, i.Diagnostico AS InternacaoDiagnostico "
                +
                "FROM EXAME e " +
                "JOIN PACIENTE pa ON e.CPF_Paciente = pa.CPF_Paciente " +
                "JOIN PESSOA p ON pa.CPF_Paciente = p.CPF " +
                "JOIN MEDICO me ON e.CPF_Medico = me.CPF_Medico " +
                "JOIN PESSOA m ON me.CPF_Medico = m.CPF " +
                "JOIN INTERNACAO i ON e.ID_Internacao = i.ID_Internacao " +
                "WHERE e.CPF_Paciente = ?";
        List<Exame> exames = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpfPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF_Paciente"));
                paciente.setNome(rs.getString("PacienteNome"));

                Medico medico = new Medico();
                medico.setCpf(rs.getString("CPF_Medico"));
                medico.setNome(rs.getString("MedicoNome"));

                Internacao internacao = new Internacao();
                internacao.setIdInternacao(rs.getInt("ID_Internacao"));
                internacao.setDiagnostico(rs.getString("InternacaoDiagnostico"));

                Exame exame = new Exame(
                        rs.getInt("ID_Exame"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getTimestamp("Data_Pedido"),
                        rs.getTimestamp("Data_Resultado"),
                        paciente,
                        medico,
                        internacao);
                exames.add(exame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exames;
    }
}