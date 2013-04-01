<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="additionalHead" fragment="true"%>
<%@attribute name="additionalJavascript" fragment="true"%>
<!DOCTYPE html>
<html>
<head>
<title>Junktion</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet" media="screen">
<script src="<c:url value="/resources/js/jquery-1.9.1.min.js" />"></script>
<link href="<c:url value="/resources/css/general.css" />"rel="stylesheet" media="screen">
<jsp:invoke fragment="additionalHead"></jsp:invoke>
</head>
<body>
	<div id="wrap">
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="/">junktion</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="/"><spring:message code="nav.home"/></a></li>
							<li><a href="#about"><spring:message code="nav.about"/></a></li>
							<li><a href="#help"><spring:message code="nav.help"/></a></li>
							<!-- <li class="divider-vertical"></li> -->
						</ul>
					</div>
					<div class="nav-collapse collapse pull-right">
						<ul class="nav">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="icon-user"></i> Tomasz Kuryłek <b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li>Logout</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="container text-center" style="display:none;">
			<jsp:doBody/>
		</div>
		<div id="push"></div>
	</div>
	
	<div id="footer">
		<div class="container">
			<jsp:invoke fragment="footer"/>
		</div>
	</div>
	
	<!-- Scripts -->
	<jsp:invoke fragment="additionalJavascript"></jsp:invoke>
	<script language="javascript">
		$(".container").delay(500).fadeIn('slow');
	</script>
</body>
</html>