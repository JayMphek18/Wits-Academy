<?php
    if(isset($_POST["register"])){
        $first_name = $_POST["user_firs_name"];
        $last_name = $_POST["user_last_name"];
        $email = $_POST["user_email"];
        $user_role= $_POST["role"];
        $user_id= $_POST["user_id"];
        $user_password = $_POST["password"];
        $confirm_password = $_POST["confirm_password"];
        $passwordHash= password_hash($user_spassword, PASSWORD_DEFAULT);

        $errors = array();

        

        require("database.php");

        $sql1= "SELECT * FROM registration WHERE email_address='$email'";
        $result1 = mysqli_query($conn, $sql1);
        $rowCount1= mysqli_num_rows($result1);
        if($rowCount1>0){
            array_push($errors, "Email already exists!");
        }

        $sql2= "SELECT * FROM registration WHERE user_id='$user_id'";
        $result2 = mysqli_query($conn, $sql2);
        $rowCount2= mysqli_num_rows($result2);
        if($rowCount2>0){
            array_push($errors, "User ID already exists!");
        }

        if (strlen($user_id)<7){
            array_push($errors, "Invalid user ID!");
        }

        if (strlen($user_password)<10){
            array_push($errors, "Password must be at least 10 characters long!");
        }
        if($user_password !== $confirm_password){
            array_push($errors, "Passwords do not match!");
        }
        if(count($errors)>0){
            foreach($errors as $error){
                echo $error;
            }
        }else{
            $sql = "INSERT INTO registration (user_id, first_name, last_name, email_address,role,password ) VALUES ($user_id,$first_name,$last_name,$email,$user_role,$user_password)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "ssssss",$user_id, $first_name, $last_name, $email, $user_role, $user_password);
                mysqli_stmt_execute($statement);
                echo "You are registered successfully! Please log in :) ";
            }else{
                die("Something went wrong :(");
            }
        }
    }
?>

