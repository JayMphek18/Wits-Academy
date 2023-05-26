<?php
    require("database.php");
    $announced = $_POST['text'];
    $sql_query = "DELETE FROM announcements WHERE announcement_text='$announced'";

    if(mysqli_query($conn,$sql_query)){
        echo "Announcement successfully deleted.";
    }
    else{
        die("Something went wrong, please try again later.");
    }
?>