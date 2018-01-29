<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	/*获得项目根路径  */
	String mPath = config.getServletContext().getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta charset="gbk">
<title>SgExam在线考试系统|学生管理</title>
<link rel="stylesheet" type="text/css" href="css/admin_common.css" />
<link rel="stylesheet" type="text/css" href="css/admin_main.css" />
</head>

<body>
	<div class="topbar-wrap white">
		<div class="topbar-inner clearfix">
			<div class="topbar-logo-wrap clearfix">
				<ul class="navbar-list clearfix">
					<li><a href="jsp/admin/index.jsp">SgExam在线考试系统</a></li>
				</ul>
			</div>
			<div class="top-info-wrap">
				<ul class="top-info-list clearfix">
					<li><a href="jsp/admin/index.jsp">你好！${adm_name }</a></li>
					<li><a href="jsp/admin/modify_password.jsp">修改密码</a></li>
					<li><a href="QuitServlet?status=admin">退出</a></li>
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
								<li>
									<a href="PaperManageServlet?operation=update"><i class="icon-font">&#xe005;</i>考试管理</a>
								</li>
								<li class="on">
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
				<div class="crumb-list">
					<i class="icon-font"></i> <a href="jsp/admin/index.jsp">首页</a><span
						class="crumb-step">&gt;</span> <a class="crumb-name"
						href="jsp/admin/news_list.jsp">学生管理</a><span class="crumb-step">&gt;</span><span>修改信息</span>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<form action="ManageStudentServlet?flag=update&pageNum=1" method="post" id="myform" name="myform">
						<table class="insert-tab" width="100%">
							<tbody>
							
								<tr>
									<th><i class="require-red">*</i>学号：</th>
									<td><input class="common-text required" id="title"
										name="s_id" size="50"
										value="<%=request.getParameter("s_id")%>" type="text" readonly="readonly"></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>姓名：</th>
									<td><input class="common-text required" id="people"
										name="s_name" size="50"
										value="<%=request.getParameter("s_name")%>" type="text"></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>密码：</th>
									<td><input class="common-text required" id="people"
										name="s_pass" size="50"
										value="<%=request.getParameter("s_pass")%>" type="text"></td>
								</tr>
								<tr>
									<th><i class="require-red">*</i>性别：</th>

									<%
										String sex = request.getParameter("s_sex");

										request.setAttribute("s_sex", sex);
									%>
									

									<td><label><input class="common-text required"
											id="people" name="s_sex" size="50" value="1" type="radio"
											${s_sex=='男'?'checked':'' }>男</label> <label><input
											class="common-text required" id="people" name="s_sex"
											size="50" value="2" type="radio" ${s_sex=='女'?'checked':''}>女</label></td>

								</tr>
								<tr>
									<th><i class="require-red">*</i>班级：</th>
									<td><input class="common-text required" id="people"
										name="cls_id" size="50"
										value="<%=request.getParameter("cls_id")%>" type="text"></td>
								</tr>
								<tr>
									<th></th>
									<td><input class="btn btn-primary btn6 mr10" value="修改"
										type="submit"> <input class="btn btn6" value="重置"
										type="reset"> <input class="btn btn6"
										onClick="history.go(-1)" value="返回" type="button"></td>
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