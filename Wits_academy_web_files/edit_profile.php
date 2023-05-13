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
        Edit Profile
    </title>
    <link type="text/css" href="./index.css" rel="stylesheet">
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
  grid-column-gap: 16px;
  grid-row-gap: 16px;
  text-align: left;
  flex-direction: column;
  grid-template: ". ."
                 ". ."
                 ". ."
                 "Area Area"
                 / 1fr 1fr;
  grid-auto-columns: 1fr;
  justify-content: flex-start;
  align-items: stretch;
  font-family: Montserrat, sans-serif;
  display: grid;
  position: relative;
  top: -30px;
}
.input {
  border-radius: 18px;
}
.submit {
  background-color: #1a2852;
  border-radius: 18px;
  position: relative;
  left: 401px;
  color:#c4d1db;
}
    </style>
</head>
<body class="body">
<div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Edit Profile</h1>
        <!--<a href="./index.php" class="link">Back To Dashboard</a>-->
    </div>
    <?php
        $user_id = $_GET['id'];
        $sql="select * from registration where user_id='$user_id'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();

        $f_name=$row['first_name'];
        $l_name=$row['last_name'];
        $email=$row['email_address'];
        $profile=$row['profile_pic'];
    ?>    
    <div class="container">
    <h2 class="head2">Update Profile</h2>
    <br>
    <div class="form-block">
        <form class="form" name="form" action="edit_profile.php?id=<?php echo $user_id?>" method="post" enctype="multipart/form-data">
            <label for="fname">First name:</label>
            <input class="input" name="fname" type="text" value="<?php echo $f_name ?>"/>
            <label for="lname">Last name:</label>
            <input class="input" name="lname" type="text" value="<?php echo $l_name ?>"/>
            <label for="email">Email Address:</label>
            <input class="input" name="email" type="email" value="<?php echo $email ?>"/>
            <label for="pp">Profile Picture</label>
		    <input type="file" name="pp" />
            <img src="profile_pic/<?php echo $profile?>" class="rounded-circle" style="width: 70px" />
            <input type="text" hidden="hidden"  name="old_pp" value="<?php echo $profile?>" />
            <div class="text-block1">Acceptable file types: .jpg, .jpeg, .png</div>
            <button type="submit" name="update" class="btn btn-primary">Update</button>
        </form>
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
            $user_id=$_SESSION['user_id'];
            $fname=$_POST["fname"];
            $lname=$_POST["lname"];
            $email=$_POST["email"];
            $old_pp=$_POST["old_pp"];
            
            $errors = array();

            /*
            require("database.php");
            $sql1= "SELECT * FROM registration WHERE email_address='$email'";
            $result1 = mysqli_query($conn, $sql1);
            $rowCount= mysqli_num_rows($result1);
            if($rowCount !== 0){
                array_push($errors, "Email address already exist");
            }*/
            $sql= "SELECT user_id, role, password FROM registration WHERE user_id='$user_id'";
            $result = mysqli_query($conn, $sql);
            $rowCount= mysqli_num_rows($result);
            $row = $result->fetch_assoc();//create a list of all the entries of this user
            $role=$row["role"];

            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }
            }
            $img_name=$_FILES["pp"]["name"];
            //echo "$img_name </br>";

            if (!empty($img_name)) {
                //$img_name = $_FILES['pp']['name'];
                $tmp_name = $_FILES['pp']['tmp_name'];
                $error = $_FILES['pp']['error'];
                
                if($error === 0){
                    $img_ex = pathinfo($img_name, PATHINFO_EXTENSION);
                    $img_ex_to_lc = strtolower($img_ex);

                    $allowed_exs = array('jpg', 'jpeg', 'png');
                    if(in_array($img_ex_to_lc, $allowed_exs)){
                    $new_img_name = uniqid($user_id, true).'.'.$img_ex_to_lc;
                    //echo "$new_img_name </br>";
                    $img_upload_path = './profile_pic/'.$new_img_name;
                    // Delete old profile pic
                    $old_pp_des = "./profile_pic/$old_pp";
                    if(unlink($old_pp_des)){
                        // just deleted
                        move_uploaded_file($tmp_name, $img_upload_path);
                    }else {
                        // error or already deleted
                        move_uploaded_file($tmp_name, $img_upload_path);
                    }
                    

                    // update the Database
                    $sql = "UPDATE registration 
                            SET first_name=?, last_name=?, email_address=?, profile_pic=?
                            WHERE user_id=?";
                    $stmt = $conn->prepare($sql);
                    $stmt->execute([$fname, $lname, $email, $new_img_name, $user_id]);
                    //$_SESSION['fname'] = $fname;
                    echo "<script type='text/javascript'>alert('You have successfully updated your profile :)')</script>";
                    if($role=='teacher'){
                        header('location:teacher/profileview.php');
                      }
                      else{
                        header('location:student/profileview.php');
                      }
                    exit;
                    }else {
                    //echo "<script type='text/javascript'>alert('You can't upload files of this type')</script>";
                    if($role=='teacher'){
                        header('location:teacher/profileview.php');
                      }
                      else{
                        header('location:student/profileview.php');
                      }
                    exit;
                    }
                }else {
                    //echo"<script type='text/javascript'>alert('unknown error occurred!')</script>";
                    if($role=='teacher'){
                        header('location:teacher/profileview.php');
                      }
                      else{
                        header('location:student/profileview.php');
                      }
                    exit;
                }

                
            }else{
                $sql2 = "UPDATE registration SET first_name='$fname', last_name='$lname', email_address='$email' WHERE user_id='$user_id'";
                $result2=mysqli_query($conn,$sql2);
                echo "<script type='text/javascript'>alert('You have successfully updated your profile :)')</script>";
                if($role=='teacher'){
                    header('location:teacher/profileview.php');
                  }
                  else{
                    header('location:student/profileview.php');
                  }   
                //header( "Refresh:0.01; url=index.php", true, 303);
            }  
            }
    ?>