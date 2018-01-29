<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="gbk"%>
<%@ page import="edu.numberone.studystar.entity.Question"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta charset="GBK">
<title>SgExam在线考试系统|试题管理</title>
<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	var skipBtn=$("#skip");
	var startBtn=$("#startPage");
	var upBtn=$("#upPage");
	var nextBtn=$("#nextPage");
	var endBtn=$("#endPage");
	
	//跳转页面
	function skipPage(page){
	with (document.getElementById("page")) {  
	    method = "post";  
	    action = "servlet/ManageQuestion.do?flag=find&manager=teacher&pager="+page;
	    submit();  
		}  
		 }
	
skipBtn.click(function(){
	var page=document.getElementById("skipPage").value;
	 skipPage(page);
});

startBtn.click(function(){
	var page='${pager.currentPage}';
	 skipPage(page);
});
upBtn.click(function(){
	var page=Number('${pager.currentPage}')-1;
	if(page!=0){
	 skipPage(page);
	 }else{
		 skipPage(1);
	 }
});
nextBtn.click(function(){
	var page=Number('${pager.currentPage}')+1;
	if(page>'${pager.totalPage}'){
	 skipPage('${pager.currentPage}');
	 }else{
		 skipPage(page);
	 }
});
endBtn.click(function(){
	var page='${pager.totalPage}';
	 skipPage(page);
});

});
</script>
</head>

<body>
	<div class="topbar-wrap white">
		<div class="topbar-inner clearfix">
			<div class="topbar-logo-wrap clearfix">
				<ul class="navbar-list clearfix">
					<li><a href="jsp/teacher/index.jsp">SgExam在线考试系统</a></li>
				</ul>
			</div>
			<div class="top-info-wrap">
				<ul class="top-info-list clearfix">
					<li><a href="#">你好！${tea_name }</a></li>
					<li><a href="jsp/teacher/modify_password.jsp">修改密码</a></li>
					<li><a href="QuitServlet?status=teacher">退出</a></li>
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
									<a href="servlet/ScoreCorrectServlet.do?operation=toScoreCorrect"><i class="icon-font">&#xe005;</i>批改试卷</a>
								</li>
								<li>
									<a href="CountServlet"><i class="icon-font">&#xe005;</i>成绩统计</a>
								</li>
							</ul>
					</li>
				</ul>
			</div>
		</div>
		<!--/sidebar-->
		<div class="main-wrap">

			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i> <a href="jsp/teacher/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span class="crumb-name">试题管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="servlet/ManageQuestion.do?flag=find&pager=1&manager=teacher" method="post">
						<table class="search-tab">
							<tr>
								<th width="120">选择考题类型:</th>
								<td><select name="type" id="catid" class="required">
								<option value="0" >所有题目</option>
											<option value="q1" ${type=='q1'?selected:""}>单选题</option>
											<option value="q2" ${type=='q2'?selected:""}>多选题</option>
											<option value="q3" ${type=='q3'?selected:""}>填空题</option>
											<option value="q4" ${type=='q4'?selected:""}>判断</option>
											<option value="q5" ${type=='q5'?selected:""}>主观题</option>
											</select></td>
								<th width="70">关键字:</th>
								<td><input class="common-text" placeholder="关键字"
									name="keyword" value="" id="" type="text"></td>
								<td><input class="btn btn-primary btn2" name="sub"
									value="查询" type="submit"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form name="myform" id="myform" method="post">
					<div class="result-title">
						<div class="result-list">
							<a href="servlet/ManageQuestion.do?flag=toAdd"><i class="icon-font"></i>添加试题</a>
							<a id="batchDel" href="javascript:void(0)"><i
								class="icon-font"></i>批量删除</a> <a id="updateOrd"
								href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
						</div>
					</div>
					<div class="result-content">
						<table class="result-tab" width="100%" id="result-tab">
							<tr>
								<th class="tc" width="5%"><input class="allChoose" name=""
									type="checkbox"></th>
								<th width="7%">试题序号</th>
								<th width="8%">题目类型</th>
								<th width="20%">题目</th>
								<th width="20%">选项</th>
								<th width="20%">答案</th>
								<th width="10%">分值</th>

								<th width="10%">操作</th>
							</tr>

							<c:if test="${! empty pager }">
								<c:if
									test="${! empty pager.getDateList() && pager.getDateList().size() >0 }">
									<c:forEach items="${pager.getDateList() }" var="question" varStatus="v">

										<tr>
											<td class="tc"><input name="id[]" value="59"
												type="checkbox"></td>

											<td>${v.index+1}</td>
											<td>
											<c:choose>
											<c:when test="${question.getQ_type()=='q1'}">
											单选题
											</c:when>
											<c:when test="${question.getQ_type()=='q2'}">
											多选题
											</c:when>
											<c:when test="${question.getQ_type()=='q3'}">
											填空题
											</c:when>
											<c:when test="${question.getQ_type()=='q4'}">
											判断题
											</c:when>
											<c:when test="${question.getQ_type()=='q5'}">
											主观题
											</c:when>
											</c:choose>
											</td>
											<td>${question.getQ_title()}</td>
											<td>A:${question.getSelect()[0]}
											B:${question.getSelect()[0]}
											C:${question.getSelect()[0]}
											D:${question.getSelect()[0]}</td>
											<td>
											<c:choose>
											<c:when test="${question.getQ_type()=='q4'}">
											${question.q_answer=='1'?'对':'错'}
											</c:when>
											<c:when test="${question.getQ_type()!='q4'}">
											${question.q_answer}
											</c:when>
											</c:choose>
											</td>
											<td>${question.q_score}</td>


											<td><a class="link-update"
												href="servlet/ManageQuestion.do?flag=findOne&q_id=${question.getQ_id()}">修改</a>
												<a class="link-del"
												href="servlet/ManageQuestion.do?flag=delete&manager=teacher&type=0&pager=1&q_id=${question.getQ_id()}">删除</a></td>
										</tr>

									</c:forEach>
								</c:if>

								<c:if
									test="${ empty pager.getDateList() || pager.getDateList().size() == 0 }">
									没有任何试题信息</c:if>
							</c:if>



						</table>


					</div>
				</form>
				<jsp:include page="/jsp/page.jsp"></jsp:include>
			</div>
		</div>
		<!--/main-->
	</div>
</body>

</html>