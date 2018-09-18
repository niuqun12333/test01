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

#pictures img {
	width: 100px;
	height: 100px;
}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		function filedelete(fileName) {
			$.ajax({
				url : "emp?type=filedelete",
				type : "post",
				data : {
					picture : fileName
				},
				dataType : "text",
				success : function(data) {
					if (data != "false") {
						alert("成功");
					} else {
						alert("失败");
					}
				}
			})
		}
		var del="";
		var del2 = "${emp.picture}";
		$("#upload").click(function() {
			var formData = new FormData();//模拟出一个表单
			for (var i = 0; i < $("[name=picture]")[0].files.length; i++) {
				formData.append("picture", $("[name=picture]")[0].files[i]);
			}
			$.ajax({
				url : "emp?type=upload",
				type : "post",
				data : formData,
				cache : false,
				processData : false,
				contentType : false,
				dataType : "text",
				success : function(data) {
					$("#pictures [name=photo]").attr("value", data);
					$("#pictures img").attr("src", "pic/" + data);
					if (del == "") {
						filedelete(del2);
						del = data;  
					}else{
						filedelete(del);
						del="";
						del2 = data;
					}
				}
			})
		})

	})
</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form action="emp" method="post" class="form-horizontal" role="form">
			<input type="hidden" name="type" value="update" /> <input
				type="hidden" name="id" value="${emp.id}" />
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="${emp.name}">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex"
						<c:if test="${emp.sex eq '男'}">checked</c:if> />男 <input
						type="radio" value="女" name="sex"
						<c:if test="${emp.sex eq '女' }"> checked</c:if> />女
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
				<label for="picture" class="col-sm-2 control-label">图片</label>
				<div class="col-sm-7 ">
					<input type="file" value="选择图片" name="picture" class="form-control" />
				</div>
				<!-- multiple -->
				<div class="col-sm-3">
					<input type="button" value="上传" id="upload"
						class="form-control btn-primary" />
				</div>
			</div>
			<div class="form-group" id="pictures">
				<img src="pic/${emp.picture}" /> <input type="hidden" name="photo"
					value="${emp.picture}" />
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