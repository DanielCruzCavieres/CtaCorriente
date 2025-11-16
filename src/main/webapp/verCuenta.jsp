<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.platinum.model.CtaCorriente" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Cuenta Corriente</title>
</head>
<body>

<h2>Información de tu Cuenta Corriente</h2>

<%
    String error = (String) request.getAttribute("error");
    CtaCorriente cuenta = (CtaCorriente) request.getAttribute("cuenta");

    if (error != null) {
%>
        <p style="color:red;"><%= error %></p>
<%
    } else if (cuenta != null) {
%>
        <p><strong>ID Cuenta:</strong> <%= cuenta.getIdCuenta() %></p>
        <p><strong>RUT Cliente:</strong> <%= cuenta.getRutCliente() %></p>
        <p><strong>Saldo Actual:</strong> $<%= cuenta.getMonto() %></p>
        <p><strong>Ejecutivo Asignado:</strong> <%= cuenta.getEjecutivo() %></p>
<%
    } else {
%>
        <p>No hay información disponible.</p>
<%
    }
%>

<br>
<a href="menuUsuario.jsp">Volver al Menú</a>

</body>
</html>
