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
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" width="239" alt="Wits emblem" />
        <h1 class="head1">Participants</h1>
       <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <label>
 
    </label>
    <?php
                    
                    $user_id=$_SESSION['user_id'];
                    $sql="select * from enrollment where student_num='$user_id'";
                    $result=$conn->query($sql);
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
                    </tr>
                    </thead>
                    <tbody>
                    <?php
            
            //$result=$conn->query($sql);
            while($row=$result->fetch_assoc())
            {
                $code=$row['course_code'];
                $sql1="select * from enrollment where course_code='$code'";
                $result1=$conn->query($sql1);
                $row1=$result1->fetch_assoc();
                $snum=$row1['student_num'];
                $sql2="select * from registration where user_id='$snum'";
                $result2=$conn->query($sql2);
                $row2=$result2->fetch_assoc();
                $name=$row2['first_name'];
                $lname=$row2['last_name'];
                echo $name
                
                
               
                

            ?>
                    <tr>
                        <td>
                    
                        
                        <td><?id=<?php echo $code ?>"><?php echo $name ?></a></td>
                        <td><?id=<?php echo $code ?>"><?php echo $lname ?></a></td>
                        <td><?id=<?php echo $code ?>"><?php echo $snum ?></a></td>
                    </tr>
                <?php }} ?>
                </tbody>
                </table>
    </body>

</html>
<?php }else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>