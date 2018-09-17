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
	width: 100px;
	height: 30px;
}

tr th, div {
	text-align: center;
}

#emp img {
	width: 30px;
	height: 30px;
}

#bigPicture {
	display: none;
	width: 150px;
	height: 150px;
	position: absolute;
}

#bigPicture img {
	width: 150px;
	height: 150px;
}
</style>
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>  
<script type="text/javascript">
	$(document).ready(function() {
		/*$("table tr:odd").css({
			background:"#ccc";
		})
		$("table tr:even").css({
			background:"#666";
		})*/
		var selectId = -1;
		var selectImg = "";
		$("#showAdd").click(function() {
			window.location.href = "showAdd.do";
		})
		$("#showAdd2").click(function() {
			window.location.href = "showAdd2.do"; 
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
				if (confirm("确定删除吗")) {
					alert("删除成功");
					location.href = "delete.do?id=" + selectId;
					if (selectImg != "" && selectImg != null) {
						$.ajax({
							url : "filedelete.do",
							type : "post",
							data : {
								picture : selectImg
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
					return true;
				} else {
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
			var length = $("#emp .select").length;
			if (length > 0) {
				selectId = $(this).data("id");
				selectImg = $(this).data("img");
				//alert(selectId+" "+selectdId);
			} else {
				selectId = -1;
			}
		})

		$("#emp img").hover(function(event) {
			var src = $(this).attr("src");
			$("#bigPicture img").attr("src", src);
			$("#bigPicture").show();
			$("#bigPicture").css({
				left : event.pageX + 10,
				top : event.pageY + 10
			});
		}, function() {
			$("#bigPicture").hide();
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
			<h1>员工管理</h1>
		</div>
		<form action="search.do" method="post">
			<div class="form-group">
				<div class="col-sm-2">
					<input type="text" class="form-control" name="name"
						placeholder="姓名" value=${c.name}>
				</div>
				<div class="col-sm-2">
					<select name="sex" class="form-control">
						<option value="">性别</option>
						<option value="男" <c:if test="${c.sex == '男'}">selected</c:if>>男</option>
						<option value="女" <c:if test="${c.sex == '女'}">selected</c:if>>女</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="age" placeholder="年龄"
						value=${c.age!=-1?c.age:''}>
				</div>
				<div class="col-sm-2">
					<select name="dep.id" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${depList}" var="dlist">
							<option value="${dlist.id}"
								<c:if test="${dlist.id == c.dep.id}"> selected </c:if>>${dlist.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
			</div>
		</form>

		<table class="table table-bordered table-striped table-hover" id="emp">
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>部门</th>
					<th>图片</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="emp">
					<tr data-id="${emp.id}" data-img="${emp.picture}">
						<td>${emp.name}</td>
						<td>${emp.sex}</td>
						<td>${emp.age}</td>
						<td>${emp.dep.name}</td>
						<td><c:if test="${not empty emp.picture}">
								<img src="<%=request.getContextPath()%>/pic/${emp.picture}" />
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="fy">
			<ul class="pagination">
				<li><a
					href="search.do?ye=1&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&dep.id=${c.dep.id!=-1?c.dep.id:''}">&laquo;首页</a></li>
				<li id="pre"><a
					href="searchEmp.do?ye=${p.ye-1}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&dep.id=${c.dep.id!=-1?c.dep.id:''}">&laquo;上一页</a></li>
				<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
					<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
						href="search.do?ye=${status.index}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&dep.id=${c.dep.id}">${status.index}</a></li>
				</c:forEach>
				<li id="next"><a
					href="search.do?ye=${p.ye+1}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&dep.id=${c.dep.id!=-1?c.dep.id:''}">下一页&raquo;</a></li>
				<li><a
					href="search.do?ye=${p.maxYe}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&dep.id=${c.dep.id!=-1?c.dep.id:''}">末页&raquo;</a></li>
			</ul>
		</div>
		<div>
			<button type="button" class="btn btn-primary" id="showAdd">增加</button>
			<button type="button" class="btn btn-primary" id="showAdd2">增加2</button>
			<button type="button" class="btn btn-primary" id="showUpdate">修改</button>
			<button type="button" class="btn btn-primary" id="delete">删除</button>
		</div>
	</div>
	<div id="bigPicture">
		<img src="">
	</div>


</body>
</html>