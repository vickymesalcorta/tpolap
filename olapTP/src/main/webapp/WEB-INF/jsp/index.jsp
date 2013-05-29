<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="content">
	<h2>Cree el MDX XML creando las tablas automaticamente pulsando el siguiente boton</h2>
	<form action="createAutomaticOutput" method="POST">
		<fieldset id="marcoLogin">
			<td><input type="submit" value="Crear automaticamente" /></td>
		</fieldset>
	</form>
		
	<h2>O cree el MDX XML seleccionando la tabla deseada de la base de datos proporcionada</h2>
	<p><c:out value="${message}" /></p>
	<c:if test="${tables == null}">
		<h3>No existe ninguna tabla en la base de datos proporcionada</h3>
	</c:if>
		<c:if test="${tables != null}">
			<form action="selectColumns" method="POST">
				<fieldset id="marcoLogin">
					<table>
						<tr>
							<select name="table"><br>
								<c:forEach items="${tables}" var="table">
									<option value="${table}"><c:out value="${table}" />
								</c:forEach>
							</select>
						</tr>
						<br/>
						<tr>
							<td><input type="submit" value="Aceptar" /></td>
						</tr>
					</table>
					</fieldset>
			</form>
		</c:if>
	</div>
</body>
</html>