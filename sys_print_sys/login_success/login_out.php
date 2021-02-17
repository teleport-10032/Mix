<?php
session_start();
header("Content-Type: text/html;charset=utf-8");
?>
<?php
if (isset($_SESSION['user_name'])) {
    unset($_SESSION['user_name']);
}
echo "<script>alert('退出成功！')</script>";
echo "<script>url=\"/\";window.location.href=url;</script>";
?>