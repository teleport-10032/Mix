<?php
header("Content-Type: text/html;charset=utf-8");
if(isset($_POST["title"]))
    $title = $_POST["title"];
if(isset($_POST["content"]))
    $content = $_POST["content"];
include './conn.php';
$conn = new mysqli($servername, $dbusername, $dbpasswd, $dbname);
if (!$conn) {
    exit("连接失败: " . $conn->connect_error);
}
$conn->query("set names 'utf8'");

$sql = "INSERT INTO board (title,content)
        VALUES ('$title','$content')";
if (mysqli_query($conn, $sql))
{
    echo "<script>alert('Successful');</script>";
    echo "<script>url=\"list.php\";window.location.href=url;</script>";
}
else {
    echo $conn->error;
}
$conn->close();
?>