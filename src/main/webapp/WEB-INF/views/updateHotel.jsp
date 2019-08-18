<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel Update</title>
</head>
<body>
<div class="content">
  <form enctype="multipart/form-data" method="POST" action="/hotel/owner/ownerUpdate.htm">
  				
  				<div class="form-group">
					<label for=hotelTypeName class="col-sm-4 control-label">Hotel Type: </label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="name"
							 value="${sessionScope.hotel.hotelType.hotelType}" disabled />
					</div>
				</div>
  				
  				<div class="form-group">
					<label for=prodName class="col-sm-4 control-label">Hotel
						Name: </label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="name"
							 value="${sessionScope.hotel.hotelName}" disabled />
					</div>
				</div>

				<div class="form-group">
					<label for="last" class="col-sm-4 control-label">Hotel Price:</label>
					<div class="col-sm-8">
						<input type="number" class="form-control" name="price"
							value="${sessionScope.hotel.hotelPrice}" required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="Hotel Description" class="col-sm-4 control-label">Hotel Description:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="description"
							value="${sessionScope.hotel.hotelDescription}" required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="Hotel Address" class="col-sm-4 control-label">Hotel Address:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="address"
							value="${sessionScope.hotel.hotelAddress}" disabled />
					</div>
				</div>
				
				<div class="col-sm-offset-4 col-sm-8">
				<input type="submit" class="btn btn-success" value="Update Hotel" />
				</div>
  </form>
</div>
</body>
</html>