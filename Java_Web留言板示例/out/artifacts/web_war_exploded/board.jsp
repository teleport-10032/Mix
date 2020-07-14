<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

Welcome,<% String name = request.getAttribute("name").toString(); out.println(name); %>


<br><br>
<%
    FileReader fr = new FileReader("/Users/teleport/Idea_Project/Test/board.dat");
    int cnt;
    String str = "";
    while((cnt = fr.read()) != -1)
    {
        str += (char)cnt;
    }
    fr.close();

    String arr[] = str.split(" ");
    String s = "";
    for(int i = 0 ; i < arr.length ; i ++)
    {
        s += arr[i];
        s += "<br>";
    }

%>
<center><%=s%></center>
<br><br>
Message:
<form action="SubmitServlet" method="post">
    <textarea rows="10" cols="50" name="text"></textarea>
    <input type="hidden" name="username" value="<%=name%>">
    <br> <input type="submit" value="submit!">
</form>

</body>
</html>
