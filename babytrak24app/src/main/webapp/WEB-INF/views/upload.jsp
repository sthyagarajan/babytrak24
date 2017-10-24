<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <script>
        $(document).ready(function() {
            $('#file').on('change', function () {

                var uploadFileName = $(this)[0].files[0].name;
                var uploadFileSize = ($(this)[0].files[0].size/1024/1024);
                console.log(uploadFileName + ' file size is: ' + uploadFileSize + 'MB');
                $('#filedetails').text('file name is: '+uploadFileName +' size is: '+ uploadFileSize + 'MB');
                console.log ("FileName is" + $('#fileName').val() + "this");
                if (!$.trim($('#fileName').val()).length > 0) {
                    console.log ("FileName is set to" + uploadFileName + "set");
                    $('#fileName').val(uploadFileName);

                }
                if(uploadFileSize>1){
                    $('#file')[0].setCustomValidity('Size greater than permissible 1MB');
                }else{
                    $('#file')[0].setCustomValidity('');
                }
            });
            $('#fileName').attr("onblur", "setCustomValidity(''); checkValidity(); setCustomValidity(validity.valid ? '' :'Filename should end with .jpg or .jpeg or .png');");
            $('#fileName').attr("oninput", "setCustomValidity(''); checkValidity(); setCustomValidity(validity.valid ? '' :'Filename should end with .jpg or .jpeg or .png');");
        });

    </script>
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
        <h3>Upload File</h3>
        </div>
<form class="well col-md-8 col-md-offset-2" enctype="multipart/form-data" action="/uploadImage" method="post">
  
    <div class="form-horizontal">
        <div class="form-group">
       		    <label class="control-label col-md-2" for="fileName">File Name</label>
                <div class="col-md-8">
                			<input class="form-control" type="text" name="fileName" id="fileName" required="required" pattern=".+[\.jpg|\.jpeg|\.png]$" oninvalid="setCustomValidity('Filename should end with .jpg or .jpeg or .png');"/>
                </div>
        </div>
        <div class="form-group">
       		    <label class="control-label col-md-2" for="fileDescription">File Description</label>
                <div class="col-md-8">
                			<input class="form-control" type="text" name="fileDescription" id="fileDescription"/>
                </div>
        </div>
         <div class="form-group">
       		<div class="col-md-8 col-md-offset-2">
            		<input class="form-control" type="file" name="file" id="file" required accept=".jpg, .jpeg, .png"/>
            		<div class="preview">
                    <p id="filedetails">No files currently selected for upload</p>
                </div>
         	</div>
        </div>
        
    </div>
    <div class="text-center" style="font-style: italic; color: red;">
           ${message}
    </div>
    <div class="text-center">
        <input type="submit" class="btn btn-primary" value="Upload File" />
    </div>

</form>
</body>
</html>