<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.platinum.model.Ejecutivo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Ejecutivo</title>
</head>
<body>

<%
    // Validar sesión
    Ejecutivo ej = (Ejecutivo) session.getAttribute("ejecutivo");
    if (ej == null) {
        response.sendRedirect("loginEjecutivo.jsp");
        return;
    }
%>

<h2>Bienvenido, <%= ej.getNombre() %> (Depto: <%= ej.getDepartamento() %>)</h2>

<ul>
    <li><a href="registrarCliente.jsp">Registrar Cliente y Cuenta Corriente</a></li>
    <li><a href="generarTransferencia">Generar Transferencia</a></li>
    <li><a href="registrarUsuario.jsp">Registrar Usuario del Sistema</a></li>
</ul>

</body>
</html>
