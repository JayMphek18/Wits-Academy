<?php 
    require('database.php');
    require('getQuiz.php');

    if($_SESSION['user_id']){
        
        $stud_num=$_SESSION['user_id'];

        $id=$_GET['id'];
        $quiz=$_GET['quiz'];
        $qname=$_GET['quiz_name'];
        $num=$_GET['num'];

        $check="select * from attempts where stud_num='$stud_num' and quiz_id='$quiz'";
        $results=$conn->query($check);
        if(mysqli_num_rows($results) > 0){
            echo "<script type='text/javascript'>alert('Attempt already submitted!')</script>";
            header("Refresh:0.01; url=../student/quizzes.php?id=$id", true, 303);
        }else{
            $sql="insert into attempts (stud_num, quiz_id) values (?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "ss",$stud_num, $quiz);
                mysqli_stmt_execute($statement);
                header("Refresh:0.01; url=../student/viewQuestions.php?id=$id&quiz=$quiz&quiz_name=$qname&num=$num", true, 303);
            }else{
                die("Something went wrong :(");
            }
        }
    }
?>