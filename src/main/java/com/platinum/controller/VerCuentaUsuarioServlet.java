package com.platinum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinum.dao.CtaCorrienteDAO;
import com.platinum.model.CtaCorriente;
import com.platinum.model.Usuario;

@WebServlet("/verCuenta")
public class VerCuentaUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CtaCorrienteDAO cuentaDAO = new CtaCorrienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogeado");
        if (usuario == null) {
            response.sendRedirect("loginUsuario.jsp");
            return;
        }

        try {
            CtaCorriente cuenta = cuentaDAO.obtenerPorRutCliente(usuario.getRutCliente());

            if (cuenta == null) {
                request.setAttribute("error", "No tienes una cuenta corriente registrada.");
            } else {
                request.setAttribute("cuenta", cuenta);
            }

            request.getRequestDispatcher("verCuenta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar la cuenta.");
            request.getRequestDispatcher("verCuenta.jsp").forward(request, response);
        }
    }
}
