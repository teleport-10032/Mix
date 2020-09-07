
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX315_PrintSys</title>
</head>
<body>

<?php

header("Content-type: text/html; charset=utf-8");
session_start();

$f = 1;
if (isset($_SESSION['user_name'])) {
    $f = 0;
}
if($f == 1)
{
    echo "<script>history.back();</script>";
    die("不合法的操作!");
}
$user_name = $_SESSION["user_name"];
echo "欢迎"." ".$user_name." 进入打印服务系统"."<br>"."<br>";
echo "说明：上传pdf文件，稍等片刻即可在xx315打印机处取出"."<br>"."(在线打印只能打印十页以内的文稿，如有需求请到sys打印)";
?>
<br>
<br>

<form action="/login_success/pdf_upload.php" method="post"  enctype="multipart/form-data">
    <input type="hidden" name="user_name" value="<?php echo "$user_name";?>">
    <input type="file" accept="application/pdf" name="avatar">
    <br><br>
    份数
    <input type="text" name="num" placeholder="1">
    <br><br>
    <input type="submit" name="submit" />
</form>


<br><br>
<a href="re_passwd.php?user_name=<?php echo"$user_name";?>" >
修改密码</a>
<br>
<a href="login_out.php">退出登录</a>



</body>
</html>