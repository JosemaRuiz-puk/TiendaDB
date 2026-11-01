package com.josutpuk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/tiendadb";

    public static Connection getConexion() throws SQLException {

        String usuario = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        return DriverManager.getConnection(URL, usuario, password);

    }
}
