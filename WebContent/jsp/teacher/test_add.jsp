<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
	%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta charset="gbk">
		<title>SgExam在线考试系统|考试管理</title>
		<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
		<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
	</head>
<script type="text/javascript">
$(function(){
	$("#classes").ready(function(){
		$.get("PaperManageServlet?operation=findAllClasses",function(data){
			var classesList=JSON.parse(data);
			for(var i=0;i<classesList.length;i++){
				var option=$("<option value="+classesList[i].cls_id+">"+classesList[i].cls_name+"</option>");
				$("#classes").append(option);
			}
		})
	})
	$("#ctype").ready(function(){
		$.get("PaperManageServlet?operation=courseType",function(date){
			var couresList=JSON.parse(date);
			for(var i=0;i<couresList.length;i++){
				var option=$("<option value="+couresList[i].c_id+">"+couresList[i].c_name+"</option>");
				$("#ctype").append(option);
			}
		})
	})
	$("#qtype").ready(function(){
		$.get("PaperManageServlet?operation=questionType",function(date){
			var questionList=JSON.parse(date);
			for(var i=0;i<questionList.length;i++){
				var option=$("<option value="+questionList[i].id+">"+questionList[i].qt_name+"</option>");
				$("#qtype").append(option);
			}
		})
	})
	var findQuestion=function(){
		$("#qTable1").empty();
		var head=$("<tr>"
		+"<th width='15%'>试题序号</th>"
		+"<th width='15%'>题目类型</th>"
		+"<th width='50%'>题目</th>"
		+"<th width='10%'>分值</th>"
		+"<th width='10%'>操作</th></tr>");
		$("#qTable1").append(head);
		$.get("PaperManageServlet?operation=findQuestion&courseType="
				+$("#ctype").val()+"&questionType="+$("#qtype").val(),
				function(date){
			var questionList=JSON.parse(date);
			for(var i=0;i<questionList.length;i++){
				var tr=$("<tr>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+(questionList[i].q_type='q1'?'单选题':questionList[i].q_type='q2'?'多选题':questionList[i].q_type='q3'?'填空题':questionList[i].q_type='q4'?'判断题':questionList[i].q_type='q5'?'主观题':'')+"</td>"
				+"<td>"+questionList[i].q_title+"</td>"
				+"<td name='scoreTd'>"+questionList[i].q_score+"</td>"
				+"<td><input type='button' value='添加' class='btn btn6' name='add'></td>"
				+"<input type='hidden' name='q_id' value='"+questionList[i].q_id+"'/>'"
				+"</tr>");
				$("#qTable1").append(tr);
			}
		})
	}
	$("#ctype").change(function(){
		findQuestion();
	})
	$("#qtype").change(function(){
		findQuestion();
	})
	$(".insert-tab").on("click","table input[name='add']",function(){
				var qTr=$(this).parent().parent();
				qTr.children("input[name='q_id']").attr("name","q_ids");
				$("#qTable2").append(qTr);
				$(this).attr("name","del").attr("value","删除");
				calculateScore();
			})
	$(".insert-tab").on("click","table input[name='del']",function(){
				var qTr=$(this).parent().parent();
				qTr.children("input[name='q_ids']").attr("name","q_id");
				$("#qTable1").append(qTr);
				$(this).attr("name","add").attr("value","添加");
				calculateScore();
			})
	
	var calculateScore=function(){
		var scoreTd=$("#qTable2").find("td[name='scoreTd']");
		var score=0;
		scoreTd.each(function(){
			score+=Number($(this).html());
		});
		$("#score").val(score);
	}
			
})
</script>
	<body>
		
		<div class="topbar-wrap white">
			<div class="topbar-inner clearfix">
				<div class="topbar-logo-wrap clearfix">
					<ul class="navbar-list clearfix">
						<li>
							<a href="jsp/teacher/index.jsp">SgExam在线考试系统</a>
						</li>
					</ul>
				</div>
				<div class="top-info-wrap">
					<ul class="top-info-list clearfix">
						<li>
							<a href="#">你好！${tea_name }</a>
						</li>
						<li>
							<a href="jsp/teacher/modify_password.jsp">修改密码</a>
						</li>
						<li>
							<a href="QuitServlet?status=teacher">退出</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="container clearfix">
			<div class="sidebar-wrap">
				<div class="sidebar-title">
					<h1>菜单</h1>
				</div>
				<div class="sidebar-content">
					<ul class="sidebar-list">
						<li>
							<ul class="sub-menu">
								<li>
									<a href="jsp/teacher/index.jsp"><i class="icon-font">&#xe012;</i>个人中心</a>
								</li>
								<li>
									<a href="jsp/teacher/news_list.jsp"><i class="icon-font">&#xe012;</i>消息中心</a>
								</li>
								<li>
									<a href="servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=teacher"><i class="icon-font">&#xe008;</i>试题管理</a>
								</li>
								<li class="on">
									<a href="PaperManageServlet?operation=update"><i class="icon-font">&#xe005;</i>考试管理</a>
								</li>
								<li>
									<a href="Class_List_BrowseServlet?updateclassform=updateClassForm"><i class="icon-font">&#xe005;</i>班级管理</a>
								</li>
								<li>
									<a href="CountServlet"><i class="icon-font">&#xe005;</i>成绩统计</a>
								</li>
								<li>
									<a href="jsp/teacher/test_score_list.jsp"><i class="icon-font">&#xe005;</i>成绩统计</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<!--/sidebar-->
			<div class="main-wrap">

				<div class="crumb-wrap">
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="jsp/teacher/index.jsp">首页</a><span class="crumb-step">&gt;</span>
						<a class="crumb-name" href="jsp/teacher/test_list.jsp">考试管理</a><span class="crumb-step">&gt;</span><span>添加考试</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<form action="PaperManageServlet?operation=add" method="post" id="myform" name="myform">
							<table class="insert-tab" width="100%">
								<tbody>
								<tr><th>考试班级</th>
								<td>
								<select name="classes" id="classes" class="required">
											<option value="0" >请选择班级</option>
								</select>
								</td>
								</tr>
									<tr>
										<th width="120"><i class="require-red">*</i>所属课程：</th>
										<td>
											<select name="course" id="ctype" class="required">
											<option value="0" >请选择课程</option>
											</select>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试名称：</th>
										<td>
											<input class="common-text required" id="title" name="papername" size="50" value="" type="text">
										</td>
									</tr>
									<tr><th style="text-align:center" colspan="2">试题列表</th></tr>
									<tr><th width="120">题目类型：</th>
									<td>
											<select name="q_type" id="qtype" class="required">
											<option value="0" >请选择题型</option>
											</select>
									</td></tr>
									<tr>
									<td colspan="2">
									<div style="overflow-y:scroll;">
						<table class="result-tab" width="100%"  id="qTable1">
							<tr>
								<th width="15%">试题序号</th>
								<th width="15%">题目类型</th>
								<th width="50%">题目</th>
								<th width="10%">分值</th>
								<th width="10%">操作</th>
							</tr>
						</table>
						</div>
									</td>
									</tr>
									<tr><th style="text-align:center" colspan="2">已选试题</th><tr>
									<tr>
										<td colspan="2">
											<div style="overflow-y:scroll;">
											<table class="result-tab" width="100%" id="qTable2">
											<tr>
												<th width="15%">试题序号</th>
												<th width="15%">题目类型</th>
												<th width="50%">题目</th>
												<th width="10%">分值</th>
												<th width="10%">操作</th>
											</tr>
											</table>
											</div>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>总分值：</th>
										<td>
											<input class="common-text required" id="score" name="score" size="50" value="" type="text">
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试日期：</th>
										<td>
											<input class="common-text required" id="title" name="p_date" size="50" value="" type="date">
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试开始时间：</th>
										<td>
											<input class="common-text required" id="title" name="startTime" size="50" value="" type="time">
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试结束时间：</th>
										<td>
											<input class="common-text required" id="title" name="endTime" size="50" value="" type="time">
										</td>
									</tr>
									<tr>
										<th></th>
										<td>
											<input class="btn btn-primary btn6 mr10" value="提交" type="submit">
											<input class="btn btn6" value="重置" type="reset">
											<input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>

			</div>
			<!--/main-->
		</div>
	</body>

</html>