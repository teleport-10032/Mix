<?php

header("Content-type: text/html; charset=utf-8");
session_start();

$user_name = $_POST["user_name"];
$user_passwd = $_POST["user_passwd"];
//echo "<script>alert('$user_name');</script>";
//echo "<script>alert('$user_passwd');</script>";

$servername = "127.0.0.1";
$dbusername = "root";
$dbpasswd = "1021822981";
$dbname = "print_sys";

$conn = new mysqli($servername, $dbusername, $dbpasswd, $dbname);
if (!$conn) {
    exit("连接失败: " . $conn->connect_error);
}
$conn->query("set names 'utf8'");
$sql = "SELECT user_passwd,user_name FROM user where user_name = '$user_name'";

$result = $conn->query($sql);
if ($result->num_rows > 0) {
    //找到邮箱
    while ($row = $result->fetch_assoc())
    {
        if (md5(md5($user_passwd)) == $row["user_passwd"])
        {
            $_SESSION['user_name'] = $row["user_name"];
            echo "<script>url='/login_success';window.location.href=url;</script>";
        }
        else
            {
            echo "<script>alert('用户名或密码不正确!');history.back();</script>";
        }
    }
    mysqli_free_result($result);
} else {
    //没有找到邮箱
    echo "<script>alert('用户名或密码不正确!');history.back();</script>";
}
$conn->close();
