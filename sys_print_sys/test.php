<?php

header("Content-type: text/html; charset=gbk");

$w = md5(md5(123456));
echo $w;

?>