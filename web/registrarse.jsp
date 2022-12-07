<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="cabecera.jsp" %>

<section class="container"><br>
	<div class=""><h1>Registrarse</h1></div><br><br>
	<div class="col-md-offset-0 col-md-6">
            <form action="Controlador_usuarios" method="POST">
		<label for="propietario">Propietario:</label>
		<input type="text" name="propietario" class="form-control" placeholder="Ingresar nombre propietario">
		<label for="empresa">Empresa:</label>
		<input type="text" class="form-control" name="empresa" placeholder="Ingresar nombre empresa">
		<label for="email">E-mail:</label>
		<input type="text" class="form-control" name="email" placeholder="Ingresar E-mail">
		<label for="contraseña">Contraseña:</label>
		<input type="password" class="form-control" name="contrasena" placeholder="ingresar contraseña">
		<br>
		<input type="submit" class="btn btn-success" value="REGISTRASE"><br><br><br>
            </form>
	</div>
	<div class="col-md-6">
            <%
            if (session.getAttribute("creado") == "registrado") {                   
            %>
	<div class="alert alert-success" role="alert">
  		Registrado exitosamente...
  		<a href="inicio.jsp" class="btn btn-success">Iniciar sesion</a>
	</div>
            <%
            }else{
                session.removeAttribute("creado");
                //response.sendRedirect("registrarse.jsp"); 
            }
            %>
	</div>      
</section>

<%@ include file="piedepagina.jsp" %>

