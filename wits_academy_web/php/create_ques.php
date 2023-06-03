<?php 
require("database.php");
if($_SESSION['user_id']){
    if(isset($_POST['submit'])){
        $qnum=$_POST['qnum'];
        $quizid=$_POST['quizid'];
        $ques=$_POST['question'];
        $num=$_POST['num'];
        $code=$_POST['course_id'];
        $name=$_POST['name'];
        $mark=$_POST['mark'];

        $sql="INSERT INTO questions(ques_num, quiz_id, ques_mark, question) VALUES (?,?,?,?)";
        $statement = mysqli_stmt_init($conn);
        $prepare = mysqli_stmt_prepare($statement, $sql);
        if($prepare){
            mysqli_stmt_bind_param($statement, "ssss",$qnum,$quizid,$mark,$ques);
            mysqli_stmt_execute($statement);
            echo "<script type='text/javascript'>alert('Question created')</script>";
            $sql="SELECT * FROM questions WHERE quiz_id='$quizid' and ques_num='$qnum'";
            $result1=$conn->query($sql);
            $row=$result1->fetch_assoc();
            $ques_id=$row['ques_id'];
            header("Refresh:0.01; url=../teacher/create_answer.php?id=$code&quiz_id=$quizid&q=$ques_id&num=$num&qname=$name", true, 303);
        }else{
            die("Something went wrong :(");
        }
    }
}
?>