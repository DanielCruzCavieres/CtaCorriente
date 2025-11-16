package com.platinum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.platinum.config.ConexionBD;
import com.platinum.model.Transaccion;

public class TransaccionDAO {

    private static final String INSERT =
        "INSERT INTO transaccion "
        + "(rutCliente, rutDueno, idCuenta, montoTransferencia, cuentaTransferencia, tipoCuenta, bancoDestino, tipoCuentaDestino) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean registrarTransaccion(Transaccion t) {

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setInt(1, t.getRutCliente());
            ps.setInt(2, t.getRutDueno());
            ps.setInt(3, t.getIdCuenta());
            ps.setDouble(4, t.getMontoTransferencia());
            ps.setString(5, t.getCuentaTransferencia());
            ps.setString(6, t.getTipoCuenta());
            ps.setString(7, t.getBancoDestino());
            ps.setString(8, t.getTipoCuentaDestino());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error en registrarTransaccion: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
