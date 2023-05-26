<?php

    require("database.php");    

    function getCourseID($course_name,$connection){
        $getCourseID = "SELECT course_id FROM courses where course_name='$course_name'";
        $result = mysqli_query($connection, $getCourseID)->fetch_assoc();
        $course_id  = $result['course_id'];
        return $course_id;
    }

    $courseName = $_POST['courseName'];
    

    $course_users = array();
    $courseID = getCourseID($courseName,$conn);

    // Select all Users where the courseID in enrollment in == courseID
    $userIDs_query = "SELECT student_id FROM enrollment WHERE course_id = '$courseID'";
    $userIDs = mysqli_query($conn, $userIDs_query);

    while($row = $userIDs->fetch_assoc()) {
        // Fetch User Information
        $student_id = $row['student_id'];
        $user_query = "SELECT first_name , last_name , email_address , user_role FROM registration WHERE user_id = '$student_id' ";
        $user = mysqli_query($conn,$user_query)->fetch_assoc();
        array_push($course_users, $user);
    }
    echo json_encode($course_users);
?>