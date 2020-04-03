<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅《 ${book.name}》</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            /*background-color: rgb(240,242,245);*/
            background: url('https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0F/09/ChMkJlauzYiIIU3UAFNltyuxFDQAAH9GAKkOzMAU2XP642.jpg') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>

</head>
<body>
    <%--<jsp:include page="head.jsp"/>--%>

<div id="lns"></div>
<div class="col-xs-6 col-md-offset-3" style="position: relative;top: 25%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">借阅《 ${book.name}》</h3>
        </div>
        <div class="panel-body">
            <form action="lendbookdo.html?id=${book.bookId}" autocomplete="off" method="post" id="lendbook" >
                <div class="input-group">
                    <span  class="input-group-addon">编号</span>
                    <input type="text" readonly="readonly" class="form-control" name="bookId" id="bookId" value="${book.bookId}">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">书名</span>
                    <input type="text" readonly="readonly" class="form-control" name="name" id="name" value="${book.name}">
                </div>
                <br/>
                <div class="input-group">
                    <span class="input-group-addon">读者证号</span>
                    <input type="text" class="form-control" name="readerId" id="readerId" placeholder="借阅人读者证号" >
                </div>

                <br/>
                <input type="submit" value="确定" id="submit" disabled="disabled" class="btn btn-success btn-sm text-left" >

                <script type="text/javascript">
                    $(function(){

                        $("input[name=readerId]").css({
                            "position":"relative"
                        });

                        $("#lns").css({
                            "border":"1px #ccc solid",
                            "width":"171px",
                            "position":"absolute",
                            "top":"51.5%",
                            "z-index": "99",
                            "height": "254px",
                            "left":"33.2%",
                            "display":"none"
                        });


                        // 键盘松开的时候触发联想功能
                        $("input[name=readerId]").keyup(function(){
                            var readerId = $("#readerId").val();
                            if(readerId != ""){
                                $.ajax({
                                    url: "${path}/lenovo_reader.html?keyword="+readerId,
                                    type:"get",
                                    dataType:"html",
                                    //async:true,//异步请求 会执行后面的
                                    success:function(result){
                                        $("#lns").show();
                                        $("#lns").html(result);

                                        // 点击模糊列表的值，必须在这里写，其他位置不起作用
                                        $("li").unbind("click").bind("click", function(){
                                            $("input[name=readerId]").val($(this).html());
                                            $("input[name=readerId]").focus();
                                            $("#lns").hide();
                                        });

                                        // 点击其他地方的时候隐藏
//                                        $("input[name=readerId]").blur(function(){
//                                          $("#lns").hide();
//                                        });
                                    }
                                });
                            }else{
                                $("#lns").html("");
                                $("#lns").hide();
                            }
                        });

                    });
                </script>

                <script>
//                    function mySubmit(flag){
//                        return flag;
//                    }
                    $("#readerId").blur(function(){
//                        $("#lns").hide();
                        checkReaderId();
                    });

                    function checkReaderId() {
                        if($("#name").val()==''||$("#readerId").val()==''){
//                            alert("请填入正确的读者证号！");
                            $("#submit").attr("disabled", "disabled");
                        }else{
                            var readerId = $("#readerId").val();
                            $.ajax({
                                type: "get",
                                url: "${path}/reader_info_do?readerId="+readerId,
                                dateType: "json",
                                success: function (res) {
                                    if(!res){
//                                        alert("请填入正确的读者证号！");
                                        $("#submit").attr("disabled", "disabled");
                                    }else{
                                        $("#submit").attr("disabled", false);
                                    }
                                }
                            });

                        }
                    }

                    $("#lendbook").submit(function () {
                        return checkReaderId();
                    });

                </script>
            </form>
        </div>
        <input type="button" style="float: right;" onclick="javascript:history.back(-1);" value="返回上一页">
    </div>

</div>

</body>
</html>
