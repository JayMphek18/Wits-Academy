<?php
        if(isset($_POST)){
        $first_name = $_POST["name"];
        $last_name = $_POST["surname"];
        $email_address = $_POST["email"];
        $user_role= $_POST["role"];
        $emp_stud_num= $_POST["user_number"];
        $user_password = $_POST["password"];
        $confirm_password = $_POST["confirm_password"];
        $passwordHash= password_hash($user_password, PASSWORD_DEFAULT);
        
        echo $_POST["name"];
        
        
        $errors = array();
        
        require("database.php");

        $sql1= "SELECT * FROM registration WHERE email_address='$email_address'";
        $result1 = mysqli_query($conn, $sql1);
        $rowCount1= mysqli_num_rows($result1);
        if($rowCount1>0){
            array_push($errors, "Email already exists!");
        }

        $sql2= "SELECT * FROM registration WHERE emp_stud_num='$emp_stud_num'";
        $result2 = mysqli_query($conn, $sql2);
        $rowCount2= mysqli_num_rows($result2);
        if($rowCount2>0){
            array_push($errors, "User ID already exists!");
        }

        if (strlen($user_password)<6){
            array_push($errors, "Password must be at least 6 characters long!");
        }
        if($user_password !== $confirm_password){
            array_push($errors, "Passwords do not match!");
        }
        if(count($errors)>0){
            foreach($errors as $error){
                echo $error;
            }
        }else{
            $sql = "INSERT INTO registration (first_name, last_name, email_address,emp_stud_num,user_role,user_password) VALUES ('$first_name','$last_name','$email_address','$emp_stud_num', '$user_role', '$user_password')";
            if(mysqli_query($conn,$sql) ==1){
                echo "You are successfully registered, Please log in.";
            }else{
                die("Something went wrong :(");
            }
        }
    }    
?>

