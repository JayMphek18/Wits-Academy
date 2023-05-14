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
        Create Announcement
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
    left: 330px;
    right: 0;
  }

.head2 {
    float: none;
    text-align: center;
    font-family: Montserrat, sans-serif;
    font-size: 25px;
    font-style: normal;
    font-weight: 800;
    text-decoration: none;
    position: static;
}
.container {
    position: relative;
    top: 49px;
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

.cpassword {
   #textboxid
{
    height:2000px;
    font-size:40pt;
}
  }
.course_year {
    border-radius: 18px;
  }

.text-block1{
    font-family: 'Droid Sans', sans-serif;
    font-size: 11px;
}

.create{
    background-color: #1a2852;
    border-radius: 18px;
    color: #c4d1db ;
    font-family: Droid Sans, sans-serif;
  }

    </style>

</head>

   <body class="body">
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" width="239" alt="Wits emblem" />
        <h1 class="head1">Create Announcement</h1>
       <a href="./index.php" class="link"><i class="fas fa-gauge"></i>Back To Dashboard</a>
    </div>

  
    <div class="container">
        <h2 class="head2">Please fill in all details below:</h2>
        <div class="form-block">
<form class="form" action="announcement.php" method="post">
      
             
                <label for="Date">Date/Time of announcement:</label>
                <input type="date" class="course_year" name="Date" placeholder="Enter Date/Time of announcement"required="" />
            

<label for="w3review">Announcement:</label>
<textarea id="" name="Announcement" rows="4" cols="50">
              


 </textarea>
                <button type="submit"  class="create" name="create">Create</button>
                </form>
        </div>

    </body>
    
             
    <?php

     

     //read data entered by user and submit to database
     if(isset($_POST["create"])){
     

       
        
        $date = $_POST["Date"];
        $announce= $_POST["Announcement"];
       
        //create array to store the errors
        $errors = array();
        
       
      

        $user_id = $_SESSION['user_id'];
       
     
        

       

      
        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $sql = "INSERT INTO announcement (date_time,announce_text) VALUES (?,?)";
            $statement = mysqli_stmt_init($conn);
            $prepare = mysqli_stmt_prepare($statement, $sql);
            if($prepare){
                mysqli_stmt_bind_param($statement,"sssssss",$date,$announce);
                mysqli_stmt_execute($statement);
                echo "<script type='text/javascript'>alert('You have successfully created your announcement!)')</script>";
                header( "Refresh:0.01; url=index.php", true, 303);
              }else{
                die("Something went wrong :(");
echo $code;
            }
        }
      }
?>
</html>


<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>