<?php
    require("database.php");
   
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
            }
            //if length of error's array is greater than 0, display all the errors on the page,
            //else log the user in to the correct home page based on his/ her role
            if(count($errors)>0){
                foreach($errors as $error){
                    echo "<script type='text/javascript'>alert('Failed to Login! $error')</script>";
                }
                header( "Refresh:0.01; url=../login.php", true, 303);
            }else{
                $_SESSION["user_id"]=$user_id;
                if($role=='teacher'){
                  header('location:../teacher/index.php');
                }
                else{
                  header('location:../student/index.php');
                }    
                //}      
            }
        }
    
?>