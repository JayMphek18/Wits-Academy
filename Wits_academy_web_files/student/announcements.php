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
        <h1 class="head1">Announcements</h1>
       <a href="./course.php?id=<?php echo $id ?>" class="link"><i class="fas fa-home"></i> Back To Home</a>
    </div>
    <?php
        
        $sql="select * from announcement where course_id='$id'";
        $result=$conn->query($sql);
        $rowcount=mysqli_num_rows($result);
        if(!($rowcount))
            echo "<br><center><h2><b><i>No announcements in this course</i></b></h2></center>";
        else
        {
        ?>
                    <table class="table" id = "tables">
                    <thead>
                    <tr>
                        <th>Date/Time</th>
                        <th>Announcement</th>
                    </tr>
                    </thead>
                    <tbody>
    <?php
        while($row=$result->fetch_assoc())
            { 
              ?>
                    <tr>
                        <td><?php echo $row['date_time'] ?></a></td>
                        <td><?php echo $row['announce_text'] ?></a></td>
                    
                        
                    </tr>
                <?php }} ?>
                </tbody>
                </table>
    </body>

</html>
<?php }else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>