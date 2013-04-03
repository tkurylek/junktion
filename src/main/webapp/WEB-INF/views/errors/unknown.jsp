<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:genericpage>
	<jsp:attribute name="footer">
		<p class="muted credit text-right">
			<spring:message code="footer.codeIsHostedOn" />
			<a href="https://github.com/tkurylek/junktion">github.com</a>.
		</p>
	</jsp:attribute>
	<jsp:body>
		<div class="page-header">
			<div class="alert alert-error text-center">
				<h2><spring:message code="error.unknownError" /></h2>
			</div>
		</div>
	</jsp:body>
</t:genericpage>