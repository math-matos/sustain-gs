package br.com.fiap.empresasParceiras;

import br.com.fiap.dao.EmpresaDao;
import br.com.fiap.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AcoesEmpresaParceira {
    Scanner scanner = new Scanner(System.in);

    public static void cadastrarEmpresa(Scanner scanner, Connection connection) {
        try {
            EmpresaDao empresaDao = new EmpresaDao(connection);
            EmpresaParceira empresaParceira = new EmpresaParceira();

            System.out.println("Digite seu CNPJ:");
            empresaParceira.setCnpj(scanner.next());

            EmpresaParceira empresaEncontrada = empresaDao.buscaEmpresa(empresaParceira.getCnpj());

            if (empresaEncontrada != null) {
                System.out.println("CNPJ já cadastrado! Faça login.");
                fazerLoginEmpresa(scanner, connection);
                return;
            }

            System.out.println("Digite o nome da empresa:");
            empresaParceira.setNome(scanner.next());

            System.out.println("Digite a área de atuação:");
            empresaParceira.setAreaAtuacao(scanner.next());

            empresaDao.criaEmpresa(empresaParceira);
            empresaDao.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void atualizarEmpresa(Scanner scanner, Connection connection) {
        try {
            EmpresaDao empresaDao = new EmpresaDao(connection);
            EmpresaParceira empresaParceira = new EmpresaParceira();

            System.out.println("Digite seu CNPJ:");
            empresaParceira.setCnpj(scanner.next());

            EmpresaParceira empresaEncontrada = empresaDao.buscaEmpresa(empresaParceira.getCnpj());

            if (empresaEncontrada == null) {
                System.out.println("CNPJ não cadastrado. Faça o cadastro.");
                cadastrarEmpresa(scanner, connection);
                return;
            }

            System.out.println("Atualize a área de atuação:");
            empresaParceira.setAreaAtuacao(scanner.next());

            System.out.println("Atualize sua senha:");
            empresaParceira.setSenha(scanner.next());

            empresaDao.atualizaEmpresa(empresaParceira);
            empresaDao.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void fazerLoginEmpresa(Scanner scanner, Connection connection) {
        try {
            EmpresaDao empresaDao = new EmpresaDao(connection);

            System.out.println("Digite seu CNPJ:");
            String cnpj = scanner.next();

            System.out.println("Digite sua senha:");
            String senha = scanner.next();

            EmpresaParceira empresaEncontrada = empresaDao.buscaEmpresa(cnpj);

            if (empresaEncontrada == null) {
                System.out.println("CNPJ não cadastrado! Faça seu cadastro.");
                cadastrarEmpresa(scanner,connection);
            }

            if (empresaEncontrada.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
            } else {
                System.out.println("Senha incorreta, tente novamente.");
                fazerLoginEmpresa(scanner,connection);
            }

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void deletarEmpresa(Scanner scanner, Connection connection) throws SQLException {
        EmpresaDao empresaDao = new EmpresaDao(connection);
        EmpresaParceira empresaParceira = new EmpresaParceira();

        System.out.println("Digite o CNPJ da empresa que deseja excluir:");
        String cnpj = scanner.next();

        try {
            empresaDao.deletaEmpresa(cnpj);
            System.out.println("Empresa deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar empresa: " + e.getMessage());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
