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
            $quizid=$quiz['quiz_id'];
            $i=1;
            $sql="select * from quizzes where course_id='$id'";
            $result=$conn->query($sql);
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
                    $sql1="select * from questions where ques_num='$i' and quiz_id='$quizid'";
                    $result1=$conn->query($sql1);
                    $rowcount=mysqli_num_rows($result1);
                    if($rowcount === 0){
                ?>
                <a href="create_ques.php?id=<?php echo $id?>&quiz_name=<?php echo $quiz_name?>&q=<?php echo $i?>&num=<?php echo $num?>" class="btn btn-success"><i class="fas fa-pencil"></i> Add Question</a>
            </li>
        <?php } else{ ?>
            <a href="edit_ques.php?id=<?php echo $id?>&quiz_name=<?php echo $quiz_name?>&q=<?php echo $i?>&num=<?php echo $num?>" class="btn btn-success"><i class="fas fa-pencil"></i> Edit Question</a>
        </li>
        <?php    } ++$i;} ?>
            <a href="./quizzes.php?id=<?php echo $id ?>" class="btn btn-primary">Go Back</a><br> 
            </ul>
        </section>
        <section class="section2">
        <ul class="ul">
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