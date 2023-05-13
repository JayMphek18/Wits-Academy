<?php
    $student_number = $_POST['student_number'];
    $course_name = $_POST['course_name'];
    $password = $_POST['course_password'];

    // $student_number = "1234567";
    // $course_name = "AAA";
    // $password = "lol";

    require("database.php");

    $user_id = "SELECT * FROM Registration WHERE emp_stud_num = '$student_number'";
    $result = mysqli_query($conn,$user_id)->fetch_assoc();
    $user = $result["user_id"];

    $course_id = "SELECT * FROM Courses WHERE course_name = '$course_name'";
    $crs_id = mysqli_query($conn,$course_id)->fetch_assoc();
    $course = $crs_id["course_id"];
    $year = $crs_id["course_year"];
    $course_password = $crs_id["course_password"];

    if ($password == $course_password){
        $enrolled = "SELECT * FROM Enrollment WHERE course_id = '$course' AND student_id = '$user'";
        if (mysqli_num_rows(mysqli_query($conn,$enrolled)) > 0){
            echo "already enrolled!";
        }
        else{
            $enroll = "INSERT INTO Enrollment(course_id, student_id, year_of_study) VALUES('$course', '$user', '$year')";
            if (mysqli_query($conn,$enroll)){
                echo "Successfully enrolled";
            }
            else{
                echo "error";
            }
        }
    }
    else{
        echo "Please request the correct password from appointed teacher";
    }
?>