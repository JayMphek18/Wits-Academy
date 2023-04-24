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
<style>
html {
    -ms-text-size-adjust: 100%;
    -webkit-text-size-adjust: 100%;
    font-family: sans-serif;
  }
  
 html {
    height: 100%;

  }
   .field-label-3 {
    width: 50%;
    color: #c4d1db;
    font-family: Droid Sans, sans-serif;
    font-size: 30px;
    position: relative;
    top: -40px;
    left: 264px;
  }
  body {
    min-height: 100%;
    
  }
  
  img {
    max-width: 100%;
    vertical-align: middle;
    display: inline-block;
  }
  
  
  

  
  .w-button {
    color: #fff;
    line-height: inherit;
    cursor: pointer;
    background-color: #3898ec;
    border: 0;
    border-radius: 0;
    padding: 9px 15px;
    text-decoration: none;
    display: inline-block;
  }
  
  html[data-w-dynpage] [data-w-cloak] {
    color: rgba(0, 0, 0, 0) !important;
  }
  
  
  h1, h2, h3, h4, h5, h6 {
    color: #c4d1db;
    font-family: Droid Sans;
    margin-bottom: -2px;
    padding-left: 250px;
    padding-right: 10px;
    font-weight: bold;
    position: relative
    top:-40px;
    
  }
 
   label {
    margin-bottom: 5px;
    font-weight: bold;
    display: block;
  }
  
   .image {
    background-color: #1a2852;
  }
  
  .section {
    width: 1334px;
    background-color: #1a2852;
  }
  
  .body {
    background-color: #bed2e2;
    border: 1px solid #fff;
  }
  
  .select-field {
    width: 80%;
    opacity: 1;
    text-align: left;
    object-fit: scale-down;
    object-position: 50% 50%;
    border: 1px solid #000;
    border-radius: 20px;
    font-size: 14px;
    position: relative;
    top: 145px;
    bottom: -175px;
    left: 146px;
    right: -13px;
  }
  
  .submit-button {
    background-color: #c1c1c4e;
    border-radius: 18px;
    position: relative;
    top: 154px;
    left: 639px;
  }
  
  .button {
    color: #c4d1db;
    background-color: #1a2852;
    font-family: Droid Sans, sans-serif;
    font-size: 25px;
    font-style: normal;
    font-weight: 700;
    position: relative;
    top: -75px;
    left: 1015px;
  }
</style>
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
