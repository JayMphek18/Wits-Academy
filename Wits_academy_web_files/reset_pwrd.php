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
    }

    .user_id {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .email {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .password {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .cpassword {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .userID_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .email_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .password_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .cpassword_input{
     padding-left: 12px;
     border-radius: 18px;
   }

   .reset_button{
     border-radius: 18px;
     background-color: #88a2b6;
     font-family: 'Droid Sans', sans-serif;
     font-weight: 700;
     color: white;
   }
   .login_button {
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
        <h1>Reset Password</h1>
    <?php
        if(isset($_POST["reset"])){
            $user_id=$_POST["user_id"];
            $email=$_POST["email"];
            $password=$_POST["password"];
            $confirm_pwrd=$_POST["cpassword"];
            $passwordHash= password_hash($password, PASSWORD_DEFAULT);

            $errors = array();

            if (strlen($user_id)<7){
                array_push($errors, "Invalid user ID!");
            }

            if (strlen($password)<10){
                array_push($errors, "Password must be at least 10 characters long!");
            }
            if($password !== $confirm_pwrd){
                array_push($errors, "Password does not match!");
            }

            require("database.php");
            $sql1= "SELECT * FROM registration WHERE user_id='$user_id' AND email_address='$email'";
            $result1 = mysqli_query($conn, $sql1);
            $rowCount= mysqli_num_rows($result1);
            if($rowCount == 0){
                array_push($errors, "Failed to reset password! Please enter correct user ID/ e-mail address details");
            }

            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }
            }else{
                $sql2 = "UPDATE registration SET password='$password' WHERE user_id='$user_id'";
                $result2=mysqli_query($conn,$sql2);
                echo "<div class='alert alert-success'>Password successfully reset! Please login:)</div>";
                }   
             }
    ?>

    <div>
    <form class="reset_password" action="reset_pwrd.php" method="post" autocomplete="off">
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
