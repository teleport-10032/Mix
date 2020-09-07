<?php

header("Content-type: text/html; charset=utf-8");
$user_name = $_GET["user_name"];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX315_PrintSys</title>
</head>
<body>

<form action="/login_success/passwd_update.php" method="post">
    <input type="hidden" name="user_name" value="<?php echo "$user_name";?>">
    原密码：
    <input type="password" name="passwd">
    <br>
    新密码：
    <input type="password" name="newpasswd">
    <br>
    重复密码：
    <input type="password" name="repasswd">
    <br>
    <br><br>
    <input type="submit" name="submit" />
</form>

</body>
</html>