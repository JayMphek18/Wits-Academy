<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        WITS Academy
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <!--styling of the registration web page; each structure of the body is given a class name which then assigns a style to it-->
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
        .register{
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

   .f_name {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .l_name {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .email {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }
   .roles {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
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
   .cpassword {
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-weight: 700;
   }

   .fname_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .lname_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .email_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .userID_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .text-block1{
     font-family: 'Droid Sans', sans-serif;
     color: #c1c9cf;
     font-size: 11px;
   }
   .password_input{
     padding-left: 12px;
     border-radius: 18px;
   }
   .cpassword_input{
     padding-left: 12px;
     border-radius: 18px;
   }

   select{
     padding-left: 12px;
     border-radius: 18px;
   }
   .register_button{
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
    <h1>Registration</h1>
    <?php
            //reading the input of the user
            if(isset($_POST["register"])){
                $first_name = $_POST["f_name"];
                $last_name = $_POST["l_name"];
                $email = $_POST["email"];
                $role= $_POST["roles"];
                $user_id= $_POST["user_id"];
                $password = $_POST["password"];
                $confirm_password = $_POST["cpassword"];
                $passwordHash= password_hash($password, PASSWORD_DEFAULT);
                // creating an array to store the error messages
                $errors = array();
                // if user does not enter a user id, append the error "User ID (student number/ employee number) is required!"
                if(empty($user_id)){
                    array_push($errors, "User ID (student number/ employee number) is required!");
                }
                //create the connection to the database
                require("database.php");
                //from the database, check if the email address exists in the registration table; if yes, append the error "Email already exists!"
                $sql1= "SELECT * FROM registration WHERE email_address='$email'";
                $result1 = mysqli_query($conn, $sql1);
                $rowCount1= mysqli_num_rows($result1);
                if($rowCount1>0){
                    array_push($errors, "Email already exists!");
                }
                //from the database, check if the user id exists in the registration table; if yes, append the error "User ID already exists!"
                $sql2= "SELECT * FROM registration WHERE user_id='$user_id'";
                $result2 = mysqli_query($conn, $sql2);
                $rowCount2= mysqli_num_rows($result2);
                if($rowCount2>0){
                    array_push($errors, "User ID already exists!");
                }
                //if the user id has less than 7 characters, append the error  "Invalid user ID!"
                if (strlen($user_id)<7){
                    array_push($errors, "Invalid user ID!");
                }
                 //if the password has less than 10 characters, append the error "Password must be at least 10 characters long!"
                if (strlen($password)<10){
                    array_push($errors, "Password must be at least 10 characters long!");
                }
                // if the password entered on the Confirm Password field is not equal to the one on Password field, append the error "Password does not match!"
                if($password !== $confirm_password){
                    array_push($errors, "Password does not match!");
                }
                //if the length of the error array is greater than 0, display the errors on the screen
                if(count($errors)>0){
                    foreach($errors as $error){
                        echo"<div class='alert alert-danger'>$error</div>";
                    }
                }
                //else, record all the data entered by the user into the registration table on the database
                else{
                    $sql = "INSERT INTO registration (user_id, first_name, last_name, email_address, role, password) VALUES (?,?,?,?,?,?)";
                    $statement = mysqli_stmt_init($conn);
                    $prepare = mysqli_stmt_prepare($statement, $sql);
                    if($prepare){
                        mysqli_stmt_bind_param($statement, "ssssss",$user_id, $first_name, $last_name, $email, $role, $password);
                        mysqli_stmt_execute($statement);
                        echo "<div class='alert alert-success'>You are registered successfully! Please log in :)</div>";
                    }
                    //if the data isn't recorded, display the error meesage "Something went wrong :("
                    else{
                        die("Something went wrong :(");
                    }
                }
            }
        ?>
    <div>
    <!-- creating the form; all fields on the form are required-->
    <form class="register" action="register.php" method="post" autocomplete="off">
        <label class = "f_name" for="f_name"> First Name: </label>
        <input class = "fname_input" type="text" maxlength = "128" placeholder = "Enter your first name" name="f_name" id = "f_name" required=""><br>
        <label class = "l_name" for="l_name"> Last Name: </label>
        <input class = "lname_input"type="text" maxlength = "128" placeholder = "Enter your last name" name="l_name" id = "l_name" required=""><br>
        <label class = "email" for="email"> E-mail Address: </label>
        <input class = "email_input" type="email" maxlength = "128" placeholder = "Enter your e-mail address" name="email" id = "email" required=""><br>
        <label class = "roles" for="roles"> Choose a role: </label>
        <select name="roles" id = "roles">
            <option value="student">Student</option>
            <option value="teacher">Teacher</option>
        </select><br>
        <label class = "user_id" for="user_id"> User Number: </label>
        <input class = "userID_input"type="text" maxlength = "7" placeholder = "Enter your user number" name="user_id" id = "user_id"><br><!-- user id is restricted to 7 characters maximum-->
        <div class="text-block1">Enter your student number/ employee number as your user ID.</div>
        <label class = "password" for="password"> Password: </label>
        <input class = "password_input" type="password" maxlength = "10" placeholder = "Enter your passowrd" name="password" id = "password" required=""><br><!-- password is restricted to 10 characters maximum-->
        <label class = "cpassword" for="cpassword"> Confirm password: </label>
        <input class = "cpassword_input"type="password" maxlength = "10" placeholder = "Confirm your password" name="cpassword" id = "cpassowrd" required=""><br>
        <button class="register_button" type="submit" name = "register">Register</button>
        <div class = "text-block2">Already have an account? <a href="./login.php" class="login_button">LOGIN</a>
    </div>    
    </form></div>
    
</body>
</html>
