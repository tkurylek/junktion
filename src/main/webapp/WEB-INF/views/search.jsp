<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<t:genericpage>
	<jsp:attribute name="footer">
		<p class="muted credit">
			<spring:message code="footer.licence"/>
			<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"
				target="_blank">Creative Commons Attribution-ShareAlike 3.0
				Unported License</a>.
		</p>
	</jsp:attribute>
	<jsp:body>
		<div class="page-header">
			<form class="form-search">
				<div class="input-append lead">
					<input type="text" class="search-query span6" placeholder="<spring:message code="home.search"/>">
					<button class="btn btn-large" type="button">
						<i class="icon-search"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="text-left">
			<blockquote>
				<dl>
					<dt>Document title</dt>
					<dd>
						This is a document content with <em class="text-info">highlighted word</em> in it.
						<small class=" muted pull-right">/the/path/to/file.pdf</small>
					</dd>
				</dl>
			</blockquote>
		</div>
	</jsp:body>
</t:genericpage>