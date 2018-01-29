<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@page import="edu.numberone.studystar.entity.Teacher"%>
<%@page import="edu.numberone.studystar.entity.Pager"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>SgExam在线考试系统|教师管理</title>
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
								<li>
									<a href="ManageStudentServlet?flag=find&pageNum=1"><i class="icon-font">&#xe005;</i>学生管理</a>
								</li>
								<li class="on">
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
						class="crumb-step">&gt;</span><span class="crumb-name">教师管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="#" method="post">
						<table class="search-tab">
							<tr>
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
							<a href="jsp/admin/teacher_add.jsp"><i class="icon-font"></i>添加教师</a>
							<a id="batchDel" href="javascript:void(0)"><i
								class="icon-font"></i>批量删除</a> <a id="updateOrd"
								href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
						</div>
					</div>
					<div class="result-content">
						<table class="result-tab" width="100%">
							<tr>
								<th class="tc" width="10%"><input class="allChoose" name=""
									type="checkbox"></th>
								<th width="15%">教师编号</th>
								<th width="15%">姓名</th>
								<th width="15%">密码</th>
								<th width="15%">职称</th>
								<th width="15%">操作</th>
							</tr>


						<!-- </table> -->

							<c:if
								test="${requestScope.pager != null && requestScope.pager.getDateList().size() > 0}">

								<c:forEach items="${requestScope.pager.getDateList() }"
									var="tea">
									<tr>
										<td class="tc"><input name="id[]" value="59"
											type="checkbox"></td>
										<td>${tea.getT_Id() }</td>
										<td>${tea.getT_Name() }</td>
										<td>${tea.getT_Pass() }</td>
										<td>${tea.getT_Job() }</td>
										<td><a class="link-update"
											href="jsp/admin/teacher_revise_info.jsp?t_id=${tea.getT_Id() }&t_name=${tea.getT_Name() }&t_pass=${tea.getT_Pass() }&t_Job=${tea.getT_Job() }">修改信息</a> 
											<a class="link-del" href="ManageTeacherServlet?flag=delete&pageNum=1&T_id=${tea.getT_Id() }">删除</a></td>
									</tr>

								</c:forEach>
						</table>
						</c:if>


						 <c:if
							test="${requestScope.pager == null || requestScope.pager.getDateList().size() == 0}">
							</table>
							<div class="list-page">当前页没有数据！</div>
						</c:if> 

					</div>
				</form>
			</div>
		</div>
		<!--/main-->
	</div>
</body>

</html>