<?php 
require("database.php");

if($_SESSION['user_id']){
    $user_id=$_SESSION['user_id'];
    $course=$_GET['id'];
    if(isset($_POST['unenroll'])){
        $answer=$_POST['answer']; 
        $sql="select * from enrollment where student_num='$user_id' and course_id='$course'";
        $result=$conn->query($sql);
        if(mysqli_num_rows($result) > 0 && $answer=='yes'){
            $unenroll="delete from enrollment where student_num='$user_id' and course_id='$course'";
            if (mysqli_query($conn, $unenroll)) {
                $deleteGrade="delete from grades where stud_num='$user_id'";
                if (mysqli_query($conn, $deleteGrade)) {
                    echo "<script type='text/javascript'>alert('Un-enrolled from course successfully')</script>";
                    header( "Refresh:0.01; url=../student/courses.php", true, 303);
                }else{
                    die("Something went wrong :(");
                }
            }
        }else{
            echo "<script type='text/javascript'>alert('Failed to un-enroll from course.Please try again later')</script>";
            header( "Refresh:0.01; url=../student/courses.php", true, 303);
        }
    }
}
?>