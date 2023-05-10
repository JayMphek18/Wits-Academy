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
        Enroll into Course
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
.container {
    position: relative;
    top: 49px;
}
.select-faculty, .course_year {
    border-radius: 18px;
}

.search{
    background-color: #1a2852;
    border-radius: 18px;
    color: #c4d1db ;
    font-family: Droid Sans, sans-serif;
  }

    </style>


</head>
<body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1">Search Course</h1>
        <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <div class="container">
        <div class="form-block">
            <form class="form" action="search_course.php" method="post">
                <label for="faculty">Faculty:</label>
                <select  name="faculty" class="select-faculty" required="">
                    <option value="">Select faculty</option>
                    <option value="Commerce, Law and Management">Commerce, Law and Management</option>
                    <option value="Engineering and the Built Environment">Engineering and the Built Environment</option>
                    <option value="Health Sciences">Health Sciences</option>
                    <option value="Humanities">Humanities</option>
                    <option value="Science">Science</option>
                </select>
                <label for="course_year">Year of Study:</label>
                <input type="number" class="course_year" maxlength="2" name="year" placeholder="Enter year of study"required="" />
                <button type="submit"  class="search" name="search">Search</button>
            </form>
            <?php
                                    if(isset($_POST['search']))
                                    {
                                        $faculty=$_POST['faculty'];
                                        $yos=$_POST['year'];
                                        $sql="select * from courses where faculty='$faculty' and year_of_study='$yos'";
                                    $result=$conn->query($sql);
                                    $rowcount=mysqli_num_rows($result);

                                    if(!($rowcount))
                                        echo "<br><center><h2><b><i>No Results</i></b></h2></center>";
                                    else
                                    {
                                    ?>
                                    <table class="table" id = "tables">
                                  <thead>
                                    <tr>
                                      <th>Course Code</th>
                                      <th>Course name</th>
                                      <th>Faculty</th>
                                      <th>School</th>
                                      <th></th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <?php
                            
                            //$result=$conn->query($sql);
                            while($row=$result->fetch_assoc())
                            {
                                $code=$row['course_code'];
                                $course=$row['course_name'];
                                $faculty=$row['faculty'];
                                $school=$row['school'];
                            ?>
                                    <tr>
                                      <td><?php echo $code ?></td>
                                      <td><?php echo $course ?></td>
                                      <td><?php echo $faculty ?></td>
                                      <td><?php echo $school ?></td>
                                      <td><center><?php
                                      		echo "<a href=\"enroll_course.php?id=".$code."\" class=\"btn btn-success\">Enroll</a>";
                                        ?>
                                        </center></td>
                                    </tr>
                               <?php }} }?>
                               </tbody>
                                </table>
                            </div>


    </html>

<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>