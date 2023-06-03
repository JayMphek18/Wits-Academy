<?php
    require('database.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Send Feedback
    </title>
    <link type="text/css" href="../teacher/css/announce.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
        <?php
            $id = $_GET['id'];
            $sql="select * from courses where course_id='$id'";
            $result=$conn->query($sql);
            $row=$result->fetch_assoc();

            $code=$row['course_code'];
            $course=$row['course_name'];
            
        ?>     
        <section class="section2">
        <ul>
                <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="announcements.php?id=<?php echo $id?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcements</a></li>
                <li><a href="student_question.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="course_material.php?id=<?php echo $id ?> " class="link-3"><i class="fas fa-file"></i>Course Materials</a></li>
                <li><a href="quizzes.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-check"></i>Quizzes</a></li>
                <li><a href="feedback.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-comments"></i>Send Course Feedback</a></li>
                <li><a href="viewCourse.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-info-circle"></i>View Course Details</a></li>
                <li><a href="logout.php" class="link-3"><i class="fas fa-arrow-right-from-bracket"></i>Logout</a></li>
            </ul>
        </section>
        <div class="form-block">
            <form class="form" method="post" action="../php/send_feedback.php?id=<?php echo $id ?>" enctype="multipart/form-data">
            <div><h2><center>Please give a short review of the course:</center></h2></div>
            <input  hidden="hidden" type="text" name="id" value="<?php echo $id ?>" required="" />
                <label for="rate">Please rate the course</label>
                <select class="input" name="rate" required>
                    <option value="">Rate the course</option>
                    <option value="1-very poor">1 - very poor</option>
                    <option value="2-bad">2 - bad</option>
                    <option value="3-neutral">3 - neutral</option>
                    <option value="4-good">4 - good</option>
                    <option value="5-excellent">5 - excellent</option>
                </select><br>
                <label for="feedback">Please provide a short comment to reason your rate:</label>
                <textarea class="input" name="feedback" rows="4" cols="50" placeholder="Enter your feedback"></textarea>
                <button type="submit"  class="submit" name="create">Send</button>
            </form>
        </div>
        <div class="container">
        <div class="section1">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1"><?php echo $code.":".$course ?></h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
        </div>
        </div>
</body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>

