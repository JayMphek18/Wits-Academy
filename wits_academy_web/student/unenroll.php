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
    <link type="text/css" href="../teacher/css/manage.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Manage Courses</h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <?php $id = $_GET['id'] ?>

    <div class="form-block">
            <form class="form" action="../php/unenroll.php?id=<?php echo $id ?>" method="post">
                <label for="answer">Are you sure you want to un-enroll from this course?</label>
                <label class="w-radio"><input type="radio" name="answer" value="yes" class="w-form-formradioinput w-radio-input" />
                <span class="w-form-label" for="radio">Yes</span></label>
                <label class="w-radio"><input type="radio" name="answer" value="no" class="w-form-formradioinput w-radio-input" />
                <span class="w-form-label" for="radio">No</span></label><br>
                <div class="text-block1"> You will have no accss to the course resources after un-enrolling.</div><br>
                <input type="submit"  class="delete" name="unenroll" value="Submit" />
            </form>
            <br>
            <center><a href="courses.php" class="btn btn-primary"> Back </a></center>
        </div>
    </body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>