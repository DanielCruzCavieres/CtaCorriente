<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Cliente y Cuenta Corriente</title>
</head>
<body>

<h1>Registrar Cliente y Cuenta Corriente</h1>

<!-- Mensajes de éxito o error -->
<%
    String error = (String) request.getAttribute("error");
    String exito = (String) request.getAttribute("exito");

    if (error != null) {
%>
        <p style="color:red; font-weight: bold;"><%= error %></p>
<%
    }

    if (exito != null) {
%>
        <p style="color:green; font-weight: bold;"><%= exito %></p>
<%
    }
%>

<!-- Formulario de registro -->
<form action="registrarCliente" method="post">

    <fieldset>
        <legend><strong>Datos del Cliente</strong></legend>

        <label>RUT Cliente (sin DV):</label>
        <input type="text" name="rutCliente" required /><br/><br/>

        <label>Nombre:</label>
        <input type="text" name="nombre" required /><br/><br/>

        <label>Apellido:</label>
        <input type="text" name="apellido" required /><br/><br/>

        <label>Dirección:</label>
        <input type="text" name="direccion" required /><br/><br/>

        <label>Correo electrónico:</label>
        <input type="email" name="correo" required /><br/><br/>

        <label>Teléfono:</label>
        <input type="text" name="telefono" required /><br/><br/>

        <label>Nombre Mascota:</label>
        <input type="text" name="nombreMascota" required /><br/><br/>
    </fieldset>

    <br/>

    <fieldset>
        <legend><strong>Datos de la Cuenta Corriente</strong></legend>

        <label>Monto de apertura:</label>
        <input type="number" name="monto" step="0.01" required /><br/><br/>
    </fieldset>

    <br/>
    <input type="submit" value="Registrar" />
</form>

<br/>
<a href="menuEjecutivo.jsp">Volver al menú del Ejecutivo</a>

</body>
</html>