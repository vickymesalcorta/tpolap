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
	<div id="content">


			<h2>Informaci&#243;n</h2>
			<p>El archivo de configuraci&#243;n de la base de datos se encuentra en la carpeta OLAP\WEB-INF\classes y se llama setup.properties, por favor introduzca en &#233;l el nombre de usuario, contrase&#250;a y url de la base de datos</p>
			<p>El archivo multidim de entrada se debe ubicar en la ra&#237;z del servidor y se debe llamar "input.xml"</p>
			<p>El archivo geomondrian de salida lo encontrar&#225; en la carpeta ra&#237;z del servidor y se llamar&#225; "geomondrian.xml"</p>

		</br>
		<form action="createAutomaticOutput" method="POST">
			<fieldset id="marcoLogin">
				<h2>Cree el MDX XML creando la tabla autom&#225;ticamente en la base de datos proporcionada pulsando el siguiente bot&#243;n</h2>
				<td><input type="submit" value="Crear autom&#225;ticamente" /></td>
			</fieldset>
		</form>
			
		<form action="selectTable" method="POST">
			<fieldset id="marcoLogin">
				<h2>O cree el MDX XML seleccionando la tabla deseada de la base de datos proporcionada en el archivo de configuraci&#243;n</h2>
				<td><input type="submit" value="Seleccionar tabla" /></td>
			</fieldset>
		</form>
	</div>
</body>
</html>