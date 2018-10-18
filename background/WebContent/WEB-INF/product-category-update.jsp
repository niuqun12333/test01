<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<title>添加产品分类</title>
</head>
<body>
	<c:forEach items="${classList}" var="cla">
		<div class="type_style">
			<div class="type_title">产品类型信息</div>
			<div class="type_content">
				<div class="Operate_btn">
					<a class="btn  btn-warning addBtn"><i
						class="icon-edit align-top bigger-125"></i>新增子类型</a> <a
						href="javascript:ovid()" class="btn  btn-success"><i
						class="icon-ok align-top bigger-125"></i>禁用该类型</a> <a
						data-id="${cla.id}" class="btn  btn-danger" id="deleteBtn"><i
						class="icon-trash   align-top bigger-125"></i>删除该类型</a>
				</div>
				<div class="form form-horizontal" id="form-user-add">
					<div id="xinzeng">
						<div class="Operate_cont clearfix">
							<label class="form-label"><span class="c-red">*</span>分类名称：</label>
							<div class="formControls ">
								<input type="text" class="input-text className" value="${cla.name}"
									placeholder="" data-id="${cla.id}" name="name">
							</div>
						</div>
						<c:forEach items="${cla.mcList}" var="mclass">
						<div class="Operate_cont clearfix mclass">
							<label class="form-label"><span class="c-red">*</span>品牌：</label>
							<div class="formControls ">
								<input type="text" class="input-text oldMClass" value="${mclass.name}"
									placeholder="" data-id="${mclass.id}" name="product-category-name">
							</div>
							<input type="button" value="删除" class="oldDelete" data-id="${mclass.id}">
						</div>
						</c:forEach>
					</div>
					<div class="">
						<div class="" style="text-align: center">
							<input class="btn btn-primary radius submitBtn" type="button" value="提交">
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</c:forEach>
	<script type="text/javascript" src="Widget/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="Widget/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="assets/layer/layer.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			$("#form-user-add").Validform({
				tiptype : 2,
				callback : function(form) {
					form[0].submit();
					var index = parent.layer.getFrameIndex(window.name);
					parent.$('.btn-refresh').click();
					parent.layer.close(index);
				}
			});
			$(".addBtn").click(function() {
								var str = "<div class='Operate_cont clearfix mclass'><label class='form-label'><span class='c-red'>*</span>品牌：</label><div class='formControls '><input type='text' class='input-text newMClass' value='' placeholder=''id='' name=''></div><input type='button' value='删除' class='newDelete'></div>";
								$("#xinzeng").append(str);
							})
			$(document).on("click", ".newDelete", function() {
				var parent = $(this).parent();
				parent.remove();
			})
			var mclassNames = "";
			function updateMClass(mclassId,mclassName){
				$.ajax({
					url:"updateMClass.do",
					type:"post",
					data:{
					id:mclassId,
					name:mclassName},
					dataType:"text",
					success:function(data){
// 						if(data=="true"){
// 							location.href="showClassPro.do";
// 						}else{
// 							location.href="showClassProUpdate.do?id="+id;
// 						}
					}					
				})
			}
			$(document).on("click",".submitBtn",function() {
						$(".newMClass").each(function(index, element) {
							mclassNames += $(this).val() + ",";
						})
						mclassNames = mclassNames.substring(0,mclassNames.length - 1);
						var className = $(".className").val();
						var id=$(".className").data("id");
						$(".oldMClass").each(function(index,element) {
							var mclassName=$(this).val();
							var mclassId=$(this).data("id");
							updateMClass(mclassId,mclassName);
						})
						$.ajax({
							url:"updateClasses.do",
							type:"post",
							data:{
							id:id,
							name:className,
							mclassNames:mclassNames},
							dataType:"text",
							success:function(data){
								if(data=="true"){
									location.href="showClassPro.do";
									window.parent.location.reload();
								}else{
									location.href="showClassProUpdate.do?id="+id;
									window.parent.location.reload();
								}
							}					
						})

					})
			$(".oldDelete").click(function() {
				var parent = $(this).parent();
				//alert(parent.html());
				var id=$(this).data("id");
				$.ajax({
					url:"deleteMClass.do",
					type:"post",
					data:{id:id},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							parent.remove();
// 							window.parent.location.reload();
						}
					}					
				})
			})
			$("#deleteBtn").click(function() {
				var id=$(this).data("id");
				$.ajax({
					url:"deleteClasses.do",
					type:"post",
					data:{id:id},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							location.href="showClassPro.do";
							window.parent.location.reload();
						}
					}					
				})
			})

		});
	</script>
</body>
</html>