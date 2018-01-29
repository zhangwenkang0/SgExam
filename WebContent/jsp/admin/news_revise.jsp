<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="GBK"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, edu.numberone.studystar.daoimpl.*, edu.numberone.studystar.entity.*"%>
    
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    /*获得项目根路径  */
    String mPath = config.getServletContext().getContextPath();
	%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<meta charset="gbk">
		<title>SgExam在线考试系统|消息中心</title>
		<link rel="stylesheet" type="text/css" href="css/admin_common.css" />
		<link rel="stylesheet" type="text/css" href="css/admin_main.css" />
	</head>

	<body>
	<%
		AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
		Announcement ann = new Announcement();
		ann.setA_id(request.getParameter("a_id"));
		ann = adi.getAnnouncement(ann, 0, 0).get(0);
		request.setAttribute("ann", ann);
	%>
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
								<li class="on">
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
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="jsp/admin/index.jsp">首页</a><span class="crumb-step">&gt;</span>
						<a class="crumb-name" href="jsp/admin/news_list.jsp">消息中心</a><span class="crumb-step">&gt;</span><span>修改消息</span>
					</div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<form action="DoUpdateAnnouncementServlet" method="post" id="myform" name="myform">
							<table class="insert-tab" width="100%">
								<tbody>
									<tr>
										<th><i class="require-red">*</i>标题：</th>
										<td>
											<input class="common-text required" id="title" name="a_title" size="50" value="${ann.a_title}" type="text">
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>内容：</th>
										<td>
											<textarea name="a_content" class="common-textarea" id="a_content" cols="30" style="width: 98%;" rows="10">${ann.a_content}</textarea>
										</td>
									</tr>

									<tr>
										<th><i class="require-red">*</i>发布人：</th>
										<td>
											<input class="common-text required" id="people" name="a_author" size="50" value="管理员" type="text">
										</td>
									</tr>
									<tr>
										<th><i class="require-red">*</i>修改时间：</th>
										<td>
											<input class="common-text required disabled" id="time" name="time" size="50" value="" type="text" disabled="disabled">
										</td>
									</tr>
									<tr>
										<th></th>
										<td>
											<input type="hidden" name="a_id" value="${ann.a_id}">
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
				<script src="js/time.js" type="text/javascript" charset="gbk"></script>
			</div>
			<!--/main-->
		</div>
	</body>

</html>