<?php
     $student_number = $_POST['user_number'];
    // $student_number = "1234567";

    require("database.php");

    $profile = "SELECT * FROM Registration WHERE emp_stud_num = '$student_number'";
    $result = mysqli_query($conn,$profile)->fetch_assoc();

    // $imageURL = $result['image'];
    // $baseLine = "<img src='$imageURL'>";
    // $imageBase64 = base64_encode($baseLine);


    echo json_encode($result);
?>