<?php
require("database.php");
if($_SESSION['user_id']){
        //read the entries of the user; if the entries are correct, update the profile information of the user
        if(isset($_POST["update"])){
            $user_id=$_SESSION['user_id'];
            $fname=$_POST["fname"];
            $lname=$_POST["lname"];
            $email=$_POST["email"];
            $old_pp=$_POST["old_pp"];
            
            $sql= "SELECT user_id, role, password FROM registration WHERE user_id='$user_id'";
            $result = mysqli_query($conn, $sql);
            $rowCount= mysqli_num_rows($result);
            $row = $result->fetch_assoc();//create a list of all the entries of this user
            $role=$row["role"];

           
            $img_name=$_FILES["pp"]["name"];

            if (!empty($img_name)) {
                $tmp_name = $_FILES['pp']['tmp_name'];
                $error = $_FILES['pp']['error'];
                
                if($error === 0){
                    $img_ex = pathinfo($img_name, PATHINFO_EXTENSION);
                    $img_ex_to_lc = strtolower($img_ex);

                    $allowed_exs = array('jpg', 'jpeg', 'png');
                    if(in_array($img_ex_to_lc, $allowed_exs)){
                    $new_img_name = $user_id.'.'.$img_ex_to_lc;
                    
                    $img_upload_path = '../profile_pic/'.$new_img_name;
                    // Delete old profile pic
                    $old_pp_des = "../profile_pic/$old_pp";
                    if(unlink($old_pp_des)){
                        // just deleted
                        move_uploaded_file($tmp_name, $img_upload_path);
                    }else {
                        // error or already deleted
                        move_uploaded_file($tmp_name, $img_upload_path);
                    }
                    

                    // update the Database
                    $sql = "UPDATE registration 
                            SET first_name=?, last_name=?, email_address=?, profile_pic=?
                            WHERE user_id=?";
                    $stmt = $conn->prepare($sql);
                    $stmt->execute([$fname, $lname, $email, $new_img_name, $user_id]);
                    
                    echo "<script type='text/javascript'>alert('You have successfully updated your profile :)')</script>";
                    if($role=='teacher'){
                        header("Refresh:0.01; url=../teacher/profileview.php", true, 303);
                      }
                      else{
                        header("Refresh:0.01; url=../student/profileview.php", true, 303);
                      }
                    
                    }else {
                      echo "<script type='text/javascript'>alert('You can't upload files of this type')</script>";
                      header("Refresh:0.01; url=../edit_profile.php?id=$user_id", true, 303);
                    }
                }else {
                    echo"<script type='text/javascript'>alert('unknown error occurred!')</script>";
                    header("Refresh:0.01; url=../edit_profile.php?id=$user_id", true, 303);
                }
            }else{
                $sql2 = "UPDATE registration SET first_name='$fname', last_name='$lname', email_address='$email' WHERE user_id='$user_id'";
                $result2=mysqli_query($conn,$sql2);
                echo "<script type='text/javascript'>alert('You have successfully updated your profile :)')</script>";
                if($role=='teacher'){
                    header("Refresh:0.01; url=../teacher/profileview.php", true, 303);
                  }
                  else{
                    header("Refresh:0.01; url=../student/profileview.php", true, 303);
                  } 
            }  
            }
          }
    ?>