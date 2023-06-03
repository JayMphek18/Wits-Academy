<?php 
function getQues($ques, $db){
    $sql="select * from qna_question where question_id='$ques'";
    $result1=$db->query($sql);
    $rowcount=mysqli_num_rows($result1);
    if(($rowcount)){
        $row=$result1->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}
?>