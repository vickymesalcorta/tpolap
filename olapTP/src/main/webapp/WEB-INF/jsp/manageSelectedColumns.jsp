<%@ include file="header.jsp"%>
<div id="content">
<p><c:out value="${message}" /></p>
<h3>A continuaci&#243;n se muestra c&#243;mo se realiz&#243; el mapeo de las columnas, de la forma:</h3>
<h4>columnaEnMultidim->columnaEnBaseDeDatos</h4>
<p><div style="color:#FF0000;"><c:out value="${columnTypeWrong}"/></div></p>
	<c:forEach items="${columnsInTable}" var="columnInTable">
		<div>
			<c:out value="${columnInTable.multidimName}" />
			<c:out value="->" />
			<c:out value="${columnInTable.columnName}" />
		</div>
	</c:forEach>
</div>
<%@ include file="footer.jsp"%>