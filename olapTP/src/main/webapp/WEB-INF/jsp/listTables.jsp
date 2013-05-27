<%@ include file="header.jsp"%>
<div id="content">
<h2>Listado de tablas y columnas</h2>
<p><c:out value="${message}" /></p>
	<table id="list">
		<tr>
			<th>Tablas</th>
		</tr>
		<c:forEach items="${tables}" var="table">
			<tr>
				<td><c:out value="${table}"/></td>
			</tr>
		</c:forEach>
	</table>
	<table id="list">
		<tr>
			<th>Columnas de usuarios</th>
		</tr>
		<c:forEach items="${columns}" var="column">
			<tr>
				<td><c:out value="${column}"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="footer.jsp"%>