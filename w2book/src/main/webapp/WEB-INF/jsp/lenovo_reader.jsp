<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    $(function(){
        $("ul").css({
            "padding":"0px",
            "margin":"0px",
            "list-style":"none",
            "width":"100%",
            "overflow-y":"scroll",
//            "overflow": "hidden"
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

<ul>
    <c:forEach items="${readers}" var="reader">
        ${reader.name }
        <li>${reader.readerId }</li>
    </c:forEach>
</ul>