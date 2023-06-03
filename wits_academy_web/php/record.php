<?php 
    require('database.php');
    require('getQuiz.php');

    if($_SESSION['user_id']){
        if(isset($_POST['submit'])){
            $answer=$_POST['answer'];
            $quesid=$_POST['question'];
            $stud_num=$_POST['studnum'];
    
            $id=$_GET['id'];
            $quiz=$_GET['quiz'];
            $qname=$_GET['quiz_name'];
            $num=$_GET['num'];
    
            $ques=getQuestion($quesid, $conn);
            $correct=$ques['answer_id'];
    
            if($answer === $correct){
                $mark=$ques['ques_mark'];
                $sql="insert into quiz_records (stud_num, quiz_id, ques_id, answer_id, mark) values (?,?,?,?,?)";
                $statement = mysqli_stmt_init($conn);
                $prepare = mysqli_stmt_prepare($statement, $sql);
                if($prepare){
                    mysqli_stmt_bind_param($statement, "sssss",$stud_num, $quiz, $quesid, $answer, $mark);
                    mysqli_stmt_execute($statement);
                    header("Refresh:0.01; url=../student/viewQuestions.php?id=$id&quiz=$quiz&quiz_name=$qname&num=$num", true, 303);
                }else{
                    die("Something went wrong :(");
                }
            }else{
                $mark=0;
                $sql="insert into quiz_records (stud_num, quiz_id, ques_id, answer_id, mark) values (?,?,?,?,?)";
                $statement = mysqli_stmt_init($conn);
                $prepare = mysqli_stmt_prepare($statement, $sql);
                if($prepare){
                    mysqli_stmt_bind_param($statement, "sssss",$stud_num, $quiz, $quesid, $answer, $mark);
                    mysqli_stmt_execute($statement);
                    header("Refresh:0.01; url=../student/viewQuestions.php?id=$id&quiz=$quiz&quiz_name=$qname&num=$num", true, 303);
    
                }else{
                    die("Something went wrong :(");
                }
            }
    
        }
    }
?>

    