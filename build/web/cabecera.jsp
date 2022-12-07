<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/estilos_web.css">
	<link rel="stylesheet" href="css/Entypo/fonts/style.css">
</head>
<body>
<header id="header" class="container-fluid">
	<div class="col-md-6"><h3>SISTEMA DE CONTROL DE INGRESOS Y GASTOS</h3></div><br>
            <%
                String mensaje = (String) session.getAttribute("login");
                if (mensaje == "OK") {                               
            %>
            <div class="col-md-offset-2 col-md-4"> 
                <%= session.getAttribute("propietario") %><span class="icon icon-user"></span>
                <a href="Controlador_usuarios?action=cerrar_sesion">
                    Terminar sesion
                    <span class="icon icon-cross"></span>
                </a>
                
            </div>            
            <%
            }else{
                session.removeAttribute("login");
            %>
<div class="col-md-offset-4 col-md-2"><a href="Controlador_usuarios?action=add">Registrarse <span class="icon icon-user"></span></a></div>            
            <%
                }            
            %>	
</header>