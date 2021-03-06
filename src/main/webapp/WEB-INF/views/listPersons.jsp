<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Person List</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="fragment/header.jspf" %>
<div class="container">
	<legend>Person List</legend>
	<c:if test="${not empty personList}">
			<table class="table">
				<thead>
					<tr>
						<th>Given Name</th>
						<th>Family Name</th>
						<th>Gender</th>
						<th>Email</th>
						<th>Homepage</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="person" items="${personList}">
						<tr>
							<td><spring:escapeBody htmlEscape="true">${person.givenName}</spring:escapeBody></td>
							<td><spring:escapeBody htmlEscape="true">${person.familyName}</spring:escapeBody></td>
							<td><spring:escapeBody htmlEscape="true">${person.gender}</spring:escapeBody></td>
							<td><spring:escapeBody htmlEscape="true">${person.mbox}</spring:escapeBody></td>
							<td><spring:escapeBody htmlEscape="true">${person.homepage}</spring:escapeBody></td>
							<td>
								<a href="${pageContext.request.contextPath}/delete/${person.id}"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
								<a href="${pageContext.request.contextPath}/view/json/${person.id}" title="Export to JSON"><span class="glyphicon glyphicon-export"></span></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	<%@ include file="fragment/footer.jspf" %>
</div>
</body>
</html>
