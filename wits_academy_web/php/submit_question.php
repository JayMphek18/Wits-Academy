<!-- submit_question.php -->
<?php
  require('database.php');
// Assuming you have a database connection established
if($_SESSION['user_id']){
if (isset($_POST['submit'])) {
    $question = $_POST["question"];
    $code=$_POST["id"];
    $topic=$_POST["topic"];
    $date = date('y-m-d h:i:s');

    // Validate and sanitize the question data if needed
    // Insert the question into the database
    $query = "INSERT INTO qna_question (date_time, ques_topic, question_text,course_id) VALUES ('$date','$topic', '$question','$code')";

    // Execute the query and handle any errors
    // You'll need to replace `your_db_connection` with the actual variable holding your database connection

    if (mysqli_query($conn, $query)) {
        echo "<script type='text/javascript'>alert('Your question have been sent!')</script>";
        header( "Refresh:0.01; url=../student/student_question.php?id=$code", true, 303);
} else {
        echo "Error submitting question: " . mysqli_error($conn);
    }

    // Close the database connection
    mysqli_close($conn);
}}
?>
