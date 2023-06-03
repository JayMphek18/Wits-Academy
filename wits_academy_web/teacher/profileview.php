<?php
require('database.php');
require('../php/getProfile.php');
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
    <link type="text/css" href="./css/viewprofile.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">View Profile</h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <div class="container">
                <?php
                $user=getProfile($_SESSION['user_id'],$conn);
                if ($user){
                ?> 
            <img  src="../profile_pic/<?php echo $user['profile_pic'] ?>" loading="lazy" width="239" alt="profile image" class="profile">   
            <div class="text"><?php echo $user['first_name']." ".$user['last_name'] ?></div>
            <h4 class="head4"><b>Teacher Number: </b><i><?php echo $user['user_id']?><i></h4>
            <h4 class="head4"><b>Email Address: </b><i><?php echo $user['email_address'] ?></i></h4><br> <?php } ?>
        <a href="../edit_profile.php?id=<?php echo $user['user_id'] ?>" class="btn btn-primary">Edit Details</a>             
    </div>
    </body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>