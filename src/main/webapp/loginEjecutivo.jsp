<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Ejecutivo</title>
</head>
<body>
    <h2>Inicio de Sesión - Ejecutivo</h2>

    <form action="loginEjecutivo" method="post">
        <label>RUT Ejecutivo (sin DV):</label>
        <input type="number" name="rutEjecutivo" required /><br/><br/>

        <label>Nombre:</label>
        <input type="text" name="nombre" required /><br/><br/>

        <input type="submit" value="Ingresar" />
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <%
        }
    %>
</body>
</html>
