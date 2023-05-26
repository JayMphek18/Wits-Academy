<?php

    require("database.php");    

    function getCourseID($course_name,$connection){
        $getCourseID = "SELECT course_id FROM courses where course_name='$course_name'";
        $result = mysqli_query($connection, $getCourseID)->fetch_assoc();
        $course_id  = $result['course_id'];
        return $course_id;
    }

    $courseName = $_POST['courseName'];
    

    $course_announcements = array();
    $courseID = getCourseID($courseName,$conn);
    
    $announcements = "SELECT * FROM announcements WHERE course_id = '$courseID'";
    $announced = mysqli_query($conn, $announcements);
    while($row = $announced->fetch_assoc()) {
        array_push($course_announcements, $row);
    }
    echo json_encode($course_announcements);
?>