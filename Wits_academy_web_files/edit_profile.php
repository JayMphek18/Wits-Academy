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

.input {
  border-radius: 18px;
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
    ?>    
    <div class="container">
    <h2 class="head2">Update Profile</h2>
    <br>
    <div class="form-block">
        <form class="form" name="form" action="edit_profile.php?id" method="post">
            <label for="fname">First name:</label>

            <input type="submit" name="update" class="submit" value="Update"/>
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


            $errors = array();


            require("database.php");
            $sql1= "SELECT * FROM registration WHERE email_address='$email'";
            $result1 = mysqli_query($conn, $sql1);
            $rowCount= mysqli_num_rows($result1);
            if($rowCount !== 0){
                array_push($errors, "Email address already exist");
            }

            $sql= "SELECT user_id, role, password FROM registration WHERE user_id='$user_id'";
            $result = mysqli_query($conn, $sql);
            $rowCount= mysqli_num_rows($result);
            $row = $result->fetch_assoc();//create a list of all the entries of this user
            $role=$row["role"];

            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }

    ?>
