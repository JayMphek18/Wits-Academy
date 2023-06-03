<?php 
function getQuiz($code, $name, $db){
    $sql="select * from quizzes where course_id='$code' and quiz_name='$name'";
    $result1=$db->query($sql);
    $rowcount=mysqli_num_rows($result1);
    if(($rowcount)){
        $row=$result1->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}

function getQuizDetails($quizid, $db){
    $sql="select * from quizzes where quiz_id='$quizid'";
    $result1=$db->query($sql);
    $rowcount=mysqli_num_rows($result1);
    if(($rowcount)){
        $row=$result1->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}

function getQuestion($quesid, $db){
    $sql="select * from questions where ques_id='$quesid'";
    $result1=$db->query($sql);
    $rowcount=mysqli_num_rows($result1);
    if(($rowcount)){
        $row=$result1->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}

function getQues($quizid, $qnum, $db){
    $sql="select * from questions where quiz_id='$quizid' and ques_num='$qnum'";
    $result1=$db->query($sql);
    $rowcount=mysqli_num_rows($result1);
    if(($rowcount)){
        $row=$result1->fetch_assoc();
        return $row;
    }else {
        return 0;
    } 
}

function getAnswer($ans, $db){
    $sql="select * from answers where answer_id='$ans'";
    $result=$db->query($sql);
    $rowcount=mysqli_num_rows($result);
    if(($rowcount)){
        $row=$result->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}

function getRecord($stud, $ques, $db){
    $sql="select * from quiz_records where stud_num='$stud' and ques_id='$ques'";
    $result=$db->query($sql);
    $rowcount=mysqli_num_rows($result);
    if(($rowcount)){
        $row=$result->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}
 ?>