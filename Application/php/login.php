<?php
      $emp_stud_num=$_POST["user_number"];
      $user_password=$_POST["password"];
      $passwordHash= password_hash($user_password, PASSWORD_DEFAULT);

      $errors = array();
      require_once("database.php");

      $sql= "SELECT first_name, last_name, email_address,user_password,user_role FROM registration WHERE emp_stud_num='$emp_stud_num' AND user_password='$user_password'";
      $result = mysqli_query($conn, $sql);
      $rowCount= mysqli_num_rows($result);
      $row = $result->fetch_assoc();
      $results = array();
      $results["login"] = array();

      $user_role = $row["user_role"];
      //$cpassword=$row["password"];

      if($rowCount == 0){
            array_push($errors, "Incorrect user ID or password! Please enter correct details");
      }
      
      if(count($errors)>0){
            foreach($errors as $error){
               echo $error;
            }
      }else{
            $index["user_role"] = $row["user_role"];
            $index["userNumber"] = $emp_stud_num;
            array_push($results['login'], $index);
            //if(strcasecmp($cpassword,$role)==0 && !empty($user_id) && !empty($password)){
            //echo "<div class='alert alert-success'>You are logged in successfully!</div>";
            // if($role=='teacher'){
            // header('location:teacher/index.php');
            // }
            // else{
            // header('location:student/index.php');
            echo ($results);
            mysqli_close($conn);

            }    
         // }      
      
   
?>

