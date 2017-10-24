<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            margin: 0px 10px 20px;
            padding-top: 15px;
            transition: 0.3s;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
        }

        .container {
            padding: 2px 16px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">BabyTrak24</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="/upload">Upload</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${login.email}</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </ul>
        </div>
    </nav>
	<c:choose>
	<c:when test="${not empty uberImages}">

		
			<c:forEach var="listValue" items="${uberImages}">
			<div class="card col-md-3">
				<img src="${listValue.imagePath}" alt="${listValue.transaction.fileName}" style="width:100%">
				 <div class="container">
			         <h4><b> File Details</b></h4>
			         <p><b>File Name :</b> ${listValue.transaction.fileName}</p>
			         <p><b>File Description : </b> ${listValue.transaction.fileDescription}</p>
			         <p><b>User : </b> ${listValue.transaction.email}</p>
			         <p><b>Uploaded Time : </b> ${listValue.transaction.uploadTime}</p>
			        	 <p><b>Updated Time : </b> ${listValue.transaction.updatedTime}</p>			        	 
			          <div class="card-block">
			          	<a href="/update?id=${listValue.transaction.id}" class="card-link">Update</a>
			         	<a href="/delete?id=${listValue.transaction.id}" class="card-link">Delete</a>
			         	<a href="${listValue.imagePath}" download>Download</a>
    
  					</div>
			     </div>
			</div>
				
			</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="header text-center">
           		<h3>Welcome Back !!!</h3>
       	</div>
				
		 <div class="text-center">
	         <h4><b>Please Upload to leverage features</b></h4>
	         <p>There are no images in your profile</p>
	         <a href="/upload">Upload</a>
	     </div>
			
	</c:otherwise>
		

	</c:choose>
   
</body>
</html>