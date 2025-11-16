package com.platinum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinum.dao.UsuarioDAO;
import com.platinum.model.Usuario;

@WebServlet("/loginUsuario")
public class LoginUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");

        Usuario user = usuarioDAO.login(nombreUsuario, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogeado", user);

            response.sendRedirect("menuUsuario.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("loginUsuario.jsp").forward(request, response);
        }
    }
}
