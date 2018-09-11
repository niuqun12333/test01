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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form action="emp" method="post" class="form-horizontal" role="form">
			<input type="hidden" name="type" value="updateBatch1" /> <input
				type="hidden" name="ids" value="${ids}" />
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="${emp.name }">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex"
						<c:if test="${emp.sex=='男'}"> checked </c:if> />男 <input
						type="radio" value="女" name="sex"
						<c:if test="${emp.sex=='女' }"> checked </c:if> />女
				</div>
			</div>
			<div class="form-group">
				<label for="age" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="${emp.age}">
				</div>
			</div>
			<div class="form-group">
				<label for="d_id" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select name="d_id" class="form-control">
						<option value="">请输入部门</option>
						<c:forEach items="${depList}" var="dlist">
							<option value="${dlist.id}"
								<c:if test="${dlist.id == emp.dep.id}">selected</c:if>>${dlist.name}</option>
						</c:forEach>
					</select>
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