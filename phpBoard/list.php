<?php
header("Content-Type: text/html;charset=utf-8");
include './conn.php';
$conn = new mysqli($servername, $dbusername, $dbpasswd, $dbname);
if (!$conn) {
    exit("连接失败: " . $conn->connect_error);
}
echo "<a href='index.php'><button>back</button></a><br>";
$sql = "select title,content from board";
$result = $conn->query($sql);
while (list($title,$content) = $result->fetch_row())
{
	echo "<span style='color:red'>".$title."</span> ".$content."<br>";
}
$conn->close();
?>