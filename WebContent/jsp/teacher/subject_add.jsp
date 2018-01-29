<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
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
		<title>SgExam在线考试系统|试题管理</title>
		<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
		<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
	</head>
	<script type="text/javascript" src="<%=path%>/js/jquery-3.1.1.js"></script>
	<script type="text/javascript">
	$(function(){
		var selectEle=$("#catid");
		selectEle.change(function(){
			if(selectEle.val()=="q1"||selectEle.val()=="q2"){
				$("tr[name='select']").remove();
				$("#selectBtn").remove();
				showQ1();
				$("#answerTxt").attr("cols","5").attr("rows","1").css("width","auto");
				var newTitle=$("<input class='common-text required' id='title' name='q_title' size='50'  type='text'>");
				$("#title").remove();
				$("#titleTd").append(newTitle);
			}else if(selectEle.val()=="q3"||selectEle.val()=="q4"){
				showQ1();
				$("tr[name='select']").remove();
				$("#selectBtn").remove();$("#selectBtn").remove();
				$("#answerTxt").attr("cols","5").attr("rows","1").css("width","98%");
				var newTitle=$("<input class='common-text required' id='title' name='q_title' size='50'  type='text'>");
				$("#title").remove();
				$("#titleTd").append(newTitle);
			}else if(selectEle.val()=="q5"){
				showQ1();
				$("tr[name='select']").remove();
				$("#selectBtn").remove();$("#selectBtn").remove();
				$("#answerTxt").attr("cols","5").attr("rows","5").css("width","98%");
				var newTitle=$("<textarea  name='q_title' id='title' cols='30' style='width: 98%;' rows='5' ></textarea>");
				$("#title").remove();
				$("#titleTd").append(newTitle);
			}
		});
		showQ1=function(){
		var selects=$("<tr name='select'>"
				+"<th>选项:</th>"
				+"<td>A:<input type='text' size='60' name='selects'/></td>"
				+"</tr>"
				+"<tr name='select'><td></td>"
				+"<td>B:<input type='text' size='60' name='selects'/></td>"
				+"</tr>"
				+"<tr name='select'><td></td>"
				+"<td>C:<input type='text' size='60' name='selects'/></td>"
				+"</tr>"
				+"<tr name='select'><td></td>"
				+"<td><span>D</span>:<input type='text' size='60' name='selects'/></td>"
				+"</tr>");
		selects.insertAfter("#viewTr");
		var selectBtn=$("<tr id='selectBtn'><td></td><td><input type='button' value='添加选项' class='btn btn6' onclick='addSelect(this)'>"
				+"<input type='button' value='删除选项' class='btn btn6' onclick='delSelect(this)'></td></tr>");
		selectBtn.insertBefore("#answerTr");
		}
	
		//删除选项
		delSelect=function(me){
			console.log($("tr[name='select']"));
			console.log($("tr[name='select']").length);
			if($("tr[name='select']").length>4){
			$(me).parent().parent().prev("tr[name='select']").remove();
			}else{
				alert("至少4个选项，无法删除!");
			}
		}
		//添加选项
		addSelect=function(me){
			var zimu=$(me).parent().parent().prev("tr[name='select']").find("span");
			var addZimu=String.fromCharCode((zimu.html().charCodeAt()+1));
			var selectOne=$("<tr name='select'><td></td>"
					+"<td><span>"+addZimu+"</span>:<input type='text' size='60' name='selects'/></td>"
					+"</tr>");
			selectOne.insertBefore("#selectBtn");
		}
		//预览问题
		showQuestion=function(){
			var selectEle=$("#catid");
			if(selectEle.val()=="0"){
				alert("请选择题目类型！");
				
			}else if(selectEle.val()=="q1"||selectEle.val()=="q2"){
				$("#viewTxt").css("display","block");
				$("#viewTxt").text("");
				showQ1Question();
			}else{
				$("#viewTxt").css("display","block");
				$("#viewTxt").text("");
				showQ345Question();
			}
		}
		//预览单选题
		showQ1Question=function(){
			var str=$("#title").val()+"<br/>";
			var selects=$("input[name='selects']");
			var startCode=65;
			selects.each(function(){
				str+=String.fromCharCode(startCode)+":"+$(this).val()+"<br/>";
				startCode++;
			})
			var reg=new RegExp("<br/>","g");
	        var stt= str.replace(reg,"\r\n");
	        $("#viewTxt").text(stt);
		}
		
		//预览填空题
		showQ345Question=function(){
			var str=$("#title").val();
	        $("#viewTxt").text(str);
		}
	});
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
								<li class="on">
									<a href="servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=teacher"><i class="icon-font">&#xe008;</i>试题管理</a>
								</li>
								<li>
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
						<a class="crumb-name" href="jsp/teacher/subject_list.jsp">试题管理</a><span class="crumb-step">&gt;</span><span>添加试题</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<form action="servlet/ManageQuestion.do?flag=add&type=0&pager=1&manager=teacher" method="post" id="myform" name="myform">
							<table class="insert-tab" width="100%">
								<tbody>
									<tr>
										<th width="120"><i class="require-red">*</i>题目类型：</th>
										<td>
											<select name="q_type" id="catid" class="required">
												<option value="0">请选择题目类型</option>
												<c:forEach items="${typeList}" var="type"><option value="${type.id }">${type.qt_name}</option></c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th width="120"><i class="require-red">*</i>课程：</th>
										<td>
											<select name="q_course" id="catid" class="required">
												<option value="0">请选择课程</option>
												<c:forEach items="${courseList}" var="type"><option value="${type.c_id }">${type.c_name}</option></c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>题目：</th>
										<td id="titleTd">
											<input class="common-text required" id="title" name="q_title" size="50" value="" type="text">
										</td>
									</tr>
									<tr id="viewTr">
										<th><input type="button" id="viewBtn" class="btn btn-primary" value="预览" onclick="showQuestion()"/></th>
										<td><textarea readonly="readonly" name="view" class="preview-textarea" id="viewTxt" cols="30" style="width: 98%;display:none;" rows="5" ></textarea></td>
									</tr>
									<tr id="answerTr">
										<th>答案：</th>
										<td><textarea name="q_answer" class="common-textarea" id="answerTxt" cols="5" rows="1"></textarea></td>
									</tr>
									<tr>
										<th>答案解析：</th>
										<td><textarea name="explaination" class="common-textarea" id="explainationTxt" cols="30" style="width: 98%;" rows="10"></textarea></td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>分值：</th>
										<td>
											<input class="common-text required" id="title" name="q_score" size="50" value="" type="text">
										</td>
									</tr>
									<tr>
										<th></th>
										<td>
											<input class="btn btn-primary btn6 mr10" value="提交" type="submit">
											<input class="btn btn6" value="重置" type="reset" id="reset">
											<input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
				<script src="<%=path%>/js/add.js" type="text/javascript" charset="utf-8"></script>
			</div>
			<!--/main-->
		</div>

	</body>

</html>