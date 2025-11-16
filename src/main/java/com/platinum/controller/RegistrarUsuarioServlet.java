package com.platinum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinum.dao.UsuarioDAO;
import com.platinum.model.Usuario;

@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombreUsuario = request.getParameter("nombreUsuario");
            String password = request.getParameter("password");
            int rutCliente = Integer.parseInt(request.getParameter("rutCliente"));

            Usuario u = new Usuario(nombreUsuario, password, rutCliente);

            if (usuarioDAO.registrarUsuario(u)) {
                request.setAttribute("exito", "Usuario registrado correctamente");
            } else {
                request.setAttribute("error", "Error registrando usuario");
            }

            request.getRequestDispatcher("registrarUsuario.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Datos inválidos o error interno");
            request.getRequestDispatcher("registrarUsuario.jsp").forward(request, response);
        }
    }
}
