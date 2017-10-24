<html>
<head>
    <title>spring boot form submit example</title>
</head>
<body>
<h2>Employee Details</h2>
<form method="post"  enctype="multipart/form-data" action="upload">
    My Name: <input type="text" name="name"/>
    <br>
   File <input type="file" name="file">
    <br>
    <input type="submit" name="upload" value="Submit">
</form>
</body>
</html>