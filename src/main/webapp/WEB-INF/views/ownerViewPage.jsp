<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %> 
<!DOCTYPE html>
<html>
<head>
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
<h2>Welcome Back ${sessionScope.ownerName}</h2>
<br/><br/>
<form action="/hotel/logout.htm"class="form-horizontal" method = "post">
		 	<div class="form-group">
					 <input type="submit" value="Logout">
 			</div>
 		</form>
<p>Please select any of the following option:</p>

<button class="collapsible">Add New Hotels</button>
<div class="content">
  <form:form commandName="hotel" enctype="multipart/form-data" class="form-horizontal">  				
  				
  				<div class="form-group">
				<label for="hotelTypeName" class="col-sm-4 control-label">Hotel Type:</label>
					<div class="col-sm-8">
			 		<select name="hotelType">
			  			<c:forEach items="${sessionScope.hotelTypeList}" var="ht">
							<option value="${ht.hotelType}">${ht.hotelType}</option>
			  			</c:forEach>
			  		</select>
			  		</div>
			  	</div>
  				
  				<div class="form-group">
					<label for=hotelName class="col-sm-4 control-label">Hotel
						Name: </label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="hotelName"
							path="hotelName" placeholder="Hotel Name" required="required" />
					</div>
				</div>

				<div class="form-group">
					<label for="last" class="col-sm-4 control-label">Hotel Price:</label>
					<div class="col-sm-8">
						<form:input type="number" class="form-control" id="hotelPrice" path="hotelPrice"
							placeholder="Hotel Price" required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="Hotel Description" class="col-sm-4 control-label">Hotel Description:</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="hotelDescription" path="hotelDescription"
							placeholder="Hotel Description" required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="Hotel Address" class="col-sm-4 control-label">Hotel Address:</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="hotelAddress" path="hotelAddress"
							placeholder="Hotel Address" required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="photo" class="col-sm-4 control-label">Photo: </label>
					<div class="col-sm-4">
						<input type="file" id="photo" name="photo" required="required" />
						<p class="help-block">Recent photo in JPG format</p>
					</div>
				</div>
				
				<div class="col-sm-offset-4 col-sm-8">
				<input type="submit" class="btn btn-success" value="Add Hotel" />
				</div>
  </form:form>
</div>
<button class="collapsible">Update a Hotel</button>
<div class="content">
  <form action="/hotel/owner/ownerUpdate.htm" enctype="multipart/form-data" class="form-horizontal">
  <div class="form-group">
					<label for="Hotel Name" class="col-sm-4 control-label">Please Enter Hotel Name:</label>
					<div class="col-sm-8">
					<input type="text" name="hotelName" value="" required/>
					</div>
					 <input type="submit" value="Submit">
 </div>
  <br/>
  </form>
</div>
<button class="collapsible">Owner Hotel Reviews</button>
<div class="content">
<c:forEach var="hotel" items="${sessionScope.Hotel}" varStatus="review">
	Hotel Name: <c:out value="${hotel}"/><br/>
	Hotel FeedBack: <c:out value="${sessionScope.Reviews[review.index]}"/><br/><br/>
</c:forEach>
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