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
	width: 600px;
	margin: 20px auto;
}

#pro, #noPro {
	width: 700px;
	height: 200px;
	border: 1px solid #337ab7;
	border-radius: 3px;
}

#btn {
	margin: 20px auto;
	width: 268px;
}

#add {
	margin-right: 50px;
}

.pro {
	background: #337ab7;
	height: 40px;
	line-height: 40px;
	float: left;
	margin-left: 5px;
	color: #fff;
	padding: 0 10px;
	border-radius: 3px;
	margin-top: 10px;
}

.select {
	background: #d9534f;
}

#return {
	text-align: center;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".pro").click(function() {
			$(this).toggleClass("select");
		})
		$("#add").click(function() {
			if ($("#noPro").find(".select").length > 0) {
				var proId=$("#noPro").find(".select").data("id");
				$.ajax({
					url:"d2p",
					type:"post",
					data:{type:"add2",depId:${dep.id},proId:proId} ,  
				    dataType:"text",
				    success:function(data){
				      if(data == "true"){
				    	  var pro=$("#noPro").find(".select");
				    	  pro.removeClass("select");
				    	  $("#pro").append(pro);
				      }
				    }
				})
			} else {
				alert("请选择数据");
			}
		})
			$("#delete").click(function() {
			if ($("#pro").find(".select").length > 0) {
				var proId=$("#pro").find(".select").data("id");
				$.ajax({
					url:"d2p",
					type:"post",
					data:{type:"delete2",depId:${dep.id},proId:proId},   
				    dataType:"text",
				    success:function(data){
				      if(data == "true"){
				    	  var pro=$("#pro").find(".select"); 
				    	  pro.removeClass("select");
				    	  $("#noPro").append(pro); 
				      }
				    }
				})
			} else {
				alert("请选择数据");
			}
		})
		$("#deleteBatch").click(function() {
			if ($("#pro").find(".select").length > 0) {
				var array =new Array();
				$(".select").each(function(index, element) {
					 var depId = ${dep.id}; 
					 var proId = $(this).data("id");
					var pro={
						depId:depId,
						proId:proId 
					}
					array.push(pro);
				})
				var str=JSON.stringify(array);
				//var str1=str.replace(/{/g,"%7b");  
				//str1=str1.replace(/}/g,"%7d");
				
				$.ajax({
					url:"d2p",
					type:"post",
					data:{type:"deleteBatch",depId:${dep.id},pro:str}, 
				    dataType:"text",
				    success:function(data){
				      if(data == "true"){
				    	  var pro=$("#pro").find(".select"); 
				    	  pro.removeClass("select");
				    	  $("#noPro").append(pro); 
				      }
				    }
				})
			}else {
				alert("请选中数据!");
			}
		})
		$("#addBatch").click(function() {
			if ($("#noPro").find(".select").length > 0) {
				var pro="";
				
				$(".select").each(function(index, element){
				     var depId = ${dep.id}; 
					 var proId = $(this).data("id");					
					 pro += depId + "," + proId + ";";
					}) 
					pro = pro.substring(0, pro.length - 1);
				
				$.ajax({
					url:"d2p",
					type:"post",
					data:{type:"addBatch",pro:pro}, 
				    dataType:"text",
				    success:function(data){
				      if(data == "true"){
				    	  var pro=$("#noPro").find(".select");
				    	  pro.removeClass("select");
				    	  $("#pro").append(pro);
				      }
				    }
				})
			} else {
				alert("请选择数据");
			}
		})
		$("#returnDep").click(function() {
			location.href="dep";
		})

	})
</script>
</head>
<body>
	<div id="main">
		<h2>${dep.name}</h2>
		<div id="pro">

			<c:forEach items="${list}" var="pro">
				<div class="pro" data-id="${pro.id}">${pro.name}</div>
			</c:forEach>

		</div>
		<div id="btn">
			<button type="button" class="btn btn-primary" id="addBatch">Batch↑</button>
			<button type="button" class="btn btn-primary" id="add">↑</button>
			<button type="button" class="btn btn-primary" id="delete">↓</button>
			<button type="button" class="btn btn-primary" id="deleteBatch">↓Batch</button>
		</div>
		<div id="noPro">

			<c:forEach items="${noList}" var="pro">
				<div class="pro" data-id="${pro.id}">${pro.name}</div>
			</c:forEach>

		</div>
		<div id="return">
			<button type="button" class="btn btn-primary" id="returnDep">返回部门</button>
		</div>
	</div>
</body>
</html>