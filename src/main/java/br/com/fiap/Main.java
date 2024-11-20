package br.com.fiap;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.menus.MenuEmpresa;
import br.com.fiap.menus.MenuUsuario;
import br.com.fiap.menus.SobreNos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            System.out.println("Conexão com Database estabilizada.");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Seja bem-vindo! Escolha uma opção:");
                System.out.println("1. Quero saber mais sobre vocês");
                System.out.println("2. Usuário");
                System.out.println("3. Empresa Parceira");
                System.out.println("4. Sair");

                int opcao = scanner.nextInt();
                scanner.next();

                switch (opcao) {
                    case 1:
                        SobreNos.sobreNos();
                        break;

                    case 2:
                        MenuUsuario.menuUsuario(connection);
                        break;

                    case 3:
                        MenuEmpresa.menuEmpresa(connection);

                    case 4:
                        System.out.println("Até mais, esperamos te ver em breve!");
                        connection.close();
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}