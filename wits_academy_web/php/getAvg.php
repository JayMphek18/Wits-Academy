<?php 
    require('database.php');
    function calculateGrade($stud_mark, $t_mark){
        $grade=( $stud_mark / $t_mark ) * 100;
        return $grade;
    }

    //get the class average
    function classAvg($quizid, $courseid, $conn){
        $sql1 = " select * from grades where quiz_id='$quizid'";
        $result1=$conn->query($sql1);
        $rowcount1=mysqli_num_rows($result1);

        //get the grades of all answered attempts
        $marks=0;
        if ($rowcount1 > 0){
            while($row=$result1->fetch_assoc()){
                $marks = $marks + $row['grades'];
            }
        }

        //get the number of students enrolled in the course
        $sql2="select * from enrollment where course_id='$courseid'";
        $result2=$conn->query($sql2);
        $rowcount2=mysqli_num_rows($result2);

        $grandtotal=100;
        if($rowcount2 > 0){
           $grandtotal=$grandtotal * $rowcount2;
        }

        //calculate average
        $average=calculateGrade(floatval($marks), $grandtotal);
        return $average;
    }

    //get the pass average
    function passAvg($quizid, $courseid, $conn){
        $sql1 = " select * from grades where quiz_id='$quizid'";
        $result1=$conn->query($sql1);
        $rowcount1=mysqli_num_rows($result1);

        //get the grades of all answered attempts
        $marks=0;
        if ($rowcount1 > 0){
            while($row=$result1->fetch_assoc()){
                if($row['grades'] >= 50){
                    $marks = $marks + $row['grades'];
                }
            }
        }
    
            //get the number of students enrolled in the course
            $sql2="select * from enrollment where course_id='$courseid'";
            $result2=$conn->query($sql2);
            $rowcount2=mysqli_num_rows($result2);
    
            $grandtotal=100;
            if($rowcount2 > 0){
               $grandtotal=$grandtotal * $rowcount2;
            }
    
            //calculate average
            $average=calculateGrade(floatval($marks), $grandtotal);
            return $average;
    }

    //get the fail average
    function failAvg($quizid, $courseid, $conn){
        $sql1 = " select * from grades where quiz_id='$quizid'";
        $result1=$conn->query($sql1);
        $rowcount1=mysqli_num_rows($result1);

        //get the grades of all answered attempts
        $marks=0;
        if ($rowcount1 > 0){
            while($row=$result1->fetch_assoc()){
                if($row['grades'] < 50){
                    $marks = $marks + $row['grades'];
                }
            }
        }

        //get the number of students enrolled in the course
        $sql2="select * from enrollment where course_id='$courseid'";
        $result2=$conn->query($sql2);
        $rowcount2=mysqli_num_rows($result2);

        $grandtotal=100;
        if($rowcount2 > 0){
           $grandtotal=$grandtotal * $rowcount2;
        }

        //calculate average
        $average=calculateGrade(floatval($marks), $grandtotal);
        return $average;
    }
?>