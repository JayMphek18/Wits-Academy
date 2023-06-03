<?php
    require('database.php');
    require('./php/getProfile.php');
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
    <link type="text/css" href="./css/editprofile.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
<div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Edit Profile</h1>
        <!--<a href="./index.php" class="link">Back To Dashboard</a>-->
    </div>
    <?php
        $user=getProfile($_SESSION['user_id'],$conn);
        if ($user){
    ?>    
    <div class="container">
    <h2 class="head2">Update Profile</h2>
    <br>
    <div class="form-block">
        <form class="form" name="form" action="./php/edit_profile.php?id=<?php echo $user['user_id']?>" method="post" enctype="multipart/form-data">
            <label for="fname">First name:</label>
            <input class="input" name="fname" type="text" value="<?php echo $user['first_name'] ?>"/>
            <label for="lname">Last name:</label>
            <input class="input" name="lname" type="text" value="<?php echo $user['last_name'] ?>"/>
            <label for="email">Email Address:</label>
            <input class="input" name="email" type="email" value="<?php echo $user['email_address'] ?>"/>
            <label for="pp">Profile Picture</label>
		        <input type="file" name="pp" />
            <img src="profile_pic/<?php echo $user['profile_pic']?>" class="rounded-circle" style="width: 70px" />
            <input type="text" hidden="hidden"  name="old_pp" value="<?php echo $user['profile_pic']?>" />
            <div class="text-block1">Acceptable file types: .jpg, .jpeg, .png</div>
            <button type="submit" name="update" class="btn btn-primary">Update</button>
        </form>
    </div>
    <?php } 
    ?>
    </div>
</body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>