<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.platinum.model.Usuario" %>

<%
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
    <title>Generar Transferencia (Usuario)</title>
</head>
<body>

<h2>Generar Transferencia</h2>
<p>RUT Cliente: <strong><%= usuario.getRutCliente() %></strong></p>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<% if (request.getAttribute("exito") != null) { %>
    <p style="color:green;"><%= request.getAttribute("exito") %></p>
<% } %>

<form action="generarTransferenciaUsuario" method="post">

    <!-- El rutCliente lo toma el servlet desde la sesión -->

    RUT Dueño Destino (sin DV):<br>
    <input type="text" name="rutDueno" required><br><br>

    Monto a transferir:<br>
    <input type="number" name="monto" step="0.01" required><br><br>

    Cuenta Destino:<br>
    <input type="text" name="cuentaDestino" required><br><br>

    Tipo de Cuenta Origen:<br>
    <select name="tipoCuenta">
        <option>Cuenta Corriente</option>
        <option>Cuenta Vista</option>
        <option>Ahorro</option>
    </select><br><br>

    Banco Destino:<br>
    <select name="bancoDestino">
        <option>Banco de Chile</option>
        <option>BCI</option>
        <option>Banco Estado</option>
        <option>Scotiabank</option>
    </select><br><br>

    Tipo Cuenta Destino:<br>
    <select name="tipoCuentaDestino">
        <option>Cuenta Corriente</option>
        <option>Cuenta Vista</option>
        <option>Ahorro</option>
    </select><br><br>

    <button type="submit">Generar Transferencia</button>
</form>

<br>
<a href="menuUsuario.jsp">Volver al Menú</a>

</body>
</html>
