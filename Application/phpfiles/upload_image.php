<?php
     
    $imageURL = $_POST['imageURL'];
    $userNumber = $_POST['userNumber'];

    // $imageURL = 'image.jpg';
    // $userNumber = "1234567";

    // $baseLine = "<img src='$imageURL'>";
    
    require("database.php");

    $imagePath = 'profile_photos/'.$userNumber.'.jpg';
    
    if (file_put_contents($imagePath, base64_decode($imageURL))){
        echo "image uploaded";
    }
    else{
        echo "failed to upload image";
    }

?>