<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
#main {
	width: 400px;
	margin: 20px auto;
}
</style>
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>  
</head>
<body>
	<!--  <form action="emp" method="post">
		<input type="hidden" name="type" value="add" /> 
		姓名:<input type="text"name="name" /> <br /> 
		性别:<input type="text" name="sex" /> <br />
		年龄:<input type="text" name="age" /> <br />
		<input type="submit" value="保存" />
	</form>-->
	<div id="main">
		<form action="add2.do" method="post" class="form-horizontal" role="form"
			enctype="multipart/form-data">
			<!-- <input type="hidden" name="type" value="add" /> -->
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex" checked />男 <input
						type="radio" value="女" name="sex" />女
				</div>
			</div>
			<div class="form-group">
				<label for="age" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						placeholder="请输入年龄">
				</div>
			</div>
			<div class="form-group">
				<label for="dep.id" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select name="dep.id" class="form-control">
						<option value="">请输入部门</option>
						<c:forEach items="${depList}" var="dlist">
							<option value="${dlist.id}">${dlist.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="picture" class="col-sm-2 control-label">图片</label>
				<div class="col-sm-10"> 
					<input type="file" value="选择图片" name="file"  class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>