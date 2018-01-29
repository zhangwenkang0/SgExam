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
								<li class="on">
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
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="jsp/teacher/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">考试管理</span></div>
				</div>
				<div class="search-wrap">
					<div class="search-content">
						<form action="#" method="post">
							<table class="search-tab">
								<tr>
									<th width="120">所属课程:</th>
									<td>
										<select name="c_id" id="">
										<option value="0">请选择课程</option>
										<c:forEach items="${courseList }" var="course">
										<option value="${course.c_id}">${course.c_name}</option>
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
						<div class="result-title">
							<div class="result-list">
								<a href="jsp/teacher/test_add.jsp"><i class="icon-font"></i>添加考试</a>
								<a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
								<a id="updateOrd" href="PaperManageServlet?operation=update"><i class="icon-font"></i>获取考试信息</a>
							</div>
						</div>
						<div class="result-content">
							<table class="result-tab" width="100%">
								<tr>
									<th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th>
									<th width="7%">考试编号</th>
									<th width="10%">考试班级</th>
									<th width="10%">所属课程</th>
									<th width="8%">名称</th>
									<th width="5%">分值</th>
									<th width="20%">考试时间</th>
									<th width="10%">操作</th>
								</tr>
								<c:if test="${! empty pager }">
								<c:if
									test="${! empty pager.getDateList() && pager.getDateList().size() >0 }">
								<c:forEach var="paperBean" items="${pager.getDateList() }"  varStatus="v">
									<tr>
									<td class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></td>
									<td>${v.index+1}</td>
									<td>
									<c:forEach items="${classesList}" var="classes">
									<c:if test="${classes.cls_id==paperBean.cls_id}">${classes.cls_name }</c:if>
									</c:forEach>
									</td>
									<td>
									<c:choose>
											<c:when test="${paperBean.c_id=='1'}">
											离散数学
											</c:when>
											<c:when test="${paperBean.c_id=='2'}">
											大学物理
											</c:when>
											<c:when test="${paperBean.c_id=='3'}">
											大学数学
											</c:when>
											<c:when test="${paperBean.c_id=='4'}">
											计算机组成原理
											</c:when>
											</c:choose>
											</td>
									<td width="8%">${paperBean.p_name}</td>
									<td width="10%">${paperBean.p_scores}</td>
									<td width="15%">${paperBean.startTime}--${paperBean.endTime}</td>
									<td width="10%">
										<a class="link-update" href="PaperManageServlet?operation=toModify&pid=${paperBean.p_id}">修改</a>
										<a class="link-del" href="PaperManageServlet?operation=delete&p_id=${paperBean.p_id}">删除</a>
									</td>
									</tr>
								</c:forEach>
								</c:if>
								
							</c:if>
								
							</table>
							<c:if
									test="${ empty pager.getDateList() || pager.getDateList().size() == 0 }">
									没有任何考试信息</c:if>
						</div>
					</form>
					<jsp:include page="/jsp/page.jsp"></jsp:include>
				</div>
			</div>
			<!--/main-->
		</div>
	</body>

</html>