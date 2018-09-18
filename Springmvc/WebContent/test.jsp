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

#main {
	overflow: hidden;
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
	background: #337ab7;
}

#title {
	color: #fff;
	font-size: 36px;
	height: 80px;
	line-height: 80px;
	margin-left: 20px;
	
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
	$().ready(function() {
		$(".yi").click(function() {
			$(this).next().slideToggle();
		})
	})
</script>
</head>
<body>
	<div id="container">
		<div id="top">
			<div id="title">员工管理系统</div>
		</div>
		<div id="main">
			<div id="left">
				<div class="yi">员工管理</div>
				<ul class="er">
					<li><a href="emp" target="right">员工管理</a></li>
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
					<li><a href="" target="right">绩效管理</a></li>
					<li><a href="" target="right">添加</a></li>
				</ul>
			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0"
				src="emp"></iframe>
		</div>
		<div id="bottom">
			<h4></h4>
		</div>
	</div>
</body>
</html>