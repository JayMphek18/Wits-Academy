<?php
    if(isset($_POST["reset"])){
        $user_id=$_POST["user_number"];
        $email=$_POST["email"];
        $password=$_POST["new_password"];
        $confirm_pwrd=$_POST["confirm_new_password"];
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
                echo $error;
            }
        }else{
            $sql2 = "UPDATE registration SET password='$password' WHERE user_id='$user_id'";
            $result2=mysqli_query($conn,$sql2);
            echo "Password successfully reset! Please login:)";
            }   
          }
?>

