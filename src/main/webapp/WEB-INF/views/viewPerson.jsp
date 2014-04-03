<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Person View</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="fragment/header.jspf" %>
<div class="container">	
	<c:if test="${not empty person}">
		<legend><spring:escapeBody htmlEscape="true">${person.givenName} ${person.familyName}</spring:escapeBody></legend>
		
		<div class="panel panel-default">
		  <div class="panel-heading">Gender</div>
		  <div class="panel-body">
		    <spring:escapeBody htmlEscape="true">${person.gender}</spring:escapeBody>
		  </div>
		</div>	
		<div class="panel panel-default">
		  <div class="panel-heading">Email adress</div>
		  <div class="panel-body">
		    <spring:escapeBody htmlEscape="true">${person.mbox}</spring:escapeBody>
		  </div>
		</div>	
		<div class="panel panel-default">
		  <div class="panel-heading">Homepage</div>
		  <div class="panel-body">
		    <spring:escapeBody htmlEscape="true">${person.homepage}</spring:escapeBody>
		  </div>
		</div>
	</c:if>
	<%@ include file="fragment/footer.jspf" %>
</div>
</body>
</html>
