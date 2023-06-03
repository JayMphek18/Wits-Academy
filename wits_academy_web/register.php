<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link type="text/css" href="./css/register.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<!--html coding for the layout of the page; find php coding towards the end of this document -->
<body class="body">
<div class="container">
    <img src="./Wits_Logo.png" loading="lazy" width="259" height="150" alt="Wits university emblem" />
    <h1 class="head1">Registration</h1>
    <div>
    <form class="register" action="./php/register.php" method="post" autocomplete="off">
        <label class = "f_name" for="f_name"> First Name: </label>
        <input class = "fname_input" type="text" maxlength = "128" placeholder = "Enter your first name" name="f_name" id = "f_name" required=""><br>
        <label class = "l_name" for="l_name"> Last Name: </label>
        <input class = "lname_input"type="text" maxlength = "128" placeholder = "Enter your last name" name="l_name" id = "l_name" required=""><br>
        <label class = "email" for="email"> E-mail Address: </label>
        <input class = "email_input" type="email" maxlength = "128" placeholder = "Enter your e-mail address" name="email" id = "email" required=""><br>
        <label class = "roles" for="roles"> Choose a role: </label>
        <select class="select" name="roles" id = "roles">
            <option value="student">Student</option>
            <option value="teacher">Teacher</option>
        </select><br>
        <label class = "user_id" for="user_id"> User Number: </label>
        <input class = "userID_input"type="text" maxlength = "7" placeholder = "Enter your user number" name="user_id" id = "user_id"><br>
        <div class="text-block1">Enter your student number/ employee number as your user ID.</div>
        <label class = "password" for="password"> Password: </label>
        <input class = "password_input" type="password" maxlength = "10" placeholder = "Enter your passowrd" name="password" id = "password" required=""><br>
        <label class = "cpassword" for="cpassword"> Confirm password: </label>
        <input class = "cpassword_input"type="password" maxlength = "10" placeholder = "Confirm your password" name="cpassword" id = "cpassowrd" required=""><br>
        <button class="register_button" type="submit" name = "register">Register</button>
        <div class = "text-block2">Already have an account?</br>
        <a href="./login.php" class="login_button">LOGIN</a>
    </div>    
    </form></div>
    
</body>
<!--end of html coding-->
</html>

