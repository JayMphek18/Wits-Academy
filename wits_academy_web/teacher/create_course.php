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
        Create Course
    </title>
    <link type="text/css" href="./css/create_course.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Create Course</h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>

    <div class="container">
        <h2 class="head2">Please fill in all details below:</h2>
        <div class="form-block">
            <form class="form" action="../php/create_course.php" method="post" enctype="multipart/form-data">
                <label for="faculty">Faculty:</label>
                <select  name="faculty" required="" class="select-faculty">
                    <option value="">Select faculty</option>
                    <option value="Commerce, Law and Management">Commerce, Law and Management</option>
                    <option value="Engineering and the Built Environment">Engineering and the Built Environment</option>
                    <option value="Health Sciences">Health Sciences</option>
                    <option value="Humanities">Humanities</option>
                    <option value="Science">Science</option>
                </select>
                <label for="school">School:</label>
                <input type="text" name="school" class="select-faculty" placeholder="Enter school of the course" required />
                <label for="course_year">Course Year of Study:</label>
                <input type="number" class="course_year" maxlength="2" name="course_year" placeholder="Enter course year of study"required="" />
                <label for="course_code">Course Code:</label>
                <input type="text" class="course_code" maxlength="10" name="course_code" placeholder="Enter course code"required="" />
                <label for="course_name">Course Name:</label>
                <input type="text" class="course_name" maxlength="256" name="course_name" placeholder="Enter Course Name" required="" />
                <label for="pic">Course Picture</label>
		        <input type="file" class="pic" name="pic"/>
                <div class="text-block1">Acceptable file types: .jpg, .jpeg, .png</div>
                <label for="description"> Course Description: </label>
                <textarea class="describe" rows="20" cols="60" name="description" placeholder="Enter course details"></textarea>
                <label for="course_password">Course Password:</label>
                <input type="password" class="password" maxlength="10" name="course_password" placeholder="Enter Course Password" required="" />
                <div class="text-block1">Enter the course password</div>
                <label for="confirm_password">Confirm Password:</label>
                <input type="password" class="cpassword" maxlength="10" name="cpassword" placeholder="Re-enter Course Password" required="" />
                <div class="text-block1">Re-enter the course password</div>
                <button type="submit"  class="create" name="create">Create</button>
                </form>
        </div>

    </body>

</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>

