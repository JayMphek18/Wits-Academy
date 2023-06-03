<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link type="text/css" href="./css/reset_pwrd.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <div class="container">
        <img src="./Wits_Logo.png" loading="lazy" width="259" height="150" alt="Wits university emblem" />
        <h1 class="head1">Reset Password</h1>
    <div>
    <form class="reset_password" action="./php/reset_pwrd.php" method="post" autocomplete="off">
        <label class = "user_id" for="user_id"> User Number: </label>
        <input class = "userID_input"type="text" maxlength = "7" placeholder = "Enter your user number" name="user_id" id = "user_id"><br>
        <label class = "email" for="email"> E-mail address: </label>
        <input class = "email_input"type="text" maxlength = "128" placeholder = "Enter your e-mail address" name="email" id = "email"><br>
        <label class = "password" for="password"> New password: </label>
        <input class = "password_input" type="password" maxlength = "10" placeholder = "Enter your new passowrd" name="password" id = "password" required=""><br>
        <label class = "cpassword" for="cpassword"> Confirm new Password: </label>
        <input class = "cpassword_input" type="password" maxlength = "10" placeholder = "Re-enter new passowrd" name="cpassword" id = "cpassword" required=""><br>
        <button class="reset_button" type="submit" name = "reset">Reset password</button>   
        <div><a href="./login.php" class="login_button">LOGIN</a>
    </div>    
    </form></div>
</body>
</html>