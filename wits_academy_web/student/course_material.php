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
        Course Modules
    </title>
    <link type="text/css" href="./css/coursematerial.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
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
    <section class="section3">
     
    <h3 class="head3"><i class="fas fa-folder"></i> Lecture Slides/ Notes</h3>
    <?php 
        $sql1="SELECT * FROM upload WHERE course_id='$id' AND type='slides/notes'";
        $result1=$conn->query($sql1);
        $rowcount=mysqli_num_rows($result1);
        if(!($rowcount)){
          ?>
          <ul role="list" class="list">
                <li class="list-item"> No Lecture Slides/ Notes</li>
          </ul>
        <?php }else{
        while($row1=$result1->fetch_assoc()){

        $path=$row1['course_material'];
        $filename=$row1['name_of_file'];
        $type=$row1['type'];
        if($type == 'slides/notes'){
      ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="../teacher/upload_material/<?php echo $path ?>" class="link_list" download><?php echo $filename ?> <i class="fas fa-download"></i></a></li>
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
                <li class="list-item"> No Lecture Videos</li>
          </ul>
        <?php }else{
          while($row1=$result1->fetch_assoc()){

          $path=$row1['course_material'];
          $filename=$row1['name_of_file'];
          $type=$row1['type'];
          if($type == 'video'){
    ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="../teacher/upload_material/<?php echo $path ?>" class="link_list" download><?php echo $filename ?> <i class="fas fa-download"></i></a></li>
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
                <li class="list-item"> No Tutorials</li>
          </ul>
        <?php }else{
        while($row1=$result1->fetch_assoc()){

        $path=$row1['course_material'];
        $filename=$row1['name_of_file'];
        $type=$row1['type'];
        if($type == 'tutorial'){
      ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="../teacher/upload_material/<?php echo $path ?>" class="link_list" download><?php echo $filename ?> <i class="fas fa-download"></i></a></li>
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
                <li class="list-item"> No Extra Resources</li>
          </ul>
        <?php }else{
        while($row1=$result1->fetch_assoc()){
        
        $path=$row1['course_material'];
        $filename=$row1['name_of_file'];
        $type=$row1['type'];
        if($type == 'extra resources'){
        ?>
            <ul role="list" class="list">
                <li class="list-item"><a href="../teacher/upload_material/<?php echo $path ?>" class="link_list" download><?php echo $filename ?> <i class="fas fa-download"></i></a></li>
            </ul>
        <?php }}}?>
    </section>
              
            <section class="section2">
            <ul class="ul">
            <li><a href="course.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-house"></i>Home</a></li>
                <li><a href="participants.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-users"></i>Participants</a></li>
                <li><a href="announcements.php?id=<?php echo $id?>" class="link-3"><i class="fas fa-bullhorn"></i>Announcements</a></li>
                <li><a href="student_question.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-question"></i>Q&amp;A Forum</a></li>
                <li><a href="course_material.php?id=<?php echo $id ?> " class="link-3"><i class="fas fa-file"></i>Course Materials</a></li>
                <li><a href="quizzes.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-check"></i>Quizzes</a></li>
                <li><a href="feedback.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-comment"></i>Send Course Feedback</a></li>
                <li><a href="viewCourse.php?id=<?php echo $id ?>" class="link-3"><i class="fas fa-info-circle"></i>View Course Details</a></li>
                <li><a href="logout.php" class="link-3"><i class="fas fa-arrow-right-from-bracket"></i>Logout</a></li>
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