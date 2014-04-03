<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Home</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="fragment/header.jspf" %>
<div class="container">
	<legend>Create new Person</legend>
	
	<%@ include file="fragment/personForm.jspf" %>
	

	<%@ include file="fragment/footer.jspf" %>
</div>
</body>
</html>
