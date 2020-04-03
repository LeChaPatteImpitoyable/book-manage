<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅《 ${book.name}》</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>

</head>
<body>
<h3>输入框联想搜索功能</h3>
请输入：<input type="text" name="uname"><input type="button" value="搜索">
<div id="lns"></div>

<script type="text/javascript">
    $(function(){

        $("input[name=uname]").css({
            "position":"relative"
        });

        $("#lns").css({
            "border":"1px #ccc solid",
            "width":"171px",
            "position":"absolute",
            "top":"84px",
            "left":"72px",
            "display":"none"
        });


        // 键盘松开的时候触发联想功能
        $("input[name=uname]").keyup(function(){
            var uname = $("#uname").val();
            if(uname != ""){
                $.ajax({
                    url: "/dev_lns.html",
                    type:"get",
                    dataType:"html",
                    async:true,
                    success:function(result){
                        $("#lns").show();
                        $("#lns").html(result);

                        // 点击模糊列表的值，必须在这里写，其他位置不起作用
                        $("li").unbind("click").bind("click", function(){
                            $("input[name=uname]").val($(this).html());
                            $("input[name=uname]").focus();
                            $("#lns").hide();
                        });

                        // 点击其他地方的时候隐藏
                        $("input[name=uname]").blur(function(){
                          $("#lns").hide();
                        });
                    }
                });
            }else{
                $("#lns").html("");
                $("#lns").hide();
            }
        });

    });
</script>

</div>

</body>
</html>
