package Ui;

import model.Emprestimo;
import model.Livro;
import model.Usuario;
import Service.BibliotecaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BibliotecaUi {
    private static final BibliotecaService Service = null;
    private BibliotecaService service;
    private Scanner scanner;

    public BibliotecaUi() {
        this.service = new BibliotecaService();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Gerenciamento de Biblioteca");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Atualizar Livro");
            System.out.println("3. Remover Livro");
            System.out.println("4. Consultar Livro");
            System.out.println("5. Listar Todos Livros");
            System.out.println("6. Adicionar Usuario");
            System.out.println("7. Atualizar Usuario");
            System.out.println("8. Remover Usuario");
            System.out.println("9. Consultar Usuario");1
            System.out.println("10. Listar Todos Usuarios");
            System.out.println("11. Realizar Emprestimo");
            System.out.println("12. Registrar Devolucao");
            System.out.println("13. Consultar Emprestimo");
            System.out.println("14. Listar Todos Emprestimos");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    atualizarLivro();
                    break;
                case 3:
                    removerLivro();
                    break;
                case 4:
                    consultarLivro();
                    break;
                case 5:
                    listarTodosLivros();
                    break;
                case 6:
                    adicionarUsuario();
                    break;
                case 7:
                    atualizarUsuario();
                    break;
                case 8:
                    removerUsuario();
                    break;
                case 9:
                    consultarUsuario();
                    break;
                case 10:
                    listarTodosUsuarios();
                    break;
                case 11:
                    realizarEmprestimo();
                    break;
                case 12:
                    registrarDevolucao();
                    break;
                case 13:
                    consultarEmprestimo();
                    break;
                case 14:
                    listarTodosEmprestimos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void adicionarLivro() {
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de Publicacao: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); 

        Livro livro2 = new Livro(titulo, autor, ano);
        Livro livro = livro2;
        service.adicionarLivro(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private void atualizarLivro() {
        System.out.print("ID do Livro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Livro livro = Service.buscarLivroPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        System.out.print("Novo Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Novo Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Novo Ano de Publicacao: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPublicacao(ano);

        service.atualizarLivro(livro);
        System.out.println("Livro atualizado com sucesso!");
    }

    private void removerLivro() {
        System.out.print("ID do Livro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        service.removerLivro(id);
        System.out.println("Livro removido com sucesso!");
    }

    private void consultarLivro() {
        System.out.print("ID do Livro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Livro livro = service.buscarLivroPorId(id);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listarTodosLivros() {
        final List<Livro> livros = service.listarTodosLivros();
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private void adicionarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email);
        service.adicionarUsuario(usuario);
        System.out.println("Usuário adicionado com sucesso!");
    }

    private void atualizarUsuario() {
        System.out.print("ID do Usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        final Usuario usuario = service.buscarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();

        usuario.setNome(nome);
        usuario.setEmail(email);

        service.atualizarUsuario(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private void removerUsuario() {
        System.out.print("ID do Usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        service.removerUsuario(id);
        System.out.println("Usuário removido com sucesso!");
    }

    private void consultarUsuario() {
        System.out.print("ID do Usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Usuario usuario = service.buscarUsuarioPorId(id);
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private void listarTodosUsuarios() {
        final List<Usuario> usuarios = service.listarTodosUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    private void realizarEmprestimo() {
        System.out.print("ID do Usuário: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine(); 

        Usuario usuario = service.buscarUsuarioPorId(usuarioId);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("ID do Livro: ");
        int livroId = scanner.nextInt();
        scanner.nextLine(); 

        Livro livro = service.buscarLivroPorId(livroId);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        if (!livro.isDisponivel()) {
            System.out.println("Livro não está disponível para empréstimo!");
            return;
        }

        System.out.print("Data de Empréstimo (yyyy-mm-dd): ");
        LocalDate dataEmprestimo = LocalDate.parse(scanner.nextLine());

        System.out.print("Data de Devolução (yyyy-mm-dd): ");
        LocalDate dataDevolucao = LocalDate.parse(scanner.nextLine());

        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
        service.adicionarEmprestimo(emprestimo);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    private void registrarDevolucao() {
        System.out.print("ID do Empréstimo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Emprestimo emprestimo = service.buscarEmprestimoPorId(id);
        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado!");
            return;
        }

        service.removerEmprestimo(id);
        System.out.println("Devolução registrada com sucesso!");
    }

    private void consultarEmprestimo() {
        System.out.print("ID do Empréstimo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Emprestimo emprestimo = service.buscarEmprestimoPorId(id);
        if (emprestimo != null) {
            System.out.println(emprestimo);
        } else {
            System.out.println("Empréstimo não encontrado!");
        }
    }

    private void listarTodosEmprestimos() {
        List<Emprestimo> emprestimos = service.listarTodosEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    public static void main(String[] args) {
        BibliotecaUi ui = new BibliotecaUi();
        ui.iniciar();
    }
}
