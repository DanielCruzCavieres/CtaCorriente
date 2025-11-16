<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.platinum.model.Usuario" %>

<%
    // Validar sesión de usuario
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
    if (usuario == null) {
        response.sendRedirect("loginUsuario.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Usuario</title>
</head>
<body>

<h2>Bienvenido, <%= usuario.getNombreUsuario() %></h2>

<ul>
    <li><a href="verCuenta">Ver Cuenta Corriente</a></li>
    <li><a href="generarTransferenciaUsuario">Generar Transferencia</a></li>
    <li><a href="logoutUsuario">Cerrar Sesión</a></li>
</ul>

</body>
</html>
