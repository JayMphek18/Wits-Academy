<?php
require("database.php");
if($_SESSION['user_id']){
     //read data entered by user and submit to database
     if(isset($_POST["create"])){
        $date = date('y-m-d h:i:s');
        $subject=$_POST["subject"];
        $announce= $_POST["announcement"];
        $course_id=$_POST["id"];
        $announce_id=$_GET['announce'];
       
        //create array to store the errors
        $errors = array();

        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $sql = "UPDATE announcement SET date_time=? ,topic = ?,announce_text = ? WHERE announce_id=?";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement,"ssss",$date,$subject,$announce,$announce_id);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('Your announcement have been updated!')</script>";
                header( "Refresh:0.01; url=../teacher/viewAnnounce.php?id=$course_id", true, 303);
                
              }else{
                die("Something went wrong :(");

            }
        }
      }
    }
?>