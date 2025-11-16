package com.platinum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.platinum.config.ConexionBD;
import com.platinum.model.Usuario;

public class UsuarioDAO {

    private static final String INSERT =
        "INSERT INTO usuario (nombreUsuario, password, rutCliente) VALUES (?, ?, ?)";

    private static final String LOGIN =
        "SELECT id, nombreUsuario, password, rutCliente " +
        "FROM usuario WHERE nombreUsuario = ? AND password = ?";

    // Registrar usuario
    public boolean registrarUsuario(Usuario u) {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getRutCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error registrando usuario: " + e.getMessage());
            return false;
        }
    }

    // Login usuario
    public Usuario login(String nombreUsuario, String password) {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(LOGIN)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombreUsuario"),
                    rs.getString("password"),
                    rs.getInt("rutCliente")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error en login(): " + e.getMessage());
        }

        return null;
    }
}
