<?php

header("Content-type: text/html; charset=utf-8");
$user_name = $_POST["user_name"];
$passwd = $_POST["passwd"];
$newpasswd = $_POST["newpasswd"];
$repasswd = $_POST["repasswd"];

if ($repasswd != $newpasswd) {
    echo "<script>alert('两次密码输入不一致！');history.back();</script>";
} else {
    $servername = "127.0.0.1";
    $dbusername = "root";
    $dbpasswd = "1021822981";
    $dbname = "print_sys";

    $conn = new mysqli($servername, $dbusername, $dbpasswd, $dbname);
    if (!$conn) {
        exit("连接失败: " . $conn->connect_error);
    }
    $conn->query("set names 'utf8'");
    $sql = "SELECT user_passwd FROM user where user_name = '$user_name'";
    $result = $conn->query($sql);
    $flag1 = 1;
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            if (md5(md5($passwd)) == $row["user_passwd"]) {
                $md5 = md5(md5($newpasswd));
                $sql = "update user set user_passwd='$md5' where user_name='$user_name'";
                if (mysqli_query($conn, $sql)) {
                    echo "<script>alert('修改成功，请重新登录')</script>";
                    echo "<script>url=\"/login_success/login_out.php\";window.location.href=url;</script>";
                } else {
                    echo "<script>alert('出大问题！请重试或联系管理员')</script>";
                    echo "<script>url=\"/\";window.location.href=url;</script>";
                }
                $flag1 = 0;
            }
        }
        if ($flag1 == 1) {
            echo "<script>alert('原密码输入错误！');history.back();</script>";
        }
        mysqli_free_result($result);
    }
    $conn->close();
}
?>