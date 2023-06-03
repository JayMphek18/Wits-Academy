<?php
require("database.php");
if($_SESSION['user_id']){
if(isset ($_POST["enroll"])){
    $course_id=$_POST["course_id"];
    $password=$_POST["course_password"];
    $user_id=$_SESSION['user_id'];
    $sql1="SELECT * FROM courses WHERE course_id='$course_id'";
    $result1=mysqli_query($conn, $sql1);
    $row1=$result1->fetch_assoc();

    $teacher=$row1["teacher"];
    $sql4 ="SELECT * FROM registration WHERE user_id='$teacher'";
    $result4=mysqli_query($conn, $sql4);
    $row4=$result4->fetch_assoc();
    $email=$row4['email_address'];

    $errors=array();

    //checking if the student is already enrolled
    $sql2="SELECT * FROM enrollment WHERE student_num='$user_id' AND course_id='$course_id'";
    $result2=mysqli_query($conn, $sql2);
    $rowCount=mysqli_num_rows($result2);
    if($rowCount>0){
        array_push($errors, "Already enrolled into course!");
    }

    $sql3="SELECT * FROM courses WHERE course_id='$course_id'";
    $result3=mysqli_query($conn, $sql3);
    $row3=$result3->fetch_assoc();
    $pass=$row3["password"];
    if($pass !== $password){
        array_push($errors, "Incorrect Password! Please request the correct password from $email.");
    }
    //insert into enrollment table
    if(count($errors)>0){
        foreach($errors as $error){
            echo"<br><br><div class='alert alert-danger'>$error</div>";
        }
    }else{
        $sql = "INSERT INTO enrollment (student_num, course_id) VALUES (?,?)";
        $statement = mysqli_stmt_init($conn);
        $prepare = mysqli_stmt_prepare($statement, $sql);
        if($prepare){
            mysqli_stmt_bind_param($statement, "ss",$user_id, $course_id);
            mysqli_stmt_execute($statement);
            echo "<script type='text/javascript'>alert('You have successfully enrolled into the course!')</script>";
            header( "Refresh:0.01; url=../student/index.php", true, 303);
        }else{
            die("Something went wrong :(");
        }
    } 
    }
}
?>