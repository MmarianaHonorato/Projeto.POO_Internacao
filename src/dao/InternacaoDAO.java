package dao;

import model.Internacao;
import model.Paciente;
import model.Medico;
import model.Leito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InternacaoDAO {
    private Connection connection;

    public InternacaoDAO() {
        // Assumindo ConnectionFactory.getConnection();
    }

    public void cadastrar(Internacao internacao) {
        String sql = "INSERT INTO INTERNACAO (Data_Entrada, Data_Alta, Diagnostico, CPF_Paciente, CPF_Medico, ID_Leito) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, new Timestamp(internacao.getDataEntrada().getTime()));
            if (internacao.getDataAlta() != null) {
                stmt.setTimestamp(2, new Timestamp(internacao.getDataAlta().getTime()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }
            stmt.setString(3, internacao.getDiagnostico());
            stmt.setString(4, internacao.getPaciente().getCpf());
            stmt.setString(5, internacao.getMedico().getCpf());
            stmt.setInt(6, internacao.getLeito().getIdLeito());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                internacao.setIdInternacao(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAlta(int idInternacao) {
        String sql = "UPDATE INTERNACAO SET Data_Alta = NOW() WHERE ID_Internacao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInternacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idInternacao) {
        String sql = "DELETE FROM INTERNACAO WHERE ID_Internacao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInternacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Internacao buscarPorId(int idInternacao) {
        String sql = "SELECT i.*, p.Nome AS PacienteNome, m.Nome AS MedicoNome, l.Numero AS LeitoNumero " +
                     "FROM INTERNACAO i " +
                     "JOIN PACIENTE pa ON i.CPF_Paciente = pa.CPF_Paciente " +
                     "JOIN PESSOA p ON pa.CPF_Paciente = p.CPF " +
                     "JOIN MEDICO me ON i.CPF_Medico = me.CPF_Medico " +
                     "JOIN PESSOA m ON me.CPF_Medico = m.CPF " +
                     "JOIN LEITO l ON i.ID_Leito = l.ID_Leito " +
                     "WHERE i.ID_Internacao = ?";
        Internacao internacao = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInternacao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF_Paciente"));
                paciente.setNome(rs.getString("PacienteNome"));

                Medico medico = new Medico();
                medico.setCpf(rs.getString("CPF_Medico"));
                medico.setNome(rs.getString("MedicoNome"));

                Leito leito = new Leito();
                leito.setIdLeito(rs.getInt("ID_Leito"));
                leito.setNumero(Integer.parseInt(rs.getString("LeitoNumero")));

                internacao = new Internacao(
                    rs.getInt("ID_Internacao"),
                    rs.getTimestamp("Data_Entrada"),
                    rs.getTimestamp("Data_Alta"),
                    rs.getString("Diagnostico"),
                    paciente,
                    medico,
                    leito
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return internacao;
    }

    public List<Internacao> buscarPorPaciente(String cpfPaciente) {
        String sql = "SELECT i.*, p.Nome AS PacienteNome, m.Nome AS MedicoNome, l.Numero AS LeitoNumero " +
                     "FROM INTERNACAO i " +
                     "JOIN PACIENTE pa ON i.CPF_Paciente = pa.CPF_Paciente " +
                     "JOIN PESSOA p ON pa.CPF_Paciente = p.CPF " +
                     "JOIN MEDICO me ON i.CPF_Medico = me.CPF_Medico " +
                     "JOIN PESSOA m ON me.CPF_Medico = m.CPF " +
                     "JOIN LEITO l ON i.ID_Leito = l.ID_Leito " +
                     "WHERE i.CPF_Paciente = ?";
        List<Internacao> internacoes = new ArrayList<>();

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

                Leito leito = new Leito();
                leito.setIdLeito(rs.getInt("ID_Leito"));
                leito.setNumero(Integer.parseInt(rs.getString("LeitoNumero")));

                Internacao internacao = new Internacao(
                    rs.getInt("ID_Internacao"),
                    rs.getTimestamp("Data_Entrada"),
                    rs.getTimestamp("Data_Alta"),
                    rs.getString("Diagnostico"),
                    paciente,
                    medico,
                    leito
                );
                internacoes.add(internacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return internacoes;
    }

    public List<Internacao> buscarPorMedico(String cpfMedico) {
        String sql = "SELECT i.*, p.Nome AS PacienteNome, m.Nome AS MedicoNome, l.Numero AS LeitoNumero " +
                     "FROM INTERNACAO i " +
                     "JOIN PACIENTE pa ON i.CPF_Paciente = pa.CPF_Paciente " +
                     "JOIN PESSOA p ON pa.CPF_Paciente = p.CPF " +
                     "JOIN MEDICO me ON i.CPF_Medico = me.CPF_Medico " +
                     "JOIN PESSOA m ON me.CPF_Medico = m.CPF " +
                     "JOIN LEITO l ON i.ID_Leito = l.ID_Leito " +
                     "WHERE i.CPF_Medico = ?";
        List<Internacao> internacoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpfMedico);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("CPF_Paciente"));
                paciente.setNome(rs.getString("PacienteNome"));

                Medico medico = new Medico();
                medico.setCpf(rs.getString("CPF_Medico"));
                medico.setNome(rs.getString("MedicoNome"));

                Leito leito = new Leito();
                leito.setIdLeito(rs.getInt("ID_Leito"));
                leito.setNumero(Integer.parseInt(rs.getString("LeitoNumero")));

                Internacao internacao = new Internacao(
                    rs.getInt("ID_Internacao"),
                    rs.getTimestamp("Data_Entrada"),
                    rs.getTimestamp("Data_Alta"),
                    rs.getString("Diagnostico"),
                    paciente,
                    medico,
                    leito
                );
                internacoes.add(internacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return internacoes;
    }

    // Método adicionado: retorna lista vazia, pois não há ligação direta com Enfermeiro no banco
    public List<Internacao> buscarPorEnfermeiro(String cpfEnfermeiro) {
        // Como não há coluna CPF_Enfermeiro na tabela INTERNACAO, retorna lista vazia
        return new ArrayList<>();
    }

    public int calcularDiasInternacao(String cpfPaciente) {
        String sql = "SELECT SUM(DATEDIFF(IFNULL(Data_Alta, NOW()), Data_Entrada)) AS TotalDias " +
                     "FROM INTERNACAO WHERE CPF_Paciente = ?";
        int totalDias = 0;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpfPaciente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalDias = rs.getInt("TotalDias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalDias;
    }
}
