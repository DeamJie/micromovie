<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!--播放页面-->
    <link rel="stylesheet" type="text/css" href="/jwplayer/skins/stormtrooper.css">
    <script type="text/javascript" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
        SyntaxHighlighter.all();
    </script>
    <!--播放页面-->
    <style>
        .navbar-brand>img {
            display: inline;
        }
        .media{
            padding:3px;
            border:1px solid #ccc
        }
        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9{
            padding-right: 3px;
            padding-left: 3px;
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
            <form class="navbar-form navbar-left" role="search" style="margin-top:18px;">
                <div class="form-group input-group">
                    <input type="text" class="form-control" placeholder="请输入电影名！">
                    <span class="input-group-btn">
                        <a class="btn btn-default" href="search.html"><span class="glyphicon glyphicon-search"></span>&nbsp;搜索</a>
                    </span>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="curlink" href="index.html"><span class="glyphicon glyphicon-film"></span>&nbsp;电影</a>
                </li>
                <li>
                    <a class="curlink" href="login.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;登录</a>
                </li>
                <li>
                    <a class="curlink" href="register.html"><span class="glyphicon glyphicon-plus"></span>&nbsp;注册</a>
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
<div class="container" style="margin-top:76px">
    <div class="row">
        <div class="col-md-8">
            <div id="moviecontainer">
                <<img src="${movie.img}" width="350px" height="500px">
            </div>
        </div>
        <div class="col-md-4" style="height:500px;">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-facetime-video"></span>&nbsp;电影介绍</h3>
                </div>
                <div class="panel-body" style="height:459px;">
                    <table class="table">
                        <tr>
                            <td style="width:30%;color:#ccc;font-weight:bold;font-style:italic;">
                                <span class="glyphicon glyphicon-film"></span>&nbsp;片名
                            </td>
                            <td>${movie.name}</td>
                        </tr>
                        <tr>
                            <td style="width:30%;color:#ccc;font-weight:bold;font-style:italic;">
                                <span class="glyphicon glyphicon-film"></span>&nbsp;片名
                            </td>
                            <td>${movie.translationname}</td>
                        </tr>
                        <tr>
                            <td style="color:#ccc;font-weight:bold;font-style:italic;">
                                <span class="glyphicon glyphicon-tag"></span>&nbsp;标签
                            </td>
                            <td>${label.name}</td>
                        </tr>
                        <tr>
                            <td style="color:#ccc;font-weight:bold;font-style:italic;">
                                <span class="glyphicon glyphicon-map-marker"></span>&nbsp;地区
                            </td>
                            <td>${movie.country}</td>
                        </tr>

                        <tr>
                            <td style="color:#ccc;font-weight:bold;font-style:italic;">
                                <span class="glyphicon glyphicon-calendar"></span>&nbsp;上映时间
                            </td>
                            <td>${movie.time}</td>
                        </tr>
                        <tr>
                            <td style="color:#ccc;font-weight:bold;font-style:italic;">
                                <span class="glyphicon glyphicon-picture"></span>&nbsp;影片介绍
                            </td>
                            <td>
                                ${movie.intro}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-12" style="margin-top:6px;">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span>&nbsp;电影评论</h3>
                </div>
                <div class="panel-body">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>请先<a href="login.html" target="_blank" class="text-info">登录</a>，才可参与评论！</strong>
                    </div>
                    <ol class="breadcrumb" style="margin-top:6px;">
                        <li>全部评论(${num})</li>
                    </ol>
                    <form role="form"action="/movie/evaluation" style="margin-bottom:6px;">
                        <div class="form-group">
                            <div>
                                <label for="input_content">内容</label>
                                <textarea id="input_content"></textarea>
                            </div>
                            <div class="col-xs-12" id="error_content"></div>
                        </div>
                        <input type="submit" class="btn btn-success" id="btn-sub" value="提交评论"></input>
                        &nbsp;
                        <a class="btn btn-danger" id="btn-col"><span class="glyphicon glyphicon-heart"></span>&nbsp;收藏电影</a>
                    </form>
                    <ul class="commentList">
                        <c:forEach items="${evaluations}" var="e">
                            <li class="item cl">
                                    <i class="avatar size-L radius">
                                        <img alt="50x50" src="holder.js/50x50" class="img-circle" style="border:1px solid #abcdef;">
                                    </i>
                                <div class="comment-main">
                                    <header class="comment-header">
                                        <div class="comment-meta">
                                            <a >${e.name}</a>
                                        </div>
                                    </header>
                                    <div class="comment-body">
                                        <p>${e.evaluation}</p>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="col-md-12 text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li>
                                    <a href="#" aria-label="First">
                                        <span aria-hidden="true">首页</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">上一页</span>
                                    </a>
                                </li>
                                <li><a href="#">1&nbsp;/&nbsp;10</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">下一页</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" aria-label="Last">
                                        <span aria-hidden="true">尾页</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
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
<!--播放页面-->
<script src="/jwplayer/jwplayer.js"></script>
<script>
    var ue = UE.getEditor('input_content',{
        toolbars: [
            ['fullscreen', 'emotion', 'preview', 'link']
        ],
        initialFrameWidth:"100%",
        initialFrameHeight:"100",
    });
</script>

<!--播放页面-->
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
