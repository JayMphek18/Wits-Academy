<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link type="text/css" href="./register.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<!--html coding for the layout of the page; find php coding towards the end of this document -->
<body class="body">
<div class="container">
    <img src="./Wits_Logo.png" loading="lazy" width="259" height="150" alt="Wits university emblem" />
    <h1 class="head1">Registration</h1>
    <div>
    <form class="register" action="register.php" method="post" autocomplete="off">
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
        <button class="register_button" type="submit" name = "register"><a href="./login.php" class="login_button">Register</a></button>
        <div class = "text-block2">Already have an account?</br>
        <a href="./login.php" class="login_button">LOGIN</a>
    </div>    
    </form></div>
    
</body>
<!--end of html coding-->
</html>

<?php
            //read data entered by user and submit to database
            if(isset($_POST["register"])){
                $first_name = $_POST["f_name"];
                $last_name = $_POST["l_name"];
                $email = $_POST["email"];
                $role= $_POST["roles"];
                $user_id= $_POST["user_id"];
                $password = $_POST["password"];
                $confirm_password = $_POST["cpassword"];
                $passwordHash= password_hash($password, PASSWORD_DEFAULT);//protect password
                //create array to store the errors
                $errors = array();
                //if the user id is not entered, append the error "User ID (student number/ employee number) is required!"
                if(empty($user_id)){
                    array_push($errors, "User ID (student number/ employee number) is required!");
                }
                //connect to the database once all entries are complete
                require("database.php");
                //check if the user id or email address exists
                $sql1= "SELECT * FROM registration WHERE email_address='$email'";
                $result1 = mysqli_query($conn, $sql1);
                $rowCount1= mysqli_num_rows($result1);
                //if there already exist the user id and/or email address, append the errors: "Email already exists!" and/or "User ID already exists!" 
                if($rowCount1>0){
                    array_push($errors, "Email already exists!");
                }

                $sql2= "SELECT * FROM registration WHERE user_id='$user_id'";
                $result2 = mysqli_query($conn, $sql2);
                $rowCount2= mysqli_num_rows($result2);
                if($rowCount2>0){
                    array_push($errors, "User ID already exists!");
                }
                //if the user enters a user id of less than 7 characters, and/ or a password of less than 10 characters, append the error: "Invalid user ID!",
                // and/ or, "Password must be at least 10 characters long!"
                if (strlen($user_id)<7){
                    array_push($errors, "Invalid user ID!");
                }

                if (strlen($password)<10){
                    array_push($errors, "Password must be at least 10 characters long!");
                }
                if($password !== $confirm_password){
                    array_push($errors, "Password does not match!");
                }
                //if the length of the error's array is greater than 0, display all the errors on the page
                //, otherwise insert all the entries into the registration table on the database
                if(count($errors)>0){
                    foreach($errors as $error){
                        echo"<div class='alert alert-danger'>$error</div>";
                    }
                }else{
                    $sql = "INSERT INTO registration (user_id, first_name, last_name, email_address, role, password) VALUES (?,?,?,?,?,?)";
                    $statement = mysqli_stmt_init($conn);
                    $prepare = mysqli_stmt_prepare($statement, $sql);
                    if($prepare){
                        mysqli_stmt_bind_param($statement, "ssssss",$user_id, $first_name, $last_name, $email, $role, $password);
                        mysqli_stmt_execute($statement);
                        echo "<script type='text/javascript'>alert('You are registered successfully! Please log in :)')</script>";
                        //header('location:../login.php');
                      }else{
                        die("Something went wrong :(");
                    }
                }
              }
?>