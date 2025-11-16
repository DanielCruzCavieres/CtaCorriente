package com.platinum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.platinum.config.ConexionBD;
import com.platinum.model.CtaCorriente;
import com.platinum.model.Persona;

public class PersonaDAO {

    // Registrar persona
    public boolean registrarPersona(Persona persona) {
        String sql = "INSERT INTO persona "
                   + "(rut, nombre, apellido, direccion, correo, telefono, nombreMascota) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, persona.getRut());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setString(4, persona.getDireccion());
            ps.setString(5, persona.getCorreo());
            ps.setString(6, persona.getTelefono());
            ps.setString(7, persona.getNombreMascota());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error registrando persona: " + e.getMessage());
            return false;
        }
    }

    // Usado por GenerarTransferenciaUsuarioServlet
    public boolean existePersona(int rut) {
        String sql = "SELECT 1 FROM persona WHERE rut = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rut);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("❌ Error en existePersona(): " + e.getMessage());
            return false;
        }
    }

    // Usado por RegistrarClienteServlet
    public boolean registrarCuentaCorriente(int rutCliente, double montoApertura) {

        CtaCorrienteDAO ctaDao = new CtaCorrienteDAO();

        // Por simplicidad, dejo un ejecutivo fijo (ej: 1).
        // Si quieres, puedes pasar el rut del ejecutivo como parámetro.
        int ejecutivoId = 1;

        CtaCorriente cuenta = new CtaCorriente(rutCliente, montoApertura, "Ejecutivo General");
        try {
            return ctaDao.insertarCuenta(cuenta);
        } catch (Exception e) {
            System.out.println("❌ Error registrando cuenta corriente: " + e.getMessage());
            return false;
        }
    }
}
