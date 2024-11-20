package br.com.fiap.menus;

import br.com.fiap.dao.ProjetoDao;
import br.com.fiap.empresasParceiras.AcoesEmpresaParceira;
import br.com.fiap.projetos.AcoesProjeto;
import br.com.fiap.usuarios.AcoesUsuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuEmpresa {
    public static void menuEmpresa(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("O que gostaria de fazer?");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Atualizar meu cadastro");
            System.out.println("4. Excluir meu cadastro");
            System.out.println("5. Cadastrar um projeto novo");
            System.out.println("6. Editar meus projetos");
            System.out.println("7. Excluir um projeto");
            System.out.println("8. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AcoesEmpresaParceira.fazerLoginEmpresa(scanner, connection);
                    break;

                case 2:
                    AcoesEmpresaParceira.cadastrarEmpresa(scanner, connection);
                    break;

                case 3:
                    AcoesEmpresaParceira.atualizarEmpresa(scanner, connection);
                    break;

                case 4:
                    AcoesEmpresaParceira.deletarEmpresa(scanner, connection);
                    break;

                case 5:
                    AcoesProjeto.cadastrarProjeto(scanner, connection);
                    break;

                case 6:
                    AcoesProjeto.atualizarProjeto(scanner, connection);
                    break;

                case 7:
                    AcoesProjeto.deletarProjeto(scanner, connection);
                    break;

                case 8:
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
