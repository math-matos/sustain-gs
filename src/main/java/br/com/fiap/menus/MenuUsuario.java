package br.com.fiap.menus;

import br.com.fiap.dao.ProjetoDao;
import br.com.fiap.usuarios.AcoesUsuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuUsuario {
    public static void menuUsuario(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("O que gostaria de fazer?");
            System.out.println("1. Login");
            System.out.println("2. Visualizar projetos de energia sustentável");
            System.out.println("3. Cadastro");
            System.out.println("4. Atualizar meu cadastro");
            System.out.println("5. Excluir meu cadastro");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AcoesUsuario.fazerLoginUsuario(scanner, connection);
                    break;

                case 2:
                    ProjetoDao projetoDao = new ProjetoDao(connection);
                    projetoDao.buscaTodosProjetos();
                    break;

                case 3:
                    AcoesUsuario.cadastrarUsuario(scanner,connection);
                    break;

                case 4:
                    AcoesUsuario.atualizarUsuario(scanner,connection);
                    break;

                case 5:
                    AcoesUsuario.deletarUsuario(scanner,connection);
                    break;

                case 6:
                    System.out.println("Até mais!");

                    try {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                    }
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
