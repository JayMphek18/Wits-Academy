<?php
    require('database.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Course Homepage
    </title>
    <link type="text/css" href="#" rel="stylesheet">
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
    section{
        max-width: 100px;
        min-height: 1000px;
        min-width: 250px;
        text-align: center;
        background-color: #1a2852;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        font-family: Droid Sans, sans-serif;
        font-weight: 700;
        display: flex;
        position: fixed;
        top: 101px;
    }
    .image-12 {
  position: relative;
  top: 104px;
  left: 257px;
}
.rich-text-block {
  position: relative;
  top: 101px;
  left: 263px;
}

    .head1{
        color: #c4d1db;
        font-family: Droid Sans, sans-serif;
        font-size: 25px;
        font-weight: 700;
        position: relative;
        bottom: -24px;
        left: 26px;
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
  .link-3 {
    color: #c4d1db;
    font-family: Droid Sans, sans-serif;
    font-size: 20px;
    font-weight: 700;
    text-decoration: none;
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
.container2 {
    max-height: 1000px;
    max-width: 1000px;
    min-height: 800px;
    position: fixed;
    top: 100px;
    left: 224px;
}
.text-block-7 {
    max-height: 500px;
    max-width: 1000px;
    min-height: 0;
    font-family: Exo, sans-serif;
    font-size: 20px;
    font-weight: 600;
}
    </style>

</head>
<body class="body">
    <?php
        $code = $_GET['id'];
        $sql="select * from courses where course_code='$code'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();

        $course=$row['course_name'];
        $description = $row['description'];
        $picture=$row['picture'];
        $password=$row['password'];
    ?>     
            <img src="./course_pic/<?php echo $picture ?>" loading="lazy" width="359" sizes="(max-width: 479px) 100vw, 358.9930725097656px" alt="course_picture" class="image-12" /> 
            <div class="rich-text-block">
                <p> <?php echo $description ?>
            </div> 
            <section class="section2">
            <ul>
                <li><a href="#" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-bullhorn"></i>Announcements</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-book"></i>Course Materials</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-book"></i>Quizzes</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-book"></i>Assignments</a></li>
                <li><a href="#" class="link-3"><i class="fas fa-comment"></i>Send Course Feedback</a></li>
                <li><a href="edit_course.php?id=<?php echo $code ?>" class="link-3"><i class="fas fa-info-circle"></i>View Course Details</a></li>
            </ul>
            </section>
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