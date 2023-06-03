<?php
    session_start();
    $hostname = "localhost";
    $dbUser = "Developer";
    $dbPassword = "WITS";
    $dbName = "wits_academy";
    $conn = mysqli_connect($hostname, $dbUser, $dbPassword, $dbName);
    if(!$conn){
        die("Something went wrong");
    }
?>