<?php
require("database.php");
     //read data entered by user and submit to database
     if($_SESSION['user_id']){
     if(isset($_POST["create"])){
        $user=$_SESSION['user_id'];
        $rate = $_POST['rate'];
        $feedback= $_POST["feedback"];
        $course_id=$_POST["id"];
       
        //create array to store the errors
        $errors = array();

        //if there is no feedback text
        if(empty($feedback)){
            array_push($errors, "Please insert feedback.");
        }
       
        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $sql = "INSERT INTO feedback (user, course_id,course_rate,feedback_text) VALUES (?,?,?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement,"ssss",$user,$course_id,$rate,$feedback);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('Thank you for your review! Your feedback have been sent.')</script>";
                header( "Refresh:0.01; url=../student/feedback.php?id=$course_id", true, 303);
                
              }else{
                die("Something went wrong :(");

            }
        }
      }
    }
?>