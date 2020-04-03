<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>联想搜索功能</title>
</head>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
    $(function(){
        $("ul").css({
            "padding":"0px",
            "margin":"0px",
            "list-style":"none",
            "width":"100%",
        });

        $("li").css({
            "padding":"0px",
            "margin":"0px",
            "width":"100%"
        });

        $("li").hover(
            function(){
                $(this).css({"background-color":"#ddd"});
            },
            function(){
                $(this).css({"background-color":"#fff"});
            }
        );
    });
</script>
<body>
<ul>
    <c:forEach items="${unames }" var="uname">
        <li>${uname }</li>
    </c:forEach>
</ul>
</body>
</html>