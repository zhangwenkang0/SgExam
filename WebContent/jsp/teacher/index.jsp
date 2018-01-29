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
		<title>SgExam在线考试系统|教师端</title>
		<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
		<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
	</head>

	<body>
		<div class="topbar-wrap white">
			<div class="topbar-inner clearfix">
				<div class="topbar-logo-wrap clearfix">
					<ul class="navbar-list clearfix">
						<li>
							<a class="on" href="jsp/teacher/index.jsp">SgExam在线考试系统</a>
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
								<li class="on">
									<a href="jsp/teacher/index.jsp"><i class="icon-font">&#xe012;</i>个人中心</a>
								</li>
								<li>
									<a href="jsp/teacher/news_list.jsp"><i class="icon-font">&#xe012;</i>消息中心</a>
								</li>
								<li>
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
					<div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用SgExam在线考试系统教师端</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-title">
						<h1>快捷操作</h1>
					</div>
					<div class="result-content">
						<div class="short-wrap">
							<a href="jsp/teacher/news_add.jsp"><i class="icon-font"></i>发布消息</a>
							<a href="jsp/teacher/subject_add.jsp"><i class="icon-font"></i>添加试题</a>
							<a href="jsp/teacher/test_add.jsp"><i class="icon-font"></i>发布考试</a>
						</div>
					</div>
				</div>
				<div class="result-wrap">
					<div class="result-title">
						<h1>个人信息</h1>
					</div>
					<div class="result-content">
						<ul class="sys-info-list">
							<li>
								<label class="res-lab">职工号</label><span class="res-info">${tea_id }</span>
							</li>
							<li>
								<label class="res-lab">姓名</label><span class="res-info">${tea_name }</span>
							</li>
							<li>
								<label class="res-lab">院系</label><span class="res-info">科学院</span>
							</li>
							<li>
								<label class="res-lab">职称</label><span class="res-info">院长</span>
							</li>
							<li>
								<label class="res-lab"></label>
								<a href="jsp/teacher/modify_password.jsp"><input class="btn btn-primary btn6 mr10" value="更改密码" type="button"></a>
							</li>
						</ul>
					</div>
				</div>

			</div>
			<!--/main-->
		</div>
	</body>

</html>