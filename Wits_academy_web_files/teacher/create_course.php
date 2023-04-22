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
        Create Course
    </title>
    <!--<link type="text/css" href="create_course.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>

.body {
    background-color: #bed2e2;
    font-family: 'Droid Serif', serif;
}

.section {
    background-color: #1a2852;
    justify-content: flex-start;
    display: flex;
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

.head2 {
    float: none;
    text-align: center;
    font-family: Montserrat, sans-serif;
    font-size: 30px;
    font-style: normal;
    font-weight: 800;
    text-decoration: none;
    position: static;
}

.form-block {
    position: relative;
    top: 34px;
  }

.form {
    text-align: left;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    font-family: Montserrat, sans-serif;
    font-weight: 700;
    display: flex;
    position: relative;
    top: -30px;
}

.select-faculty, .select-school, .course_code, .course_name {
    border-radius: 18px;
  }

    </style>

</head>
    <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Create Course</h1>
        <a href="./index.php" class="link">Back To Dashboard</a>
    </div>

    <div class="container">
        <h2 class="head2">Please fill in all details below:</h2>
        <div class="form-block">
            <form class="form">
                <label for="Faculty">Faculty:</label>
                <select  name="Faculty" required="" class="select-faculty">
                    <option value="">Select faculty</option>
                    <option value="clm">Commerce, Law and Management</option>
                    <option value="ebe">Engineering and the Built Environment</option>
                    <option value="hs">Health Sciences</option>
                    <option value="humanities">Humanities</option>
                    <option value="science">Science</option>
                </select>
                <label for="school">School:</label>
                <select name="school" class="select-school">
                    <option value="">Select school</option>
                    <option value="accounting">Accountancy</option>
                    <option value="bs">Business Sciences</option>
                    <option value="ef">Economics and Finance</option>
                    <option value="law">Law</option>
                    <option value="wbs">Wits Business School</option>
                    <option value="#">Another option</option>
                </select>
                <label for="course_code">Course Code:</label>
                <input type="text" class="course_code" maxlength="128" name="field" placeholder="Enter course code"required="" />
                <label for="course_name">Course Name:</label>
                <input type="text" class="course_name" maxlength="256" name="course_name" placeholder="Enter Course Name" required="" />
                <label for="course_password">Course Password:</label>
                <input type="password" class="password" maxlength="256" name="course_password" placeholder="Enter Course Password" required="" />
                <button type="submit"  data-wait="Please wait..." class="create">Create</button>
                </form>
        </div>

    </body>
</html>

<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>