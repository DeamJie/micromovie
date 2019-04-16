<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <form class="navbar-form navbar-left" action="/search" role="search" style="margin-top:18px;">
                <div class="form-group input-group">
                    <input name="movieName" type="text" class="form-control" placeholder="请输入电影名！">
                    <span class="input-group-btn">
                        <input type="submit" class="btn btn-default" value="搜索"><span class="glyphicon glyphicon-search"></span></input>
                    </span>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="curlink" href="index.html"><span class="glyphicon glyphicon-film"></span>&nbsp;电影</a>
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
<!--内容-->
<!--热门电影-->
<section id="hotmovie" style="margin-top:50px">
    
</section>
<!--热门电影-->
<!--电影列表-->
<section id="movielist">
    <div class="container">
        <div class="row wow fadeIn" data-wow-delay="0.6s">
            <div class="col-md-12 table-responsive">
                <table class="table text-left table-bordered" id="movietags">
                    <tr>
                        <td style="width:10%;">电影标签</td>
                        <td style="width:90%;">
                            <c:forEach items="${labels}" var="lab">
                                 <a href="/type/${lab.name}" class="label label-info"><span class="glyphicon glyphicon-tag"></span>&nbsp;${lab.name}</a>
                                 &nbsp
                            </c:forEach>

                    </tr>
                    
                    <tr>
                        <td>上映时间</td>
                        <td>
                            <a href="/new" class="label label-default"><span class="glyphicon glyphicon-time"></span>&nbsp;最近</a>
                            &nbsp;
                            <a href="/time/2017" class="label label-default"><span class="glyphicon glyphicon-time"></span>&nbsp;2017</a>
                            &nbsp;
                            <a href="/time/2016" class="label label-default"><span class="glyphicon glyphicon-time"></span>&nbsp;2016</a>
                            &nbsp;
                            <a href="/time/2015" class="label label-default"><span class="glyphicon glyphicon-time"></span>&nbsp;2015</a>
                            &nbsp;
                            <a href="/old" class="label label-default"><span class="glyphicon glyphicon-time"></span>&nbsp;更早</a>
                        </td>
                    </tr>
                    <tr>
                        <td>播放数量</td>
                        <td>
                            <a class="label label-success"><span class="glyphicon glyphicon-arrow-down"></span>&nbsp;从高到底</span></a>
                            &nbsp;
                            <a class="label label-danger"><span class="glyphicon glyphicon-arrow-up"></span>&nbsp;从低到高</span></a>
                        </td>
                    </tr>
                    <tr>
                        <td>评论数量</td>
                        <td>
                            <a class="label label-success"><span class="glyphicon glyphicon-arrow-down"></span>&nbsp;从高到底</span></a>
                            &nbsp;
                            <a class="label label-danger"><span class="glyphicon glyphicon-arrow-up"></span>&nbsp;从低到高</span></a>
                        </td>
                    </tr>
                </table>
            </div>

            <c:forEach items="${movies}" var="movie">
                <div class="col-md-3">
                    <div class="movielist text-center">
                        <!--<img data-original="holder.js/262x166"
                                 class="img-responsive lazy center-block" alt="">-->
                        <img src="${movie.img}" class="img-responsive center-block" alt="" width="220px" height="400"px/>
                        <div class="text-left" style="margin-left:auto;margin-right:auto;width:210px;">
                            <span style="color:#999;font-style: italic;">${movie.name}</span><br>
                        </div>
                        <a href="/movie/${movie.id}" class="btn btn-primary" target="_blank" role="button"><span class="glyphicon glyphicon-play"></span>&nbsp;查看</a>
                    </div>
                </div>
            </c:forEach>


            
            <div class="col-md-12">
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
</section>
<!--电影列表-->
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

