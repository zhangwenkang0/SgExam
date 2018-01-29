<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
	%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta charset="gbk">
		<title>SgExam在线考试系统|考试管理</title>
		<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
		<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	</head>
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
								<li >
									<a href="PaperManageServlet?operation=update"><i class="icon-font">&#xe005;</i>考试管理</a>
								</li>
								<li>
									<a href="Class_List_BrowseServlet?updateclassform=updateClassForm"><i class="icon-font">&#xe005;</i>班级管理</a>
								</li>
								<li class="on">
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
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="jsp/teacher/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">批改试卷</span></div>
				</div>
				<div class="search-wrap">
					<div class="search-content">
						<form action="#" method="post">
							<table class="search-tab">
								<tr>
									<th width="120">所属课程:</th>
									<td>
										<select name="c_id" >
										<option value="0">请选择课程</option>
										<c:forEach items="${courseList }" var="course">
										<option value="${course.c_id}">${course.c_name}</option>
										</c:forEach>
										</select>
									</td>
									<th width="120">所属班级:</th>
										<td>
										<select name="c_id" >
										<option value="0">请选择班级</option>
										<c:forEach items="${classesList }" var="classes">
										<option value="${classes.cls_id}">${classes.cls_name}</option>
										</c:forEach>
										</select>
									</td>
									<th width="70">关键字:</th>
									<td><input class="common-text" placeholder="关键字" name="keyword" value="" id="" type="text"></td>
									<td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="result-wrap">
					<form name="myform" id="myform" method="post">
						<div class="result-content">
							<table class="result-tab" width="100%">
								<tr>
									<th width="7%">考试编号</th>
									<th width="10%">所属课程</th>
									<th width="15%">考试班级</th>
									<th width="10%">学生名称</th>
									<th width="15%">考试名称</th>
									<th width="10%">得分</th>
									<th width="10%">考试状态</th>
									<th width="10%">操作</th>
								</tr>
								<c:set var="flag" value="0"></c:set>
								<c:set var="index" value="0"></c:set>
								<c:if
									test="${! empty pager.getDateList() && pager.getDateList().size() >0 }">
								<c:forEach var="sco" items="${pager.getDateList() }"  varStatus="v">
									<c:if test="${sco.ifdone=='0'}">
									<c:set var="flag" value="1"></c:set>
									<c:set var="index" value="${index}+1"></c:set>
									<tr>
									<td>${index}</td>
									<td>
									<c:forEach items="${courseList }" var="course">
										<c:if test="${course.c_id==sco.c_id }">${ course.c_name}</c:if>
										</c:forEach>
									</td>
									<td>${sco.cls_name}</td>
										<td >
											${sco.s_name}
										</td>
										<td>${sco.p_name}</td>
										<td>${sco.ifdone=='0'?'(客观题)':'' }${sco.sc_score}</td>
										<td>${sco.ifdone=='0'?'待批改':'' }</td>
										<td><a href="servlet/ScoreCorrectServlet.do?operation=toCorrect&sc_id=${sco.sc_id}">批改</a></td>
									</tr>
									</c:if>
								</c:forEach>
							</c:if>
								
							</table>
							<c:if
									test="${ flag==0}">
									<h1>暂无可批改试卷</h1>
									</c:if>
						</div>
					</form>
					<jsp:include page="/jsp/page.jsp"></jsp:include>
				</div>
			</div>
			<!--/main-->
		</div>
	</body>

</html>