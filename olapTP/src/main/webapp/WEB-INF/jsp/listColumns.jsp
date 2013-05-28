<%@ include file="header.jsp"%>
<div id="content">
<h2>Listado de columnas de la tabla <c:out value="${uniqueTable}" /></h2>
<p><c:out value="${message}" /></p>
	<table id="list">
		<tr>
			<th>Columnas de la tabla <c:out value="${uniqueTable}" /></th>
		</tr>
		<c:forEach items="${columns}" var="column">
			<tr>
				<td><c:out value="${column}"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="footer.jsp"%>