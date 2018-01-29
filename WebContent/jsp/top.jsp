<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
    <html>
    <body>
			<div class="header psAb">
				<div class="psRe head">
					<h2 class="logo"><a class="disin" href="servlet/StudentServlet.do?operation=toIndex"><img src="images/index_logo.png" width="309px" alt="SgExam在线考试系统"/></a></h2>
					<div id="top_nav">
						<ul>
							<li class="nav-li per">
								<a class="top_nav" href="servlet/StudentServlet.do?operation=toMyTest"><span class="span1">我的考试</span></a>
							</li>
							<li class="nav-li per">
								<a class="top_nav" href="servlet/StudentServlet.do?operation=showScore"><span class="span1">我的成绩</span></a>
							</li>
							<li class="nav-li per">
								<a class="top_nav" href="jsp/student/student_news.jsp"><span class="span1">信息公告</span></a>
							</li>
							<li class="nav-li per">
								<a class="top_nav" href="jsp/student/student_personal.jsp"><span class="span1">个人中心</span></a>
							</li>
							<li class="nav-li per unuse">
								<div style="background: url(images/sub_nav.png) repeat-x; height:124px; width: 124px;">
									<div class="line" style="background: url(images/top-line.png) repeat-x 0 34px; height: 35px;"></div>
								</div>
							</li>
						</ul>
					</div>
					<!--/nav-->
					<div class="clear hover_right psRe">
						<ul class="head_right">
							<li class="head_li rt head_responsive">
								<a class="animate2" href="QuitServlet?status=student"><span>退出系统</span></a>
							</li>
							<li class="head_li rt">
								<a class="animate2" href="#"><span></span></a>
							</li>
							<li class="head_li rt">
								<font class="vet lh35 disin">&nbsp;&nbsp;
									<a href="servlet/StudentServlet.do?operation=toIndex">你好！</a>&nbsp;&nbsp;&sdot;&nbsp;&nbsp;
									<a href="jsp/student/student_personal.jsp">${stu_name }</a>&nbsp;&nbsp;&sdot;&nbsp;&nbsp;
									<a href="servlet/StudentServlet.do?operation=toIndex">同学</a>
								</font><i class="icos ico_id"></i></li>
							<li class="head_li rt h35">&nbsp;</li>
						</ul>
					</div>
				</div>
			</div>
			</body>
			</html>