<?php
    $teacher_number = $_POST['teacher_number'];
    
    // $teacher_number = "2467513";

    require("database.php");

    $course_list = array();
    $user_id = "SELECT * FROM Registration WHERE emp_stud_num = '$teacher_number'";
    $result = mysqli_query($conn,$user_id);
    $result = $result->fetch_assoc();
    $user = $result["user_id"];
    $enrolled_courses = "SELECT * FROM Courses WHERE teacher_id = '$user'";
    $courses = mysqli_query($conn, $enrolled_courses);
    while($row = $courses->fetch_assoc()) {
        $teacher_id = $row['teacher_id'];
        $teacher = "SELECT * FROM Registration WHERE user_id = '$teacher_id'";
        $teacher_name = mysqli_query($conn, $teacher)-> fetch_assoc();
        $teacher_name = array($teacher_name['first_name'], $teacher_name['last_name']);
        $row['teacher_id'] = join(" " , $teacher_name);
        array_push($course_list, $row);
    }
    echo json_encode($course_list);
?>