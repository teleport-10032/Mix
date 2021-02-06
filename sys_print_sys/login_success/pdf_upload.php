<?php
header("Content-type: text/html; charset=utf-8");

function getPdfPages($path){
    if(!file_exists($path)) return array(false,"文件\"{$path}\"不存在！");
    if(!is_readable($path)) return array(false,"文件\"{$path}\"不可读！");
    // 打开文件
    $fp=@fopen($path,"r");
    if (!$fp) {
        return array(false,"打开文件\"{$path}\"失败");
    }else {
        $max=0;
        while(!feof($fp)) {
            $line = fgets($fp,255);
            if (preg_match('/\/Count [0-9]+/', $line, $matches)){
                preg_match('/[0-9]+/',$matches[0], $matches2);
                if ($max<$matches2[0]) $max=$matches2[0];
            }
        }
        fclose($fp);
        // 返回页数
        return array(true,$max);
    }
}

?>

<?php
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

if (($_FILES["avatar"]["type"] == "application/pdf")) {
    if ($_FILES["avatar"]["error"] > 0) {
        echo "<script>alert('出大问题!请稍后再试或联系管理员');history.back();</script>";
    } else {
        $user_name = $_POST["user_name"];
//        $file_name = iconv('utf-8', 'gb2312', $_FILES["avatar"]["name"]);
        $num = $_POST["num"];
        if($num == "")
            $num = "1";
        $num1 =(int)$num;   

        // echo "<script>alert('$num');</script>";
        $file_name_utf8 = $_FILES["avatar"]["name"];
        $file_name_gbk = iconv('utf-8', 'gb2312', $file_name_utf8);
        $file = "pdf_files\\" . $user_name . "\\" . $file_name_gbk;
        // $file_gbk = iconv('utf-8', 'gb2312', $file);
        $file_utf8 = iconv('gb2312', 'utf-8', $file);
        // echo $file_utf8."<br>";
        if (file_exists($file)) {
            unlink($file);
        }
        $result = getPdfPages($_FILES["avatar"]["tmp_name"]);
        if ($result[0]) 
        {
            $n = $result[1] * $num1;
//            echo "$result[1]";
            if ($n > 10 )
            {
                echo "<script>alert('大于10页的打印作业请到sys打印');history.back()</script>";
            } else
                {
                $r = move_uploaded_file($_FILES["avatar"]["tmp_name"], $file);
                if ($r == 1)
                {
                    $servername = "127.0.0.1";
                    $dbusername = "root";
                    $dbpasswd = "1021822981";
                    $dbname = "print_sys";

                    $conn = new mysqli($servername, $dbusername, $dbpasswd, $dbname);

                    if (!$conn) {
                        die("连接失败: " . $conn->connect_error);
                    }
                    $conn->query("set names 'utf8'");
                    $sql = "INSERT INTO task (create_time,user_name,page_cnt,file_name) VALUES (now(),'$user_name','$result[1]','$file_name_utf8')";
                    if (mysqli_query($conn, $sql))
                    {
                        echo "<script>alert('成功提交打印任务');</script>";

                        for($i = 1 ;$i <= $num ; $i ++)
                        {
                            $tmp =  "D:\\adobe_reader\\Reader\\AcroRd32 /p /h D:\\phpstudy_pro\\WWW\\login_success\\"."$file_utf8";
                            $tmp_2 = iconv('utf-8', 'gb2312', $tmp);
                            // $tmp =  "D:\pdfprint/pdfprint.exe C:\phpStudy\PHPTutorial\WWW\login_success\\"."$file_utf8";
                            // echo $tmp;
                            exec($tmp_2,$out , $re);
                        }

                        echo "<br><br><br>";
                        echo "<script>history.back();</script>";
                    }
                    else {
                        echo "<script>alert('出大问题！请重试或联系管理员!');history.back();</script>";
                    }
                    $conn->close();
                }
            }
        }
        else
            {
            echo "<script>alert('qwq请上传正确的文件');history.back();</script>";
        }



    }
}

