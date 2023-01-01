<%@ taglib prefix ="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Welcome to CodXdoC Company Home Page</title>
</head>

<body>
	<h2>Welcome to CodXdoC Company Home Page</h2>
	<hr>
	
	<p>Home page !</p>
	
	<!-- Add Logout Button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>

</html>