<?php
    $employee_number = $_POST['employee_number'];
    $course_code = $_POST['course_code'];
    $course_name = $_POST['course_name'];
    $course_year = $_POST['course_year'];
    $course_year = number_format($course_year);
    $school = $_POST['school'];
    $faculty = $_POST['faculty'];
    $password = $_POST['course_password'];

    // $employee_number = "2433301";
    // $course_code= "kjsnka";
    // $course_name= " jdkdjafk";
    // $course_year =0;
    // $school = "kdsdak";
    // $faculty = "kfkskfmfl";
    // $password = "dlflsk";

    require("database.php");

    $teacher = "SELECT * FROM Registration WHERE emp_stud_num = '$employee_number'";
    $result = mysqli_query($conn,$teacher);
    $result = $result->fetch_assoc();
    $user = $result["user_id"];

    // echo $user;

    $found = "SELECT * FROM Courses WHERE course_code = '$course_code'";
    $result = mysqli_query($conn,$found);
    // echo " hi";
    if (mysqli_num_rows($result) > 0){
        echo "course already exists";
    }
    else{
        $insert = "INSERT INTO Courses(course_name, course_code, teacher_id,course_year, school, faculty, course_password) VALUES('$course_name','$course_code','$user','$course_year', '$school', '$faculty', '$password')";
        if(mysqli_query($conn,$insert) == 1){
            echo "course sucessfully created";
        }
        else{
            echo "something went wrong";
        }
    }

    
?>