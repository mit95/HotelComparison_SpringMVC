<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Title</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.collapsible {
  background-color: #777;
  color: white;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  background-color: #555;
}

.content {
  padding: 0 18px;
  display: none;
  overflow: hidden;
  background-color: #f1f1f1;
}
</style>
</head>
<body>
<h2>Welcome Moderator ${sessionScope.moderatorName}!</h2>

<form action="/hotel/logout.htm"class="form-horizontal" method = "post">
		 	<div class="form-group">
					 <input type="submit" value="Logout">
 			</div>
 		</form>
 		<br/><br/>

<button class="collapsible">Create New Hotel Type</button>
<div class="content">
  <form action="/hotel/moderator/createHotelType.htm"class="form-horizontal" method = "post">
  <div class="form-group">
					<label for="HotelType Name" class="col-sm-4 control-label">Please Enter the type of Hotel:</label>
					<div class="col-sm-8">
					<input type="text" name="hotelTypeName" value="" required/> <br/>
					<label for="HotelType Name" class="col-sm-4 control-label">Please Enter Description for Hotel Type:</label>
					<div class="col-sm-8">
					<input type="text" name="hotelTypeDescription" value="" required/>
					</div>
					 <input type="submit" value="Submit">
 					</div>
 </div>
  <br/>
  </form>
</div>


<button class="collapsible">Show Hotel Types</button>
<div class="content">
<table>
	<tr>
 		<th>Type: </th><th>Description: </th> 
 	</tr>
 	<c:forEach var="hotelType" items="${sessionScope.hotelTypeList}">
 		<tr>
 			<td>${hotelType.hotelType}</td><td>${hotelType.hotelTypeDescription}</td> 
 		</tr>
	</c:forEach>
</table>
</div>
<button class="collapsible">Show Clients</button>
<div class="content">
<table>
		<tr>
 			<th>First name: </th><th>Last Name: </th><th>Email ID: </th> 
 		</tr>
 		<c:forEach var="client" items="${sessionScope.clientList}">
 			<tr>
 				<td>${client.firstName}</td><td>${client.lastName}</td><td>${customer.emailId}</td>  
 			</tr>
		</c:forEach>
</table>
</div>
<button class="collapsible">Show Owners</button>
<div class="content">
<table>
	<tr>
 		<th>First name: </th><th>Last Name: </th><th>Email ID: </th> 
 	</tr>
 	<c:forEach var="own" items="${sessionScope.ownerList}">
 		<tr>
 			<td>${own.firstName}</td><td>${own.lastName}</td><td>${own.emailId}</td>  
 		</tr>
	</c:forEach>
</table>
</div>
<script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.display === "block") {
      content.style.display = "none";
    } else {
      content.style.display = "block";
    }
  });
}
</script>

</body>
</html>