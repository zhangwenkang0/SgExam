<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="edu.numberone.studystar.entity.Question"%>
<%@ page import="edu.numberone.studystar.entity.Pager" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	/*获得项目根路径  */
	String mPath = config.getServletContext().getContextPath();
%>

<!---------------------------------------------------------  -->

<%!int currentPage = 1;
	int pageSize = 2;
	int totalPage = 2;%>

<%

	Pager<Question> pager = (Pager<Question>) request.getAttribute("pager");

	if (pager != null) {

		currentPage = pager.getCurrentPage();
		pageSize = pager.getPageSize();
		totalPage = pager.getTotalPage();
	}
%>


<!---------------------------------------------------------  -->


<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta charset="gbk">
<title>SgExam在线考试系统|试题管理</title>
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
					<li><a href="#">你好！${adm_name }</a></li>
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
							<li><a href="jsp/admin/index.jsp"><i class="icon-font">&#xe012;</i>基本信息</a>
							</li>
							<li><a href="ManageAdminServlet?flag=find&pageNum=1"><i
									class="icon-font">&#xe012;</i>管理员管理</a></li>
							<li><a href="jsp/admin/news_list.jsp"><i
									class="icon-font">&#xe012;</i>消息中心</a></li>
							<li class="on"><a href="servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=admin"><i
									class="icon-font">&#xe008;</i>试题管理</a></li>
							<li><a href="PaperManageServlet?operation=update"><i
									class="icon-font">&#xe005;</i>考试管理</a></li>
							<li><a href="ManageStudentServlet?flag=find&pageNum=1"><i
									class="icon-font">&#xe005;</i>学生管理</a></li>
							<li><a href="ManageTeacherServlet?flag=find&pageNum=1"><i
									class="icon-font">&#xe005;</i>教师管理</a></li>
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
						class="crumb-step">&gt;</span><span class="crumb-name">试题管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="#" method="post">
						<table class="search-tab">
							<tr>
								<th width="120">选择分类:</th>
								<td><select name="search-sort" id="">
										<option value="-1">全部</option>
										<option value="1">单选题</option>
										<option value="2">多选题</option>
										<option value="3">填空题</option>
										<option value="4">判断</option>
										<option value="5">主观题</option>
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
						<table class="result-tab" width="100%">
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
											<td>${question.getQ_select()}</td>
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
											<td>${question.getQ_score().toString()}</td>


											<td><a class="link-update"
												href="servlet/ManageQuestion.do?flag=findOne&q_id=${question.getQ_id()}">修改</a>
												<a class="link-del"
												href="servlet/ManageQuestion.do?flag=delete&manager=admin&type=0&pager=1&q_id=${question.getQ_id()}">删除</a></td>
										</tr>

									</c:forEach>
								</c:if>

								<c:if
									test="${ empty pager.getDateList() || pager.getDateList().size() == 0 }">
									没有任何试题信息</c:if>
							</c:if>

						</table>
						<div class="list-page">&nbsp;&nbsp;<input class="btn btn-primary btn3 mr10" value="首页" type="button" onclick="homeClick()"><input class="btn btn-primary btn3 mr10" value="上一页" type="button" onclick="preiousClick()"> <%=pageSize %>条 <%=currentPage %>/<%=totalPage %>页&nbsp;&nbsp;<input class="btn btn-primary btn3 mr10" value="下一页" type="button" onclick="nextClick()"><input class="btn btn-primary btn3 mr10" value="尾页" type="button" onclick="lastClick()">
						
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--/main-->
		
		<script type="text/javascript">
    var homeClick = function(){
		var hr;

		/* var stuname = document.getElementById("name").value;
		var stuSex = document.getElementById("sex").value; */
		
		
		/* servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=admin */
		
		
		hr = "servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=admin";
		window.open(hr, "_self");
	} 

	 var nextClick = function() {
		 var hr;

	/* 	var stuname = document.getElementById("name").value;
		var stuSex = document.getElementById("sex").value; */
		var totalPage =<%=totalPage%>;
		var currentPage = <%=currentPage%>;

		
		
		if(currentPage >= totalPage){
			currentPage =totalPage;
		
			alert("已经是最后一页");
			
		}else{
			currentPage++;
		
		}
		

		hr = "servlet/ManageQuestion.do?flag=find&type=0&pager="+currentPage+"&manager=admin";
		
		window.open(hr, "_self");
	} 
	
	var preiousClick = function(){
		var hr;
	/* 	var stuname = document.getElementById("name").value;
		var stuSex = document.getElementById("sex").value;
		 */
		
		var totalPage =<%=totalPage%>;
		var currentPage = <%=currentPage%>;
		
       if(currentPage >= totalPage){
			
    	   currentPage = totalPage
			
			
		}
 
		if (currentPage <= 1) {
			alert("已经是第一页面")
		} else {
			currentPage --;
		}
	
		hr = "servlet/ManageQuestion.do?flag=find&type=0&pager="+currentPage+"&manager=admin";
		
	
		window.open(hr, "_self");
	} 
	
	 var lastClick = function() {

		/* var stuname = document.getElementById("name").value;
		var stuSex = document.getElementById("sex").value; */
		var totalPage =<%=totalPage%>;
		
		hr = "servlet/ManageQuestion.do?flag=find&type=0&pager="+totalPage+"&manager=admin";
		
		window.open(hr, "_self");
	} 
</script>
	</div>
</body>

</html>