<?php 
require('database.php');

if($_SESSION['user_id']){
    $user_id=$_SESSION['user_id'];
    $course=$_GET['id'];
    if(isset($_POST['delete'])){
        $answer=$_POST['answer']; 
        $sql="select * from courses where teacher='$user_id' and course_id='$course'";
        $result=$conn->query($sql);

        if(mysqli_num_rows($result) > 0 && $answer=='yes'){
            $deletecourse="delete from courses where teacher='$user_id' and course_id='$course'";
            if (mysqli_query($conn, $deletecourse)) {
                $deletematerial="delete from upload where course_id='$course'";
                if (mysqli_query($conn, $deletematerial)) {
                echo "<script type='text/javascript'>alert('Deleted course successfully')</script>";
                header( "Refresh:0.01; url=../teacher/index.php", true, 303);
                }else{
                    die("Something went wrong :(");
                }
        }}else{
            echo "<script type='text/javascript'>alert('Failed to delete course.Please try again later')</script>";
            header( "Refresh:0.01; url=../teacher/manage_courses.php", true, 303);
    }}else{
        header( "Refresh:0.01; url=../teacher/manage_courses.php", true, 303);
    }
}
?>