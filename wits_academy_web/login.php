<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link type="text/css" href="./css/login.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<!--html coding for layout of the page -->
<body class="body">
    <div class="container">
        <img src="./Wits_Logo.png" loading="lazy" width="259" height="150" alt="Wits university emblem" />
        <h1 class="head1">Welcome back!</h1>

    <div>
    <form class="login" action="./php/login.php" method="post" autocomplete="off">
        <label class = "user_id" for="user_id"> User Number: </label>
        <input class = "userID_input"type="text" maxlength = "7" placeholder = "Enter your user number" name="user_id" id = "user_id"><br>
        <label class = "password" for="password"> Password: </label>
        <input class = "password_input" type="password" maxlength = "10" placeholder = "Enter your password" name="password" id = "password" required=""><br>
        <button class="login_button" type="submit" name = "login">Login</button>
        <div><a href="./reset_pwrd.php" class="reset_password">Forgot your password?</a></div>    
        <div class = "text-block2">Don't have an account?</br>
             <a href="./register.php" class="register_button">REGISTER HERE</a>
        </div>    
    </form>
    </div>
</body>
<!--end of html coding-->
</html>

