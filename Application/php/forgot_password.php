<?php
    if(isset($_POST)){
        $emp_stud_num=$_POST["user_number"];
        $email_address=$_POST["email"];
        $user_password=$_POST["password"];
        $confirm_pwrd=$_POST["password"];
        $passwordHash= password_hash($user_password, PASSWORD_DEFAULT);

        $errors = array();

        if (strlen($emp_stud_num)<7){
            array_push($errors, "Invalid user ID!");
        }

        if (strlen($user_password)<6){
            array_push($errors, "Password must be at least 6 characters long!");
        }
        if($user_password !== $confirm_pwrd){
            array_push($errors, "Password does not match!");
        }

        require("database.php");
        $sql1= "SELECT * FROM registration WHERE emp_stud_num='$emp_stud_num' AND email_address='$email_address'";
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
            $sql2 = "UPDATE registration SET user_password='$user_password' WHERE emp_stud_num='$emp_stud_num'";
            $result2=mysqli_query($conn,$sql2);
            echo "Password successfully reset! Please login:)";
            }   
        }
    else{
        echo "Something went Wrong";
    }
?>

