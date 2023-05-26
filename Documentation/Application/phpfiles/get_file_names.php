<?php
    function getFileNames($directory){
        $list = array();
        foreach (new DirectoryIterator($directory) as $file) {
            if ($file->isFile()) {
                array_push($list,$file->getFilename());
            }
          }
          return $list;
    }

    require("database.php");
    $courseName  = $_POST["courseName"];
    // Change to where Documents are store
    $DocumentsDir = "../Courses/".$courseName."/Documents";

    // Get all name files in Documents Directior
    $fileNamesDocuments = getFileNames($DocumentsDir);
    
    echo json_encode($fileNamesDocuments);
?>