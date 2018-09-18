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
<%@include file="../common.jsp" %>
</head>
<body>
	<div id="main">
		<form action="update.do" method="post" class="form-horizontal" role="form">
			<input type="hidden" name="id" value="${dep.id}" />
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="${dep.name}">
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