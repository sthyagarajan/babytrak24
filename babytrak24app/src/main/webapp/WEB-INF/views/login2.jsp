<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        html {
            display: flex;
            height: 100%;
        }
        body {
            align-items: center;
            display: flex;
            flex: 1;
            justify-content: center;
        }
        .login-form {

        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <form class="well col-md-8" action="/loginProcess" method="post">
        <div class="header text-center">
            <h3>Welcome Back !!!</h3>
        </div>
        <div class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-2" for="email" >Email Address</label>
                <div class="col-md-8">
                    <input class="form-control" type="email" name="email" id="email" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-2" for="password">Password</label>
                <div class="col-md-8">
                    <input class="form-control" type="password" name="password" id="password" required="required"/>
                </div>
            </div>
        </div>
        <div class="text-center" style="font-style: italic; color: red;">
           ${message}
        </div>
        <div class="text-center">
            <input type="submit" class="btn btn-primary" value="Login" />
        </div>

    </form>
</body>
</html>