package br.com.fiap.dao;

import br.com.fiap.exception.NotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.empresasParceiras.EmpresaParceira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EmpresaDao {
    private Connection connection;

    public EmpresaDao(Connection connection) throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public void criaEmpresa(EmpresaParceira empresa) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
                "INSERT INTO EmpresasParceiras (Nome, CNPJ, Senha, AreaAtuacao, DataParceria) VALUES (?, ?, ?, ?, ?)"
        );

        preparedStatement.setString(1, empresa.getNome());
        preparedStatement.setString(2, empresa.getCnpj());
        preparedStatement.setString(3, empresa.getSenha());
        preparedStatement.setString(4, empresa.getAreaAtuacao());
        preparedStatement.setDate(5, Date.valueOf(empresa.getDataParceria()));

        preparedStatement.executeUpdate();
    }

    public void atualizaEmpresa(EmpresaParceira empresa) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE EmpresasParceiras SET Nome = ?, CNPJ = ?, Senha = ?, AreaAtuacao = ?, DataParceria = ? WHERE CNPJ = ?"
        );

        statement.setString(1, empresa.getNome());
        statement.setString(2, empresa.getCnpj());
        statement.setString(3, empresa.getSenha());
        statement.setString(4, empresa.getAreaAtuacao());
        statement.setDate(5, Date.valueOf(empresa.getDataParceria()));
        statement.setInt(6, empresa.getId());

        statement.executeUpdate();
    }

    public void deletaEmpresa(String cnpj) throws SQLException, NotFoundException {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM EmpresasParceiras WHERE CNPJ = ?"
        );

        statement.setString(2, cnpj);

        int linhas = statement.executeUpdate();

        if (linhas == 0) {
            throw new NotFoundException("Empresa não encontrada para exclusão.");
        }
    }

    public EmpresaParceira buscaEmpresa(String cnpj) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM EmpresasParceiras WHERE CNPJ = ?"
        );

        statement.setString(2, cnpj);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            EmpresaParceira empresa = new EmpresaParceira();

            empresa.setId(resultSet.getInt("EmpresaID"));
            empresa.setNome(resultSet.getString("Nome"));
            empresa.setCnpj(resultSet.getString("CNPJ"));
            empresa.setSenha(resultSet.getString("Senha"));
            empresa.setAreaAtuacao(resultSet.getString("AreaAtuacao"));
            empresa.setDataParceria(resultSet.getDate("DataParceria").toLocalDate());

            return empresa;
        }

        return null;
    }
}
