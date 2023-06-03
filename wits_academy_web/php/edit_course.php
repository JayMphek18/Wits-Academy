<?php
    require("database.php");
    if($_SESSION['user_id']){
        //read the entries of the user; if the entries are correct, change course details of the course
        if(isset($_POST["update"])){
            $id=$_POST["course_id"];
            $code=$_POST["code"];
            $course=$_POST["course"];
            $old_pp=$_POST["old_pic"];
            $descrip=$_POST["description"];
            $password=$_POST["course_password"];
            $cpass=$_POST["cpassword"];
            
            $errors = array();
            //the new password should be at least >5 characters long
            if (strlen($password)<5){
                array_push($errors, "Password must be at least 10 characters long!");
            }
            //the password and confirmed password should match
            if($password !== $cpass){
                array_push($errors, "Password does not match!");
            }
            //if there are errors, display all the errors appended in the error array
            if(count($errors)>0){
                foreach($errors as $error){
                    echo"<div class='alert alert-danger'>$error</div>";
                }
            }else{
                    $img_name=$_FILES['pic']['name'];

                    if (!empty($img_name)) {
                        $tmp_name = $_FILES['pic']['tmp_name'];
                        $error = $_FILES['pic']['error'];
                        
                        if($error === 0){
                            $img_ex = pathinfo($img_name, PATHINFO_EXTENSION);
                            $img_ex_to_lc = strtolower($img_ex);

                            $allowed_exs = array('jpg', 'jpeg', 'png');
                            if(in_array($img_ex_to_lc, $allowed_exs)){
                            $new_img_name = $code.$course.'.'.$img_ex_to_lc;
                            //echo "$new_img_name </br>";
                            $img_upload_path = '../teacher/course_pic/'.$new_img_name;
                            // Delete old profile pic
                            $old_pp_des = "../teacher/course_pic/$old_pp";
                            if(unlink($old_pp_des)){
                                // just deleted
                                move_uploaded_file($tmp_name, $img_upload_path);
                            }else {
                                // error or already deleted
                                move_uploaded_file($tmp_name, $img_upload_path);
                            }
                            

                            // update the Database
                            $sql = "UPDATE courses 
                                    SET course_code=?, course_name=?, description=?, picture=?, password=?
                                    WHERE course_id=?";
                            $stmt = $conn->prepare($sql);
                            $stmt->execute([$code, $course, $descrip, $new_img_name, $password, $id]);
                            //$_SESSION['fname'] = $fname;
                            echo "<script type='text/javascript'>alert('Course Details Updated:)')</script>";
                            header( "Refresh:0.01; url=../teacher/edit_course.php?id=$id", true, 303);
                            }else {
                            echo "<script type='text/javascript'>alert('You can't upload files of this type')</script>";
                            header( "Refresh:0.01; url=../teacher/edit_course.php?id=$id", true, 303);
                            }
                        }else {
                            echo"<script type='text/javascript'>alert('unknown error occurred!')</script>";
                            header( "Refresh:0.01; url=../teacher/edit_course.php?id=$id", true, 303);
                        }

                        
                    }else{
                        $sql = "UPDATE courses 
                        SET course_code=?, course_name=?, description=?, password=?
                        WHERE course_id=?";
                        $stmt = $conn->prepare($sql);
                        $stmt->execute([$code, $course,$descrip, $password, $id]);
                        echo "<script type='text/javascript'>alert('Course Details Updated:)')</script>";
                        header( "Refresh:0.01; url=../teacher/edit_course.php?id=$id", true, 303);
                    }  
                    }}
                }
    ?>