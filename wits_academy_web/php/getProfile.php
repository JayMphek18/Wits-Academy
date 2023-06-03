<?php 
function getProfile($user_id, $db){
    $sql="select * from registration where user_id='$user_id'";
    $result1=$db->query($sql);
    $rowcount=mysqli_num_rows($result1);
    if(($rowcount)){
        $row=$result1->fetch_assoc();
        return $row;
    }else {
        return 0;
    }
}

 ?>