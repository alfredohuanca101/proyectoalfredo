<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="cabecera.jsp" %>

<section class="container">
	<div class="centrar"><h1>Recursos Humanos</h1></div>	
	<hr>	
<%@include file="opciones.jsp" %>
<input name="area" type="hidden" value="recursos" />
	<div class="col-md-offset-1 col-md-5">
		<h3>Ingresos</h3>
		<form action="Controlador_ingresos" method="get">
                <input type="hidden" name="area_ingreso" value="recursos" />
                <input type="hidden" name="action" value="add" />
		<label for="descripcion"></label>
		<input type="text" name="descripcion_ingreso" class="form-control" placeholder="Ingresar descripcion">
		<label for="monto"></label>
		<input type="number" name="monto_ingreso" class="form-control" placeholder="Ingresar monto">
		<label for="fecha"></label>		
		<input type="date" name="fecha_ingreso" class="form-control"> <br>
		<input type="submit" value="Registrar" class="btn btn-primary"><br>
		</form>		
	</div>
	<div class="col-md-offset-0 col-md-5">
		<h3>Gastos</h3>
		<form action="Controlador_gastos" method="get">
                <input type="hidden" name="area_gasto" value="recursos" />
                <input type="hidden" name="action2" value="add" />
		<label for="descripcion_gasto"></label>
		<input type="text" name="descripcion_gasto" class="form-control" placeholder="Ingresar descripcion">
		<label for="monto_gasto"></label>
		<input type="number" name="monto_gasto" class="form-control" placeholder="Ingresar monto">
		<label for="fecha_gasto"></label>		
		<input type="date" name="fecha_gasto" class="form-control"><br>
		<input type="submit" value="Registrar" class="btn btn-primary"><br>
		</form>		
	</div>	
	<div class="col-md-offset-3 col-md-9">	<br>
	<div class="estadisticas centrar">
		<div class="info_estadistica">
			<h1><strong>${total_ingresos_area} BS.</strong></h1>
			<h3>Ingresos Totales</h3>
		</div>
		<div class="info_estadistica">
			<h1><strong>${total_ingresos_area - total_gastos_area} BS.</strong></h1>
			<div class="text_info">
			<h3>Saldo Actual</h3>	
			</div>			
		</div>
		<div class="info_estadistica">
			<h1><strong>${total_gastos_area} BS.</strong> </h1>
			<h3>Gastos Totales</h3>
		</div>
	</div>		
	</div>
	<div class="container">
            <div class="col-md-6"><br>
		<div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                    <th>Descripcion</th>
                    <th>Fecha</th>
                    <th>Monto</th>
                    <th></th>
                    </tr>
                    </thead>
		<tbody>
                    <c:forEach var="item_ingreso" items="${ingreso}">
                <tr>
		<td>${item_ingreso.descripcion}</td>
		<td>${item_ingreso.fecha}</td>
		<td>${item_ingreso.monto}</td>
		<td>
                    <a href="Controlador_ingresos?action=edit&id=${item_ingreso.cod_ingreso}" style="color: #4000F6;">Modificar</a>&nbsp;&nbsp;
		<a href="Controlador_ingresos?action=delete&area_ingreso=${item_ingreso.area}&id=${item_ingreso.cod_ingreso}" style="color: #4000F6;">Eliminar</a>
		</td>
		</tr>
                    </c:forEach>
		</tbody>
		</table>
		</div>
            </div>
                <div class="col-md-6"><br>
		<div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                    <th>Descripcion</th>
                    <th>Fecha</th>
                    <th>Monto</th>
                    <th></th>
                    </tr>
                    </thead>
		<tbody>
                    <c:forEach var="item_gasto" items="${gasto}">
                <tr>
		<td>${item_gasto.descripcion}</td>
		<td>${item_gasto.fecha}</td>
		<td>${item_gasto.monto}</td>
		<td>
		<a href="Controlador_gastos?action2=edit&id=${item_gasto.cod_gasto}" style="color: #4000F6;">Modificar</a>&nbsp;&nbsp;
		<a href="Controlador_gastos?action2=delete&area_gasto=${item_gasto.area}&id=${item_gasto.cod_gasto}" style="color: #4000F6;">Eliminar</a>
		</td>
		</tr>
                    </c:forEach>
		</tbody>
		</table>
		</div>
            </div>            
	</div>
</section><br><br><br><br><br>

<%@ include file="piedepagina.jsp" %>

