<?php
    require('database.php');
    require('../php/getCourse.php');
    require('../php/getQues.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Response
    </title>
    <link type="text/css" href="./css/announce.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
        <?php
            $id = $_GET['id'];
            $q=$_GET['ques'];
            
            $course=getCourse($id, $conn);
            $ques=getQues($q, $conn);
            
        ?>     
        <section class="section2">
        <ul>
        <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="announcements.php?id=<?php echo $id?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcements</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="course_material.php?id=<?php echo $id ?> " class="link-3"><i class="fas fa-book"></i>Course Materials</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-book"></i>Quizzes</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-book"></i>Assignments</a></li>
                <li><a href="send_feedback.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-comment"></i>Send Course Feedback</a></li>
                <li><a href="view_course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-info-circle"></i>View Course Details</a></li>
        </ul>
        </section>
        <div class="form-block">
            <form class="form" method="post" action="../php/submit_answer.php" enctype="multipart/form-data">
            <input  hidden="hidden" type="text" name="id" value="<?php echo $id ?>" required/>
            <input hidden type="text" name="question" value="<?php echo $ques['question_id']?>" required />
                <label for="topic">Question : <?php echo $ques['question_text']?></label>
                <label for="Answer">Response:</label>
                <textarea class="input" name="response" rows="4" cols="50" placeholder="Enter your response"></textarea>
                <button type="submit"  class="submit" name="submit">Send response</button>
            </form><br>
            <center><a href="teacher_answer.php?id=<?php echo $id ?>" class="btn btn-primary"> Back </a></center>
        </div>
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

