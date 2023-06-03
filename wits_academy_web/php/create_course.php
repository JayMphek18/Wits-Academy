<?php
require("database.php");
if($_SESSION['user_id']){
     //read data entered by user and submit to database
     if(isset($_POST["create"])){
        $faculty = $_POST["faculty"];
        $school = $_POST["school"];
        $yos = $_POST["course_year"];
        $code= $_POST["course_code"];
        $course= $_POST["course_name"];
        $description=$_POST["description"];
        //echo "$description </br>"; 
        $password = $_POST["course_password"];
        $confirm_password = $_POST["cpassword"];
        $passwordHash= password_hash($password, PASSWORD_DEFAULT);//protect password
        //create array to store the errors
        $errors = array();


        $user_id = $_SESSION['user_id'];
        
        //check if the course exists
        $sql1= "SELECT * FROM courses WHERE course_code='$code' and course_name='$course'";
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
        }
        else{
        $img_name=$_FILES["pic"]["name"];
        //echo $img_name;
        if (!empty($img_name)) {
            //$img_name = $_FILES['pic']['name'];
            $tmp_name = $_FILES['pic']['tmp_name'];
            $error = $_FILES['pic']['error'];
            
            if($error === 0){
               $img_ex = pathinfo($img_name, PATHINFO_EXTENSION);
               $img_ex_to_lc = strtolower($img_ex);
   
               $allowed_exs = array('jpg', 'jpeg', 'png');
               if(in_array($img_ex_to_lc, $allowed_exs)){
                  $new_img_name = $code.$course.'.'.$img_ex_to_lc;
                  $img_upload_path = '../teacher/course_pic/'.$new_img_name;
                  move_uploaded_file($tmp_name, $img_upload_path);
   
                  // Insert into Database
                  $sql = "INSERT INTO courses (course_code, course_name, faculty, school, year_of_study, teacher, description, picture, password) 
                    VALUES (?,?,?,?,?,?,?,?,?)";
                  $stmt = $conn->prepare($sql);
                  $stmt->execute([$code, $course, $faculty, $school, $yos, $user_id, $description, $new_img_name, $password]);
                echo "<script type='text/javascript'>alert('You have successfully created your course!')</script>";
                header( "Refresh:0.01; url=../teacher/index.php", true, 303);
               }else {
                echo "<script type='text/javascript'>alert('You have uploaded wrong file type!')</script>";
                header( "Refresh:0.01; url=../teacher/create_course.php", true, 303);
                  exit;
               }
            }else {
                echo "<script type='text/javascript'>alert('Something went wrong')</script>";
                header( "Refresh:0.01; url=../teacher/create_course.php", true, 303);
               exit;
            }
   
           
         }
        else{
            $sql = "INSERT INTO courses (course_code, course_name, faculty, school, year_of_study, teacher, password) VALUES (?,?,?,?,?,?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement, "sssssss",$code, $course, $faculty, $school, $yos, $user_id, $password);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('You have successfully created your course!')</script>";
                header( "Refresh:0.01; url=../teacher/index.php", true, 303);
              }else{
                die("Something went wrong :(");
            }
        }
      }}
    }
?>