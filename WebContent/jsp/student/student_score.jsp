<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, edu.numberone.studystar.daoimpl.*, edu.numberone.studystar.entity.*"%>    
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
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title>SgExam在线考试系统|我的成绩</title>
		<link href="css/common.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/js.js" type="text/javascript"></script>
	</head>

	<body>
	<% //获取学生编号
		String stu_id = (String)session.getAttribute("stu_id");
		ScoresDaoImpl sdi = new ScoresDaoImpl();
		Scores sco = new Scores();
		sco.setS_id(stu_id);
		//查找出该学生所有成绩
		LinkedList<Scores> scores = sdi.getScores(sco, 0, 0);
		request.setAttribute("scores", scores);
	%>
		<div class="clear list_top ovhi psRe">
			<jsp:include page="../top.jsp"></jsp:include>
			<ul class="psRe">
				<img src="images/study_background_img.png" />
				<div class="list_grd_bg psAb"></div>
			</ul>
			<a class="disin psAb menuname" href="servlet/StudentServlet.do?operation=toMyTest">我的成绩&nbsp;&nbsp;My Score</a>
		</div>
		<div class="crumbs_left lf"></div>
		<div class="crumbs lh35 lf crumb">
			<a href="servlet/StudentServlet.do?operation=toIndex">首 页</a>&nbsp;&nbsp;&sdot;&nbsp;&nbsp;
			<a href="servlet/StudentServlet.do?operation=toMyTest">我的成绩</a>
		</div>

		<div class="bk"></div>
		<div class="bagf wrap_list clear grd24 mar0a">
			<div class="list_left lf">
				<div id="wrap_sub"></div>
				<ul class="wrap_sub">
					<li>
						<a class="subnav_a" href="servlet/StudentServlet.do?operation=toMyTest">我的考试</a>
					</li>
					<li class="on">
						<a class="subnav_a" href="servlet/StudentServlet.do?operation=showScore">我的成绩</a>
					</li>
					<li>
						<a class="subnav_a" href="jsp/student/student_news.jsp">信息公告</a>
					</li>
					<li>
						<a class="subnav_a" href="jsp/student/student_personal.jsp">个人中心</a>
					</li>
				</ul>
			</div>

			<div class="list-content rt list_main">
				<div class="article psRe">
					<h1>我的成绩</h1>
					<div class="bk20"></div>
					<div class="content detail">
						<style type="text/css">
							.content a {
								text-decoration: underline;
							}
						</style>
						<p>亲爱的同学！你可以在这里看到你往期的成绩以及考试哦～</p>

						<table border="1" cellpadding="0" cellspacing="0" id="changecolor" style="width: 100%;">

							<tbody>
								<tr>
									<td width="40%">考试名称</td>
									<td width="20%">考试时间</td>
									<td width="10%">得分</td>
									<td width="10%">成绩状态</td>
								</tr>
								
								<c:forEach var="sco" items="${scores}" varStatus="status">
									<tr>
										<td align="left">
											<a href="#">${sco.p_name}</a>
										</td>
										<td>${sco.sc_date}</td>
										<td>${sco.ifdone=='0'?'(客观题)':'' }${sco.sc_score}</td>
										<td>${sco.ifdone=='0'?'未批改完成':'批改完成' }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div class="page">第一页 | &lt;&lt;上一页 1
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#">4</a>
							<a href="#">5</a>
							<a href="#">6</a>
							<a href="#">7</a>
							<a href="#">8</a>
							<a href="#">下一页&gt;&gt;</a> |
							<a href="#">最后一页</a>
						</div>
					</div>
					<div class="bk20"></div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$(".content.detail").find("img").each(function() {
					var w = $(this).width(),
						h = $(this).height();
					if(w >= 710 || w == 0) {
						$(this).css({
							width: 710,
							height: 710 * h / w
						});
					}
				});
			})
		</script>
		<div style="display:none;" id="goTopBtn" title="返回顶部"> </div>
		<script type=text/javascript>
			goTopEx();
		</script>
		</div>
		<!--/wrap-->
		<div id="footer" class="psRe">
			<p class="padt10 textc white lh20">
				<a class="disin padlr10" href="#"> </a>
			</p>
			<p class="padt5 textc white lh20">版权所有&nbsp;&copy;&nbsp;SgExam在线考试系统&nbsp;&nbsp;湘ICP备&nbsp;00000001号&nbsp;湘公网安备000000000001</p>
			<div class="foot_bg psAb"></div>
		</div>
	</body>

</html>