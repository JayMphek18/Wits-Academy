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
    <!--<link type="text/css" href="create_course.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>

.body {
    background-color: #bed2e2;
    font-family: 'Droid Sans', sans-serif;
}

.section {
    background-color: #1a2852;
    justify-content: flex-start;
    display: flex;
  }

.head1 {
    color: #c4d1db;
    font-family: Droid Sans, sans-serif;
    font-weight: 700;
    position: relative;
    bottom: -24px;
    left: 30px;
}

.link {
    color: #c4d1db;
    font-family: Montserrat, sans-serif;
    font-size: 25px;
    font-weight: 700;
    text-decoration: none;
    position: relative;
    top: 50px;
    left: 494px;
    right: 0;
  }

.head2 {
    float: none;
    text-align: center;
    font-family: Montserrat, sans-serif;
    font-size: 30px;
    font-style: normal;
    font-weight: 800;
    text-decoration: none;
    position: static;
}
.container {
    position: relative;
    top: 49px;
}

.form-block {
    position: relative;
    top: 34px;
  }

.form {
    text-align: left;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    font-family: Montserrat, sans-serif;
    font-weight: 700;
    display: flex;
    position: relative;
    top: -30px;
}

.password{
    border-radius: 18px;
  }

.text-block1{
    font-family: 'Droid Sans', sans-serif;
    font-size: 11px;
}

.enroll{
    background-color: #1a2852;
    border-radius: 18px;
    color: #c4d1db ;
    font-family: Droid Sans, sans-serif;
  }

    </style>

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
            $code=$_GET['id'];
            $sql="select * from courses where course_code='$code'";
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
        <form class="form" action="enroll_course.php?id=<?php echo $code?>" method="post">
            <input hidden = "hidden" name="course_code" type="text" value="<?php echo $code ?>" /><br>
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

<?php

if(isset ($_POST["enroll"])){
    $course_code=$_POST["course_code"];
    $password=$_POST["course_password"];
    $user_id=$_SESSION['user_id'];
    $sql1="SELECT * FROM courses WHERE course_code='$course_code'";
    $result1=mysqli_query($conn, $sql1);
    $row1=$result1->fetch_assoc();

    $teacher=$row1["teacher"];
    $sql4 ="SELECT * FROM registration WHERE user_id='$teacher'";
    $result4=mysqli_query($conn, $sql4);
    $row4=$result4->fetch_assoc();
    $email=$row4['email_address'];

    $errors=array();

    //checking if the student is already enrolled
    $sql2="SELECT * FROM enrollment WHERE student_num='$user_id' AND course_code='$course_code'";
    $result2=mysqli_query($conn, $sql2);
    $rowCount=mysqli_num_rows($result2);
    if($rowCount>0){
        array_push($errors, "Already enrolled into course!");
    }

    $sql3="SELECT * FROM courses WHERE course_code='$course_code'";
    $result3=mysqli_query($conn, $sql3);
    $row3=$result3->fetch_assoc();
    $pass=$row3["password"];
    if($pass !== $password){
        array_push($errors, "Incorrect Password! Please request the correct password from $email.");
    }
    //insert into enrollment table
    if(count($errors)>0){
        foreach($errors as $error){
            echo"<br><br><div class='alert alert-danger'>$error</div>";
        }
    }else{
        $sql = "INSERT INTO enrollment (student_num, course_code) VALUES (?,?)";
        $statement = mysqli_stmt_init($conn);
        $prepare = mysqli_stmt_prepare($statement, $sql);
        if($prepare){
            mysqli_stmt_bind_param($statement, "ss",$user_id, $course_code);
            mysqli_stmt_execute($statement);
            echo "<script type='text/javascript'>alert('You have successfully enrolled into the course!')</script>";
            header( "Refresh:0.01; url=./index.php", true, 303);
        }else{
            die("Something went wrong :(");
        }
    } 
    }
?>