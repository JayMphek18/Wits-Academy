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
        Participants
    </title>
    <link type="text/css" href="./index.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
    <body class="body">
   
    <?php
        $id = $_GET['id'];
        ?>
         <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" width="239" alt="Wits emblem" />
        <h1 class="head1">Participants</h1>
       <a href="./course.php?id=<?php echo $id ?>" class="link"><i class="fas fa-house"></i> Back To Home</a>
        </div>
        <?php 
        $sql="select * from enrollment where course_id='$id'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();
        $rowcount=mysqli_num_rows($result);
        if(!($rowcount))
            echo "<br><center><h2><b><i>No participants in this course</i></b></h2></center>";
        else
        {
        ?>
                    <table class="table" id = "tables">
                    <thead>
                    <tr>
                        <th></th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Student Number</th>
                        <th>Email Address</th>
                    </tr>
                    </thead>
                    <tbody>
    <?php
            //$result=$conn->query($sql);
        $sql1="select * from enrollment inner join registration where enrollment.student_num=registration.user_id && course_id='$id'";
        $result1=$conn->query($sql1);
        while($row=$result1->fetch_assoc())
            { 
              ?>
                    <tr>
                        <td><img src="../profile_pic/<?php echo $row['profile_pic']?>" alt="profile_picture" loading="lazy" width="50"></td>
                        <td><?php echo $row['first_name'] ?></td>
                        <td><?php echo $row['last_name'] ?></td>
                        <td><?php echo $row['student_num'] ?></td> 
                        <td><a href="mailto: <?php echo $row['email_address'] ?>"><?php echo $row['email_address'] ?></a></td>  
                    </tr>
                <?php }} ?>
                </tbody>
            </table>
        
    </body>

</html>
<?php }else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>