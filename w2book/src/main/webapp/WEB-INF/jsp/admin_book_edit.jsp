<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑《 ${detail.name}》</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            /*background-color: rgb(240,242,245);*/
            background: url('https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0E/0F/ChMkJ1sYylmIJMWEAA2kWnSS2pMAAo2TQGcckMADaRy914.jpg') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
        .panel{
            opacity:0.90;
        }
    </style>

</head>
<body>
<jsp:include page="head.jsp"/>

<div class="col-xs-6 col-md-offset-3" style="position: relative;top: 10%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">编辑《 ${detail.name}》</h3>
        </div>
        <div class="panel-body">
            <form action="book_edit_do.html?id=${detail.bookId}" method="post" id="addbook" >

                <div class="input-group">
                    <span  class="input-group-addon">书名</span>
                    <input type="text" class="form-control" name="name" id="name" value="${detail.name}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">作者</span>
                    <input type="text" class="form-control" name="author" id="author" value="${detail.author}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">出版社</span>
                    <input type="text" class="form-control" name="publish" id="publish"  value="${detail.publish}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">ISBN</span>
                    <input type="text" class="form-control" name="isbn" id="isbn"  value="${detail.isbn}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">简介</span>
                    <input type="text" class="form-control" name="introduction" id="introduction"  value="${detail.introduction}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">语言</span>
                    <input type="text" class="form-control" name="language" id="language" value="${detail.language}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">价格</span>
                    <input type="text" class="form-control" name="price"  id="price" value="${detail.price}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">出版日期</span>
                    <input type="text" class="form-control" name="pubdate" id="pubdate" value="<fmt:formatDate value='${detail.pubdate}' type='date'/>">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">分类号</span>
                    <input type="text" class="form-control" name="classId" id="classId" value="${detail.classId}">
                    <%--<select name="classId" id="classId" class="form-control" name="分类号">--%>
                        <%--<option value="${detail.classId}">请选择...</option>--%>
                        <%--<c:forEach items="${classInfos}" var="classInfo">--%>
                            <%--<option value="${classInfo.classId}" <c:if test="${classInfo.classId==detail.classId}">selected='true'</c:if>>${classInfo.className}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">书架号</span>
                    <input type="text" class="form-control" name="pressmark" id="pressmark" value="${detail.pressmark}">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">状态</span>
                    <%--<input type="text" class="form-control" name="state"  id="state" value="${detail.state}">--%>
                    <div class="form-control">
                        <input type="hidden" id="state" value="${detail.state}">
                        <input type="radio" value="1" name="state" <c:if test="${1==detail.state}">checked="checked"</c:if>/> 未借出
                        <input type="radio" value="0" name="state" <c:if test="${0==detail.state}">checked="checked"</c:if>/> 已借出
                    </div>
                </div>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#addbook").submit(function () {
                        var val=$('input:radio[name="state"]:checked').val();
                        console.log("state:"+val);
                        $("#state").val(val);
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#isbn").val()==''||$("#introduction").val()==''||$("#language").val()==''||$("#price").val()==''||$("#pubdate").val()==''||$("#classId").val()==''||$("#pressmark").val()==''||$("#state").val()==''){
                            alert("请填入完整图书信息！");
                            return mySubmit(false);
                        }
                        console.log("classId:"+$("#classId").val());
//                        return mySubmit(false);
                    })
                </script>
            </form>
        </div>
        <input type="button" style="float: right;" onclick="javascript:history.back(-1);" value="返回上一页">
    </div>

</div>

</body>
</html>
