<?php
   if(isset($_POST["login"])){
      $user_id=$_POST["User_number"];
      $password=$_POST["Password"];
      $passwordHash= password_hash($password, PASSWORD_DEFAULT);

      $errors = array();

      require("database.php");

      $sql= "SELECT username, role, password FROM registration WHERE user_id='$user_id' AND password='$password'";
      $result = mysqli_query($conn, $sql);
      $rowCount= mysqli_num_rows($result);
      $row = $result->fetch_assoc();
      $role=$row["role"];
      //$cpassword=$row["password"];

      if($rowCount == 0){
            array_push($errors, "Incorrect user ID or password! Please enter correct details");
      }
      
      if(count($errors)>0){
            foreach($errors as $error){
               echo $error;
            }
      }else{
            //if(strcasecmp($cpassword,$role)==0 && !empty($user_id) && !empty($password)){
            //echo "<div class='alert alert-success'>You are logged in successfully!</div>";
            $_SESSION["user_id"]=$user_id;
            if($role=='teacher'){
            header('location:teacher/index.php');
            }
            else{
            header('location:student/index.php');
            }    
         // }      
      }
   }
?>

