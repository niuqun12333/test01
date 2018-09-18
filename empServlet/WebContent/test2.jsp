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

#pro1 .select, #pro2 .select {
	background: #337ab7;
}

#pro1 tr, #pro2 tr {
	width: 183px;
}

#pro1 input, #pro2 input {
	width: 100px;
}

#pro1 select, #pro2 select {
	width: 50px;
	height: 25px;
}

#pro1 div, #pro2 div {
	text-align: center;
	float: left;
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
							   proName1=$(this).text();
							   i=index;  
							   alert($(this).val()+" "+proName);
						  }
			    	  })
			    	  $("td").each(function(index,element) {
							  if($(this).data("id")==selectId){
								   proName2=$(this).eq(0).text();
								   i=index;  
								   alert($(this).data("id")+" "+proName);
							  }
				    	  })
						  var td1="<td data-id='"+proId+"' class='bordered'>"+proName1+"</td>";
						  var td2="<td data-id='"+selectId+"' class='bordered'>"+proName2+"</td>";
			    		  $("#pro1").append(td1);
			    		  $("#pro2").append(td2);
			    		  $("#selectPro").children().eq(i).remove();		
						}
			    		  f1();
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
				    	  $("td").each(function(index,element) {
							  if($(this).data("id")==selectId){
								   proName=$(this).eq(0).text();
								   i=index;  
								   alert($(this).data("id")+" "+proName);
							  }
				    	  })
							  var option="<option value='"+selectId+"'>"+proName+"</option>";
							  var td="<td data-id='"+selectId+"' class='bordered'>"+proName+"</td>";
				    		  $("#pro2").append(td);
				    		  $("#selectPro").append(option);
				    		  $("td").eq(i).remove();			    		  				          
				        }
				      f1();
				    }
				})
			} else {
				alert("请选中一条数据!");
			}
		})	
		$("#returnDep").click(function() {
			location.href="dep";
		})
		$(document).on("click","td",function() {
			$(this).toggleClass("select");
			selectId = $(this).data("id");
			//alert(selectId);
		})
	})
</script>
</head>
<body>
	<div id="main">
		<div>
			<h3>${dep.name}</h3>
		</div>
		<!--<table class="table table-bordered table-striped table-hover" id="pro">
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
		</table>-->
		<table class="table table-bordered table-striped table-hover"
			id="pro1">
			<div>
				<h3>已有项目</h3>
			</div>
			<c:forEach var="in" items="${list}" varStatus="status">
				<td data-id="${in.id}"><c:out value="${in.name}" /></td>
			</c:forEach>
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
		<div>
			<table class="table table-bordered table-striped table-hover"
				id="pro2">
				<div>
					<h3>未有项目</h3>
				</div>
				<c:forEach items="${notProList}" var="notIn">
					<td data-id="${notIn.id}"><c:out value="${notIn.name}" /></td>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>