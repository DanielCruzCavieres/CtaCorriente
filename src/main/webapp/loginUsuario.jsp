<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Usuario</title>
</head>
<body>

<h2>Login Usuario</h2>

<form action="loginUsuario" method="post">

    Usuario: <br>
    <input type="text" name="nombreUsuario" required><br><br>

    Password: <br>
    <input type="password" name="password" required><br><br>

    <button type="submit">Ingresar</button>
</form>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

</body>
</html>
