<?php
    $announcement = $_POST['announcementText'];
    $courseName = $_POST['courseName'];
    require("database.php");
    $getCourseID = "SELECT course_id FROM courses where course_name='$courseName'";

    $result = mysqli_query($conn, $getCourseID)->fetch_assoc();
    echo "Hello";
    echo $result['course_id'];
    $course_id  = $result['course_id'];

        if($result){
        $sendAnnouncement = "INSERT INTO announcements (course_id, announcement_text) VALUES ($course_id,'$announcement')";
        if(mysqli_query($conn, $sendAnnouncement)){
            echo "Announcement sent successfully";
        }
        else{
            die("Something went wrong");
        }
    }
?>