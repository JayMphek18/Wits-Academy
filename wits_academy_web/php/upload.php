<?php
require("database.php");
if($_SESSION['user_id']){
     //read data entered by user and submit to database
     if(isset($_POST["insert"])){
        $type = $_POST["type"];
        $course_id=$_POST["id"];
        $file_name=$_POST["name"];
        
       
        //create array to store the errors
        $errors = array();
        if(empty($_FILES["material"]["name"])){
            array_push($errors, "Please upload a file");
        }
       
        if(count($errors)>0){
            foreach($errors as $error){
                echo"<div class='alert alert-danger'>$error</div>";
            }
        }else{
            $material=$_FILES["material"]["name"];
            //echo $img_name;
            if (!empty($material)) {
                //$img_name = $_FILES['pic']['name'];
                $tmp_name = $_FILES['material']['tmp_name'];
                $error = $_FILES['material']['error'];
                
                if($error === 0){
                   $file_ex = pathinfo($material, PATHINFO_EXTENSION);
                   $file_ex_to_lc = strtolower($file_ex);
       
                   $not_allowed_exs = array('jpg', 'jpeg', 'png');
                   if(!in_array($file_ex_to_lc, $not_allowed_exs)){
                      $new_file_name = $file_name.'.'.$file_ex_to_lc;
                      $file_upload_path = '../teacher/upload_material/'.$new_file_name;
                      move_uploaded_file($tmp_name, $file_upload_path);
       
                      // Insert into Database
                      $sql = "INSERT INTO upload(course_material, type, name_of_file, course_id) 
                        VALUES(?,?,?,?)";
                      $stmt = $conn->prepare($sql);
                      $stmt->execute([$new_file_name, $type, $file_name, $course_id]);
       
                      echo "<script type='text/javascript'>alert('File Uploaded!')</script>";
                      header( "Refresh:0.01; url=../teacher/upload.php?id=$course_id", true, 303);

                   }else {
                    echo "<script type='text/javascript'>alert('You have uploaded wrong file type!')</script>";
                    header( "Refresh:0.01; url=../teacher/upload.php?id=$course_id", true, 303);

                   }
                }else {
                    echo "<script type='text/javascript'>alert('Something went wrong')</script>";
                    header( "Refresh:0.01; url=../teacher/upload.php?id=$course_id", true, 303);

                }
            }
        }
    }
}
?>