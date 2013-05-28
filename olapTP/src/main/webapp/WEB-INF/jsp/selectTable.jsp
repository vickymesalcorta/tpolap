<%@ include file="header.jsp"%>
<div id="content">
<h2>Listado de tablas de la base de datos</h2>
<p><c:out value="${message}" /></p>
	<form action="selectTable" method="POST">
		<fieldset id="marcoLogin">
			<table>
				<tr>
					<c:if test="${tables == null}">
						<h3>No existe ninguna tabla en la base de datos proporcionada</h3>
					</c:if>
					<c:if test="${tables != null}">
						<select name="table"><br>
							<c:forEach items="${tables}" var="table">
								<option value="${table}"><c:out value="${table}" />
							</c:forEach>
						</select>
					</c:if>
				</tr>
				<br/>
				<tr>
					<td><input type="submit" value="Aceptar" /></td>
				</tr>
			</table>
			</fieldset>
	</form>
</div>
<%@ include file="footer.jsp"%>