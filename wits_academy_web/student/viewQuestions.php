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
        View Questions
    </title>
    <link type="text/css" href="./css/quizzes.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
        <?php
            $id=$_GET['id'];
            $num=$_GET['num'];
            $quiz=getQuiz($id, $_GET['quiz_name'], $conn);
            $course=getCourse($id, $conn);
            $quiz_name=$quiz['quiz_name'];
            $i=1;
            $sql="select * from quizzes where course_id='$id'";
            $result1=$conn->query($sql);
        ?>   
        <section class="section3">
            <h2 class="heading"><?php echo $quiz_name ?></h2>
            <ul role="list" class="list-5">
        <?php 
            while($i<=$num)
            {
        ?>
            <li class="list-item">
                <div class="text-block">Question <?php echo $i ?></div>
                <?php 
                $userid=$_SESSION['user_id'];
                $quizid=$quiz['quiz_id'];
                $ques=getQues($quizid, $i, $conn);
                $quesid=$ques['ques_id'];

                $sql="select * from quiz_records where stud_num='$userid' and quiz_id='$quizid' and ques_id='$quesid'";
                $result=$conn->query($sql);
                $rowcount=mysqli_num_rows($result);
                if(!($rowcount)){
                    ?>
                <a href="answer_quiz.php?id=<?php echo $quiz['course_id']?>&quiz=<?php echo $_GET['quiz'] ?>&quiz_name=<?php echo $quiz['quiz_name']?>&num=<?php echo $quiz['ques_num']?>&q=<?php echo $i ?>" class="btn btn-primary">
                <i class="fas fa-pencil"></i> Answer</a>
                <?php
                }else{
                    ?>
                <a href="#" class="btn btn-success">
                <i class="fas fa-pencil"></i> Answer submitted</a>
                <?php
                }
                ?>
                
            </li>
        <?php ++$i;
            } ?>
            <a href="../php/gradeAttempt.php?id=<?php echo $id ?>&quiz=<?php echo $_GET['quiz'] ?>&user=<?php echo $userid ?>" class="btn btn-primary">Finish Attempt</a><br> 
            </ul>
        </section>
        <section class="section2">
        <ul class="ul">
        <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="announcements.php?id=<?php echo $id?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcements</a></li>
                <li><a href="student_question.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="course_material.php?id=<?php echo $id ?> " class="link-3"><i class="fas fa-file"></i>Course Materials</a></li>
                <li><a href="quizzes.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-check"></i>Quizzes</a></li>
                <li><a href="feedback.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-comment"></i>Send Course Feedback</a></li>
                <li><a href="viewCourse.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-info-circle"></i>View Course Details</a></li>
                <li><a href="logout.php" class="link-3"><i class="fas fa-arrow-right-from-bracket"></i>Logout</a></li>
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