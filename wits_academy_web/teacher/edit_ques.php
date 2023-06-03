<?php
    require('database.php');
    require('../php/getQuiz.php');
    require('../php/getCourse.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Create question
    </title>
    <link type="text/css" href="./css/announce.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <div class="container">
        <?php
            $id=$_GET['id'];
            $course=getCourse($id, $conn);
            $q=$_GET['q'];
            $num=$_GET['num'];
            $name=$_GET['quiz_name'];
            $quiz=getQuiz($id,$name,$conn);

            $ques=getQues($quiz['quiz_id'], $q, $conn);
            ?>
             <div class="form-block">
            <form class="form" action="../php/update_ques.php?id=<?php echo $id ?>&quiz_name=<?php echo $name ?>&q=<?php echo $q ?>&num=<?php echo $num ?>" method="post">
                <input hidden name="quizid" value="<?php echo $quiz['quiz_id'] ?>" />
                <input hidden name="course_id" value="<?php echo $id ?>" />
                <input hidden name="num" value="<?php echo $num ?>" />
                <input hidden name="qnum" value="<?php echo $q ?>" />
                <input hidden name="name" value="<?php echo $name ?>" />
                <label for="question">Question <?php echo $q ?></label>
                <textarea class="input" name="question" rows="4" cols="50" required><?php echo $ques['question'] ?></textarea>
                <label> Enter mark for question:</label>
                <input type="number"  name="mark" maxlength="255" value="<?php echo $ques['ques_mark'] ?>" class="input" required>
                <input type="submit"  class="submit" name="submit" value="Submit" />
            </form></br></br>
            <center>
            <a href="./view_questions.php?id=<?php echo $id ?>&quiz_name=<?php echo $name ?>&num=<?php echo $num ?>" class="btn btn-primary">Go Back</a><br> 
            </center>
            </div>

        <section class="section2">
        <ul>
                <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="viewAnnounce.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcement</a></li>
                <li><a href="teacher_answer.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="quizzes.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-check"></i> Quizzes</a></li>
                <li><a href="view_feedback.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-comments"></i>View Course Feedback</a></li>
                <li><a href="edit_course.php?id=<?php echo $id ?>" class="link-3"><i class="fa-solid fa-pen-to-square"></i>Edit Course Details</a></li>
                <li><a href="logout.php" class="link-3"><i class="fas fa-arrow-right-from-bracket"></i>Log Out</a></li>
            </ul>
        </section>
        <div class="container">
        <div class="section1">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1"><?php echo $course['course_code'].":".$course['course_name'] ?></h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
        </div>
        </div>
    </body>
</html>

<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>