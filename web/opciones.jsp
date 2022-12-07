<style>
	/*ESTILOS MENU*/
.contenedor_menu{
	position: absolute;
	background: #2a3f54;
	color: #FFF;
	width: 200px;
	height: 380px;
	left: 0%;
}
ul.menu li, a {
	text-decoration-line: none;
	list-style: none;
	color: #FFF;
}
ul.menu li {
	padding: 1rem;
}
li:hover{
	background: #286BE6;;
	text-decoration: none;					
}
a:hover{
	text-decoration-line: none;
	color:#FFF;
}
.activo{
	background: #286BE6;
}


</style>
<div class="contenedor_menu">
	<ul class="menu">
		<li class="activo desplegar">MENU</li>
		<li class="activo desplegar2" style="display: none;">MENU</li>
		<li class="item"><a href="Controlador_usuarios?action=inicio">Inicio</a></li><!--Controlador_usuarios?action=inicio-->
		<li class="item"><a href="Controlador_estados?action=marketing&area=marketing">Marketing</a></li>
		<li class="item"><a href="Controlador_estados?action=recursos&area=recursos">Recursos humanos</a></li>
		<li class="item"><a href="Controlador_estados?action=finanzas&area=finanzas">Finanzas</a></li>
		<li class="item"><a href="configuracion.jsp">Configuracion</a></li>
	</ul>
</div>
