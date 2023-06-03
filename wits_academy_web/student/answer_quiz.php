<?php
    require('database.php');
    require('../php/getCourse.php');
    require('../php/getQuiz.php');
?>
<?php 
    if ($_SESSION['user_id']) {
?>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>
        <?php echo $_GET['quiz_name'] ?>
    </title>
    <link type="text/css" href="./css/answer.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="body">
    <?php
        $id = $_GET['id'];
        $course=getCourse($id, $conn);

        $quiz=getQuiz($id, $_GET['quiz_name'], $conn);
        $quizid=$quiz["quiz_id"];
        $qnum=$quiz['ques_num'];
        $q=$_GET['q'];

        $sql="select * from questions where quiz_id='$quizid' and ques_num='$q'";
        $result1=$conn->query($sql);
    ?>   
    <div class="section">
        <img src="./Wits_Logo.png" loading="lazy" alt="wits logo" />
        <h1 class="head1"><?php echo $course['course_code'].":".$course['course_name'] ?></h1>
        <a href="./course.php?id=<?php echo $id ?>" class="link"><i class="fas fa-home"></i> Back To Home</a>
    </div>

    <div class="container">
        <h2 class="head2"><?php echo $_GET['quiz_name'] ?></h2>
        <div class="form-block">
            <form action="../php/record.php?id=<?php echo $quiz['course_id']?>&quiz=<?php echo $quizid ?>&quiz_name=<?php echo $quiz['quiz_name']?>&num=<?php echo $quiz['ques_num']?>&q=<?php echo $_GET['q']?>"  method="post" class="form">
            <input type="text" hidden value="<?php echo $_SESSION['user_id'] ?>" name="studnum" />
                <?php 
                    $row=$result1->fetch_assoc();
                    $quesid=$row['ques_id'];

                    $ques=getQuestion($quesid, $conn);

                    $sql="select * from answers where ques_id='$quesid'";
                    $result=$conn->query($sql);
                ?>
                <label for="question"><b>Question <?php echo $q.": ".$ques['question'] ?> </b></label>
                <input type="text" hidden value="<?php echo $quesid ?>" name="question" />
                <?php while ($answer=$result->fetch_assoc())
                {
                ?>
                <label class="w-radio"><input type="radio" name="answer" value="<?php echo $answer['answer_id']?>" class="w-form-formradioinput w-radio-input" />
                <span class="w-form-label" for="radio"><?php echo $answer['answer_text'] ?></span></label>
                <?php
                     }
                ?>
                <input type="submit" name="submit" value="Submit Answer" class="create" />  
            </form>
            <center><a href="#" class="btn btn-primary">Back</a></center>
        </div>
    </div>
</body>
</html>
<?php }
else {
    echo "<script type='text/javascript'>alert('Access Denied!!!')</script>";
} ?>