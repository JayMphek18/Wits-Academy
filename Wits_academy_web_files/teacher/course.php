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
.block {
    background-color: rgba(0, 0, 0, 0);
    justify-content: space-between;
    font-family: Droid Sans, sans-serif;
    font-weight: 700;
    display: inline-block;
    position: relative;
    top: 100px;
    left: 270px;
  }

  .head3 {
    text-shadow: 1px 0 1px rgba(0, 0, 0, .2);
    font-family: Droid Sans, sans-serif;
    text-decoration: none;
    position: relative;
    top: 95px;
    left: 265px;
  }
  .list {
    font-family: Droid Sans, sans-serif;
    font-weight: 400;
    position: relative;
    top: 88px;
    left: 243px;
    list-style: none;
  }
  .list-item {
    font-family: Montserrat, sans-serif;
    font-weight: 500;
  }
  .link_list {
    font-family: Montserrat, sans-serif;
    color: blue;
    text-decoration: none;
  }
    </style>

</head>
<body class="body">
    <?php
        $id = $_GET['id'];
        $sql="select * from courses where course_id='$id'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();

        $course=$row['course_name'];
        $code=$row['course_code'];
    ?>   
     
    <div class="block">
    <a href="upload.php?id=<?php echo $id ?>" class="btn btn-primary"><i class="fas fa-add"></i> Upload</a>
    </div>
    <h3 class="head3"><i class="fas fa-folder"></i> Slides/ Notes</h3>
    <?php 
        $sql1="SELECT * FROM upload WHERE course_id='$id' AND type='slides/notes'";
        $result1=$conn->query($sql1);
        $rowcount=mysqli_num_rows($result1);
        if(!($rowcount)){
          ?>
          <ul role="list" class="list">
                <li class="list-item"> Upload Lecture Slides/ Notes</li>
          </ul>
        <?php }else{
        while($row1=$result1->fetch_assoc()){

        $path=$row1['course_material'];
        $filename=$row1['name_of_file'];
        $type=$row1['type'];
        if($type == 'slides/notes'){
      ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="./upload_material/<?php echo $path ?>" class="link_list" ><?php echo $filename ?> <i class="fas fa-eye"></i></a></li>
            </ul>
            <?php }}} ?>
            
            <h3 class="head3"><i class="fas fa-file-video"></i> Lecture Videos</h3>
    <?php 
          $sql1="SELECT * FROM upload WHERE course_id='$id' AND type='video'";
          $result1=$conn->query($sql1);
          $rowcount=mysqli_num_rows($result1);
        if(!($rowcount)){
          ?>
          <ul role="list" class="list">
                <li class="list-item"> Upload Lecture Videos</li>
          </ul>
        <?php }else{
          while($row1=$result1->fetch_assoc()){

          $path=$row1['course_material'];
          $filename=$row1['name_of_file'];
          $type=$row1['type'];
          if($type == 'video'){
    ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="./upload_material/<?php echo $path ?>" class="link_list" ><?php echo $filename ?> <i class="fas fa-eye"></i></a></li>
            </ul>
            <?php }} } ?>

            <h3 class="head3"><i class="fas fa-folder"></i> Tutorials</h3>
      <?php 
        $sql1="SELECT * FROM upload WHERE course_id='$id' AND type='tutorial'";
        $result1=$conn->query($sql1);
        $rowcount=mysqli_num_rows($result1);
        if(!($rowcount)){
          ?>
          <ul role="list" class="list">
                <li class="list-item"> Upload Tutorials</li>
          </ul>
        <?php }else{
        while($row1=$result1->fetch_assoc()){

        $path=$row1['course_material'];
        $filename=$row1['name_of_file'];
        $type=$row1['type'];
        if($type == 'tutorial'){
      ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="./upload_material/<?php echo $path ?>" class="link_list" ><?php echo $filename ?> <i class="fas fa-eye"></i></a></li>
            </ul>
            <?php }} }?>

        <h3 class="head3"><i class="fas fa-folder-plus"></i> Extra Resources</h3>
      <?php 
        $sql1="SELECT * FROM upload WHERE course_id='$id' AND type='extra resources'";
        $result1=$conn->query($sql1);
        $rowcount=mysqli_num_rows($result1);
        if(!($rowcount)){
          ?>
          <ul role="list" class="list">
                <li class="list-item"> Upload Extra Resources</li>
          </ul>
        <?php }else{
        while($row1=$result1->fetch_assoc()){
        
        $path=$row1['course_material'];
        $filename=$row1['name_of_file'];
        $type=$row1['type'];
        if($type == 'extra resources'){
        ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="./upload_material/<?php echo $path ?>" class="link_list" ><?php echo $filename ?> <i class="fas fa-eye"></i></a></li>
            </ul>
        <?php }}}?>
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