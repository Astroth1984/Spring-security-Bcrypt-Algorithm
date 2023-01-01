<%@ taglib prefix ="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix ="security" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Welcome to CodXdoC Company Home Page</title>
</head>

<body>
	<h2>Welcome to CodXdoC Company Home Page</h2>
	<hr>
		
		<!-- Display user name and role -->
		<p>
			User: <security:authentication property="principal.username" /> 
			<br><br>
			Role(s) : <security:authentication property="principal.authorities" />
		</p>
		
	<hr>

	<security:authorize access="hasRole('MANAGER')">
		<!-- Add link to point to /leaders ... For Manager role -->	
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a> (Only for Manager peeps)
		</p>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<!-- Add link to point to /systems ... For Admin role -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT System Meeting</a> (Only for Admin peeps)
		</p>
	</security:authorize>
			
	<hr>
	
	<p>Home page !</p>
	
	<!-- Add Logout Button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>

</html>