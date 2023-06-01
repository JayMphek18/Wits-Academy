<?php
    require("database.php");
?>

<?php 
if ($_SESSION['user_id']) {
    ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Create Course
    </title>
    <!--<link type="text/css" href="create_course.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>

.body {
    background-color: #bed2e2;
    font-family: 'Droid Serif', serif;
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

.select-faculty, .select-school, .select-year, .course_code, .course_name, .password, .course_year, .cpassword {
    border-radius: 18px;
  }

.text-block1{
    font-family: 'Droid Sans', sans-serif;
    font-size: 11px;
}

.create{
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
        <h1 class="head1">Create Course</h1>
        <a href="./index.php" class="link">Back To Dashboard</a>
    </div>

    <div class="container">
        <h2 class="head2">Please fill in all details below:</h2>
        <div class="form-block">
            <form class="form" action="create_course.php" method="post">
                <label for="faculty">Faculty:</label>
                <select  name="faculty" required="" class="select-faculty">
                    <option value="">Select faculty</option>
                    <option value="Commerce, Law and Management">Commerce, Law and Management</option>
                    <option value="Engineering and the Built Environment">Engineering and the Built Environment</option>
                    <option value="Health Sciences">Health Sciences</option>
                    <option value="Humanities">Humanities</option>
                    <option value="Science">Science</option>
                </select>
                <label for="school">School:</label>
                <select name="school" class="select-school">
                    <option value="">Select school</option>
                    <option value="accounting">Accountancy</option>
                    <option value="bs">Business Sciences</option>
                    <option value="ef">Economics and Finance</option>
                    <option value="law">Law</option>
                    <option value="wbs">Wits Business School</option>
                    <option value="#">Another option</option>
                </select>
                <label for="course_year">Course Year of Study:</label>
                <input type="number" class="course_year" maxlength="2" name="course_year" placeholder="Enter course year of study"required="" />
                <label for="course_code">Course Code:</label>
                <input type="text" class="course_code" maxlength="10" name="course_code" placeholder="Enter course code"required="" />
                <label for="course_name">Course Name:</label>
                <input type="text" class="course_name" maxlength="256" name="course_name" placeholder="Enter Course Name" required="" />
                <label for="course_password">Course Password:</label>
                <input type="password" class="password" maxlength="10" name="course_password" placeholder="Enter Course Password" required="" />
                <div class="text-block1">Enter the course password</div>
                <label for="confirm_password">Course Password:</label>
                <input type="password" class="cpassword" maxlength="10" name="cpassword" placeholder="Re-enter Course Password" required="" />
                <div class="text-block1">Re-enter the course password</div>
                <button type="submit"  class="create" name="create">Create</button>
                </form>
        </div>

    </body>
    <?php
     //read data entered by user and submit to database
     if(isset($_POST["create"])){
        $faculty = $_POST["faculty"];
        $school = $_POST["school"];
        $yos = $_POST["course_year"];
        $code= $_POST["course_code"];
        $course_name= $_POST["course_name"];
        $password = $_POST["course_password"];
        $confirm_password = $_POST["cpassword"];
        $passwordHash= password_hash($password, PASSWORD_DEFAULT);//protect password
        //create array to store the errors
        $errors = array();
       
        //connect to the database once all entries are complete
        //require("database.php");

        $user_id = $_SESSION['user_id'];
        /*
        $sql="select * from registration where user_id='$user_id'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();
        $f_name=$row['first_name'];
        $l_name=$row['last_name'];
        */
        //check if the course exists
        $sql1= "SELECT * FROM courses WHERE course_code='$code'";
        $result1 = mysqli_query($conn, $sql1);
        $rowCount1= mysqli_num_rows($result1);
        //if the course already exists, append the error: "Course already exists. Please create an available course."
        if($rowCount1>0){
            array_push($errors, "Course already exists. Please create an available course.");
        }
        
        //if the user enters a user id of less than 7 characters, and/ or a password of less than 10 characters, append the error: "Invalid user ID!",
        // and/ or, "Password must be at least 10 characters long!"
        if (strlen($yos)>2){
            array_push($errors, "Invalid year of study!");
        }

        if (strlen($password)<5){
            array_push($errors, "Password must be at least 10 characters long!");
        }
        if($password !== $confirm_password){
            array_push($errors, "Password does not match!");
        }
        //if the length of the error's array is greater than 0, display all the errors on the page
        //, otherwise insert all the entries into the registration table on the database
        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $sql = "INSERT INTO courses (course_code, course_name, faculty, school, year_of_study, teacher, password) VALUES (?,?,?,?,?,?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "sssssss",$code, $course_name, $faculty, $school, $yos, $user_id, $password);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('You have successfully created your course!)')</script>";
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

