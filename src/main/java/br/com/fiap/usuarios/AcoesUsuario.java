package br.com.fiap.usuarios;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AcoesUsuario {
    Scanner scanner = new Scanner(System.in);

    public static void cadastrarUsuario(Scanner scanner, Connection connection) {
        try {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            Usuario usuario = new Usuario();

            System.out.println("Digite seu CPF:");
            usuario.setCpf(scanner.next());

            Usuario usuarioEncontrado = usuarioDao.buscaUsuario(usuario.getCpf());

            if (usuarioEncontrado != null) {
                System.out.println("CPF já cadastrado! Faça login.");
                fazerLoginUsuario(scanner, connection);
                return;
            }

            System.out.println("Digite seu nome:");
            usuario.setNome(scanner.next());

            System.out.println("Digite seu e-mail:");
            usuario.setCpf(scanner.next());

            System.out.println("Digite sua senha:");
            usuario.setSenha(scanner.next());

            usuarioDao.criaUsuario(usuario);
            usuarioDao.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void atualizarUsuario(Scanner scanner, Connection connection) {
        try {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            Usuario usuario = new Usuario();

            System.out.println("Digite seu CPF:");
            usuario.setCpf(scanner.next());

            Usuario usuarioEncontrado = usuarioDao.buscaUsuario(usuario.getCpf());

            if (usuarioEncontrado == null) {
                System.out.println("CPF não cadastrado. Faça o cadastro.");
                cadastrarUsuario(scanner, connection);
                return;
            }

            System.out.println("Atualize seu e-mail:");
            usuario.setEmail(scanner.next());

            System.out.println("Atualize a senha:");
            usuario.setSenha(scanner.next());

            usuarioDao.atualizaUsuario(usuario);
            usuarioDao.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
        }
    }

    public static void fazerLoginUsuario(Scanner scanner, Connection connection) {
        try {
            UsuarioDao usuarioDao = new UsuarioDao(connection);

            System.out.println("Digite seu CPF:");
            String cpf = scanner.next();

            System.out.println("Digite sua senha:");
            String senha = scanner.next();

            Usuario usuarioEncontrado = usuarioDao.buscaUsuario(cpf);

            if (usuarioEncontrado == null) {
                System.out.println("CPF não cadastrado! Faça seu cadastro.");
                cadastrarUsuario(scanner, connection);
            }

            if (usuarioEncontrado.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
            } else {
                System.out.println("Senha incorreta, tente novamente.");
                fazerLoginUsuario(scanner, connection);
            }

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void deletarUsuario(Scanner scanner, Connection connection) throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        Usuario usuario = new Usuario();

        System.out.println("Digite o CPF do usuário que deseja excluir:");
        String cpf = scanner.next();

        try {
            usuarioDao.deletaUsuario(cpf);
            System.out.println("Usuário deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
