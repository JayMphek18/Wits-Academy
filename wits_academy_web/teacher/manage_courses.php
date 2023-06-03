<?php
    require("database.php");
?>

<?php 
if ($_SESSION['user_id']) {
    ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Manage Courses
    </title>
    <link type="text/css" href="./css/manage.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Manage Courses</h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <ul role="list" class="list-10">
    <?php 
        $user=$_SESSION['user_id'];
        $sql="select * from courses where teacher='$user'";
        $result=$conn->query($sql);

        if(mysqli_num_rows($result) == 0){
    ?><li class="list-item">No courses available</li>
    <?php }else{
        while($course=$result->fetch_assoc()){
            ?>
            <li class="list-item-8">
            <div class="div-block-12">
                <div class="text-block-18"><i class="fas fa-book"></i> <?php echo $course['course_code']." ".$course['course_name']?></div>
                <a href="course.php?id=<?php echo $course['course_id']?>" class="text-block-19"><i class="fas fa-eye"></i> View details</a>
                <a href="deleteCourse.php?id=<?php echo $course['course_id']?>" class="text-block-20"><i class="fas fa-trash"></i> Delete Course</a>
            </div>
        </li>
    <?php
        }
    }
    ?>
    </ul>
    </body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>
