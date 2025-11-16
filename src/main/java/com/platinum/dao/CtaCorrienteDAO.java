package com.platinum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.platinum.config.ConexionBD;
import com.platinum.model.CtaCorriente;

public class CtaCorrienteDAO {

    // Insert con nombre del ejecutivo (VARCHAR en la BD)
    private static final String INSERT_CUENTA =
        "INSERT INTO ctacorriente (rutCliente, monto, ejecutivo) VALUES (?, ?, ?)";

    private static final String GET_CUENTA_BY_ID =
        "SELECT idCuenta, rutCliente, monto, ejecutivo FROM ctacorriente WHERE idCuenta = ?";

    private static final String GET_CUENTA_BY_RUT =
        "SELECT idCuenta, rutCliente, monto, ejecutivo FROM ctacorriente WHERE rutCliente = ?";

    private static final String UPDATE_SALDO =
        "UPDATE ctacorriente SET monto = ? WHERE idCuenta = ?";

    private static final String EXISTS_CUENTA =
        "SELECT idCuenta FROM ctacorriente WHERE idCuenta = ?";


    // ============================================================
    // 1. INSERTAR CUENTA CORRIENTE
    // ============================================================
    public boolean insertarCuenta(CtaCorriente cuenta) throws Exception {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(INSERT_CUENTA)) {

            ps.setInt(1, cuenta.getRutCliente());
            ps.setDouble(2, cuenta.getMonto());
            ps.setString(3, cuenta.getEjecutivo()); // 👈 ahora es String

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar cuenta corriente: " + e.getMessage());
            throw e;
        }
    }


    // ============================================================
    // 2. OBTENER CUENTA POR ID
    // ============================================================
    public CtaCorriente obtenerPorId(int idCuenta) {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(GET_CUENTA_BY_ID)) {

            ps.setInt(1, idCuenta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CtaCorriente(
                    rs.getInt("idCuenta"),
                    rs.getInt("rutCliente"),
                    rs.getDouble("monto"),
                    rs.getString("ejecutivo")   // 👈 String
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error en obtenerPorId(): " + e.getMessage());
        }

        return null;
    }


    // ============================================================
    // 3. OBTENER CUENTA POR RUT CLIENTE (muy usado)
    // ============================================================
    public CtaCorriente obtenerPorRutCliente(int rutCliente) {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(GET_CUENTA_BY_RUT)) {

            ps.setInt(1, rutCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CtaCorriente(
                    rs.getInt("idCuenta"),
                    rs.getInt("rutCliente"),
                    rs.getDouble("monto"),
                    rs.getString("ejecutivo")   // 👈 String
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error en obtenerPorRutCliente(): " + e.getMessage());
        }

        return null;
    }


    // ============================================================
    // 4. ACTUALIZAR SALDO DE LA CUENTA
    // ============================================================
    public boolean actualizarSaldo(int idCuenta, double nuevoMonto) {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SALDO)) {

            ps.setDouble(1, nuevoMonto);
            ps.setInt(2, idCuenta);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar saldo: " + e.getMessage());
            return false;
        }
    }


    // ============================================================
    // 5. VALIDAR EXISTENCIA DE CUENTA
    // ============================================================
    public boolean existeCuenta(int idCuenta) {
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(EXISTS_CUENTA)) {

            ps.setInt(1, idCuenta);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("❌ Error en existeCuenta(): " + e.getMessage());
            return false;
        }
    }
}
