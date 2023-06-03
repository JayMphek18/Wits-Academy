<?php
    require('database.php');
    require('../php/getCourse.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Participants
    </title>
    <link type="text/css" href="#" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
        .body {
  background-color: #bed2e2;
  font-family: 'Droid Sans', sans-serif;
}

.section2{
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

.ul li{
 list-style: none;
}
.ul li a{
 font-family: 'Droid Sans', sans-serif;
 font-weight: 500px;
 padding: 5px 0;
 display: block;
 text-decoration: none;
 transition: 0.2s ease-out;
 color: #c4d1db;
}
.ul li:hover a{
 color: #1a2852;
 background-color: #c4d1db;
}
.ul li i{
 width: 40px;
 text-align: justify;
}

.section-9 {
    position: relative;
    top: 95px;
    left: 300px;
}

.heading {
  font-family: Droid Sans, sans-serif;
  font-size: 30px;
  font-style: normal;
  font-weight: 800;
  text-decoration: none;
  position: relative;
  left: -38.01px;
}

.list-5 {
  font-family: Montserrat, sans-serif;
    font-weight: 600;
    list-style-type: none;
    position: relative;
    left: -41px;
}

.list-item {
  justify-content: flex-start;
  display: flex;
}

.heading-18 {
  padding-left: 35px;
}

.heading-19 {
  padding-left: 67px;
}

.heading-20 {
  padding-left: 54px;
}

.image-19 {
  border: 3px solid #f8f8f8;
  border-radius: 35px;
}
        </style>
</head>
    <body class="body">
    <section class="section-9">
        <h2 class="heading">Participants</h2>
            <ul role="list" class="list-5">
   
    <?php
        $id = $_GET['id'];
        $course=getCourse($id, $conn);
        $sql="select * from enrollment where course_id='$id'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();
        $rowcount=mysqli_num_rows($result);
        if(!($rowcount)){?>
        <li class="list-item">No participants available in course</li>
        <?php   
        }else
        {
        $sql1="select * from enrollment inner join registration where enrollment.student_num=registration.user_id && course_id='$id'";
        $result1=$conn->query($sql1);
        while($row=$result1->fetch_assoc())
            { 
    ?>
            <li class="list-item">
                <img src="../profile_pic/<?php echo $row['profile_pic'] ?>" loading="lazy" width="68" sizes="67.98611450195312px"
                    alt="profile_pic" class="image-19" />
                <h3 class="heading-18"><?php echo $row['first_name']." ".$row['last_name'] ?></h3>
                <h3 class="heading-19"><?php echo $row['role'] ?></h3>
                <h3 class="heading-20"><a href="mailto: <?php echo $row['email_address'] ?>"><?php echo $row['email_address'] ?></a></h3>
            </li>         
    <?php 
        }?>
<?php } 
    ?>  </ul>
    </section>
    
            <section class="section2">
            <ul class="ul">
                <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="viewAnnounce.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcement</a></li>
                <li><a href="teacher_answer.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="quizzes.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-check"></i> Quizzes</a></li>
                <li><a href="view_feedback.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-comments"></i>View Course Feedback</a></li>
                <li><a href="edit_course.php?id=<?php echo $id ?>" class="link-3"><i class="fa-solid fa-pen-to-square"></i>Edit Course Details</a></li>
                <li><a href="logout.php" class="link-3"><i class="fas fa-arrow-right-from-bracket"></i>Log Out</a></li>
            </ul>
            </section> 
           
            <div class="container">
            <div class="section1">
            <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
            <h1 class="head1"><?php echo $course['course_code'].":".$course['course_name'] ?></h1>
            <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
            </div>
            </div>
        
    </body>

</html>
<?php }else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>