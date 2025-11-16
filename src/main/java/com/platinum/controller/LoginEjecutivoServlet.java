package com.platinum.controller;

import java.io.IOException;

import com.platinum.dao.EjecutivoDAO;
import com.platinum.model.Ejecutivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginEjecutivo")
public class LoginEjecutivoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 🔹 Variable pública para registrar quién está logueado (usada en PersonaDAO)
    public static String nombreEjecutivoActivo = null;

    private EjecutivoDAO ejecutivoDAO = new EjecutivoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rutStr = request.getParameter("rutEjecutivo");
        String nombreInput = request.getParameter("nombre");

        try {
            int rut = Integer.parseInt(rutStr.trim());

            // Buscar ejecutivo por rut
            Ejecutivo ejecutivo = ejecutivoDAO.obtenerEjecutivo(rut);

            if (ejecutivo != null) {

                // Validación opcional del nombre
                if (nombreInput != null && 
                    !nombreInput.trim().equalsIgnoreCase(ejecutivo.getNombre().trim())) {

                    request.setAttribute("error", "RUT o nombre incorrectos");
                    request.getRequestDispatcher("loginEjecutivo.jsp")
                           .forward(request, response);
                    return;
                }

                // Guardamos en sesión
                request.getSession().setAttribute("ejecutivo", ejecutivo);

                // Guardamos el nombre en la variable estática (para registrar cuentas)
                nombreEjecutivoActivo = ejecutivo.getNombre();

                System.out.println("✔ Ejecutivo logueado: " + nombreEjecutivoActivo);

                // Redirigir al menú
                request.getRequestDispatcher("menuEjecutivo.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "RUT o nombre incorrectos");
                request.getRequestDispatcher("loginEjecutivo.jsp")
                       .forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "RUT inválido");
            request.getRequestDispatcher("loginEjecutivo.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error al validar login de ejecutivo: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error interno en el Login");
            request.getRequestDispatcher("loginEjecutivo.jsp").forward(request, response);
        }
    }
}
