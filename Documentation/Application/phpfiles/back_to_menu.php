<?php
    $emp_stud_num=$_POST["user_number"];

    $errors = array();
    require_once("database.php");
      
    $sql= "SELECT * FROM Registration WHERE emp_stud_num='$emp_stud_num'";
    $result = mysqli_query($conn, $sql);
    $rowCount= mysqli_num_rows($result);
    $row = $result->fetch_assoc();
    $results = array();
    $results["login"] = array();

    $user_role = $row["user_role"];
            //$cpassword=$row["password"];

    if($rowCount == 0){
        echo "incorrect password/user number, please try again";
    }
    else{
        echo $user_role;
        mysqli_close($conn);
    }
?>
   