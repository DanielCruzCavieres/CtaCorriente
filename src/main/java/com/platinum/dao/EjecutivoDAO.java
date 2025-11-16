package com.platinum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.platinum.config.ConexionBD;
import com.platinum.model.Ejecutivo;

public class EjecutivoDAO {

    // Buscar solo por RUT (más simple y seguro por ahora)
    public Ejecutivo obtenerEjecutivo(int rut) {
        Ejecutivo eje = null;

        String sql = "SELECT rutEjecutivo, nombre, departamento "
                   + "FROM ejecutivo "
                   + "WHERE rutEjecutivo = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, rut);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    eje = new Ejecutivo();
                    eje.setRutEjecutivo(rs.getInt("rutEjecutivo"));
                    eje.setNombre(rs.getString("nombre"));
                    eje.setDepartamento(rs.getString("departamento"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ejecutivo: " + e.getMessage());
            e.printStackTrace();
        }

        return eje;
    }
}
