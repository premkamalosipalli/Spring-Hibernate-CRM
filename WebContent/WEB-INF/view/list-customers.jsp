<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- apply styling to the page using css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer RelationShip Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<!-- buttton to search a existing customer -->
			<form:form action="search" method="get">
				Search Customer:<input type="text" name="theSearchName"/>
				<input type="submit" value="Search" class="add-button"/>
			</form:form>
			
			<!-- button for adding a new customer -->
			Click the Button to Add New Customer to the Database:<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd';return false;" class="add-button"/>
			<table border="1">
			<!-- headings for the customers table -->
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email Name</th>
					<th>Action</th>
					<th>Action</th>
				</tr>
				
				<!-- Data in the customers table -->
				<c:forEach var="tempCustomer" items="${customers}">
				<!-- construct the update link with customer id -->
				<c:url var="updateLink" value="showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				<!-- construct the delete link with customer id -->
				<c:url var="deleteLink" value="deleteCustomer">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				<tr>
					<td>${tempCustomer.firstName}</td>
					<td>${tempCustomer.lastName}</td>
					<td>${tempCustomer.email}</td>
					<td><a href="${updateLink}">Update</a>
					<td><a href="${deleteLink}">Delete</a>
				</tr>
					
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>