<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object cod_usuario_ses = "";
    Object propietario_ses = "";
    Object empresa_ses = "";
    Object email_ses = "";
    Object password_ses = "";
    if (session.getAttribute("login")== "OK") {
        cod_usuario_ses = session.getAttribute("cod_usuario");
        propietario_ses = session.getAttribute("propietario");        
        empresa_ses = session.getAttribute("empresa");        
        email_ses = session.getAttribute("email");        
        password_ses = session.getAttribute("password");        
    }
%>
<%@ include file="cabecera.jsp" %>

<section class="container">
	<div class="col-md-offset-3 col-md-5"><h1>Configuracion Usuario</h1></div>	
	<hr>	
<%@include file="opciones.jsp" %>
	<div class="col-md-offset-3 col-md-5">
		<form action="Controlador_usuarios" method="get">
	<label for="propietario">Propietario:</label>
        <input name="action" type="hidden" value="edit" />
        <input  name="cod" type="hidden" value="<%= cod_usuario_ses %>"/>
        <input value="<%= propietario_ses %>" type="text" name="propietario" class="form-control" placeholder="Ingresar nombre propietario">
		<label for="empresa">Empresa:</label>
                <input value="<%= empresa_ses %>" type="text" class="form-control" name="empresa" placeholder="Ingresar nombre empresa">
		<label for="email">E-mail:</label>
                <input value="<%= email_ses %>" type="text" class="form-control" name="email" placeholder="Ingresar E-mail">
		<label for="contraseña">Contraseña:</label>
                <input value="<%= password_ses %>" type="password" class="form-control" name="contrasena" placeholder="ingresar contraseña">
		<br>
		<input type="submit" class="btn btn-success" value="MODIFICAR"><br><br><br>
		</form>
	</div>
        <%
        if (session.getAttribute("confirmacion")== "conf_si") {     
        %>
        <script>alert("Usuario modificado exitosamente")</script>
        <%        }    %>
	
</section><br><br><br><br><br>

<%@ include file="piedepagina.jsp" %>

