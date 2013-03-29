<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<t:genericpage>
	<jsp:attribute name="header">
		<h1><spring:message code="home.header"/></h1>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<p class="muted credit">
			<spring:message code="footer.licence"/> <a rel="license"
				href="http://creativecommons.org/licenses/by-sa/3.0/"
				target="_blank">Creative Commons Attribution-ShareAlike 3.0
				Unported License</a>.
		</p>
	</jsp:attribute>
	<jsp:body>
		<form class="form-search">
			<div class="input-append lead">
				<input type="text" class="search-query span6" placeholder="<spring:message code="home.search"/>">
				<button class="btn btn-large" type="button">
					<i class="icon-search"></i>
				</button>
			</div>
		</form>
	</jsp:body>
</t:genericpage>