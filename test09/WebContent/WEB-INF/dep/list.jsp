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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
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
			window.location.href = "dep?type=showAdd";
		})
		$("#showUpdate").click(function() {
			if (selectId > -1) {
				location.href = "dep?type=showUpdate&id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})

		$("#delete").click(function() {
			if (selectId > -1) {
				if(confirm("确定删除吗")){
					alert("删除成功"); 
					location.href = "dep?type=delete&id=" + selectId;
					return true;
				}else{
					return false;	 
				}	
			} else {
				alert("请选中一条数据!");
			}
		})
		$("#showProject2Dpatment").click(function () {
			if (selectId > -1) {
			location.href = "d2p?id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})
		$("#showProject2Dpatment2").click(function () {
			if (selectId > -1) {
			location.href = "d2p?type=m2&id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})
		$("#showProject2Dpatment3").click(function () {
			if (selectId > -1) {
			location.href = "d2p?type=m3&id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})
		$("#showProject2Dpatment4").click(function () {
			if (selectId > -1) {
			location.href = "d2p?type=m4&id=" + selectId;
			} else {
				alert("请选中一条数据!");
			}
		})
	   $("#showProject2Dpatment5").click(function() {
		   if (selectId > -1) {
			   $("#mBody").html("");
			   var html="d2p?type=m5&id=" + selectId;
			   $("#mBody").load(html);
			    $('#myModal').modal('show');
			    
				} else {
					alert("请选中一条数据!");
				}
	  })
		function doBatch(type) {
			var length = $("#dep .select").length;
			//var ids = "";
			var ids = new Array();
			if (length > 0) {
				$("#dep .select").each(function(index, element) {
					//ids += $(this).data("id") + ",";
					ids.push($(this).data("id"));
				})
				//ids = ids.substring(0, ids.length - 1);
				//alert(ids);				
				location.href = "dep?type=" + type + "&ids=" + ids;
			} else { 
				alert("请选中数据!");
			}
		}
		$("#deleteBatch").click(function() {
			var length = $("#dep .select").length;
			if (length > 0) {
			   if(confirm("确定删除吗")){
				   alert("删除成功"); 
			       doBatch("deleteBatch");
			       return true;
			   }else{
			      return false;	
			  }
			}else { 
				alert("请选中数据!");
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
			<h1>部门管理</h1>
		</div>
		<form action="dep" method="post" class="form-horizontal" role="form">
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
						<td><a href="emp?d_id=${dep.id}">${dep.empCount}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="fy">
			<ul class="pagination">
				<li id="pre"><a
					href="dep?ye=${p.ye-1}&name=${c.name}&empCount=${c.empCount!=-1?c.empCount:''}">&laquo;上一页</a></li>
				<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
					<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
						href="dep?ye=${status.index}&name=${c.name}&empCount=${c.empCount!=-1?c.empCount:''}">${status.index}</a></li>
				</c:forEach>
				<li id="next"><a
					href="dep?ye=${p.ye+1}&name=${c.name}&empCount=${c.empCount!=-1?c.empCount:''}">下一页&raquo;</a></li>
			</ul>
		</div>
		<div>
			<button type="button" class="btn btn-primary" id="showAdd">增加</button>
			<button type="button" class="btn btn-primary" id="showUpdate">修改</button>
			<button type="button" class="btn btn-primary" id="delete">删除</button>
			<button type="button" class="btn btn-primary" id="deleteBatch">批量删除</button>
			<button type="button" class="btn btn-primary"
				id="showProject2Dpatment">管理项目</button>
			<button type="button" class="btn btn-primary"
				id="showProject2Dpatment2">管理项目2</button>
			<button type="button" class="btn btn-primary"
				id="showProject2Dpatment3">管理项目3</button>
			<button type="button" class="btn btn-primary"
				id="showProject2Dpatment4">管理项目4</button>
			<button type="button" class="btn btn-primary"
				id="showProject2Dpatment5" >管理项目5</button>
		</div>
	</div>
	<!--<input type="button" value="增加" id="addBtn" type="name">  -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
				</div>
				<div class="modal-body" id="mBody">
				<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<script>

</script>
</body>
</html>