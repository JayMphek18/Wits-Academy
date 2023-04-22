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
        Enter Course Password
    </title>
    <!--<link type="text/css" href="create_course.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Enroll into Course</h1>
        <a href="./index.php" class="link">Back To Dashboard</a>
    </div>
    <div class="container">
    <div class="span9">
                    <div class="content">
                    <div class="module">
                        <div class="module-head">
                            <h3>Course Details</h3>
                        </div>
                        <div class="module-body">
                    <?php
                        $x=$_GET['id'];
                        $sql="select * from courses where course_code='$x'";
                        $result=$conn->query($sql);
                        $row=$result->fetch_assoc();    
                        
                        $code=$row['course_code'];
                        $course=$row['course_name'];
                        $lecturer=$row['teacher'];
                        $year=$row['year_of_study'];
                        $course_password=$row['password'];
                        
                        echo "<b>Course Code:</b> ".$code."<br><br>";
                        echo "<b>Course Name:</b> ".$course."<br><br>";
                        echo "<b>Lecturer:</b> ".$lecturer."<br><br>";
                        echo "<b>Year:</b> ".$year."<br><br>";
                        ?> 
                        </div>             
    <div class="form-block">
        <form class="form" action="enter_password.php?id" method="post">
            <label for="course_password">Course Password:</label>
            <input type="password" class="password" maxlength="10" name="course_password" placeholder="Enter Course Password" required="" />
            <div class="text-block1">Enter the course password</div>
            <button type="submit"  class="enroll" name="enroll">Enroll</button>
        </form>
        <a href="enroll.php" class="btn btn-primary">Go Back</a> 
    </div>
</div>
</body>
<?php
    if(isset ($_POST['enroll'])){
        $x=$_GET['id'];
        $sql="select * from courses where course_code='$x'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();    
        $course_password=$row['password'];
        $password=$_POST['password'];
        $user_id = $_SESSION['user_id'];
        if($password !== $course_password){
                echo"<div class='alert alert-danger'>Incorrect Password</div>";
        }else{
            $sql = "INSERT INTO enrollment (student_num, course_code) VALUES (?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "ss",$user_id, $x);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('You have successfully enrolled into the course!)')</script>";
                header( "Refresh:0.01; url=index.php", true, 303);
            }else{
                die("Something went wrong :(");
            }
        } 
}  
?>

</html>

<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>