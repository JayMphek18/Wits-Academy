<?php
    require('database.php');
    require('../php/getCourse.php')
?>
<?php 
    if ($_SESSION['user_id']) {
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Edit Course Details
    </title>
    <link type="text/css" href="./css/edit_course.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
        <?php
            $id = $_GET['id'];
            $course=getCourse($id,$conn);
            
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
        <?php if ($course) { ?>
        <div class="form-block">
            <form class="form" method="post" action="../php/edit_course.php?id=<?php echo $id ?>" enctype="multipart/form-data">
            <div><h2 class="head2"><center>Update Details</center></h2></div>
                <input hidden="hidden" type="text"  maxlength="10" name="course_id" value="<?php echo $course['course_id'] ?>"required="" />
                <label for="code">Course Code: </label>
                <input type="text" class="input" maxlength="10" name="code" value="<?php echo $course['course_code'] ?>"required="" />
                <label for="course">Course Name: </label>
                <input type="text" class="input" maxlength="10" name="course" value="<?php echo $course['course_name'] ?>"required="" />
                <label for="pic">Course Picture</label>
		        <input type="file" class="pic" name="pic"/>
                <img src="course_pic/<?php echo $course['picture']?>" class="rounded-circle" style="width: 70px" />
                <input type="text" hidden="hidden"  name="old_pic" value="<?php echo $picture?>" />
                <div class="text-block1">Acceptable file types: .jpg, .jpeg, .png</div>
                <label for="description"> Course Description: </label>
                <textarea class="input" rows="20" cols="60" name="description" ><?php echo $course['description'] ?></textarea>
                <label for="course_password">Course Password:</label>
                <input type="password" class="input" maxlength="10" name="course_password" value="<?php echo $course['password'] ?>" required="" />
                <div class="text-block1">Enter the course password</div>
                <label for="confirm_password">Confirm Password:</label>
                <input type="password" class="input" maxlength="10" name="cpassword" value="<?php echo $course['password'] ?>" required="" />
                <div class="text-block1">Re-enter the course password</div>
                <button type="submit"  class="submit" name="update">Update</button>
            </form>
        </div>
        <?php } ?>
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

