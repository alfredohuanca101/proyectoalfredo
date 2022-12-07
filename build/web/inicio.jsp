<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="cabecera.jsp" %>

<section class="container-fluid">
	<div class="col-md-12">
	<div class="contenerdor_imagen">
            <img src="img/fondo.jpg" alt="">
                <!--https://blog.selfbank.es/wp-content/uploads/2019/12/GettyImages-1148634836-720x320.jpg-->
		<div class="ubicar_login">
                    <form action="Controlador_usuarios" method="GET">
				<div class="col-md-3 centrar">
					<div class="contenedor_login">
					<h1>ACCEDER</h1>
                                        <input type="hidden" name="action" value="inicio_sesion"/>
                                        <input name="email" type="text" class="form-control" placeholder="Ingresar correo electronico"><br>
					<input name="contrasena" type="password" class="form-control" placeholder="Ingresar password"><br>
					<input type="submit" name="" class="btn btn-primary" style="width: auto;">						
					</div>
				</div>
                    </form>
		</div>
                        <%
                        String mensaje_error = (String) session.getAttribute("error");
                        if (mensaje_error != null) {                               
                        %>
                        <script>alert("E-mail y contraseña erronea vuelva a intentar.")</script>
                        <%
                        session.removeAttribute("error");
                        }
                        %>
	</div>		
	</div>
</section>

<%@ include file="piedepagina.jsp" %>

