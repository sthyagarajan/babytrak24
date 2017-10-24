<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">BabyTrak24</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/home">Home</a></li>
                <li class="active"><a href="#">Upload</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${login.email}</a></li>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </ul>
        </div>
    </nav>
    <div class="header text-center">
        <h3>Delete File</h3>
     </div>
     <div class="card container">
        <div class="text-center">
        <img src="${uberImage.imagePath}" alt="${uberImage.transaction.fileName}" style="width:100%">
			 <div class="container">
		         <h4><b>${uberImage.transaction.email}</b></h4>
		         <p>${uberImage.transaction.fileName}</p>
		     </div>
    		</div>
    </div>

<form class="well col-md-8 col-md-offset-2"  action="/deleteImage" method="post">
  
    <div class="form-horizontal">
        <div class="form-group">
                <div class="col-md-8">
                			<input class="form-control" type="hidden" name="id" value="${uberImage.transaction.id}"/>
                </div>
        </div>
        
    </div>
    <div class="text-center">
        <input type="submit" class="btn btn-primary" value="Delete File" />
    </div>

</form>

</body>
</html>