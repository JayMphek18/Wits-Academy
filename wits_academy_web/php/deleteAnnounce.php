<?php 
require("database.php");

if($_SESSION['user_id']){
    $announce=$_GET['announce'];
    $course=$_GET['id'];
    if(isset($_POST['delete'])){
        $answer=$_POST['answer'];
        $sql="select * from announcement where announce_id='$announce'";
        $result=$conn->query($sql);
        if(mysqli_num_rows($result) > 0 && $answer=='yes'){
            $delete="delete from announcement where announce_id='$announce'";
            if (mysqli_query($conn, $delete)) {
            echo "<script type='text/javascript'>alert('Deleted announcement')</script>";
            header( "Refresh:0.01; url=../teacher/viewAnnounce.php?id=$course", true, 303);
            }else{
                die("Something went wrong :(");
            }
        }else{
            echo "<script type='text/javascript'>alert('Failed to delete announcement.Please try again later')</script>";
            header( "Refresh:0.01; url=../teacher/viewAnnounce.php?id=$course", true, 303);
        }
    }else{
        header( "Refresh:0.01; url=../teacher/viewAnnounce.php?id=$course", true, 303);
    }
}
?>