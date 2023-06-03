<?php
    require('database.php');
    require('../php/getCourse.php');
    require('../php/getQuiz.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>
        View quizzes
    </title>
    <link type="text/css" href="./css/quizzes.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <section class="section3">
    <h2 class="heading">Quizzes</h2>
    <ul role="list" class="list-5">
    <?php
        $id = $_GET['id'];
        $course=getCourse($id, $conn);
        $userid=$_SESSION['user_id'];

        $sql="select * from quizzes where course_id='$id'";
        $result1=$conn->query($sql);
        $rowcount=mysqli_num_rows($result1);
        
        if(($rowcount > 0)){ 
            while($quiz=$result1->fetch_assoc())
        {
            $quizid=$quiz['quiz_id'];
            $sql="select * from grades where stud_num='$userid'";
            $result1=$conn->query($sql); 
            while($grade=$result1->fetch_assoc()){
                $quiz=getQuizDetails($grade['quiz_id'], $conn);
    ?>
        <!--For attempted quizzes -->
        <li class="list-item">
        <div class="text-block"><?php echo $quiz['quiz_name'] ?></div>    
            <a href="viewGrade.php?id=<?php echo $id ?>&stud=<?php echo $grade['stud_num'] ?>&quiz=<?php echo $grade['quiz_id'] ?>"
               class="btn btn-success"><i class="fas fa-bar-chart"></i> View Grade</a>
        </li>
    <?php } }}else{?>
                <li class="list item">
                    <div class="text-block">No past quizzes available</div>
                </li>
            <?php } ?>
                <li class="list-item">
                    <a href="quizzes.php?id=<?php echo $id ?>" class="btn btn-primary"><i class="fas fa-long-arrow-left"></i> Back</a>
                </li>
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