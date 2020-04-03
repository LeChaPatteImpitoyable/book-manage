<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加读者</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
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
            opacity:0.95;
        }
    </style>

</head>
<body>
<jsp:include page="head.jsp"/>

<div class="col-xs-6 col-md-offset-3" style="position: relative;top: 25%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">添加读者</h3>
        </div>
        <div class="panel-body">
            <form action="reader_add_do.html" method="post" id="readeredit" >
                <div class="input-group">
                    <span  class="input-group-addon">读者证号</span>
                    <input  type="text" class="form-control" name="readerId" id="readerId" placeholder="请输入证件号码">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">性别</span>
                    <%--<input type="text" class="form-control" name="sex" id="sex" >--%>
                    <div class="form-control">
                        <input type="hidden" id="sex" value="0">
                        <input type="radio" value="0" name="sex" checked="checked"/> 男
                        <input type="radio" value="1" name="sex" /> 女
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">生日</span>
                    <input type="text" class="form-control" name="birth" id="birth" placeholder="2018-01-01" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address" placeholder="请输入地址">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">电话</span>
                    <input type="text" class="form-control" name="telcode" id="telcode" placeholder="请输入电话" >
                </div>
                <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#readeredit").submit(function () {
                        var val=$('input:radio[name="sex"]:checked').val();
                        $("#sex").val(val);
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#isbn").val()==''||$("#introduction").val()==''||$("#language").val()==''||$("#price").val()==''||$("#pubdate").val()==''||$("#classId").val()==''||$("#pressmark").val()==''||$("#state").val()==''){
                            alert("请填入完整读者信息！");
                            return mySubmit(false);
                        }
                    })
                </script>
            </form>
        </div>
        <input type="button" style="float: right;" onclick="javascript:history.back(-1);" value="返回上一页">
    </div>

</div>

</body>
</html>
