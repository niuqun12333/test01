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

#sco .select {
	background: #337ab7;
}

#sco tr {
	width: 183px;
}

#sco input {
	width: 100px;
}

#sco select {
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
	
		$("tr").click(function() {
			$(this).toggleClass("select");
			//selectId=$(this).children().eq(0).text();
			//alert(selectId);
			var length = $("#sco .select").length;
			if(length>0){
			selectId = $(this).data("id");
			//alert(selectId);
			} else{
				selectId = -1;
			}
		})
		$("tr").dblclick(function() {
			var eq1=$(this).children().eq(1).text();
			var eq2=$(this).children().eq(2).text();
			//alert(eq1+" "+eq2);
			if( eq1 !="" && eq2 != ""){
			 $(this).unbind("dblclick");
			 $(this).unbind("click"); 
			 $(this).addClass("updateSco");
			 var value=$(this).children().eq(3).text();
			 $(this).children().eq(3).html("<input  type='text' value='"+value+"' name='value'/> ");
			}else{
				alert("无法修改");
			}
		})
		/*$(document).on("blur","[name=value]",function(){
			var length = $("#sco .updateSco").length;
			var val=$(this);
			if (length > 0) {
				//var emps = "";
				var array =new Array();
				$(".updateSco").each(function(index, element) {
					var id = $(this).data("id");
					var value = $(this).find("[name=value]").val();
					var sco={
					    id:id,
						value:value
					}
					array.push(sco);
				})
				var str=JSON.stringify(array);
				//str=str.replace(/{/g,"%7b");//g表示全局
				//str=str.replace(/}/g,"%7d");
				$.ajax({
					url:"sco",
					type:"post",
					data:{type:"updata2",sco:str},
					dataType : "text",
					success : function(data) {
					   if(data=="true"){
						   
					   }
					}
				})			
			} else {
				alert("请选中数据!");
			}
		})*/
		$(document).on("blur","#sco [name=value]",function(){
			var length = $("#sco .updateSco").length;
			var value=$(this).val();
			var grade=$(this).parents("tr").find("td").eq(4);
			var input=$(this).parents("tr");
			var id=$(this).parents("tr").data("id");
			var empId=$(this).parents("tr").data("empid");
			var proId=$(this).parents("tr").data("proid");
			//alert(value+" "+grade.html()+" "+id); 
			if (length > 0) {
				$.ajax({
					url:"sco",
					type:"post",
					data:{type:"input",
						id:id,
						value:value,
						empId:empId,
				        proId:proId
						}, 
				    dataType:"json",
				    success:function(data){
				    	grade.html(data.grade);
				    	input.data("id",data.id);
				    }
				})
			} else {
				alert("请选中数据!");
			}
		})
		var html = $("[name=proId]").html();
		var html2 = html;
		$("form [name=depId]").bind("change",function(){
		   var depId=$(this).val();
		   var proHtml=$("[name=proId]");
           var returnHtml = html2;
           if(depId == null|| depId == ""){
        	   proHtml.html("");
        	   proHtml.html(returnHtml);
           }else{
        	   $.ajax({
            	   url:"sco",
    				type:"post",
    				data:{type:"change",depId:depId}, 
    			    dataType:"json", 
    			    success:function(data){
    			    	$("[name=proId]").html("");
    			    	var str = "<option value=''>项目</option>"; 
    			    	$("[name=proId]").append(str);
    			    	var str2 = "";
    			    	$.each(data,function(i,result){
    			    		str2="<option value='"+data[i].pro.id+"'>"+data[i].pro.name+"</option>";
    			    		$("[name=proId]").append(str2);
    			        }) 
    			     }
    			 })
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
		//alert("${c.dep.id}")
	})
</script>
</head>
<body>
	<div id="main">
		<form action="sco" method="post" class="form-horizontal">
			<input type="hidden" name="type" value="m2" />
			<div class="form-group">
				<div class="col-sm-2">
					<input type="text" class="form-control" name="depName"
						placeholder="姓名" value="${c.dep.name}">
				</div>
				<div class="col-sm-2">
					<select name="depId" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.id}"
								<c:if test="${dep.id == c.dep.id}"> selected </c:if>>${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<select name="proId" class="form-control">
						<option value="">项目</option>
						<c:forEach items="${proList}" var="pro">
							<option value="${pro.id}"
								<c:if test="${pro.id == c.pro.id}"> selected </c:if>>${pro.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="value"
						placeholder="成绩" value="${c.value!=-1?c.value:''}">
				</div>
				<div class="col-sm-2">
					<select name="grade" class="form-control">
						<option value="">等级</option>
						<c:forEach items="${gradeList}" var="grade">
							<option value="${grade.grade}"
								<c:if test="${grade.grade == c.grade}"> selected </c:if>>${grade.grade}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
			</div>
		</form>

		<table class="table table-bordered table-striped table-hover" id="sco">
			<thead>
				<tr>
					<th>姓名</th>
					<th>部门</th>
					<th>项目</th>
					<th>成绩</th>
					<th>等级</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="score">
					<tr data-id="${score.id}" data-empid="${score.emp.id}"
						data-proid="${score.pro.id}">
						<td>${score.emp.name}</td>
						<td>${score.dep.name}</td>
						<td>${score.pro.name}</td>
						<td>${score.value}</td>
						<td>${score.grade}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="fy">
			<ul class="pagination">
				<li><a
					href="sco?type=m2&ye=1&depName=${c.dep.name}&depId=${c.dep.id!=-1?c.dep.id:''}&proId=${c.pro.id!=-1?c.pro.id:''}&value=${c.value!=-1?c.value:''}&grade=${c.grade}">&laquo;首页</a></li>
				<li id="pre"><a
					href="sco?type=m2&ye=${p.ye-1}&depName=${c.dep.name}&depId=${c.dep.id!=-1?c.dep.id:''}&proId=${c.pro.id!=-1?c.pro.id:''}&value=${c.value!=-1?c.value:''}&grade=${c.grade}">&laquo;上一页</a></li>
				<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
					<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
						href="sco?type=m2&ye=${status.index}&depName=${c.dep.name}&depId=${c.dep.id!=-1?c.dep.id:''}&proId=${c.pro.id!=-1?c.pro.id:''}&value=${c.value!=-1?c.value:''}&grade=${c.grade}">${status.index}</a></li>
				</c:forEach>
				<li id="next"><a
					href="sco?type=m2&ye=${p.ye+1}&depName=${c.dep.name}&depId=${c.dep.id!=-1?c.dep.id:''}&proId=${c.pro.id!=-1?c.pro.id:''}&value=${c.value!=-1?c.value:''}&grade=${c.grade}">下一页&raquo;</a></li>
				<li><a
					href="sco?type=m2&ye=${p.maxYe}&depName=${c.dep.name}&depId=${c.dep.id!=-1?c.dep.id:''}&proId=${c.pro.id!=-1?c.pro.id:''}&value=${c.value!=-1?c.value:''}&grade=${c.grade}">末页&raquo;</a></li>
			</ul>
		</div>
	</div>
	<!--<input type="button" value="增加" id="addBtn" type="name">  -->

</body>
</html>