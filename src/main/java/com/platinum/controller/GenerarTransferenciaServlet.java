package com.platinum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinum.dao.CtaCorrienteDAO;
import com.platinum.dao.TransaccionDAO;
import com.platinum.model.CtaCorriente;
import com.platinum.model.Transaccion;

@WebServlet("/generarTransferencia")
public class GenerarTransferenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CtaCorrienteDAO cuentaDAO = new CtaCorrienteDAO();
    private TransaccionDAO transaccionDAO = new TransaccionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int rutCliente = Integer.parseInt(request.getParameter("rutCliente"));
            int rutDueno = Integer.parseInt(request.getParameter("rutDueno"));
            int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
            double monto = Double.parseDouble(request.getParameter("monto"));
            String cuentaDestino = request.getParameter("cuentaDestino");

            String tipoCuenta = request.getParameter("tipoCuenta");
            String bancoDestino = request.getParameter("bancoDestino");
            String tipoCuentaDestino = request.getParameter("tipoCuentaDestino");

            CtaCorriente cuentaOrigen = cuentaDAO.obtenerPorId(idCuentaOrigen);

            if (cuentaOrigen == null || cuentaOrigen.getRutCliente() != rutCliente) {
                request.setAttribute("error", "La cuenta origen no existe o no pertenece al cliente indicado.");
                request.getRequestDispatcher("generarTransferencia.jsp").forward(request, response);
                return;
            }

            if (cuentaOrigen.getMonto() < monto) {
                request.setAttribute("error", "Saldo insuficiente en la cuenta origen.");
                request.getRequestDispatcher("generarTransferencia.jsp").forward(request, response);
                return;
            }

            double nuevoSaldoOrigen = cuentaOrigen.getMonto() - monto;
            boolean okSaldo = cuentaDAO.actualizarSaldo(idCuentaOrigen, nuevoSaldoOrigen);

            if (!okSaldo) {
                request.setAttribute("error", "No se pudo actualizar el saldo de la cuenta origen.");
                request.getRequestDispatcher("generarTransferencia.jsp").forward(request, response);
                return;
            }

            Transaccion t = new Transaccion(
                rutCliente,
                rutDueno,
                idCuentaOrigen,
                monto,
                cuentaDestino,
                tipoCuenta,
                bancoDestino,
                tipoCuentaDestino
            );

            boolean okTx = transaccionDAO.registrarTransaccion(t);

            if (!okTx) {
                request.setAttribute("error", "No se pudo registrar la transacción.");
            } else {
                request.setAttribute("exito", "Transferencia registrada correctamente.");
            }

            request.getRequestDispatcher("generarTransferencia.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la transferencia: " + e.getMessage());
            request.getRequestDispatcher("generarTransferencia.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("generarTransferencia.jsp").forward(req, resp);
    }
}
