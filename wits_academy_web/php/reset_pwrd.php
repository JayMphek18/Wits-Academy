<?php
    require('database.php');
        //read the entries of the user; if the entries are correct, change the password of the user to 
        //the new password
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
                echo "<script type='text/javascript'>alert('You have successfully reset your password! Please log in :)')</script>";
                header("Refresh:0.01; url=../login.php", true, 303);
                }
             }
            
    ?>