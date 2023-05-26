<?php
    require("database.php");

    $course_list = array();
    $enrolled_courses = "SELECT * FROM Courses";
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