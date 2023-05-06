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
        View Profile
    </title>
    <link type="text/css" href="" rel="stylesheet">
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
  .container {
  flex-direction: column;
  align-items: center;
  display: flex;
}
.profile{
  border-radius: 100px;
  position: static;
  left: 402px;
}
.text {
  color: #131618;
  position: static;
  left: 433px;
}
.head4 {
  color: #020202;
  display: inline-block;
}

    </style>

</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">View Profile</h1>
        <a href="./index.php" class="link">Back To Dashboard</a>
    </div>
    <div class="container">
        
                <img  src="" alt="profile image" class="profile">

                <?php
                $user_id = $_SESSION['user_id'];
                $sql="select * from registration where user_id='$user_id'";
                $result=$conn->query($sql);
                $row=$result->fetch_assoc();

                $fname=$row['first_name'];
                $lname=$row['last_name'];
                $fullname=$fname." ".$lname;
                $email=$row['email_address'];
                ?>    
            <div class="text"><?php echo $fullname ?></div>
            <h4 class="head4"><b>Student Number: </b><i><?php echo $user_id?><i></h4>
            <h4 class="head4"><b>Email Address: </b><i><?php echo $email?></i></h4><br>
        <a href="../edit_profile.php?id=<?php echo $user_id ?>" class="btn btn-primary">Edit Details</a>             
    </div>
    
    <!--/.span9-->
    </body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>