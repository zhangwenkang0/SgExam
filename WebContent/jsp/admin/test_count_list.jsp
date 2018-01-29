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
		<title>SgExam在线考试系统|成绩统计</title>
		<link rel="stylesheet" type="text/css" href="css/admin_common.css" />
		<link rel="stylesheet" type="text/css" href="css/admin_main.css" />
	</head>

	<body>
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
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="jsp/admin/index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">题目答对率</span></div>
				</div>
				<div class="search-wrap">
					<div class="search-content">
						<form action="#" method="post">
							<table class="search-tab">
								<tr>
									<th width="120">所属课程:</th>
									<td>
										<select name="search-sort" id="">
											<option value="-1">全部</option>
											<option value="1">c语言</option>
											<option value="2">java</option>
											<option value="3">jsp</option>
											<option value="4">汇编</option>
											<option value="5">linux</option>
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
								<a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
							</div>
						</div>
						<div class="result-content">
							<table class="result-tab" width="100%">
								<tr>
									<th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th>
									<th width="10%">考试编号</th>
									<th width="15%">名称</th>
									<th width="10%">创建人编号</th>
									<th width="10%">所属课程</th>
									<th width="15%">考试班级</th>
									<th width="20%">考试时间</th>
									<th width="20%">操作</th>
								</tr>
								<tr>
									<td class="tc"><input name="id[]" value="59" type="checkbox"></td>
									<td>1</td>
									<td>c语言</td>
									<td>0000000001</td>
									<td>计算机</td>
									<td>计算机科学与技术1班</td>
									<td>2016-10-18 21:30:19至2016-10-18 21:31:10</td>
									<td>
										<a class="link-update" href="jsp/admin/test_revise.jsp">题目答对率</a>
										<a class="link-update" href="jsp/admin/test_revise.jsp">错题分布</a>
									</td>
								</tr>
								<tr>
									<td class="tc"><input name="id[]" value="59" type="checkbox"></td>
									<td>1</td>
									<td>c语言</td>
									<td>0000000001</td>
									<td>计算机</td>
									<td>计算机科学与技术1班</td>
									<td>2016-10-18 21:30:19至2016-10-18 21:31:10</td>
									<td>
										<a class="link-update" href="jsp/admin/test_revise.jsp">题目答对率</a>
										<a class="link-update" href="jsp/admin/test_revise.jsp">错题分布</a>
									</td>
								</tr>
							</table>
							<div class="list-page"> 2 条 1/1 页</div>
						</div>
					</form>
				</div>
			</div>
			<!--/main-->
		</div>
	</body>

</html>