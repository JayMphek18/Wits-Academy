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
        Create answers
    </title>
    <link type="text/css" href="./css/edit_course.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
        <?php
            $id=$_GET['id'];
            $q=$_GET['q'];
            $quiz=$_GET['quiz_id'];
            $name=$_GET['qname'];
            $num=$_GET['num'];
            $ques=getQuestion($q, $conn);
            $course=getCourse($id, $conn);
            ?>
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
             <div class="form-block">
            <form class="form" action="../php/create_answer.php?id=<?php echo $id ?>&quiz_id=<?php echo $quiz?>&q=<?php echo $q ?>&num=<?php echo $num?>&qname=<?php echo $name?>" method="post">
                <input hidden name="quizid" value="<?php echo $quiz ?>" />
                <input hidden  name="code" value="<?php echo $id ?>" />
                <input hidden name="ques_id" value="<?php echo $q ?>" />
                <input hidden name="qname" value="<?php echo $name ?>" />
                <input hidden name="qnum" value="<?php echo $num ?>" />
                <label for="question">Question: <?php echo $ques['question'] ?></label>
                <label> Enter option values:</label>
                <input type="text"  name="value_1" maxlength="255" placeholder="Enter question" class="input" required=""></br>
                <input type="text"  name="value_2" maxlength="255" placeholder="Enter question" class="input" required=""></br>
                <input type="text"  name="value_3" maxlength="255" placeholder="Enter question" class="input" required=""></br>
                <input type="text"  name="value_4" maxlength="255" placeholder="Enter question" class="input" required=""></br>
                <label for="answer">Select correct answer: </label>
                <select class="input" name="answer" id = "roles">
                    <option value="#">Select correct answer</option>
                    <option value="0">Value 1</option>
                    <option value="1">Value 2</option>
                    <option value="2">Value 3</option>
                    <option value="3">Value 4</option>
                </select> 
                <input type="submit"  class="submit" name="submit" value="Submit Answers" />
            </form>
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