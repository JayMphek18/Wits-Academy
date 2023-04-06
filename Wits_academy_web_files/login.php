<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
         body {
            background-color: #00255c;
        }

        h1 {
            font-family: 'Droid Sans', sans-serif;
            color: #c1c9cf;
            font-weight: 700;
         }

        .container{
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        max-height: 500px;
        max-width: 1002px;
        min-height: 500px;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        -webkit-box-pack: start;
        -webkit-justify-content: flex-start;
        -ms-flex-pack: start;
        justify-content: flex-start;
        -webkit-box-align: center;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
        }
    .login{
     display: -webkit-box;
     display: -webkit-flex;
     display: -ms-flexbox;
     display: flex;
     -webkit-box-orient: vertical;
     -webkit-box-direction: normal;
     -webkit-flex-direction: column;
     -ms-flex-direction: column;
     flex-direction: column;
     -webkit-box-align: stretch;
     -webkit-align-items: stretch;
     -ms-flex-align: stretch;
     align-items: stretch;
   }

   .user_id {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .password {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .userID_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .password_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .reset_password{
     display: -webkit-box;
     display: -webkit-flex;
     display: -ms-flexbox;
     display: flex;
     -webkit-box-orient: vertical;
     -webkit-box-direction: normal;
     -webkit-flex-direction: column;
     -ms-flex-direction: column;
     flex-direction: column;
     -webkit-box-align: stretch;
     -webkit-align-items: stretch;
     -ms-flex-align: stretch;
     align-items: stretch;
     border-radius: 18px;
     font-family: 'Droid Sans', sans-serif;
     font-size: 14;
     font-weight: 700;
     text-align: center;
     padding-left: 12px;
     color: white;
   }
  
    .login_button{
     border-radius: 18px;
     background-color: #88a2b6;
     font-family: 'Droid Sans', sans-serif;
     font-weight: 700;
     color: white;
   }
   .text-block2{
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-size: 14px;
     text-align: center;
   }
   .register_button {
     display: -webkit-box;
     display: -webkit-flex;
     display: -ms-flexbox;
     display: flex;
     -webkit-box-orient: vertical;
     -webkit-box-direction: normal;
     -webkit-flex-direction: column;
     -ms-flex-direction: column;
     flex-direction: column;
     -webkit-box-align: stretch;
     -webkit-align-items: stretch;
     -ms-flex-align: stretch;
     align-items: stretch;
     border-radius: 18px;
     font-family: 'Droid Sans', sans-serif;
     font-size: 14;
     font-weight: 700;
     text-align: center;
     padding-left: 12px;
     color: white;
   }
    </style>
</head>
<body>
    <div class="container">
        <img src="./Wits_Logo.png" loading="lazy" width="259" height="150" alt="Wits university emblem" />
        <h1>Welcome back!</h1>
    <?php
        //reading the input entered by the user
        if(isset($_POST["login"])){
            $user_id=$_POST["user_id"];
            $password=$_POST["password"];
            $passwordHash= password_hash($password, PASSWORD_DEFAULT);
            //create array to store error messages
            $errors = array();
            //create connection to database
            require("database.php");
            //select a row from the registration table where the user id and email exist ON THE SAME ROW
            $sql= "SELECT user_id, role, password FROM registration WHERE user_id='$user_id' AND password='$password'";
            $result = mysqli_query($conn, $sql);
            $rowCount= mysqli_num_rows($result);
            //create a list of the entries on this row; each element is iterated according to the attribute name on the table
            $row = $result->fetch_assoc();
            //fetch the role of the user
            $role=$row["role"];
            //$cpassword=$row["password"];
            //if the user id does not exist or is incorrect, append the error "Incorrect user ID or password! Please enter correct details"
            if($rowCount == 0){
                array_push($errors, "Incorrect user ID or password! Please enter correct details");
            }
            //if the length of the error's array is greater than 0, display all the errors onto the screen
            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }
            }
            //else, log the user into the correct home page based on the role he/ she is registered in
            else{
      
                $_SESSION["user_id"]=$user_id;
                if($role=='teacher'){
                  header('location:teacher/index.php');
                }
                else{
                  header('location:student/index.php');
                }    
                     
            }
        }
    ?>

    <div>
        <!--creating the form; all fields are required for user to log in-->
    <form class="login" action="login.php" method="post" autocomplete="off">
        <label class = "user_id" for="user_id"> User Number: </label>
        <input class = "userID_input"type="text" maxlength = "7" placeholder = "Enter your user number" name="user_id" id = "user_id"><br>
        <label class = "password" for="password"> Password: </label>
        <input class = "password_input" type="password" maxlength = "10" placeholder = "Enter your passowrd" name="password" id = "password" required=""><br>
        <button class="login_button" type="submit" name = "login">Login</button>
        <div><a href="./reset_pwrd.php" class="reset_password">Forgot your password?</a>
    </div>    
        <div class = "text-block2">Don't have an account? <a href="./register.php" class="register_button">REGISTER HERE</a>
    </div>    
    </form></div>
</body>
</html>
