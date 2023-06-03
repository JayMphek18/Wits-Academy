<?php require('database.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Enroll into Course
    </title>
    <link type="text/css" href="./css/enroll.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Enroll into Course</h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <center>
        <div>
        <h2 class="head2">Course Details:</h2>
        </div>
        <?php
            $id=$_GET['id'];
            $sql="select * from courses where course_id='$id'";
            $result=$conn->query($sql);
            $row=$result->fetch_assoc();
            
            $code=$row['course_code'];
            $course=$row['course_name'];
            $year=$row['year_of_study'];
            $course_password=$row['password'];
            $lecturer=$row['teacher'];

            $sql1 = "select * from registration where user_id='$lecturer'";
            $result1=$conn->query($sql1);
            $row1=$result1->fetch_assoc();
            $fullname=$row1['first_name']." ".$row1['last_name'];

            echo "<div><b>Course Code:</b> $code <br><br>";
            echo "<b>Course Name:</b> $course <br><br>";
            echo "<b>Course Teacher:</b> $fullname <br><br>";
            echo "<b>Course Year:</b> $year <br><br></div>";
            ?>
            <div class="form-block">
        <form class="form" action="../php/enroll.php?id=<?php echo $id?>" method="post">
            <input hidden = "hidden" name="course_id" type="text" value="<?php echo $id ?>" /><br>
            <label for="course_password">Course Password:</label>
            <input type="password" class="password" maxlength="10" name="course_password" placeholder="Enter Course Password" required="" />
            <div class="text-block1">Enter the course password</div>
            <button type="submit"  class="enroll" name="enroll">Enroll</button>
        </form>

        <a href="search_course.php" class="btn btn-primary">Go Back</a><br> 
    </div>
    </center>
    </body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>