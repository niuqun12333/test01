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
#main {
	width: 650px;
	margin: 20px auto;
}

#emp .select {
	background: #337ab7;
}

#emp tr {
	width: 183px;
}

#emp input {
	width: 100px;
}

#emp select {
	width: 50px;
	height: 25px;
}

tr th, div {
	text-align: center;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var selectId = -1;
		$("#add").click(function(){
			var pId=$("#selectPro").val();
			var dId=${dId};
			location.href="d2p?type=add&proId="+pId+"&depId=${dep.id}";
		})
		//<c:if test="${f:length(notProList)==0}">
		//$("#add").unbind("click");
		//$("#add").addClass("disabled");
		//</c:if>
		if($("#selectPro").children().length==0){
			$("#add").unbind("click");
			$("#add").addClass("disabled");
		}
		$("#delete").click(function() {
			if (selectId > -1) {
				if(confirm("确定删除吗")){
					alert("删除成功"); 
					var pId=selectId;
					var dId=${dId};
					location.href="d2p?type=delete&proId="+pId+"&depId=${dep.id}";
					return true;
				}else{
					return false;	 
				}	
			} else {
				alert("请选中一条数据!");
			}
		})	
		$("#returnDep").click(function() {
			location.href="dep";
		})
		$("tr").click(function() {
			$(this).toggleClass("select");
			var length = $("#emp .select").length;
			if(length>0){
			selectId = $(this).data("id");
			} else{
				selectId = -1;
			}
		})
	})
</script>
</head>
<body>
	<div id="main">
	   <div>
           <h1>${dep.name}</h1> 
       </div>
		<table class="table table-bordered table-striped table-hover" id="emp">
			<thead>
				<tr>
					<th>项目</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="in">
					<tr data-id="${in.id}">
						<td>${in.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<div class="col-sm-6">
				<select class="form-control" id="selectPro">
					<c:forEach items="${notProList}" var="notIn">
						<option value="${notIn.id}">${notIn.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<button type="button" class="btn btn-primary" id="add">增加</button>
			</div>
			<div class="col-sm-2">
				<button type="button" class="btn btn-primary" id="delete">删除</button>
			</div>
			<div class="col-sm-2">
				<button type="button" class="btn btn-primary" id="returnDep">返回部门</button>
			</div>
		</div>
	</div>

</body>
</html>