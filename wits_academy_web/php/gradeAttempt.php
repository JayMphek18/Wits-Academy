<?php 
    require('database.php');

    function calculateGrade($stud_mark, $t_mark){
        $grade=( $stud_mark / $t_mark ) * 100;
        return $grade;
    }

    if($_SESSION['user_id']){
        $user=$_GET['user'];
        $quiz=$_GET['quiz'];
       
        $code=$_GET['id'];
        $sql2="select * from grades where stud_num='$user' and quiz_id='$quiz'";
        $result2=$conn->query($sql2);
        $rowcount2=mysqli_num_rows($result2);

        if($rowcount2 > 0){
            header("Refresh:0.01; url=../student/quizzes.php?id=$code", true, 303);
        }else{

            //select each question's mark
            $sql="select * from questions where quiz_id='$quiz'";
            $result=$conn->query($sql);
            $rowcount=mysqli_num_rows($result);
            
            $t_mark=0;
            $total=array();
            if($rowcount>0){
                while($ques=$result->fetch_assoc()){
                    $qmark=$ques['ques_mark'];
                    array_push($total, $qmark);
                }
                $t_mark=array_sum($total);
            }

            //select all the student's mark for each question
            $sql1="select * from quiz_records where quiz_id='$quiz' and stud_num='$user'";
            $result1=$conn->query($sql1);
            $rowcount1=mysqli_num_rows($result1);

            $stud_marks=0;
            $total_stud=array();
            if($rowcount1>0){
                while($records=$result1->fetch_assoc()){
                    $stud_mark=$records['mark'];
                    array_push($total_stud, $stud_mark);
                }
                $stud_marks=array_sum($total_stud);
            }

            $grade=calculateGrade($stud_marks, $t_mark);
            $sql2="insert into grades (stud_num, quiz_id, grades) values (?,?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql2);
            if($prepare){
                mysqli_stmt_bind_param($statement,"sss",$user, $quiz, $grade);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('Attempt submitted!')</script>";
                header("Refresh:0.01; url=../student/quizzes.php?id=$code", true, 303);
            }else{
                die("Something went wrong :(");
            }
        }        
    }
?>