<?php
    require("database.php");
    if($_SESSION['user_id']){
    if(isset($_POST['submit']))
    {
        $id=$_POST["code"];
        $name=$_POST['quiz_name'];
        $num=$_POST['ques_num'];
        $date = date('y-m-d h:i:s');

        $sql = "INSERT INTO quizzes (quiz_name, course_id, ques_num, date_time) VALUES (?,?,?,?)";
        $statement = mysqli_stmt_init($conn);
        $prepare = mysqli_stmt_prepare($statement, $sql);
        if($prepare){
            mysqli_stmt_bind_param($statement, "ssss",$name,$id, $num,$date);
            mysqli_stmt_execute($statement);
            echo "<script type='text/javascript'>alert('$name created')</script>";
            header("Refresh:0.01; url=../teacher/view_questions.php?id=$id&quiz_name=$name&num=$num", true, 303);
            }else{
            die("Something went wrong :(");
        }
        }
    }
            ?>