package br.com.fiap.dao;

import br.com.fiap.empresasParceiras.EmpresaParceira;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.exception.NotFoundException;
import br.com.fiap.projetos.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDao {
    private Connection connection;

    public ProjetoDao(Connection connection) throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public void criaProjeto(Projeto projeto) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
                "INSERT INTO ProjetosEnergeticos (NomeProjeto, TipoEnergia, Custo, DataInicio, DataConclusao, EmpresaID) " +
                        "VALUES (?, ?, ?, ?, ?, ?)"
        );

        preparedStatement.setString(1, projeto.getNomeProjeto());
        preparedStatement.setString(2, projeto.getTipoEnergia());
        preparedStatement.setBigDecimal(3, projeto.getCusto());
        preparedStatement.setDate(4, Date.valueOf(projeto.getDataInicio()));
        preparedStatement.setDate(5, Date.valueOf(projeto.getDataConclusao()));
        preparedStatement.setInt(6, projeto.getEmpresaParceira().getId());

        preparedStatement.executeUpdate();
    }

    public void atualizaProjeto(Projeto projeto) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE ProjetosEnergeticos SET NomeProjeto = ?, TipoEnergia = ?, Custo = ?, DataInicio = ?, " +
                        "DataConclusao = ?, EmpresaID = ? WHERE ProjetoID = ?"
        );

        statement.setString(1, projeto.getNomeProjeto());
        statement.setString(2, projeto.getTipoEnergia());
        statement.setBigDecimal(3, projeto.getCusto());
        statement.setDate(4, Date.valueOf(projeto.getDataInicio()));
        statement.setDate(5, Date.valueOf(projeto.getDataConclusao()));
        statement.setInt(6, projeto.getEmpresaParceira().getId());
        statement.setInt(7, projeto.getId());

        statement.executeUpdate();
    }

    public void deletaProjeto(String nome) throws SQLException, NotFoundException {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM ProjetosEnergeticos WHERE ProjetoID = ?"
        );

        statement.setString(1, nome);

        int linhas = statement.executeUpdate();

        if (linhas == 0) {
            throw new NotFoundException("Projeto não encontrado para exclusão.");
        }
    }

    public Projeto buscaProjeto(String nome) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT p.*, e.Nome AS NomeEmpresa, e.AreaAtuacao, e.DataParceria " +
                        "FROM ProjetosEnergeticos p " +
                        "JOIN EmpresasParceiras e ON p.EmpresaID = e.EmpresaID " +
                        "WHERE p.ProjetoID = ?"
        );

        statement.setString(1, nome);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Projeto projeto = new Projeto();

            projeto.setId(resultSet.getInt("ProjetoID"));
            projeto.setNomeProjeto(resultSet.getString("NomeProjeto"));
            projeto.setTipoEnergia(resultSet.getString("TipoEnergia"));
            projeto.setCusto(resultSet.getBigDecimal("Custo"));
            projeto.setDataInicio(resultSet.getDate("DataInicio").toLocalDate());
            projeto.setDataConclusao(resultSet.getDate("DataConclusao").toLocalDate());

            EmpresaParceira empresa = new EmpresaParceira();
            empresa.setId(resultSet.getInt("EmpresaID"));
            empresa.setNome(resultSet.getString("NomeEmpresa"));
            empresa.setCnpj(resultSet.getString("CNPJ"));
            empresa.setSenha(resultSet.getString("Senha"));
            empresa.setAreaAtuacao(resultSet.getString("AreaAtuacao"));
            empresa.setDataParceria(resultSet.getDate("DataParceria").toLocalDate());

            projeto.setEmpresaParceira(empresa);

            return projeto;
        }

        return null;
    }

    public List<Projeto> buscaTodosProjetos() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT p.*, e.Nome AS NomeEmpresa, e.CNPJ, e.Senha, e.AreaAtuacao, e.DataParceria " +
                        "FROM ProjetosEnergeticos p " +
                        "JOIN EmpresasParceiras e ON p.EmpresaID = e.EmpresaID"
        );

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Projeto projeto = new Projeto();

            projeto.setId(resultSet.getInt("ProjetoID"));
            projeto.setNomeProjeto(resultSet.getString("NomeProjeto"));
            projeto.setTipoEnergia(resultSet.getString("TipoEnergia"));
            projeto.setCusto(resultSet.getBigDecimal("Custo"));
            projeto.setDataInicio(resultSet.getDate("DataInicio").toLocalDate());
            projeto.setDataConclusao(resultSet.getDate("DataConclusao").toLocalDate());

            EmpresaParceira empresa = new EmpresaParceira();
            empresa.setId(resultSet.getInt("EmpresaID"));
            empresa.setNome(resultSet.getString("NomeEmpresa"));
            empresa.setCnpj(resultSet.getString("CNPJ"));
            empresa.setSenha(resultSet.getString("Senha"));
            empresa.setAreaAtuacao(resultSet.getString("AreaAtuacao"));
            empresa.setDataParceria(resultSet.getDate("DataParceria").toLocalDate());

            projeto.setEmpresaParceira(empresa);

            projetos.add(projeto);
        }

        return projetos;
    }
}
