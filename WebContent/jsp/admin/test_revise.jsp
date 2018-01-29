<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    
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
		<link rel="stylesheet" type="text/css" href="css/admin_common.css" />
		<link rel="stylesheet" type="text/css" href="css/admin_main.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
$(function(){
	//初始化
	inint();
	function inint(){
		var qidArray1='${paper.q_id}';
		var qidArray2 = []; 
		qidArray2=qidArray1.split("@");
		$.post("PaperManageServlet?operation=findSelectedQuestion",{"qids":qidArray2},function(data,status){
			if(status=="success"){
			var questionList=JSON.parse(data);
			console.log(questionList);
			for(var i=0;i<questionList.length;i++){
				console.log(questionList[i].q_type);
				var tr=$("<tr>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+(questionList[i].q_type=='q1'?'单选题':questionList[i].q_type=='q2'?'多选题':questionList[i].q_type=='q3'?'填空题':questionList[i].q_type=='q4'?'判断题':questionList[i].q_type=='q5'?'主观题':'')+"</td>"
				+"<td>"+questionList[i].q_title+"</td>"
				+"<td name='scoreTd'>"+questionList[i].q_score+"</td>"
				+"<td><input type='button' value='删除' class='btn btn6' name='del'></td>"
				+"<input type='hidden' name='q_ids' value='"+questionList[i].q_id+"'/>'"
				+"</tr>");
				$("#qTable2").append(tr);
			}
			}
		});
		
	}
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
			var q_id2=$("#qTable2").find("input[name='q_id']");
			for(var i=0;i<questionList.length;i++){
				var flag=1;
				q_id2.each(function(){
					if($(this).val()==questionList[i].q_id){
						flag=0;
					}
				})
				var tr=$("<tr>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+(questionList[i].q_type=='q1'?'单选题':questionList[i].q_type=='q2'?'多选题':questionList[i].q_type=='q3'?'填空题':questionList[i].q_type=='q4'?'判断题':questionList[i].q_type=='q5'?'主观题':'')+"</td>"
				+"<td>"+questionList[i].q_title+"</td>"
				+"<td name='scoreTd'>"+questionList[i].q_score+"</td>"
				+"<td><input type='button' value='添加' class='btn btn6' name='add'></td>"
				+"<input type='hidden' name='q_id' value='"+questionList[i].q_id+"'/>'"
				+"</tr>");
				if(flag==1){
				$("#qTable1").append(tr);
				}
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
				var scoreTd=$("td[name='scoreTd']");
				var score=0;
				console.log(scoreTd);
				scoreTd.each(function(){
					score+=Number($(this).html());
				});
				$("input[name='score']").val(score);
			})
	$(".insert-tab").on("click","table input[name='del']",function(){
				var qTr=$(this).parent().parent();
				qTr.children("input[name='q_ids']").attr("name","q_id");
				$("#qTable1").append(qTr);
				$(this).attr("name","add").attr("value","添加");
			})
			
})
</script>
	</head>

	<body>
		<div class="topbar-wrap white">
			<div class="topbar-inner clearfix">
				<div class="topbar-logo-wrap clearfix">
					<ul class="navbar-list clearfix">
						<li>
							<a href="jsp/admin/index.jsp">SgExam在线考试系统</a>
						</li>
					</ul>
				</div>
				<div class="top-info-wrap">
					<ul class="top-info-list clearfix">
						<li>
							<a href="jsp/admin/index.jsp">你好！${adm_name }</a>
						</li>
						<li>
							<a href="jsp/admin/modify_password.jsp">修改密码</a>
						</li>
						<li>
							<a href="QuitServlet?status=admin">退出</a>
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
									<a href="jsp/admin/index.jsp"><i class="icon-font">&#xe012;</i>基本信息</a>
								</li>
								<li>
									<a href="ManageAdminServlet?flag=find&pageNum=1"><i class="icon-font">&#xe012;</i>管理员管理</a>
								</li>
								<li>
									<a href="jsp/admin/news_list.jsp"><i class="icon-font">&#xe012;</i>消息中心</a>
								</li>
								<li>
									<a href="servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=admin"><i class="icon-font">&#xe008;</i>试题管理</a>
								</li>
								<li class="on">
									<a href="PaperManageServlet?operation=update"><i class="icon-font">&#xe005;</i>考试管理</a>
								</li>
								<li>
									<a href="ManageStudentServlet?flag=find&pageNum=1"><i class="icon-font">&#xe005;</i>学生管理</a>
								</li>
								<li>
									<a href="ManageTeacherServlet?flag=find&pageNum=1"><i class="icon-font">&#xe005;</i>教师管理</a>
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
						<a href="jsp/admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>
						<a class="crumb-name" href="PaperManageServlet?operation=update">考试管理</a><span class="crumb-step">&gt;</span><span>修改考试</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<form action="PaperManageServlet?operation=modify&pid=${paper.p_id}" method="post" id="myform" name="myform">
							<table class="insert-tab" width="100%">
								<tbody>
								<tr><th>考试班级</th>
								<td>
								<select name="classes" class="required">
											<option value="0" >请选择班级</option>
											<c:forEach items="${classesList}" var="classes">
												<option value="${classes.cls_id }" ${classes.cls_id==paper.cls_id?'selected':''}>${classes.cls_name}</option>
												</c:forEach>
								</select>
								</td>
								</tr>
								<tr>
										<th width="120"><i class="require-red">*</i>所属课程：</th>
										<td>
											<select name="course" id="ctype">
												<c:forEach items="${coursList }" var="course">
												<option value="${course.c_id }" ${course.c_id==paper.c_id?'selected':''}>${course.c_name }</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试名称：</th>
										<td>
											<input class="common-text required" id="title" name="papername" size="50" value="${paper.p_name}" type="text">
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
											<input class="common-text required" id="title" name="score" size="50" value="${paper.p_scores}" type="text"/>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试日期：</th>
										<td>
											<input class="common-text required" id="title" name="p_date" size="50" value="${paper.p_date }" type="date"/>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试开始时间：</th>
										<td>
											<input class="common-text required" id="title" name="startTime" size="50" value="${ paper.startTime}" type="time"/>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>考试结束时间：</th>
										<td>
											<input class="common-text required" id="title" name="endTime" size="50" value="${ paper.endTime}" type="time">
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