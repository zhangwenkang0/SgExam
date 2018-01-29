<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, edu.numberone.studystar.daoimpl.*, edu.numberone.studystar.entity.*"%>    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<title>SgExam在线考试系统|我的考试</title>
		<link href="css/common.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/js.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
	$(function(){
		
		var cacluate=function(startTime,event){
			var date2 = Date.parse(new Date(startTime));
			var date1=new Date();  //开始时间
			var date3=date2-date1.getTime()  //时间差的毫秒数
			//计算出相差天数
			var days=Math.floor(date3/(24*3600*1000))
			//计算出小时数

			var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
			var hours=Math.floor(leave1/(3600*1000))
			//计算相差分钟数
			var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
			var minutes=Math.floor(leave2/(60*1000))
			//计算相差秒数
			var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
			var seconds=Math.round(leave3/1000)
			if(date3>0){
				alert("考试时间还未到!"+" 离考试开始还有 "+days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒");
				event.preventDefault();
			}
		}
		$("a[name='joinTest']").each(function(){
			$(this).click(function(event){
				var startTime=$(this).prevAll("input[name='startTime']");
				cacluate(startTime.val(),event);
			});
			});
	});
	</script>
	<body>

		<div class="clear list_top ovhi psRe">
			<jsp:include page="../top.jsp"></jsp:include>
			<ul class="psRe">
				<img src="images/study_background_img.png"/>
				<div class="list_grd_bg psAb"></div>
			</ul>
			<a class="disin psAb menuname" href="#">我的考试&nbsp;&nbsp;My Exam</a>
		</div>
		<div class="crumbs_left lf"></div>
		<div class="crumbs lh35 lf crumb">
			<a href="servlet/StudentServlet.do?operation=toIndex">首 页</a>&nbsp;&nbsp;&sdot;&nbsp;&nbsp;
			<a href="#">我的考试</a>
		</div>

		<div class="bk"></div>
		<div class="bagf wrap_list clear grd24 mar0a">
			<div class="list_left lf">
				<div id="wrap_sub"></div>
				<ul class="wrap_sub">
					<li class="on">
						<a class="subnav_a" href="servlet/StudentServlet.do?operation=showScore">我的考试</a>
					</li>
					<li>
						<a class="subnav_a" href="servlet/StudentServlet.do?operation=toMyTest">我的成绩</a>
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
					<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm" var="today"/>
					<h1>我的考试</h1>
					<div class="bk20"></div>
						<c:if test="${! empty papers }">
							<c:forEach var="pap" items="${papers.getDateList()}" varStatus="status">
							<fmt:parseDate value="${pap.endTime}:00" var="testday" type="both"/>
							<fmt:formatDate  pattern="yyyy-MM-dd HH:mm" value="${testday}" var="testDay"/>
							<c:if test="${today<=testDay}">
								<div class="bkrx_ul psRe clear">
									<a class="bkzsjz_a" href="#">
										<div class="exam_list">
											<font class="exam_class">${pap.p_name}</font><br>
											<font class="exam_type">考试</font>
										</div><img src="images/exam_logo.png" width="252px"></a>
									<a class="h25 lf grd6">&nbsp;</a>
									<ul>
										<li class="bkrx_li">
											<a href="#">考试</a>
										</li>
										<li class="bkrx_li">
											<a href="#"></a>
										</li>
										<li class="bkrx_li">
											<a href="#">${pap.p_name}</a>
										</li>
										<li class="bkrx_li">
											<a href="#"></a>
										</li>
										<li class="bkrx_li">
											<a href="#">考试时间：</a>
										</li>
										<li class="bkrx_li">
											<a href="#"></a>
										</li>
										<li class="bkrx_li">
											<a href="#">${pap.startTime}--${pap.endTime}</a>
										</li>
										<li class="bkrx_li">
											<a href="#"></a>
										</li>
									</ul>
									<input type="hidden" name="startTime" value="${pap.startTime}">
									<input type="hidden" name="endTime" value="${pap.endTime}">
									<a class="xzby_wla xzby_zs" href="servlet/StudentServlet.do?operation=test&p_id=${pap.p_id}" name="joinTest">&gt;&gt;立即参加</a>
									<p>
										&nbsp;</p>
								</div>
								<p class="h50 bk">
									&nbsp;</p>
									</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty papers}">
							暂无考试
						</c:if>
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
				<a class="disin padlr10" href="#">微博微信</a>
				<a class="disin padlr10" href="#">招标公告</a>
				<a class="disin padlr10" href="#">人才招聘</a>
			</p>
			<p class="padt5 textc white lh20">版权所有&nbsp;&copy;&nbsp;SgExam在线考试系统&nbsp;&nbsp;湘ICP备&nbsp;00000001号&nbsp;湘公网安备000000000001</p>
			<div class="foot_bg psAb"></div>
		</div>
	</body>

</html>