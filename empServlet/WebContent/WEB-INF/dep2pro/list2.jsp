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
		var selectId = -1;
		$("#add").click(function add(){
			var proId=$("#selectPro").val();
			var i=0;
			$.ajax({
				url:"d2p",
				type:"post",
				data:{type:"add2",depId:${dep.id},proId:proId}, 
			    dataType:"text",
			    success:function(data){
			      if(data == "true"){
			    	  var proName="";
			    	  $("#selectPro").children().each(function(index,element) {
						  if($(this).val()==proId){
							   proName=$(this).text();
							   i=index;  
						  }
			    	  })
						  var tr="<tr data-id='"+proId+"'><td>"+proId+"</td><td>"+proName+"</td></tr>";
			    		  $("#pro").append(tr);
			    		  $("#selectPro").children().eq(i).remove();
			    		  f1();
			        }
			      
			    }
			})
		})
		//<c:if test="${f:length(notProList)==0}">
		//$("#add").unbind("click");
		//$("#add").addClass("disabled");
		//</c:if>
	   function f1(){
		   if($("#selectPro").children().length==0){
				$("#add").unbind("click",add);
				$("#add").addClass("disabled");
		   }else {
				$("#add").bind("click",add);
				$("#add").removeClass("disabled");
		   }
		}
		$("#delete").click(function() {
			if (selectId > -1) {
				var proId=selectId;
				var i=0;
				$.ajax({ 
					url:"d2p",
					type:"post",
					data:{type:"delete2",depId:${dep.id},proId:proId},   
				    dataType:"text",
				    success:function(data){
				      if(data == "true"){
				    	  var proName="";
				    	  $("tr").each(function(index,element) {
							  if($(this).data("id")==selectId){
								   proName=$(this).children().eq(1).text();
								   i=index;  
							  }
				    	  })
							  var option="<option value='"+selectId+"'>"+proName+"</option>";							  
				    		  $("#selectPro").append(option);
				    		  $("tr").eq(i).remove();
				    		  f1();
				        }
				      
				    }
				})
			} else {
				alert("请选中一条数据!");
			}
		})	
		$("#returnDep").click(function() {
			location.href="dep";
		})
		$(document).on("click","tr",function() {
			$(this).toggleClass("select");
			selectId = $(this).data("id");
		})
	})
</script>
</head>
<body>
	<div id="main">
		<div>
			<h1>${dep.name}</h1>
		</div>
		<table class="table table-bordered table-striped table-hover" id="pro">
			<thead>
				<tr>
					<th>id</th>
					<th>项目</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="in">
					<tr data-id="${in.id}">
						<td>${in.id}</td>
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