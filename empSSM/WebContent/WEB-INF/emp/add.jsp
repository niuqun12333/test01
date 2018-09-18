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
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>  
<script type="text/javascript">
	$().ready(function() {
		$("#upload").click(function() {
			var formData = new FormData();//模拟出一个表单
			for (var i = 0; i < $("[name=file]")[0].files.length; i++) {
				formData.append("file", $("[name=file]")[0].files[i]);
			}
			$.ajax({
				url : "upload.do",
				type : "post",
				data : formData,
				cache : false,
				processData : false,
				contentType : false,
				dataType : "text",
				success : function(data) {
					var str = "<img src='<%=request.getContextPath()%>/pic/"+data+"' />";  
					str+="<input type='hidden' name='photo' value='"+data+"'/>";
					$("#pictures").append(str);
					//$("[name=picture]").val(data);//不能给file定义value
				}
			})
		})
		$(document).on("click","#pictures img",function(){
			var picName=$(this).next().val();
			$(this).next().remove();
			$(this).remove();
			$.ajax({
				url:"filedelete.do",
                type:"post",
                data: {picture:picName},
                dataType : "text",
                success:function (data) {
                	if(data !="false"){
                		alert("成功");
                	}else{
                		alert("失败");
                	}
                }
            })
		})
	})
</script>
</head>
<body>
	<div id="main">
		<form action="add.do" method="post" class="form-horizontal"
			role="form"><!--enctype="multipart/form-data"  -->
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
				<label for="d_id" class="col-sm-2 control-label">部门</label>
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
				<div class="col-sm-7 ">
					<input type="file" value="选择图片" name="file" class="form-control" />
				</div>
				<div class="col-sm-3">
					<input type="button" value="上传" id="upload"
						class="form-control btn-primary" />
				</div>
			</div> 
			<div class="form-group" id="pictures"></div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>