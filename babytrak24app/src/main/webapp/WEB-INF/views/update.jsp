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
                console.log(uploadFileName + ' file size is : ' + uploadFileSize + 'MB');
                $('#filedetails').text('file name is: '+uploadFileName +' size is: '+ uploadFileSize + 'MB(uses Math.round)');
                if(uploadFileSize>1){
                    $('#file')[0].setCustomValidity('Size is greater than permissible 1 MB');
                }else{
                    $('#file')[0].setCustomValidity('');
                }
            });
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
    <h3>Update File</h3>
</div>

    <div class="container col-md-2">
        <div class="card">
            <div class="card-img-top"><img src="${uberImage.imagePath}" alt="${uberImage.transaction.fileName}" style="width:100%"></div>
            <div class="card-block">

                <h4><b>File Details</b></h4>
                <p><b>File Name :</b> ${uberImage.transaction.fileName}</p>
                <p><b>File Description :</b> ${uberImage.transaction.fileDescription}</p>
                <p><b>User :</b> ${uberImage.transaction.email}</p>
                <p><b>Uploaded Time :</b> ${uberImage.transaction.uploadTime}</p>
                <p><b>Updated Time :</b> ${uberImage.transaction.updatedTime}</p>
                <p><b>Cloud Front Url :</b> ${uberImage.imagePath}</p>
            </div>
        </div>
    </div>


<form class="well col-md-8 col-md-offset-2" enctype="multipart/form-data" action="/updateImage" method="post">

    <div class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-md-2">File Name</label>
            <div class="col-md-8">
                <input class="form-control" type="text" name="fileName" value="${uberImage.transaction.fileName}" readonly="readonly"/>
                <input class="form-control" type="hidden" name="id" value="${uberImage.transaction.id}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2">File Description</label>
            <div class="col-md-8">
                <input class="form-control" type="text" name="fileDescription" value="${uberImage.transaction.fileDescription}"/>

            </div>
        </div>
        <div class="form-group">
            <div class="col-md-8 col-md-offset-2">
                <input class="form-control" type="file" name="file" id="file" required accept=".jpg, .jpeg, .png"/>
                <div class="preview">
                    <p id="filedetails">No files currently selected for update</p>
                </div>
            </div>
        </div>

    </div>
    <div class="text-center" style="font-style: italic; color: red;">
        ${message}
    </div>
    <div class="text-center">
        <input type="submit" class="btn btn-primary" value="Update File" />
    </div>

</form>

</body>
</html>