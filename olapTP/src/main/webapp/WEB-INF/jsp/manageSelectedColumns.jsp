<%@ include file="header.jsp"%>
<div id="content">
<h2>Su archivo esta listo</h2>
<p><c:out value="${message}" /></p>
	<c:forEach items="${columnsInTable}" var="columnInTable">
		<div>
			<c:out value="${columnInTable.multidimName}" />
			<c:out value="->" />
			<c:out value="${columnInTable.columnName}" />
		</div>
	</c:forEach>
</div>
<%@ include file="footer.jsp"%>