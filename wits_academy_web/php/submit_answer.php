<!-- submit_question.php -->
<?php
require('database.php');
if($_SESSION['user_id']){
if ($_SERVER["REQUEST_METHOD"] === "POST") {
   
    $question = $_POST["question"];
    $code=$_POST["id"];
    $response=$_POST['response'];

    // Validate and sanitize the question data if needed

    // Insert the question into the database
    $query = "UPDATE qna_question SET answer_text = '$response' WHERE question_id = '$question'";


    // Execute the query and handle any errors
    // You'll need to replace `your_db_connection` with the actual variable holding your database connection

    if (mysqli_query($conn, $query)) {
        echo "<script type='text/javascript'>alert('Your response have been sent!')</script>";
        header( "Refresh:0.01; url=../teacher/teacher_answer.php?id=$code", true, 303);
    } else {
        echo "Error submitting question: " . mysqli_error($conn);
    }

    // Close the database connection
    mysqli_close($conn);
}}
?>
