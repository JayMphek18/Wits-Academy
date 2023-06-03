<?php
    require("database.php");
    
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
                        header("Refresh:0.01; url=../login.php", true, 303);
                      }else{
                        die("Something went wrong :(");
                    }
                }
              }
            
?>