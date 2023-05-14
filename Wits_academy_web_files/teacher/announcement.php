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
            <form class="form" method="post" action="announcement.php?id=<?php echo $id ?>" enctype="multipart/form-data">
            <div><h2><center>Please fill in all details below:</center></h2></div>
                <input  hidden="hidden" type="text" name="id" value="<?php echo $id ?>" required="" />
                <label for="Announcement">Announcement:</label>
                <textarea class="input" name="announcement" rows="4" cols="50" placeholder="Enter your announcement"></textarea>
                <button type="submit"  class="submit" name="create">Send</button>
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
     //read data entered by user and submit to database
     if(isset($_POST["create"])){
        $date = date('y-m-d h:i:s');
        //echo $date;
        $announce= $_POST["announcement"];
        $course_id=$_POST["id"];
       
        //create array to store the errors
        $errors = array();
       
        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $sql = "INSERT INTO announcement (course_id,date_time,announce_text) VALUES (?,?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement,"sss",$course_id,$date,$announce);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('Your announcement have been sent!)')</script>";
                
              }else{
                die("Something went wrong :(");

            }
        }
      }
?>
