package br.com.fiap.dao;

import br.com.fiap.exception.NotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.usuarios.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao(Connection connection) throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public void criaUsuario(Usuario usuario) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(
                "INSERT INTO Usuarios (Nome,CPF,Email,Senha) VALUES (SEQ.Usuarios.nextval,?,?,?,?,GETDATE)"
        );

        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getCpf());
        preparedStatement.setString(3, usuario.getEmail());
        preparedStatement.setString(4, usuario.getSenha());

        preparedStatement.executeUpdate();
    }

    public void atualizaUsuario(Usuario usuario) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE Usuarios SET Nome = ?, CPF = ?, Email = ?, Senha = ? WHERE UsuarioID = ?"
        );

        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getEmail());
        statement.setString(4, usuario.getSenha());
    }

    public void deletaUsuario(String cpf) throws SQLException, NotFoundException {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Usuarios WHERE UsuarioID = ?"
        );

        statement.setString(2, cpf);

        int line = statement.executeUpdate();

        if (line ==0) {
            throw new NotFoundException("Usuário não encontrado para ser deletado.");
        }
    }

    public Usuario buscaUsuario(String cpf) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM Usuarios WHERE UsuarioCPF = ?"
        );

        statement.setString(2, cpf);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Usuario usuario = new Usuario();

            usuario.setNome(resultSet.getString("Nome"));
            usuario.setCpf(resultSet.getString("CPF"));
            usuario.setEmail(resultSet.getString("Email"));
            usuario.setSenha(resultSet.getString("Senha"));
            return usuario;
        }
        return null;
    }
}
