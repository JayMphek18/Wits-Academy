<?php

  	// echo $_SERVER["DOCUMENT_ROOT"];  // /home1/demonuts/public_html
	//including the database connection file
  	include_once("database.php");

  	//$_FILES['image']['name']   give original name from parameter where 'image' == parametername eg. city.jpg
  	//$_FILES['image']['tmp_name']  temporary system generated name
        $courseName = $_POST['courseName'];
        $type = $_POST['type'];
        $originalFileName= $_FILES['filename']['name'];
        $tempName= $_FILES['filename']['tmp_name'];
        $folder="../"."Courses/".$courseName."/".$type."/";
        $url = $folder.$originalFileName; //update path as per your directory structure 
        
        if(move_uploaded_file($tempName,$folder.$originalFileName)){
            // Insert path into Database **TODO:**
                // $query = "INSERT INTO upload_image_video (pathToFile) VALUES ('$url')";
                // if(mysqli_query($con,$query)){
                
                // 	 $query= "SELECT * FROM upload_image_video WHERE pathToFile='$url'";
	            //      $result= mysqli_query($con, $query);
	            //      $emparray = array();
	            //          if(mysqli_num_rows($result) > 0){  
	            //          while ($row = mysqli_fetch_assoc($result)) {
                //                      $emparray[] = $row;
                //                    }
                //                    echo json_encode(array( "status" => "true","message" => "Successfully file added!" , "data" => $emparray) );
                                   
	            //          }else{
	            //          		echo json_encode(array( "status" => "false","message" => "Failed!") );
	            //          }
			   
                // }else{
                // 	echo json_encode(array( "status" => "false","message" => "Failed!") );
                // }
        	echo "moved to ".$url;
        }else{
        	echo json_encode(array( "status" => "false","message" => "Failed!") );
        }
  
?>