<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hotel Comparer Website  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form action="login" method="post">
		Username:<input type="text" name="username"/ required>
		Password:<input type="password" name="password"/ required>
		<input type="submit" value="Submit"/>
</form>
<form action="registerNew">
    <input type="submit" value="Register" />
</form>
</body>
</html>
