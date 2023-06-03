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
        Search Course
    </title>
    <link type="text/css" href="./css/searchcourse.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
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
                                $id=$row['course_id'];
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
                                      		echo "<a href=\"enroll_course.php?id=".$id."\" class=\"btn btn-success\">Enroll</a>";
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