package dao;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emprestimo.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET usuario_id = ?, livro_id = ?, data_emprestimo = ?, data_devolucao = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(5, emprestimo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerEmprestimo(int id) {
        String sql = "DELETE FROM emprestimos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        String sql = "SELECT e.*, u.nome AS usuario_nome, u.email AS usuario_email, l.titulo AS livro_titulo, l.autor AS livro_autor, l.ano_publicacao AS livro_ano_publicacao, l.disponivel AS livro_disponivel " +
                     "FROM emprestimos e " +
                     "JOIN usuarios u ON e.usuario_id = u.id " +
                     "JOIN livros l ON e.livro_id = l.id " +
                     "WHERE e.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(rs.getString("usuario_nome"));
                    usuario.setId(rs.getInt("usuario_id"));

                    Livro livro = new Livro(rs.getString("livro_titulo"));
                    livro.setId(rs.getInt("livro_id"));
                    livro.setDisponivel(rs.getBoolean("livro_disponivel"));

                    LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                    LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();

                    Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
                    emprestimo.setId(rs.getInt("id"));

                    return emprestimo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        String sql = "SELECT e.*, u.nome AS usuario_nome, u.email AS usuario_email, l.titulo AS livro_titulo, l.autor AS livro_autor, l.ano_publicacao AS livro_ano_publicacao, l.disponivel AS livro_disponivel " +
                     "FROM emprestimos e " +
                     "JOIN usuarios u ON e.usuario_id = u.id " +
                     "JOIN livros l ON e.livro_id = l.id";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("usuario_nome"));
                usuario.setId(rs.getInt("usuario_id"));

                Livro livro = new Livro(rs.getString("livro_titulo"));
                livro.setId(rs.getInt("livro_id"));
                livro.setDisponivel(rs.getBoolean("livro_disponivel"));

                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();

                Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
                emprestimo.setId(rs.getInt("id"));

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
}
