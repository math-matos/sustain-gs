package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        //TODO: Colocar usuário, senha e url do DB
        String user = "";
        String password = "";

        Connection connection = DriverManager.getConnection("urlAqui", user, password);

        return connection;
    }
}
