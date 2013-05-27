<%@ include file="header.jsp"%>
<div id="content">
<h2>Listado de tablas</h2>
<p><c:out value="${message}" /></p>
<table id="list">
	<tr>
		<th>Tabla</th>
	</tr>
	<c:forEach items="${tables}" var="table">
		<tr>
			<td><c:out value="${table}"/></td>
		</tr>
	</c:forEach>
	</table>
</div>
<%@ include file="footer.jsp"%>