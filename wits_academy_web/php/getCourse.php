<?php 
function getCourse($id, $db){
    $sql="select * from courses where course_id='$id'";
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