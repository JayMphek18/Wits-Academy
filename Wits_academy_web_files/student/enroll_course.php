<?php require('database.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE html>
<html>
    <body>
    <center>
        <div>
        <h2>Course Details:</h2>
        </div>
        <?php
            $code=$_GET['id'];
            /*
            $sql="select * from courses where course_code='$code'";
            $result=$conn->query($sql);
            $row=$result->fetch_assoc();
            
            $code=$row['course_code'];
            $course=$row['course_name'];
            $lecturer=$row['teacher'];
            $year=$row['year_of_study'];
            $course_password=$row['password'];
            
            echo "<b>Course Code: $code <br><br>";
            echo "<b>Course Name: $course <br><br>";
            echo "<b>Course Teacher: $lecturer <br><br>";
            echo "<b>Course Year: $year <br><br>";*/
            ?>
            <div class="form-block">
        <form class="form" action="enroll_course.php?id=<?php echo $code?>" method="post">
            <label for="course_code"> Course Code: </label>
            <input name="course_code" type="text" value=" <?php echo $code ?>" /><br>
            <!--<label for="course_name"> Course name: </label>
            <input name="course_name" type="text" value=" <?php //echo $course ?>" required/><br>
            <label for="course_teacher"> Course Teacher: </label>
            <input name="course_teacher" type="text" value=" <?php //echo $lecturer ?>" required/><br>-->
            <div class="text-block1">Do not change the value!</div>
            <label for="course_password">Course Password:</label>
            <input type="password" class="password" maxlength="10" name="course_password" placeholder="Enter Course Password" required="" />
            <div class="text-block1">Enter the course password</div>
            <button type="submit"  class="enroll" name="enroll">Enroll</button>
        </form>
        <a href="enroll.php" class="btn btn-primary">Go Back</a> 
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
    /*$code=$_GET['id'];
    $result=$conn->query($sql);
    $row=$result->fetch_assoc(); */   
    $course_code=$_POST["course_code"];
    $password=$_POST["course_password"];
    $user_id =$_SESSION['user_id'];
    $sql1="SELECT * FROM registration WHERE user_id='$user_id'";
    $result1=mysqli_query($conn, $sql1);
    $row1=$result1->fetch_assoc();
    $email=$row1["email_address"];
    

    //checking if the student is already enrolled
    $sql2="SELECT * FROM enrollment WHERE student_num='$user_id' AND course_code='$course_code'";
    $result2=mysqli_query($conn, $sql2);
    $rowCount=mysqli_num_rows($result2);
    if($rowCount>0){
       echo "<div class='alert alert-danger'>Already enrolled into course!</div>";
    }

    $sql3="SELECT * FROM courses WHERE course_code='$course_code'";
    $result3=mysqli_query($conn, $sql3);
    $row3=$result3->fetch_assoc();
    $course_password=$row3["password"];
    echo $course_password;
    if($course_password !== $password){
        echo"<div class='alert alert-danger'>Incorrect Password! Please request the correct password from $email.</div>";
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