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

#dep .select {
	background: #337ab7;
}

#dep tr {
	width: 183px;
}

#dep input {
	width: 100px;
}

#dep select {
	width: 50px;
	height: 25px;
}

tr th, div {
	text-align: center;
}
</style>
<%@include file="../common.jsp" %>
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
			window.location.href = "showAdd.do";
		})
		$("#showUpdate").click(function() {
			if (selectId > -1) {
				location.href = "showUpdate.do?id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})

		$("#delete").click(function() {
			if (selectId > -1) {
				if(confirm("确定删除吗")){
					alert("删除成功"); 
					location.href = "delete.do?id=" + selectId;
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
			var length = $("#dep .select").length;
			if(length>0){
			selectId = $(this).data("id");
			} else{
				selectId = -1;
			}
		})	
		if(${p.ye} <= 1){
			$("#pre").addClass("disabled");
		    $("#pre").find("a").attr("onclick","return false");
		}
		
		if(${p.ye} >= ${p.maxYe}){  
			$("#next").addClass("disabled");
		    $("#next").find("a").attr("onclick","return false");
		}
		
	})
</script>
</head>
<body>
	<div id="main">
		<div>
			<h1>部门管理</h1>
		</div>
		<form action="search.do" method="post" class="form-horizontal" role="form">
			<!--<input type="hidden" name="type" value="search" />-->
			<div class="form-group" id="searchDiv">
				<div class="col-sm-3">
					<input type="text" class="form-control" name="name"
						placeholder="请输入部门名" value=${c.name}>
				</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="empCount"
						placeholder="请输入人数" value=${c.empCount!=-1?c.empCount:''}>
				</div>
				<div class="col-sm-3">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
			</div>
		</form>

		<table class="table table-bordered table-striped table-hover" id="dep">
			<thead>
				<tr>
					<th>部门</th>
					<th>人数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dep">
					<tr data-id="${dep.id}">
						<td>${dep.name}</td>
						<td><a href="../emp/search.do?dep.id=${dep.id}">${dep.empCount}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="fy">
			<ul class="pagination">
				<li><a
					href="search.do?ye=1&name=${c.name}">&laquo;首页</a></li>
				<li id="pre"><a
					href="search.do?ye=${p.ye-1}&name=${c.name}">&laquo;上一页</a></li>
				<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
					<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
						href="search.do?ye=${status.index}&name=${c.name}">${status.index}</a></li>
				</c:forEach>
				<li id="next"><a
					href="search.do?ye=${p.ye+1}&name=${c.name}">下一页&raquo;</a></li>
				<li><a
					href="search.do?ye=${p.maxYe}&name=${c.name}">末页&raquo;</a></li>
			</ul>
		</div> 
		<div>
			<button type="button" class="btn btn-primary" id="showAdd">增加</button>
			<button type="button" class="btn btn-primary" id="showUpdate">修改</button>
			<button type="button" class="btn btn-primary" id="delete">删除</button>
		</div>
	</div>
</body>
</html>