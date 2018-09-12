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
	width: 900px;
	margin: 20px auto;
}

.emp {
	width: 400px;
	float: left;
	margin: 10px 50px 10px 0;
	border: 1px solid #ccc;
	padding: 20px 20px 10px 0;
}

#saveBtn {
	clear: both;
	text-align: center;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#save").click(function() {
			var emps = "";
			$(".emp").each(function(index, element) {
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=name]").val();
				var sex = $(this).find("[name=sex]:checked").val();
				var age = $(this).find("[name=age]").val();
				var d_id=$(this).find("[name=d_id]").val();
				emps += id + "," + name + "," + sex + "," + age +","+ d_id + ";";
			})
			emps = emps.substring(0, emps.length - 1);
			window.location.href = "emp?type=updateBatch2&emps=" + emps;
		})
	})
</script>
</head>
<body>

	<div id="main">
		<c:forEach items="${list}" var="emp">
			<form action="emp" method="post" class="form-horizontal emp"
				role="form">
				<input type="hidden" name="type" value="updateBatch2" /> <input
					type="hidden" name="id" value="${emp.id }" />
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
							<c:if test="${emp.sex eq '男'}"> checked </c:if> />男 <input
							type="radio" value="女" name="sex"
							<c:if test="${emp.sex eq '女'}"> checked </c:if> />女
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
			</form>
		</c:forEach>
		<div id="saveBtn">
			<button type="button" class="btn btn-primary" id="save">保存</button>
		</div>
	</div>
</body>
</html>