<?php
    // Requires hostIP, Username Password and database to connect to a running mysql Server
   $con=mysqli_connect();

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

   $Username = ['username']
   $Email = $_POST['email_address'];
   $First_Name = $_POST['first_name'];
   $Last_Name  = $_POST['last_name'];
   $Role = $_POST['role'];
   $roleID = $_POST['emp_stud_num']
   $Password = $_POST['password'];

   
   $query = mysqli_query($con, "INSERT INTO registration ( username, email_address , 'role' , 'first_name',last_name , 'emp_stud_num', 'password' ) values  ('$Username', '$Email', '$First_Name', '$Last_Name', '$Role', '$roleID' ,'$Password')");

   if($data) {
	$UserId = $data['UserId'];
      echo $UserId;
   }
else{
	echo 'ERROR';
}


   mysqli_close($con);
?>
