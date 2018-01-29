<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,java.text.*"  %>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    /*获得项目根路径  */
    String mPath = config.getServletContext().getContextPath();
	%>
<!DOCTYPE html>
<%
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
	String now=sdf.format(date);
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta charset="gbk">
		<title>SgExam在线考试系统|后台管理</title>
		<link rel="stylesheet" type="text/css" href="css/admin_common.css" />
		<link rel="stylesheet" type="text/css" href="css/admin_main.css" />
	</head>

	<body>
		<div class="topbar-wrap white">
			<div class="topbar-inner clearfix">
				<div class="topbar-logo-wrap clearfix">
					<ul class="navbar-list clearfix">
						<li>
							<a class="on" href="jsp/admin/index.jsp">SgExam在线考试系统</a>
						</li>
					</ul>
				</div>
				<div class="top-info-wrap">
					<ul class="top-info-list clearfix">
						<li>
							<a href="#">你好！${adm_name }</a>
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
								<li class="on">
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
								<li>
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
					<div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用SgExam在线考试系统后台管理</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-title">
						<h1>快捷操作</h1>
					</div>
					<div class="result-content">
						<div class="short-wrap">
							<a href="jsp/admin/admin_add.jsp"><i class="icon-font"></i>添加管理员</a>
							<a href="jsp/admin/news_add.jsp"><i class="icon-font"></i>发布消息</a>
							<a href="jsp/admin/subject_add.jsp"><i class="icon-font"></i>添加试题</a>
							<a href="jsp/admin/test_add.jsp"><i class="icon-font"></i>发布考试</a>
							<a href="jsp/admin/student_add.jsp"><i class="icon-font"></i>添加学生</a>
							<a href="jsp/admin/teacher_add.jsp"><i class="icon-font"></i>添加教师</a>
						</div>
					</div>
				</div>
				<div class="result-wrap">
					<div class="result-title">
						<h1>欢迎您</h1>
					</div>
					<div class="result-content">
						<ul class="sys-info-list">
							<li>
								<label class="res-lab">用户名</label><span class="res-info">${adm_name }</span>
							</li>
							<li>
								<label class="res-lab">系统时间</label><span class="res-info"><%=now %></span>
							</li>
							<li>
								<label class="res-lab">上次登录时间</label><span class="res-info">${lastTime }</span>
							</li>
							<li>
								<label class="res-lab"></label>
								<a href="jsp/admin/modify_password.jsp"><input class="btn btn-primary btn6 mr10" value="更改密码" type="button"></a>
							</li>
						</ul>
					</div>
				</div>

			</div>
			<!--/main-->
		</div>
	</body>

</html>