<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="com.me.hotel.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel Description</title>
</head>
<body>
<c:set var="hotel" value= "${sessionScope.hotelList}"/>
	<c:out value="${hotel.getHotelName()}"/>
	
           
            <c:out value="${hotel.getHotelPrice()}"/><br/>
            <c:out value="${hotel.getHotelAddress()}"/><br/>
            <c:out value="${hotel.getHotelDescription()}"/><br/><br/>
 			
 			<img src="<c:url value="C:/Users/Mit/Desktop/hotelComparerFiles/${hotel.getHotelImage()}"/>" alt="image"
                             width="200px" height="600px"/><br/><br/>
 	<c:forEach var="review" items="${sessionScope.reviewList}">
 		<c:out value="${review.getReview()}"/>
 	</c:forEach>
 	<form action="/hotel/client/addReview" method="post">
 		Enter your Review:<input type="text" name="review"/>
 		<input type="submit" value="Add"/>
 	</form>
</body>
</html>