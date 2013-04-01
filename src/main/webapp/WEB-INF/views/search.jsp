<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>
	<jsp:attribute name="footer">
		<p class="muted credit text-right">
			<spring:message code="footer.codeIsHostedOn" />
			<a href="https://github.com/tkurylek/junktion">github</a>.
			<i class="icon-thumbs-up"></i>
		</p>
	</jsp:attribute>
	<jsp:attribute name="additionalHead">
		<link href="<c:url value="/resources/css/asynchSearch.css" />"
			rel="stylesheet" media="screen">
	</jsp:attribute>
	<jsp:attribute name="additionalJavascript">
		<script src="<c:url value="/resources/js/plugins/asynchSearch.js" />"></script>
		<script language="javascript">
			$('.search-bar').asynchSearch({
				url : '/search/',
				form : '.form-search',
				results : '.search-results',
				more : '<spring:message code="button.moreResults" javaScriptEscape="true" />'
			});
		</script>
	</jsp:attribute>
	<jsp:body>
		<div class="page-header">
			<form class="form-search" method="get">
				<div class="input-append lead">
					<input type="text" class="search-bar search-query span6"
						placeholder="<spring:message code="home.search"/>">
					<button class="btn btn-large" type="submit">
						<i class="icon-search"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="text-left search-results">
			<p class="text-center">
				<spring:message code="home.header" />
			</p>
		</div>
	</jsp:body>
</t:genericpage>