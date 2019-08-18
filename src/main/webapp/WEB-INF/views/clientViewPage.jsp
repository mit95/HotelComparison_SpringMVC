<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="com.me.hotel.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client Page</title>
</head>
<body>
<h1>Client Page</h1>
	<c:forEach var = "ht" items= "${sessionScope.hotelTypeList}">
        <ul>
        <li>[<a href="/hotel/client/getHotel?selectedHotelType=${ht.hotelType}">${ht.hotelType}</a>]&nbsp;&nbsp;</li>
        </ul>
    </c:forEach>

<br/><br/>
<form action="/hotel/logout.htm"class="form-horizontal" method = "post">
		 	<div class="form-group">
					 <input type="submit" value="Logout">
 			</div>
 		</form>
</body>
</html>