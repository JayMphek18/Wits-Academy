<?php
require("database.php");
if($_SESSION['user_id']){
     //read data entered by user and submit to database
     if(isset($_POST["create"])){
        $date = date('y-m-d h:i:s');
        $subject=$_POST["subject"];
        $announce= $_POST["announcement"];
        $course_id=$_POST["id"];
       
        //create array to store the errors
        $errors = array();

        //if there is no announcement text
        if(empty($announce)){
            array_push($errors, "Please insert an announcement.");
        }
       
        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $sql = "INSERT INTO announcement (course_id,date_time,topic,announce_text) VALUES (?,?,?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement,"ssss",$course_id,$date,$subject,$announce);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('Your announcement have been sent!')</script>";
                header( "Refresh:0.01; url=../teacher/announcement.php?id=$course_id", true, 303);
                
              }else{
                die("Something went wrong :(");

            }
        }
      }
    }
?>