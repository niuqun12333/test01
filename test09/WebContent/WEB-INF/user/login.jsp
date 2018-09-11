<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
* {
	padding: 0;
	margin: 0;
}

html body {
	background: #337ab7;
}

li, a {
	list-style: none;
	text-decoration: none;
}

#main {
	overflow: hidden;
	width: 480px;
	margin: 30px auto;
	width: 480px;
}

.w {
	width: 100%;
	background: #fff;
}

li, a {
	list-style: none;
	text-decoration: none;
}

#top, #bottom {
	clear: both;
	height: 80px;
}

#title {
	color: #000;
	font-size: 36px;
	height: 80px;
	line-height: 80px;
	margin-left: 240px;
}

#top {
	width: 100%;
	border-bottom: 1px solid #337ab7;
}

#bottom {
	width: 100%;
	border-top: 1px solid #337ab7;
}

#login {
	width: 320px;
	border: 1px solid #ccc;
	padding: 20px 20px 15px 15px;
	margin: 100px auto;
	border-radius: 3px;
	float: left;
}

#login a {
	text-decoration: none;
}

#showRegister {
	height: 16px;
	line-height: 16px;
	float: right;
}

#yanzheng {
	width: 130px;
	float: left;
}

#userName, #passWord {
	width: 241px;
}

#mes {
	color: red;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#image").click(function() {
			$(this).attr("src", "user?type=getCode&" + Math.random());
		})
		function showMessage(mes) {
			$("#mes").html(mes);
			setTimeout(function() {
				$("#mes").html("");
			}, 2000);
		}
		if ("${mes}" != "") {
			showMessage("${mes}");
		}
	})
</script>
<body>
	<div class="w">
		<div id="container">
			<div id="top">
				<div id="title">员工管理系统登录</div>
			</div>
			<div id="main">
				<div id="login">
					<form action="user" method="post">
						<input type="hidden" name="type" value="doLogin" />
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">@</span> <input
								id="userName" type="text" class="form-control" placeholder="用户名"
								aria-describedby="basic-addon1" name="username"
								value="${username}">
						</div>
						<br>
						<!--下面是密码输入框-->
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">@</span> <input
								id="passWord" type="password" class="form-control"
								placeholder="密码" aria-describedby="basic-addon1" name="password">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">@</span> <input
								id="yanzheng" type="text" class="form-control" placeholder="验证码"
								aria-describedby="basic-addon1" name="random"> <img
								src="user?type=getCode" id="image" />
						</div>
						<br>
						<div id="showRegister">
							<a href="user?type=showRegister">注册</a>
						</div>
						<div id="mes" style="height: 30px;"></div>
						<!--下面是登陆按钮,包括颜色控制-->
						<input type="submit" style="width: 280px;" class="btn btn-default"
							value="登录">
					</form>
				</div>
			</div>
			<div id="bottom">
				<h4></h4>
			</div>
		</div>
	</div>
</body>
</html>