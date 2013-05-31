<%@ page contentType="text/html"%>
<%@ page pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<p>El archivo de configuraci�n de la base de datos se encuentra en la carpeta OLAP\WEB-INF\classes y se llama setup.properties, por favor introduzca en �l el nombre de usuario, contrase�a y url de la base de datos</p>
<p>El archivo multidim de entrada se debe ubicar en la ra�z del servidor y se debe llamar "input.xml"</p>
<p>El archivo geomondrian de salida lo encontrar� en la carpeta ra�z del servidor y se llamar� "geomondrian.xml"</p>

	<div id="content">
	<form action="createAutomaticOutput" method="POST">
		<fieldset id="marcoLogin">
			<h2>Cree el MDX XML creando la tabla autom�ticamente en la base de datos proporcionada pulsando el siguiente bot�n</h2>
			<td><input type="submit" value="Crear autom�ticamente" /></td>
		</fieldset>
	</form>
		
	<form action="selectTable" method="POST">
		<fieldset id="marcoLogin">
			<h2>O cree el MDX XML seleccionando la tabla deseada de la base de datos proporcionada en el archivo de configuraci�n</h2>
			<td><input type="submit" value="Seleccionar tabla" /></td>
		</fieldset>
	</form>
	</div>
</body>
</html>