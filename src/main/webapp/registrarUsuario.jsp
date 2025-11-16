<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Usuario</title>
</head>
<body>

<h2>Registrar Usuario del Sistema</h2>

<form action="registrarUsuario" method="post">

    Nombre de Usuario: <br>
    <input type="text" name="nombreUsuario" required><br><br>

    Password: <br>
    <input type="password" name="password" required><br><br>

    RUT Cliente (sin DV): <br>
    <input type="number" name="rutCliente" required><br><br>

    <button type="submit">Registrar</button>
</form>

<% if (request.getAttribute("exito") != null) { %>
    <p style="color:green;"><%= request.getAttribute("exito") %></p>
<% } %>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

</body>
</html>
