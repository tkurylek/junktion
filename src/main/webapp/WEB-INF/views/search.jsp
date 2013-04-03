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
	<jsp:attribute name="additionalHead">
		<link href="<c:url value="/resources/css/asyncSearch.css" />"
			rel="stylesheet" media="screen">
	</jsp:attribute>
	<jsp:attribute name="additionalJavascript">
		<script src="<c:url value="/resources/js/plugins/intelligentPopover.js" />"></script>
		<script src="<c:url value="/resources/js/plugins/asyncSearch.js" />"></script>
		<script language="javascript">
			$('.search-bar').asyncSearch({
				url : 'search/',
				form : '.form-search',
				results : '.search-results',
				i18n : {
					more : '<spring:message code="button.moreResults" javaScriptEscape="true" />',
					author : '<spring:message code="document.author" javaScriptEscape="true" />',
					modified : '<spring:message code="document.modified" javaScriptEscape="true" />',
					size : '<spring:message code="document.size" javaScriptEscape="true" />',
					noResults : '<spring:message code="error.documentNotFound" javaScriptEscape="true" />',
					unknownError : '<spring:message code="error.unknownError" javaScriptEscape="true" />'
				}
			});
		</script>
	</jsp:attribute>
	<jsp:body>
		<div class="page-header">
			<form class="form-search" method="get">
				<div class="input-append lead">
					<input type="text" class="search-bar search-query span6"
						placeholder="<spring:message code="searchbar.search"/>">
					<button class="btn btn-large" type="submit">
						<i class="icon-search"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="text-left search-results"></div>
	</jsp:body>
</t:genericpage>