<?php 
require("database.php");
if($_SESSION['user_id']){
    if(isset($_POST['submit'])){
        $qnum=$_POST['ques_id'];
        $answers=array();
        $v1=$_POST['value_1'];
        array_push($answers, $v1);
        $v2=$_POST['value_2'];
        array_push($answers, $v2);
        $v3=$_POST['value_3'];
        array_push($answers, $v3);
        $v4=$_POST['value_4'];
        array_push($answers, $v4);
        $correct=$_POST['answer'];

       //insert all the answer values into the database
        foreach($answers as $value){
            $sql="UPDATE answers SET answer_text=? WHERE ques_id=?";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "ss",$qnum,$value);
                mysqli_stmt_execute($statement);
            }
            else{
                die("Something went wrong :(");
            }
        }
        //insert the answer id of the correct answer into the questions table for the specific question
        $id=$_POST['code'];
        $name=$_POST['qname'];
        $num=$_POST['qnum'];
        if($answers[$correct]){
            $sql1="SELECT * FROM answers WHERE ques_id='$qnum' AND answer_text='$value'";
            $result1 = mysqli_query($conn, $sql1);
            $row=$result1->fetch_assoc();
            $answer_id=$row['answer_id'];
            echo $answer_id;
            $sql="UPDATE questions SET answer_id=? WHERE ques_id=?";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "ss",$answer_id,$qnum);
                mysqli_stmt_execute($statement);
                header("Refresh:0.01; url=../teacher/view_questions.php?id=$id&quiz_name=$name&num=$num", true, 303);
            }
            else{
                die("Something went wrong :(");
            } 
        }
    }
}
?>