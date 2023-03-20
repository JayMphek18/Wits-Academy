<?php
 // Requires hostIP, Username Password and database to connect to a running mysql Server
   $con=mysqli_connect("127.0.0.1","root"," ","wits_academy");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

   $username = $_POST['username'];
   $Password = $_POST['password'];

   $result = mysqli_query($con,"SELECT username FROM registration where username='$username'  and Password = '$Password'");
   $data = mysqli_fetch_array($result);

   if($data) {
	$username = $data['username'];
      echo $username ;
   }
else{
	echo 'ERROR';
}


   mysqli_close($con);
?>
