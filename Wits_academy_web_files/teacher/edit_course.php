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
        Edit Course Details
    </title>
    <link type="text/css" href="*" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
    .body {
    max-width: 500px;
    background-color: #bed2e2;
    }
    .container {
    position: relative;
    top: 49px;
    }
    .section1 {
    background-color: #1a2852;
    justify-content: flex-start;
    display: flex;
    position: fixed;
    top: 0%;
    bottom: auto;
    left: 0%;
    right: 0%;
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
    .section2 {
    max-width: 100px;
    min-height: 1000px;
    min-width: 250px;
    text-align: center;
    background-color: #1a2852;
    flex-direction: column;
    justify-content: flex-start;
    align-items: justify;
    font-family: Droid Sans, sans-serif;
    font-weight: 700;
    display: flex;
    position: fixed;
    top: 101px;
    }
    .link-3 {
    color: #c4d1db;
    font-family: Droid Sans, sans-serif;
    font-size: 20px;
    font-weight: 700;
    text-decoration: none;
  }
  h2{
    float: none;
    text-align: center;
    font-family: Montserrat, sans-serif;
    font-size: 30px;
    font-style: normal;
    font-weight: 800;
    text-decoration: none;
    position: static;
}
  ul li{
   list-style: none;
}
  ul li a{
   font-family: 'Droid Sans', sans-serif;
   font-weight: 500px;
   padding: 5px 0;
   display: block;
   text-decoration: none;
   transition: 0.2s ease-out;
   color: #c4d1db;
}
ul li:hover a{
   color: #1a2852;
   background-color: #c4d1db;
}
ul li i{
   width: 40px;
   text-align: justify;
}
.form-block {
flex-direction: column;
display: block;
position: relative;
top: 141px;
left: 503px;
}
.form {
    flex-direction: column;
    justify-content: center;
    align-items: center;
    display: flex;
}
.input {
  border-radius: 18px;
}
.submit {
    background-color: #1a2852;
    color: #c4d1db;
    border-radius: 18px;
    font-family: Droid Sans, sans-serif;
}
label {
    color: black;
    font-weight: 700;
    font-family: Droid Sans, sans-serif;
}
.text-block1{
    font-family: 'Droid Sans', sans-serif;
    font-size: 11px;
}
  
    </style>
</head>
<body class="body">
        <?php
            $id = $_GET['id'];
            $sql="select * from courses where course_id='$id'";
            $result=$conn->query($sql);
            $row=$result->fetch_assoc();

            $code=$row['course_code'];
            $course=$row['course_name'];
            $description = $row['description'];
            $picture=$row['picture'];
            $password=$row['password'];
            
        ?>     
        <section class="section2">
        <ul>
            <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
            <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
            <li><a href="announcement.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcement</a></li>
            <li><a href="#" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
            <li><a href="#" class="link-3"><i class="fas fa-square-plus"></i>Create Quiz</a></li>
            <li><a href="#" class="link-3"><i class="fas fa-comment"></i>View Course Feedback</a></li>
            <li><a href="edit_course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-pen-to-square"></i>Edit Course Details</a></li>
        </ul>
        </section>
        <div class="form-block">
            <form class="form" method="post" action="edit_course.php?id=<?php echo $id ?>" enctype="multipart/form-data">
            <div><h2><center>Update Details</center></h2></div>
                <input hidden="hidden" type="text"  maxlength="10" name="course_id" value="<?php echo $id ?>"required="" />
                <label for="code">Course Code: </label>
                <input type="text" class="input" maxlength="10" name="code" value="<?php echo $code ?>"required="" />
                <label for="course">Course Name: </label>
                <input type="text" class="input" maxlength="10" name="course" value="<?php echo $course ?>"required="" />
                <label for="pic">Course Picture</label>
		        <input type="file" class="pic" name="pic"/>
                <img src="course_pic/<?php echo $picture?>" class="rounded-circle" style="width: 70px" />
                <input type="text" hidden="hidden"  name="old_pic" value="<?php echo $picture?>" />
                <div class="text-block1">Acceptable file types: .jpg, .jpeg, .png</div>
                <label for="description"> Course Description: </label>
                <textarea class="input" rows="20" cols="60" name="description" ><?php echo $description ?></textarea>
                <label for="course_password">Course Password:</label>
                <input type="password" class="input" maxlength="10" name="course_password" value="<?php echo $password ?>" required="" />
                <div class="text-block1">Enter the course password</div>
                <label for="confirm_password">Confirm Password:</label>
                <input type="password" class="input" maxlength="10" name="cpassword" value="<?php echo $password ?>" required="" />
                <div class="text-block1">Re-enter the course password</div>
                <button type="submit"  class="submit" name="update">Update</button>
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

<?php
        //read the entries of the user; if the entries are correct, change the password of the user to 
        //the new password
        if(isset($_POST["update"])){
            $id=$_POST["course_id"];
            $code=$_POST["code"];
            $course=$_POST["course"];
            $old_pp=$_POST["old_pic"];
            $descrip=$_POST["description"];
            $password=$_POST["course_password"];
            $cpass=$_POST["cpassword"];
            
            $errors = array();

            /*
            require("database.php");
            $sql1= "SELECT * FROM registration WHERE email_address='$email'";
            $result1 = mysqli_query($conn, $sql1);
            $rowCount= mysqli_num_rows($result1);
            if($rowCount !== 0){
                array_push($errors, "Email address already exist");
            }*/
            if (strlen($password)<5){
                array_push($errors, "Password must be at least 10 characters long!");
            }
            if($password !== $cpass){
                array_push($errors, "Password does not match!");
            }

            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }
            }
            $img_name=$_FILES['pic']['name'];
            echo "$img_name </br>";

            if (!empty($img_name)) {
                $tmp_name = $_FILES['pic']['tmp_name'];
                $error = $_FILES['pic']['error'];
                
                if($error === 0){
                    $img_ex = pathinfo($img_name, PATHINFO_EXTENSION);
                    $img_ex_to_lc = strtolower($img_ex);

                    $allowed_exs = array('jpg', 'jpeg', 'png');
                    if(in_array($img_ex_to_lc, $allowed_exs)){
                    $new_img_name = uniqid($id, true).'.'.$img_ex_to_lc;
                    //echo "$new_img_name </br>";
                    $img_upload_path = './course_pic/'.$new_img_name;
                    // Delete old profile pic
                    $old_pp_des = "./course_pic/$old_pp";
                    if(unlink($old_pp_des)){
                        // just deleted
                        move_uploaded_file($tmp_name, $img_upload_path);
                    }else {
                        // error or already deleted
                        move_uploaded_file($tmp_name, $img_upload_path);
                    }
                    

                    // update the Database
                    $sql = "UPDATE courses 
                            SET course_code=?, course_name=?, description=?, picture=?, password=?
                            WHERE course_id=?";
                    $stmt = $conn->prepare($sql);
                    $stmt->execute([$code, $course, $descrip, $new_img_name, $password, $id]);
                    //$_SESSION['fname'] = $fname;
                    echo "<script type='text/javascript'>alert('Course Details Updated:)')</script>";
                    exit;
                    }else {
                    echo "<script type='text/javascript'>alert('You can't upload files of this type')</script>";
                    exit;
                    }
                }else {
                    echo"<script type='text/javascript'>alert('unknown error occurred!')</script>";
                    exit;
                }

                
            }else{
                $sql = "UPDATE courses 
                SET course_code=?, course_name=?, description=?, password=?
                WHERE course_id=?";
        $stmt = $conn->prepare($sql);
        $stmt->execute([$code, $course,$descrip, $password, $id]);
                echo "<script type='text/javascript'>alert('Course Details Updated:)')</script>";
                //header( "Refresh:0.01; url=index.php", true, 303);
            }  
            }
    ?>