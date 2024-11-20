package br.com.fiap.projetos;

import br.com.fiap.dao.EmpresaDao;
import br.com.fiap.dao.ProjetoDao;
import br.com.fiap.empresasParceiras.EmpresaParceira;
import br.com.fiap.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AcoesProjeto {
    Scanner scanner = new Scanner(System.in);

    public static void cadastrarProjeto(Scanner scanner, Connection connection) {
        try {
            ProjetoDao projetoDao = new ProjetoDao(connection);
            Projeto projeto = new Projeto();

            System.out.println("Digite o nome do seu Projeto:");
            projeto.setNomeProjeto(scanner.next());

            System.out.println("Que tipo de energia ele gera ou ajuda? (Ex. solar, eólica...");
            projeto.setTipoEnergia(scanner.next());

            System.out.println("Qual o custo total do projeto?");
            projeto.setCusto(scanner.nextBigDecimal());

            System.out.println("Quando o projeto irá iniciar?");
            String dataInicioStr = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            projeto.setDataInicio(LocalDate.parse(dataInicioStr, formatter));

            System.out.println("Qual a previsão de conclusão?");
            String dataConclusaoStr = scanner.next();
            projeto.setDataConclusao(LocalDate.parse(dataConclusaoStr, formatter));

            EmpresaDao empresaDao = new EmpresaDao(connection);
            EmpresaParceira empresaParceira = new EmpresaParceira();

            System.out.println("Digite o CNPJ da sua empresa:");
            empresaParceira.setCnpj(scanner.next());

            EmpresaParceira empresaEncontrada = empresaDao.buscaEmpresa(empresaParceira.getCnpj());

            if (empresaEncontrada != null) {
                projeto.setEmpresaParceira(empresaEncontrada);

                System.out.println("Empresa encontrada: " + empresaEncontrada.getNome());
                System.out.println("Cadastro do projeto realizado com sucesso!");

                projetoDao.criaProjeto(projeto);
            } else {
                System.out.println("Empresa não encontrada com esse CNPJ.");
            }

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void atualizarProjeto (Scanner scanner, Connection connection) {
        try {
            ProjetoDao projetoDao = new ProjetoDao(connection);
            Projeto projeto = new Projeto();

            System.out.println("Digite o nome do projeto: ");
            projeto.setNomeProjeto(scanner.next());

            Projeto projetoEncontrado = projetoDao.buscaProjeto(projeto.getNomeProjeto());

            if (projetoEncontrado == null) {
                System.out.println("Projeto não encontrado. Veja nossa lista de projetos atuais:");
                projetoDao.buscaTodosProjetos();
            }

            System.out.println("Atualize o tipo de energia:");
            projeto.setTipoEnergia(scanner.next());

            System.out.println("Atualize o custo do projeto:");
            projeto.setCusto(scanner.nextBigDecimal());

            System.out.println("Atualize a data de conclusão:");
            String dataConclusaoStr = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            projeto.setDataConclusao(LocalDate.parse(dataConclusaoStr, formatter));

            projetoDao.atualizaProjeto(projeto);
            projetoDao.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void deletarProjeto(Scanner scanner, Connection connection) throws SQLException {
        ProjetoDao projetoDao = new ProjetoDao(connection);
        Projeto projeto = new Projeto();

        System.out.println("Digite o nome do projeto que deseja excluir:");
        String nomeProjeto = scanner.next();

        try {
            projetoDao.deletaProjeto(nomeProjeto);
            System.out.println("Projeto deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar projeto: " + e.getMessage());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
