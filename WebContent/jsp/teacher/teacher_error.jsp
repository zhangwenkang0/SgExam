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
		<title>SgExam在线考试系统|错误提示</title>
		<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
		<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
	</head>
	<script type="text/javascript" src="<%=path%>/js/jquery-3.1.1.js"></script>
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
						<a class="crumb-name" href="jsp/teacher/subject_list.jsp">错误提示</a><span class="crumb-step">&gt;</span><span>添加试题</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<div style="width:600px ; margin: 0 auto; margin-top: 50px; box-shadow: 1px 1px 5px #666666;" class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">错误提示</h2>
			</div>
			<div class="panel-body">
				<div class="text-center text-danger">
					<span class="glyphicon glyphicon-remove-sign msg-icon"></span>
					<span class="msg-text">${error }</span><br />
					<a href="javascript:void(0);" onclick="window.history.go(-1);" class="btn btn-danger"><i class="glyphicon glyphicon-share-alt"></i> 返 回 </a>
				</div>
			</div>
		</div>
					</div>
				</div>
			</div>
			<!--/main-->
		</div>

	</body>

</html>