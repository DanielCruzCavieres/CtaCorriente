<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generar Transferencia (Ejecutivo)</title>
</head>
<body>

<h1>Generar Transferencia</h1>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<% if (request.getAttribute("exito") != null) { %>
    <p style="color:green;"><%= request.getAttribute("exito") %></p>
<% } %>

<form action="generarTransferencia" method="post">
    RUT Cliente (sin DV):<br>
    <input type="text" name="rutCliente" required><br><br>

    RUT Dueño Destino (sin DV):<br>
    <input type="text" name="rutDueno" required><br><br>

    ID Cuenta Origen:<br>
    <input type="text" name="idCuentaOrigen" required><br><br>

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
<a href="menuEjecutivo.jsp">Volver al menú del Ejecutivo</a>

</body>
</html>
