<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link type="text/css" href="./login.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<!--html coding for layout of the page -->
<body class="body">
    <div class="container">
        <img src="./Wits_Logo.png" loading="lazy" width="259" height="150" alt="Wits university emblem" />
        <h1 class="head1">Welcome back!</h1>

    <div>
    <form class="login" action="login.php" method="post" autocomplete="off">
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

<?php
        //read the entries of the user and check if they exist on the database
        if(isset($_POST["login"])){
            $user_id=$_POST["user_id"];
            $password=$_POST["password"];
            $passwordHash= password_hash($password, PASSWORD_DEFAULT);

            //create error array to store arrays
            $errors = array();

            //connect to the database
            require("database.php");

            //check if the user id and password exist in the registration table
            $sql= "SELECT user_id, role, password FROM registration WHERE user_id='$user_id' AND password='$password'";
            $result = mysqli_query($conn, $sql);
            $rowCount= mysqli_num_rows($result);
            $row = $result->fetch_assoc();//create a list of all the entries of this user
            $role=$row["role"];//assign the $role variable to the value in the 'role' position

            //if the user id does not exist, or if the password entered is incorrect,
            // append the error "Incorrect user ID or password! Please enter correct details" and
            //display the script
            if($rowCount == 0){
                array_push($errors, "Incorrect user ID or password! Please enter correct details");
                echo "<script type='text/javascript'>alert('Failed to Login!')</script>";
            }
            //if length of error's array is greater than 0, display all the errors on the page,
            //else log the user in to the correct home page based on his/ her role
            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }
            }else{
                $_SESSION["user_id"]=$user_id;
                if($role=='teacher'){
                  header('location:teacher/index.php');
                }
                else{
                  header('location:student/index.php');
                }    
                //}      
            }
        }
?>