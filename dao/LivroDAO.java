package dao;

import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    public LivroDAO(Connection conexao) {
        
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor, anoPublicacao, disponivel) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.executeUpdate();
        }
    }

    public Livro buscarLivroPorId(int i) throws SQLException {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, i);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Livro livro = new Livro(rs.getString("titulo"));
                    livro.setId(rs.getInt("id"));
                    livro.setAutor(rs.getString("autor"));
                    livro.setAnoPublicacao(rs.getInt("anoPublicacao"));
                    livro.setDisponivel(rs.getBoolean("disponivel"));
                    return livro;
                }
            }
        }
        return null;
    }

    public void removerLivro(int id) throws SQLException {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Livro> listarTodos() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(rs.getString("titulo"));
                livro.setId(rs.getInt("id"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("anoPublicacao"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                livros.add(livro);
            }
        }
        return livros;
    }

    public void atualizarLivro(Livro livro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarLivro'");
    }

    public void excluirLivro(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluirLivro'");
    }

    public Livro consultarLivro(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarLivro'");
    }
}
