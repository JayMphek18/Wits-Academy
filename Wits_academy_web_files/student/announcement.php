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
        <h1 class="head1">Announcements</h1>
       <a href="./index.php" class="link"><i class="fas fa-gauge"></i> Back To Dashboard</a>
    </div>
    <label>
 
    </label>
    <?php
                     $code = $_GET['id'];
        $sql="select * from courses where course_code='$code'";
        $result=$conn->query($sql);
        $row=$result->fetch_assoc();

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
                        <th>Announcement ID</th>
                        <th>Date/Time</th>
                        <th>Announcement</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
          
            //$result=$conn->query($sql);
        $sql1="select * from announcement where course_code='$code'";
 $result1=$conn->query($sql1);
        while($row=$result1->fetch_assoc())
            {
               
              ?>
                    <tr>
                        <td>
                    
                        
                        <td><?id=<?php echo $code ?>"><?php echo $row['announce_id'] ?></a></td>
                        <td><?id=<?php echo $code ?>"><?php echo $row['date_time'] ?></a></td>
                        <td><?id=<?php echo $code ?>"><?php echo $row['announce_text'] ?></a></td>
                    
                        
                    </tr>
                <?php }} ?>
                </tbody>
                </table>
    </body>

</html>
<?php }else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>