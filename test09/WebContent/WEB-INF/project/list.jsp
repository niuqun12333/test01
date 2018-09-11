<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">

/*table  tr:nth-child(even) 
	{
	background-color: #D4FFFF;
}

table  tr:nth-child(odd) 
	{
	background-color: #D4BFFF;
}

table{
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid #666;
}

td {
	width: 100px;
	height: 50px;
	text-align: center;
}*/
#main {
	width: 580px;
	margin: 20px auto;
}

#pro .select {
	background: #337ab7;
}

#pro tr {
	width: 183px;
}

#pro input {
	width: 100px;
}

#pro select {
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
		/*$("table tr:odd").css({
			background:"#ccc";
		})
		$("table tr:even").css({
			background:"#666";
		})*/
		var selectId = -1;
		$("#showAdd").click(function() {
			window.location.href = "pro?type=showAdd";
		})
		$("#showUpdate").click(function() {
			if (selectId > -1) {
				location.href = "pro?type=showUpdate&id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})

		$("#delete").click(function() {
			if (selectId > -1) {
				if(confirm("确定删除吗")){
					alert("删除成功"); 
					location.href = "pro?type=delete&id=" + selectId;
					return true;
				}else{
					return false;	 
				}	
			} else {
				alert("请选中一条数据!");
			}
		})
		
		$("tr").click(function() {
			$(this).toggleClass("select");
			//selectId=$(this).children().eq(0).text();
			//alert(selectId);
			var length = $("#pro .select").length;
			if(length>0){
			selectId = $(this).data("id");
			} else{
				selectId = -1;
			}
		})
		
		if(${p.ye}<=1){
			$("#pre").addClass("disabled");
		    $("#pre").find("a").attr("onclick","return false");
		}
		if(${p.ye}>=${p.maxYe}){ 
			$("#next").addClass("disabled");
		    $("#next").find("a").attr("onclick","return false");
		}
	})
</script>
</head>
<body>
	<div id="main">
	    <div>
           <h1>项目管理</h1> 
       </div>
		<form action="pro" method="post" class="form-horizontal" role="form">
			<!--<input type="hidden" name="type" value="search" />-->
			<div class="form-group">
				<div class="col-sm-6">
					<input type="text" class="form-control" name="name"
						placeholder="请输入项目名" value=${c.name}>
				</div>
				<div class="col-sm-6">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
			</div>
		</form>

		<table class="table table-bordered table-striped table-hover" id="pro">
			<thead>
				<tr>
					<th>项目</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="pro">
					<tr data-id="${pro.id}">
						<td>${pro.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="fy">
			<ul class="pagination">
				<li id="pre"><a
					href="pro?ye=${p.ye-1}&name=${c.name}">&laquo;上一页</a></li>
				<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
					<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
						href="pro?ye=${status.index}&name=${c.name}">${status.index}</a></li>
				</c:forEach>
				<li id="next"><a
					href="pro?ye=${p.ye+1}&name=${c.name}">下一页&raquo;</a></li>
			</ul>
		</div>
		<div>
			<button type="button" class="btn btn-primary" id="showAdd">增加</button>
			<button type="button" class="btn btn-primary" id="showUpdate">修改</button>
			<button type="button" class="btn btn-primary" id="delete">删除</button>
			
		</div>
	</div>
	<!--<input type="button" value="增加" id="addBtn" type="name">  -->

</body>
</html>