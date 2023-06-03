<?php
    require('database.php');
    require('../php/getCourse.php');
    require('../php/getQuiz.php');
?>
<?php 
    if ($_SESSION['user_id']) {
        $id = $_GET['id'];
        $course=getCourse($id, $conn);

        $quizid=$_GET['quiz'];
        $user=$_SESSION['user_id'];

        $sql =" select * from quizzes where quiz_id='$quizid'";
        $result=$conn->query($sql);
        $quiz=$result->fetch_assoc();
        $qname=$quiz['quiz_name'];
?>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>
        <?php echo $qname." : Grades" ?>
    </title>
    <link type="text/css" href="./css/viewGrade.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <?php
        $sql1="select * from grades where quiz_id='$quizid' and stud_num='$user'";
        $result1=$conn->query($sql1);
        $grade=$result1->fetch_assoc();
    ?>   
    <section class="section3">
        <h2 class="heading"><?php echo $qname." Mark : ".$grade['grades'] ?> %</h2>
        <div class="form-block">
            <form  method="post" class="form">
                <?php 
                    $sql2="select * from questions where quiz_id='$quizid'";
                    $result2=$conn->query($sql2);
                    while($ques=$result2->fetch_assoc()){
                        $quesid=$ques['ques_id'];
                        $ans=$ques['answer_id'];
                        $correct=getAnswer($ans, $conn);

                        $record=getRecord($user, $quesid, $conn);
                        $stud_ans=getAnswer($record['answer_id'], $conn);
                ?>
                <label for="name">Question <?php echo $ques['ques_num']." : ".$ques['question'] ?> <b><?php echo "( ".$record['mark']." / ".$ques['ques_mark']." marks )" ?></b></label>
                <ul role="list" class="list">
                    <?php 
                        $sql3="select * from answers where ques_id='$quesid'";
                        $result3=$conn->query($sql3);
                        while($ans=$result3->fetch_assoc()){
                    ?>
                    <li><?php echo $ans['answer_text']; }?></li>
                </ul>
                <div class="text-block-2">Your answer: <?php echo $stud_ans['answer_text'] ?></div>
                <div class="text-block-1">Correct Answer: <?php echo $correct['answer_text'] ?></div> </br>
                <?php } ?>
                <a href="./pastQuizzes.php?id=<?php echo $id ?>" class="btn btn-primary"><i class= "fas fa-long-arrow-left"></i> Finish Review</a>
            </form>
        </div>      
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