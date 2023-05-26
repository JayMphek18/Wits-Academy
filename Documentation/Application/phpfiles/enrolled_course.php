<?php
    $student_number = $_POST["student_number"];

    // $student_number = "1234567";

    require("database.php");

    $course_list = array();
    $user_id = "SELECT * FROM Registration WHERE emp_stud_num = '$student_number'";
    $result = mysqli_query($conn,$user_id);
    $result = $result->fetch_assoc();
    $user = $result["user_id"];
    $enrolled_courses = "SELECT * FROM Enrollment WHERE student_id = '$user'";
    $courses = mysqli_query($conn, $enrolled_courses);
    while($row = $courses->fetch_assoc()) {
        $course_id = $row['course_id'];
        $get_course = "SELECT * FROM Courses WHERE  course_id = '$course_id'";
        $each_course = mysqli_query($conn, $get_course);
        while($column = $each_course->fetch_assoc()) {
            $teacher_id = $column['teacher_id'];
            $teacher = "SELECT * FROM Registration WHERE user_id = '$teacher_id'";
            $teacher_name = mysqli_query($conn, $teacher)-> fetch_assoc();
            $teacher_name = array($teacher_name['first_name'], $teacher_name['last_name']);
            $column['teacher_id'] = join(" " , $teacher_name);
            array_push($course_list, $column);
        }
    }
    if(sizeof($course_list) == 0){
        echo "";
    }
    else{
        echo json_encode($course_list);
    }
    
?>