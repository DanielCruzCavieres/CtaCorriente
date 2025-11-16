package com.platinum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.platinum.dao.PersonaDAO;
import com.platinum.model.Persona;

@WebServlet("/registrarCliente")
public class RegistrarClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PersonaDAO personaDAO = new PersonaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int rutCliente = Integer.parseInt(request.getParameter("rutCliente"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String nombreMascota = request.getParameter("nombreMascota");

            double montoApertura = Double.parseDouble(request.getParameter("monto"));

            Persona persona = new Persona(
                rutCliente, nombre, apellido, direccion, correo, telefono, nombreMascota
            );

            boolean clienteCreado = personaDAO.registrarPersona(persona);

            if (!clienteCreado) {
                request.setAttribute("error", "Error al registrar el cliente.");
                request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
                return;
            }

            boolean cuentaCreada = personaDAO.registrarCuentaCorriente(rutCliente, montoApertura);

            if (!cuentaCreada) {
                request.setAttribute("error", "Cliente creado, pero falló la creación de la cuenta.");
                request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
                return;
            }

            request.setAttribute("exito", "Cliente y cuenta corriente registrados correctamente.");
            request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error interno: " + e.getMessage());
            request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
        }
    }
}
