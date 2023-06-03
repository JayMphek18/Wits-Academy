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
            
        ?>     
        <<div class="form-block">
            <form class="form" method="post" action="../php/upload.php?id=<?php echo $id ?>" enctype="multipart/form-data">
            <div><h2><center>Fill in the following Details</center></h2></div>
                <input hidden="hidden" type="text" maxlength="10" name="id" value="<?php echo $id ?>" required="" />
                <label for="type">Type of course material:</label>
                <select  name="type" required="" class="input">
                    <option value="">Select type of course material</option>
                    <option value="slides/notes">Lecture slides/ notes</option>
                    <option value="video">Lecture video</option>
                    <option value="tutorial">Tutorial</option>
                    <option value="extra resources">Extra Resources</option>
                </select>
                <label for="material"> Upload course material</label>
		        <input type="file" name="material"/>
                <div class="text-block1">Acceptable file types: all types, must be less than 25MB </div>
                <label for="name"> Name of file: </label>
                <input type="text" class="input" name="name" placeholder="Enter the name of the file" required />
                <button type="submit"  class="submit" name="insert">Upload</button>
            </form>
        </div>
            <section class="section2">
            <ul>
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

