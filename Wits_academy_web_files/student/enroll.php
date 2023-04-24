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
html {
    -ms-text-size-adjust: 100%;
    -webkit-text-size-adjust: 100%;
    font-family: sans-serif;
  }
  
 html {
    height: 100%;

  }
   .field-label-3 {
    width: 50%;
    color: #c4d1db;
    font-family: Droid Sans, sans-serif;
    font-size: 30px;
    position: relative;
    top: -40px;
    left: 264px;
  }
  body {
    min-height: 100%;
    
  }
  
  img {
    max-width: 100%;
    vertical-align: middle;
    display: inline-block;
  }
  
  
  

  
  .w-button {
    color: #fff;
    line-height: inherit;
    cursor: pointer;
    background-color: #3898ec;
    border: 0;
    border-radius: 0;
    padding: 9px 15px;
    text-decoration: none;
    display: inline-block;
  }
  
  html[data-w-dynpage] [data-w-cloak] {
    color: rgba(0, 0, 0, 0) !important;
  }
  
  
  h1, h2, h3, h4, h5, h6 {
    color: #c4d1db;
    font-family: Droid Sans;
    margin-bottom: -2px;
    padding-left: 250px;
    padding-right: 10px;
    font-weight: bold;
    position: relative
    top:-40px;
    
  }
 
   label {
    margin-bottom: 5px;
    font-weight: bold;
    display: block;
  }
  
   .image {
    background-color: #1a2852;
  }
  
  .section {
    width: 1334px;
    background-color: #1a2852;
  }
  
  .body {
    background-color: #bed2e2;
    border: 1px solid #fff;
  }
  
  .select-field {
    width: 80%;
    opacity: 1;
    text-align: left;
    object-fit: scale-down;
    object-position: 50% 50%;
    border: 1px solid #000;
    border-radius: 20px;
    font-size: 14px;
    position: relative;
    top: 145px;
    bottom: -175px;
    left: 146px;
    right: -13px;
  }
  
  .submit-button {
    background-color: #c1c1c4e;
    border-radius: 18px;
    position: relative;
    top: 154px;
    left: 639px;
  }
  
  .button {
    color: #c4d1db;
    background-color: #1a2852;
    font-family: Droid Sans, sans-serif;
    font-size: 25px;
    font-style: normal;
    font-weight: 700;
    position: relative;
    top: -75px;
    left: 1015px;
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
        <div class="form-block">
            <form class="form" action="enroll.php" method="post">
                <label for="faculty">Faculty:</label>
                <select  name="faculty" required="" class="select-faculty">
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
                                      <td><center><a href="enter_password.php?id=<?php echo $code; ?>" class="btn btn-primary">Enroll</a>
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
