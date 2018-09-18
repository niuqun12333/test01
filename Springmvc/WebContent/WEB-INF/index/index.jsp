<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

a {
	color: #fff;
	text-decoration: none;
}

html body {
	background: #337ab7;
}

#main {
	overflow: hidden;
	width: 1400px;
	margin: 20px auto;
	background-color: #fff;
}

.w {
	width: 100%;
	background: #fff;
}

#left {
	width: 400px;
	height: 600px;
	float: left;
	padding-left: 20px;
}

#left a {
	color: #fff;
	text-decoration: none;
}

#right {
	width: 800px;
	height: 600px;
	float: left;
}

#top, #bottom {
	clear: both;
	height: 80px;
}

#top {
	width: 100%;
	border-bottom: 1px solid #337ab7;
	position: relative;
}

#bottom {
	width: 100%;
	border-top: 1px solid #337ab7;
}

#title {
	color: #000;
	font-size: 36px;
	height: 80px;
	line-height: 80px;
	margin-left: 240px;
	float: left;
}

#num {
	color: #000;
	font-size: 20px;
	height: 80px;
	line-height: 80px;
	float: right;
	margin-right: 100px;
}

#count {
	color: #000;
	font-size: 20px;
	width: 30px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	background: #337ab7;
	position: absolute;
	right: 75px;
	bottom: 0;
	background: #337ab7;
}

.yi {
	width: 160px;
	height: 40px;
	background: #337ab7;
	color: #fff;
	margin-top: 10px;
	border-radius: 3px;
	text-align: center;
	line-height: 40px;
}

.er {
	list-style: none;
	width: 160px;
	display: none;
}

.er li {
	color: #fff;
	height: 30px;
	line-height: 30px;
	text-align: center;
	margin-top: 5px;
	background: #337a97;
	border-radius: 3px;
	font-size: 14px;
}

.er a {
	color: #000;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script type="text/javascript">
	/*if (self != top) {//dom编程
		top.location.href = "user";
	}*/
	var websocket = null;

	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://192.168.0.102:8080/test09/websocket");
	} else {
		alert('没有建立websocket连接')
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		//setMessage("错误");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(event) {
		//setMessage("建立连接");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		$("#count").html(event.data);
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		//setMessage("close");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
	window.onbeforeunload = function() {
		websocket.close();
	}

	//关闭连接
	function closeWebSocket() {
		websocket.close();
	}
	$().ready(function() {
		$(".yi").click(function() {
			$(this).next().slideToggle();
		})
	})
</script>
</head>
<body>
	<div class="w">
		<div id="container">
			<div id="top">
				<div id="title">员工管理系统</div>
				<div id="num">本网站共有${anum}人访问</div>
				<div id="count">${applicationScope.num}</div>
			</div>
			<div id="main">
				<div id="left">
					<div class="yi">员工管理</div>
					<ul class="er">
						<li><a href="emp?type=search" target="right">员工管理</a></li>
						<li><a href="emp?type=showAdd" target="right">添加员工</a></li>
					</ul>
					<div class="yi">部门管理</div>
					<ul class="er">
						<li><a href="dep" target="right">部门管理</a></li>
						<li><a href="dep?type=showAdd" target="right">添加部门</a></li>
					</ul>
					<div class="yi">项目管理</div>
					<ul class="er">
						<li><a href="pro" target="right">项目管理</a></li>
						<li><a href="pro?type=showAdd" target="right">添加项目</a></li>
					</ul>
					<div class="yi">绩效管理</div>
					<ul class="er">
						<li><a href="sco" target="right">绩效查看</a></li>
						<li><a href="sco?type=m2" target="right">绩效管理</a></li>
					</ul>
				</div>
				<iframe id="right" name="right" scrolling="no" frameborder="0"
					src="emp?type=search"></iframe>
			</div>
			<div id="bottom"></div>
		</div>
	</div>
</body>
</html>