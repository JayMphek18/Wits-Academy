<?php
    function createPath($path) {
        if (is_dir($path)) 
            return true;
        $prev_path = substr($path, 0, strrpos($path, '/', -2) + 1 );
        $return = createPath($prev_path);
        return ($return && is_writable($prev_path)) ? mkdir($path) : false;
    }

    $course_name = $_POST['course_name'];
    $Videopath = "../Courses/"."$course_name"."/Videos"; 
    $DocumentPath = "../Courses/"."$course_name"."/Documents";
    
    createPath($Videopath);
    createPath($DocumentPath);
?>
