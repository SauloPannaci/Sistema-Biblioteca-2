package Service;

import java.sql.SQLException;
import java.util.List;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

public class BibliotecaService {
    private LivroDAO livroDAO;
    private UsuarioDAO usuarioDAO;
    private EmprestimoDAO emprestimoDAO;

    public BibliotecaService() {
        this.livroDAO = new LivroDAO(null);
        this.usuarioDAO = new UsuarioDAO();
        this.emprestimoDAO = new EmprestimoDAO();
    }

    
    public void adicionarLivro(Livro livro) {
        try {
            livroDAO.adicionarLivro(livro);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    public void atualizarLivro(Livro livro) {
        livroDAO.atualizarLivro(livro);
    }

    public void removerLivro(int id) {
        try {
            livroDAO.removerLivro(id);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    public Livro buscarLivroPorId(int id) {
        try {
               livroDAO.buscarLivroPorId(id);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    public List<Livro> listarTodosLivros() {
        try {
               livroDAO.listarTodos();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    
    public void adicionarUsuario(Usuario usuario) {
        try {
            usuarioDAO.adicionarUsuario(usuario);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void removerUsuario(int id) {
        try {
            usuarioDAO.removerUsuario(id);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        try {
             usuarioDAO.buscarUsuarioPorId(id);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarTodosUsuarios() {
        try {
             usuarioDAO.listarTodos();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return null;
    }

    
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        Livro livro = emprestimo.getLivro();
        if (livro.isDisponivel()) {
            emprestimoDAO.adicionarEmprestimo(emprestimo);
            livro.setDisponivel(false);
            livroDAO.atualizarLivro(livro);
        } else {
            throw new IllegalStateException("Livro não está disponível para empréstimo");
        }
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        emprestimoDAO.atualizarEmprestimo(emprestimo);
    }

    public void removerEmprestimo(int id) {
        Emprestimo emprestimo = emprestimoDAO.buscarEmprestimoPorId(id);
        if (emprestimo != null) {
            Livro livro = emprestimo.getLivro();
            livro.setDisponivel(true);
            livroDAO.atualizarLivro(livro);
            emprestimoDAO.removerEmprestimo(id);
        }
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        return emprestimoDAO.buscarEmprestimoPorId(id);
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        return emprestimoDAO.listarTodosEmprestimos();
    }
}
