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
	<legend>Create new Person</legend>
	
	<%@ include file="fragment/personForm.jspf" %>
	
	<div class="container well">
		<form:form action="${pageContext.request.contextPath}" class="form-horizontal" enctype="multipart/form-data" method="post" role="form">
			<div class="row">
				<div class="form-group input-group-sm col-md-8">
				    <label for="uploadFile">Upload xml-file</label>
				    <input type="file" id="xmlFile" name="xmlFile"/>
				    <input type="submit" value="Upload"/>
				</div>
			</div>
		</form:form>
	</div>
	<%@ include file="fragment/footer.jspf" %>
</body>
</html>
