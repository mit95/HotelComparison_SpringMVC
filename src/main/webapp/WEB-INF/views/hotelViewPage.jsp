<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="com.me.hotel.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel View Page</title>
</head>
<body>
<a href="/hotel/client/getPDFView?selectedHotelType=${sessionScope.HotelTypeName}">View PDF</a><br><br>
	<c:forEach var = "hotel" items="${sessionScope.allHotelList}">
		<ul>
        <li>[<a href="/hotel/client/hotelData?selectedHotel=${hotel.hotelName}">${hotel.hotelName}</a>]</li>
        </ul>
	</c:forEach>
</body>
</html>