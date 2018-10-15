<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<meta name="renderer" content="webkit">
<title>登录.云购物商城</title>
<link rel="shortcut icon" type="image/x-icon"
	href="img/icon/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<style type="text/css">
#mes {
	color: red;
}

section {
	height: 700px;
}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$.ready(function() {
		function showMessage(mes) {
			$("#mes").html(mes);
			setTimeout(function() {
				$("#mes").html("");
			}, 2000);
		}
		$("form").submit(function() {
			var str = $("#passWord").val();
			if (str == "") {
				showMessage("密码不能为空");
				return false;
			}
			/*if (str.length < 6) {
				showMessage("密码不能小于六位");
				return false;
			}
			var reg = /^\w*[A-Z]+\w*$/
			if (reg.test(str) == false) {
				showMessage("密码必须包含大写");
				return false;
			}*/
			var reStr = $("#rePwd").val();
			if (str != reStr) {
				showMessage("两次密码输入不一致");
				return false;
			}
		})
		if ("${mes}" != "") {
			showMessage("${mes}");
		}
	})
</script>
</head>
<body>

	<header id="pc-header">
	<div class="center">
		<div class="pc-fl-logo">
			<h1>
				<a href="shoIndex.do"></a>
			</h1>
		</div>
	</div>
	</header>
	<section>
	<div class="pc-login-bj">
		<div class="center clearfix">
			<div class="fl"></div>
			<div class="fr pc-login-box">
				<div class="pc-login-title">
					<h2>用户注册</h2>
				</div>
				<form action="doRegister.do">
					<div class="pc-sign">
						<input type="text" placeholder="账号" name="user">
					</div>
					<div class="pc-sign">
						<input type="password" placeholder="请输入您的密码" id="password"
							name="password">
					</div>
					<div class="pc-sign">
						<input type="password" placeholder="请确认您的密码" id="rePwd">
					</div>
					<div class="pc-sign">
						<input type="text" placeholder="用户名" name="name">
					</div>
					<div class="pc-sign">
						<input type="text" placeholder="手机号" name="phone">
					</div>
					<div class="pc-sign">
						<input type="password" placeholder="请输入您的验证码">
					</div>
					<div id="mes" style="height: 30px;"></div>
					<div class="pc-submit-ss">
						<input type="submit" value="立即注册" placeholder="">
					</div>
					<div class="pc-item-san clearfix">
						<a href="#"><img src="img/icon/weixin.png" alt="">微信登录</a> <a
							href="#"><img src="img/icon/weibo.png" alt="">微博登录</a> <a
							href="#" style="margin-right: 0"><img
							src="img/icon/tengxun.png" alt="">QQ登录</a>
					</div>
					<div class="pc-reg">

						<a href="showLogin.do" class="red">已有账号 请登录</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<footer>
	<div class="center">
		<div class="pc-footer-login">
			<p>关于我们 招聘信息 联系我们 商家入驻 商家后台 商家社区 ©2017 Yungouwu.com 北京云购物网络有限公司</p>
			<p style="color: #999">营业执照注册号：990106000129004 |
				网络文化经营许可证：北网文（2016）0349-219号 | 增值电信业务经营许可证：京2-20110349 | 安全责任书 |
				京公网安备 99010602002329号</p>
		</div>
	</div>
	</footer>

</body>
</html>