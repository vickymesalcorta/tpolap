<%@ include file="header.jsp"%>
<div id="content">
<h2>Seleccione las columnas correspondientes de la tabla <c:out value="${uniqueTable}" /></h2>
<p><c:out value="${message}" /></p>
	<form action="manageSelectedColumns" method="POST">
		<fieldset id="marcoLogin">
			<c:forEach items="${multidimNames}" var="multidimName">
				<div>
					<c:out value="${multidimName}" />
					<select name="${multidimName}">
						<c:forEach items="${columns}" var="column">
							<option value="${column}"><c:out value="${column}" />
						</c:forEach>
					</select>
				</div>
			</c:forEach>
			
				<input type="submit" value="Aceptar" />
		</fieldset>
	</form>
</div>
<%@ include file="footer.jsp"%>