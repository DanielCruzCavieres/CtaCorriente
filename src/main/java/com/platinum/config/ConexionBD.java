package com.platinum.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL  =
            "jdbc:mysql://localhost:3306/cuentas_clientes?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "1234"; // tu clave

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de MySQL:");
            e.printStackTrace();
        }
    }

    public static Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("✔ Conexión exitosa a MySQL.");
        return con;
    }
}
