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
        Course Manager
    </title>
    <link type="text/css" href="./css/index.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" width="239" alt="Wits emblem" />
        <h1 class="head1">Courses Dashboard</h1>
    </div>
    <label>
    <input type="checkbox">
        <div class="toggle">
            <span class="top_line common"></span>
            <span class="middle_line common"></span>
            <span class="bottom_line common"></span>
        </div>
        <div class="slide">
         <h2 class="head2">MENU </h2>
            <ul>
                <li><a href="./index.php"><i class="fas fa-gauge"></i>Dashboard</a></li>
                <li><a href="./profileview.php"><i class="fas fa-user"></i>Profile</a></li>
                <li><a href="./create_course.php"><i class="fas fa-add"></i>Create Course</a></li>
                <li><a href="manage_courses.php"><i class="fas fa-folder"></i>Manage Courses</a></li>
                <li><a href="logout.php"><i class="fas fa-arrow-right-from-bracket"></i>Logout</a></li>
            </ul>
        </div>
    </label>
<?php
                  
                    $user_id=$_SESSION['user_id'];
                    $sql="select * from courses where teacher='$user_id'";
                    $result=$conn->query($sql);
                    $rowcount=mysqli_num_rows($result);
                    if(!($rowcount))
                        echo "<br><center><h2><b><i>Create/ Add Course</i></b></h2></center>";
                    else
                    {
                    ?>
                    <table class="table" id = "tables">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Course Code</th>
                        <th>Course name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
            
            //$result=$conn->query($sql);
            while($row=$result->fetch_assoc())
            {
                $course_id=$row['course_id'];
                $pic=$row['picture'];
                $code=$row['course_code'];
                $course=$row['course_name'];

            ?>
                    <tr>
                        <td>
                            <img src="./course_pic/<?php echo $pic ?>" alt="course_picture" loading="lazy" width="50" />
                        </td>
                        <td><a href="./course.php?id=<?php echo $course_id ?>"><?php echo $code ?></a></td>
                        <td><a href="./course.php?id=<?php echo $course_id ?>"><?php echo $course ?></a></td>
                    </tr>
                <?php }} ?>
                </tbody>
                </table>
    </body>

</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>

