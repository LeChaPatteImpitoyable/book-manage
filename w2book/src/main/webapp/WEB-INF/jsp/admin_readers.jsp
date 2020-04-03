<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部读者</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/layui.all.js" ></script>
    <style src="css/layui.css"></style>
    <script src="js/initPage.js" ></script>
    <style>
        body{
            /*background-color: rgb(240,242,245);*/
            background: url('https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/06/ChMkJ1bKynKIJdkTAEoM11B450YAALIbwFdKEgASgzv882.jpg') no-repeat center center fixed;
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
<c:if test="${!empty info}">
    <script>alert("${info}");window.location.href="allreaders.html"</script>
</c:if>
<jsp:include page="head.jsp"/>

<div style="position: relative;top: 15%">
<c:if test="${!empty succ}">
    <div class="alert alert-success alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${succ}
    </div>
</c:if>
<c:if test="${!empty error}">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${error}
    </div>
</c:if>
</div>

<div style="padding: 70px 550px 10px">
    <form   method="post" action="allreaders.html" class="form-inline"  id="searchform">
        <div class="input-group">
            <input type="text" placeholder="输入读者姓名或卡号" value="${keyword}" autocomplete="off" id="search" name="keyword" class="form-control">
            <span class="input-group-btn">
                <input type="submit" value="搜索" class="btn btn-default">
            </span>
            <span class="input-group-btn">
                <a href="allreaders.html"><input type="button" value="清空" class="btn btn-default"></a>
            </span>
        </div>
    </form>
</div>
<div class="panel panel-default" style="position:relative;width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            全部读者
        </h3>
    </div>
    <div class="panel-body" id="list">
        <jsp:include page="admin_reader_list.jsp" />
    </div>
    <div style="width: 90%;text-align: center;background-color: #ffffff;margin-left: 5%;margin-bottom: 15px;">
        <div id="page"></div>
    </div>
</div>

<script>

    function mySubmit(flag){
        return flag;
    }
    $("#searchform").submit(function () {
        var val=$("#keyword").val();
        if(val==''){
            alert("请输入关键字");
            return mySubmit(false);
        }
    });

    /**
     * 纯粹的JS分页插件，代码缺点：JS操作DOM冗余太多，太繁琐
     */
    pageUtil.initPage('page',{
        totalCount:${totalCount},//总页数，一般从回调函数中获取。如果没有数据则默认为1页
        curPage:1,//初始化时的默认选中页，默认第一页。如果所填范围溢出或者非数字或者数字字符串，则默认第一页
        showCount:9,//分页栏显示的数量
        pageSizeList:[9],//自定义分页数，默认[5,10,15,20,50]
        defaultPageSize:9,//默认选中的分页数,默认选中第一个。如果未匹配到数组或者默认数组中，则也为第一个
        isJump:false,//是否包含跳转功能，默认false
        isPageNum:false,//是否显示分页下拉选择，默认false
        isPN:true,//是否显示上一页和下一面，默认true
        isFL:true,//是否显示首页和末页，默认true
        jump:function(curPage,pageSize){//跳转功能回调，传递回来2个参数，当前页和每页大小。如果没有设置分页下拉，则第二个参数永远为0。这里的this被指定为一个空对象，如果回调中需用到this请自行使用bind方法
            console.log(curPage,pageSize);
            if(pageSize == 0){
                pageSize = 9;
            }
            refrechHtm(curPage,pageSize);
//            $("#book_list").html("");
        },
    });

    function refrechHtm (curPage,pageSize) {
        var keyword = $("#search").val();
        $.ajax({
            type: "get",
            url: "${path}/allreaders_do.html?curPage="+curPage+"&pageSize="+pageSize+"&keyword="+keyword,
            dateType: "html",
            success: function (res) {
                console.log(JSON.stringify(${totalCount}));
                $("#list").html(res);
            }
        });
    }
</script>
</body>
</html>
