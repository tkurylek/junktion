<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>
	<jsp:attribute name="footer">
		<p class="muted credit">
			<spring:message code="footer.licence"/>
			<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"
				target="_blank">Creative Commons Attribution-ShareAlike 3.0
				Unported License</a>.
		</p>
	</jsp:attribute>
	<jsp:attribute name="additionalJavascript">
		<script src="<c:url value="/resources/js/plugins/asynchSearch.js" />"></script>
		<script language="javascript">
			$('.search-bar').asynchSearch({
				url : '/search/',
				form : '.form-search',
				results : '.search-results'
			});
		</script>
	</jsp:attribute>
	<jsp:body>
		<div class="page-header">
			<form class="form-search" method="get">
				<div class="input-append lead">
					<input type="text" class="search-bar search-query span6" placeholder="<spring:message code="home.search"/>">
					<button class="btn btn-large" type="submit">
						<i class="icon-search"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="text-left search-results">
			<p class="text-center"><spring:message code="home.header"/></p>
		</div>
	</jsp:body>
</t:genericpage>