<?php
    require('database.php');
    require('../php/getCourse.php');
    require('../php/getAvg.php');

?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>
        View Grades
    </title>
    <link type="text/css" href="./css/viewGrades.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <?php
        $id = $_GET['id'];
        $course=getCourse($id, $conn);

        $quizid=$_GET['quiz'];

        $class=classAvg($quizid,$id,$conn);
        $pass=passAvg($quizid,$id,$conn);
        $fail=failAvg($quizid,$id,$conn);
    ?>   
        <section class="section-9">
                <div class="text-block-17">Class Average: <?php echo $class ?> %</div>
                <div class="text-block-17">Pass Average: <?php echo $pass ?> %</div>
                <div class="text-block-17">Fail Average: <?php echo $fail ?> %</div>
                <ul role="list" class="list-9">
                <?php 
                    $sql="select * from grades where quiz_id='$quizid'";
                    $result=$conn->query($sql);
                    if(mysqli_num_rows($result) > 0){
                        while($grade=$result->fetch_assoc()){
                ?>
                    <li class="list-item-7">
                        <div class="text-block-13"><?php echo $grade['stud_num'] ?> </div>
                        <div class="div-block-10">
                            <div class="text-block-14"><?php echo $grade['grades'] ?> %</div>
                        </div>
                    </li>
                    <?php     }
                    }else{
                    ?>
                    <li class="list-item-7">
                        No grades of students available
                    </li>
                    <?php } ?>
                </ul>
                <a href="quizzes.php?id=<?php echo $id ?>" class="btn btn-primary"><i class="fas fa-long-arrow-left"></i> Back</a>
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