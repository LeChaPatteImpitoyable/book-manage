<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息添加</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            /*background-color: rgb(240,242,245);*/
            background: url('https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/01/ChMkJ1bKxTWIcg6zABCsG4tshzUAALHSgDUY_cAEKwz686.jpg') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>

</head>
<body>
<jsp:include page="head.jsp"/>

<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
            <form action="book_add_do.html" autocomplete="off" method="post" id="addbook" >
                <div class="form-group">
                    <label for="name">图书名</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
                </div>
                <div class="form-group">
                    <label for="price">价格</label>
                    <input type="text" class="form-control"  name="price"  id="price" placeholder="请输入价格">
                </div>
                <div class="form-group">
                    <label for="author">作者</label>
                    <input type="text" class="form-control" name="author" id="author"  placeholder="请输入作者名">
                </div>
                <div class="form-group">
                    <label for="publish">出版社</label>
                    <input type="text" class="form-control"  name="publish" id="publish"  placeholder="请输入出版社">
                </div>
                <div class="form-group">
                    <label for="isbn">ISBN</label>
                    <input type="text" class="form-control" name="isbn" id="isbn"  placeholder="请输入ISBN">
                </div>
                <div class="form-group">
                    <label for="introduction">简介</label>
                    <textarea class="form-control" rows="3"  name="introduction" id="introduction" placeholder="请输入简介"></textarea>
                </div>
                <div class="form-group">
                    <label for="language">语言</label>
                    <input type="text" class="form-control" name="language" id="language"  placeholder="请输入语言">
                </div>

                <div class="form-group">
                    <label for="pubdate">出版日期</label>
                    <input type="text" class="form-control"  name="pubdate" id="pubdate"   placeholder="请输入出版日期">
                </div>
                <%--<div class="form-group">--%>
                    <%--<label for="classId">分类号</label>--%>
                    <%--<input type="text" class="form-control" name="classId" id="classId"  placeholder="请输入分类号">--%>
                <%--</div>--%>
                <div class="form-group">
                    <label for="classId">分类号</label>
                    <input type="text" class="form-control" name="classId" id="classId" placeholder="请输入分类号">
                    <%--<select id="classId" class="form-control" name="分类号">--%>
                        <%--<option value="">请选择...</option>--%>
                        <%--<c:forEach items="${classInfos}" var="classInfo">--%>
                            <%--<option value="${classInfo.classId}">${classInfo.className}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                </div>
                <div class="form-group">
                    <label for="pressmark">书架号</label>
                    <input type="text" class="form-control"  name="pressmark" id="pressmark" placeholder="请输入书架号">
                </div>
                <div class="form-group">
                    <label for="state">状态</label>
                    <%--<input type="text" class="form-control"  name="state"  id="state"   placeholder="请输入图书状态">--%>
                    <div class="form-control">
                        <input type="hidden" id="state" value="1">
                        <input type="radio" value="1" name="state" checked="checked"/> 未借出
                        <input type="radio" value="0" name="state" /> 已借出
                    </div>
                </div>


                <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
                <input type="button" style="float: right;" onclick="javascript:history.back(-1);" value="返回上一页">
                <script>
                    window.onload = function () {
                        $("#author").val("作者");
                        $("#publish").val("出版社");
                        $("#isbn").val("2018");
                        $("#introduction").val("好书");
                        $("#language").val("中文");
//                        $("#price").val("0.00");
                        $("#pubdate").val("2018-06-01");
                        $("#classId").val("图书");
//                        $("#classId").find("option[value='9']").attr("selected",true)
                        $("#pressmark").val("1");
                        $("#state").val("1");
                    }
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#addbook").submit(function () {

                        var val=$('input:radio[name="state"]:checked').val();
                        console.log("state:"+val);
                        $("#state").val()
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#isbn").val()==''
                            ||$("#introduction").val()==''||$("#language").val()==''||$("#price").val()==''
                            ||$("#pubdate").val()==''||$("#classId").val()==''||$("#pressmark").val()==''||$("#state").val()==''){
                            alert("请填入完整图书信息！");
                            return mySubmit(false);
                        }
                    })
                </script>
            </form>
</div>



</body>
</html>
