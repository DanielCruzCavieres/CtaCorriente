package com.platinum.config;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConexionBDTest {

    private static Connection conexion;

    @BeforeAll
    public static void iniciarConexion() {
        try {
            conexion = ConexionBD.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("❌ No se pudo establecer la conexión a la base de datos: " + e.getMessage());
        }
    }

    @AfterAll
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("✔ Conexión cerrada correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @DisplayName("La conexión no debe ser nula")
    public void testConexionNoNula() {
        assertNotNull(conexion, "❌ La conexión es nula. No se logró conectar correctamente.");
    }

    @Test
    @DisplayName("La conexión debe estar abierta")
    public void testConexionAbierta() throws SQLException {
        assertFalse(conexion.isClosed(), "❌ La conexión está cerrada.");
    }

}
