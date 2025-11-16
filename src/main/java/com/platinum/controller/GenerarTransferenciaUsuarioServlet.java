package com.platinum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinum.dao.CtaCorrienteDAO;
import com.platinum.dao.PersonaDAO;
import com.platinum.dao.TransaccionDAO;
import com.platinum.model.*;

@WebServlet("/generarTransferenciaUsuario")
public class GenerarTransferenciaUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CtaCorrienteDAO cuentaDAO = new CtaCorrienteDAO();
    private PersonaDAO personaDAO = new PersonaDAO();
    private TransaccionDAO transaccionDAO = new TransaccionDAO();

    // ===========================
    // GET → mostrar formulario
    // ===========================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogeado");

        if (usuario == null) {
            response.sendRedirect("loginUsuario.jsp");
            return;
        }

        // Cargar cuenta del usuario para mostrar saldo antes de transferir
        CtaCorriente cuenta = cuentaDAO.obtenerPorRutCliente(usuario.getRutCliente());
        request.setAttribute("cuenta", cuenta);

        request.getRequestDispatcher("generarTransferenciaUsuario.jsp").forward(request, response);
    }

    // ==========================================
    // POST → procesar la transferencia
    // ==========================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogeado");

        // Seguridad: usuario debe estar logueado
        if (usuario == null) {
            response.sendRedirect("loginUsuario.jsp");
            return;
        }

        try {
            // Datos origen (usuario logueado)
            int rutCliente = usuario.getRutCliente();

            // Datos destino
            int rutDueno = Integer.parseInt(request.getParameter("rutDueno"));
            String cuentaDestino = request.getParameter("cuentaDestino");
            double monto = Double.parseDouble(request.getParameter("monto"));
            String bancoDestino = request.getParameter("bancoDestino");
            String tipoCuentaDestino = request.getParameter("tipoCuentaDestino");

            // 1. Validar existencia del dueño destino
            if (!personaDAO.existePersona(rutDueno)) {
                request.setAttribute("error", "El RUT destino no existe en el sistema.");
                doGet(request, response);
                return;
            }

            // 2. Obtener cuenta origen
            CtaCorriente cuenta = cuentaDAO.obtenerPorRutCliente(rutCliente);

            if (cuenta == null) {
                request.setAttribute("error", "No tienes una cuenta corriente registrada.");
                doGet(request, response);
                return;
            }

            // 3. Validar saldo
            if (cuenta.getMonto() < monto) {
                request.setAttribute("error", "Saldo insuficiente.");
                doGet(request, response);
                return;
            }

            // 4. Crear objeto Transaccion
            Transaccion t = new Transaccion(
                rutCliente,
                rutDueno,
                cuenta.getIdCuenta(),
                monto,
                cuentaDestino,
                "Cuenta Corriente",   // tipo de cuenta origen
                bancoDestino,
                tipoCuentaDestino
            );

            // 5. Registrar transacción
            boolean okTx = transaccionDAO.registrarTransaccion(t);

            if (!okTx) {
                request.setAttribute("error", "No se pudo registrar la transferencia.");
                doGet(request, response);
                return;
            }

            // 6. Actualizar saldo origen
            double nuevoSaldo = cuenta.getMonto() - monto;
            cuentaDAO.actualizarSaldo(cuenta.getIdCuenta(), nuevoSaldo);

            // 7. Mensaje de éxito
            request.setAttribute("exito", "Transferencia realizada correctamente.");

            // Recargar datos actualizados
            doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error interno: " + e.getMessage());
            doGet(request, response);
        }
    }
}
