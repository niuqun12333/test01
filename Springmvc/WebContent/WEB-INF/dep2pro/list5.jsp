<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>


<style type="text/css">
#main2 {
	width: 600px;
	margin: 20px auto;
}

#pro, #noPro {
	width: 575px;
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
<script type="text/javascript">
	$().ready(function() {
		$("#main2 .pro").click(function() {
			$(this).toggleClass("select");
		})
		$("#main2 #add").click(function() {
			if ($("#main2 #noPro").find(".select").length > 0) {
				var proId=$("#main2 #noPro").find(".select").data("id");
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
			$("#main2 #delete").click(function() {
			if ($("#main2 #pro").find(".select").length > 0) {
				var proId=$("#main2 #pro").find(".select").data("id");
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
		$("#main2 #deleteBatch").click(function() {
			if ($("#main2 #pro").find(".select").length > 0) {
				var array =new Array();
				$("#main2 .select").each(function(index, element) {
					 var depId = ${dep.id}; 
					 var proId = $(this).data("id");
					var pro={
						depId:depId,
						proId:proId 
					}
					array.push(pro);
				})
				var str=JSON.stringify(array);//json在ajax传参时不用加下面两行替代
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
		$("#main2 #addBatch").click(function() {
			if ($("#main2 #noPro").find(".select").length > 0) {
				var pro="";
				
				$("#main2 .select").each(function(index, element){
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
		$("#main2 #returnDep").click(function() {
			location.href="dep";
		})
		 /*$( "#pro .pro" ).draggable({
			 connectToSortable: "#pro,#noPro",
		      start: function() {
		        
		      },
		      drag: function() {
		        
		      },
		      stop: function() {
		        console.log($(this).offset().top+" "+$(this).offset().left);
		        console.log($("#noPro").offset().top+" "+$("#noPro").offset().left);
		        revert: "invalid";
		        
		      }
		    });*/
		    var noProTop=$("#main2 #noPro").offset().top;
	        var noProLeft=$("#main2 #noPro").offset().left;
	        var noProWidth=parseFloat($("#main2 #noPro").css("width"));
	        var noProHeight=parseFloat($("#main2 #noPro").css("height"));	
	        //alert(noProWidth+" "+noProHeight);
	        var proTop=$("#main2 #pro").offset().top;
	        var proLeft=$("#main2 #pro").offset().left;
	        var proWidth=parseFloat($("#main2 #pro").css("width"));
	        var proHeight=parseFloat($("#main2 #pro").css("height"));
	        var startTop;
	        var startLeft;
		$("#main2 .pro").draggable({
			 //connectToSortable: "#noPro,#pro",
		      start: function() {
		    	  startTop=$(this).offset().top;
		    	  startLeft=$(this).offset().left;
		      },
		      drag: function() {
		        
		      },
		      stop: function() {
		        //console.log($(this).offset().top+" "+$(this).offset().left);
		        //console.log($("#noPro").offset().top+" "+$("#noPro").offset().left); 
		        var stopTop=$(this).offset().top;
		        var stopLeft=$(this).offset().left;
		        if(stopTop>=noProTop&&stopTop<=noProTop+noProHeight&&stopLeft>=noProLeft&&stopLeft<=noProLeft+noProWidth){
		        	var pro = $(this);
		        	var proId=$(this).data("id");
		        		$.ajax({
							url:"d2p",
							type:"post",
							data:{type:"delete2",depId:${dep.id},proId:proId} ,   
						    dataType:"text",
						    success:function(data){
						      if(data == "true"){
						    	  pro.css("position","static");
						    	  $("#noPro").append(pro);
						    	  pro.css("position","relative");
						    	  pro.css("left","0");
						    	  pro.css("top","0");
						      }
						    }
						})
		        	
		        }else if(stopTop>=proTop&&stopTop<=proTop+proHeight&&stopLeft>=proLeft&&stopLeft<=proLeft+proWidth){
		        	var pro = $(this);
		        	var proId=$(this).data("id");
		        	$.ajax({
						url:"d2p",
						type:"post",
						data:{type:"add2",depId:${dep.id},proId:proId} ,   
					    dataType:"text",
					    success:function(data){
					      if(data == "true"){
					    	  pro.css("position","static");
					    	  $("#pro").append(pro);
					    	  pro.css("position","relative");
					    	  pro.css("left","0");
					    	  pro.css("top","0");
					      }
					    }
					})
		        } else {
		        	$(this).offset({top:startTop,left:startLeft});
		        } 	
		      } 
		    });
	})
</script>

	<div id="main2">
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
