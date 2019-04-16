<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>微电影</title>
    <link rel="shortcut icon" href="/base/images/logo.png">
    <link rel="stylesheet" href="/base/css/bootstrap.min.css">
    <link rel="stylesheet" href="/base/css/bootstrap-movie.css">
    <link rel="stylesheet" href="/base/css/animate.css">
    <style>
        .navbar-brand>img {
            display: inline;
        }

    </style>
    <style>
        .media{
            padding:3px;
            border:1px solid #ccc
        }

    </style>
</head>

<body>
<!--导航-->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!--小屏幕导航按钮和logo-->
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="index.html" class="navbar-brand" style="width:250px;">
                <img src="/base/images/logo.png" style="height:30px;">&nbsp;微电影
            </a>
        </div>
        <!--小屏幕导航按钮和logo-->
        <!--导航-->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="curlink" href="/index"><span class="glyphicon glyphicon-film"></span>&nbsp;电影</a>
                </li>
                <li>
                    <a class="curlink" href="/login_view"><span class="glyphicon glyphicon-log-in"></span>&nbsp;登录</a>
                </li>
                <li>
                    <a class="curlink" href="/register_view"><span class="glyphicon glyphicon-plus"></span>&nbsp;注册</a>
                </li>
                <li>
                    <a class="curlink" href="logout.html"><span class="glyphicon glyphicon-log-out"></span>&nbsp;退出</a>
                </li>
                <li>
                    <a class="curlink" href="user.html"><span class="glyphicon glyphicon-user"></span>&nbsp;会员</a>
                </li>
            </ul>
        </div>
        <!--导航-->

    </div>
</nav>
<!--导航-->
<!--内容-->
<div class="container" style="margin-top:120px">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-log-in"></span>&nbsp;会员登录</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="/login">
                        <fieldset>
                            <div class="form-group">
                                <label for="contact"><span class="glyphicon glyphicon-user"></span>&nbsp;账号</label>
                                <input id="contact" class="form-control input-lg" placeholder="邮箱" name="contact" type="text" autofocus>
                            </div>
                            <div class="col-md-12" id="error_contact"></div>
                            <div class="form-group">
                                <label for="password"><span class="glyphicon glyphicon-lock"></span>&nbsp;密码</label>
                                <input id="password" class="form-control input-lg" placeholder="密码" name="password" type="password" value="">
                            </div>
                            <div class="col-md-12" id="error_password"></div>
                            <input type="submit" value="登录" class="btn btn-lg btn-success btn-block"></input>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--内容-->
<!--底部-->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p>

                </p>
            </div>
        </div>
    </div>
</footer>
<!--底部-->
<script src="/base/js/jquery.min.js"></script>
<script src="/base/js/bootstrap.min.js"></script>
<script src="/base/js/jquery.singlePageNav.min.js"></script>
<script src="/base/js/wow.min.js"></script>
<script src="/lazyload/jquery.lazyload.min.js"></script>
<script src="//cdn.bootcss.com/holder/2.9.4/holder.min.js"></script>
<script>
    $(function() {
        new WOW().init();
    })

</script>
<script>
    $(document).ready(function() {
        $("img.lazy").lazyload({
            effect: "fadeIn"
        });
    });

</script>
</body>
</html>