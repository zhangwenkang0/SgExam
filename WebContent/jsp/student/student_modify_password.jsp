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
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<title>SgExam在线考试系统|修改密码</title>
		<link href="css/common.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/js.js" type="text/javascript"></script>
		<script src="js/check.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="clear list_top ovhi psRe">
			<jsp:include page="../top.jsp"></jsp:include>
			<ul class="psRe">
				<img src="images/study_background_img.png" />
				<div class="list_grd_bg psAb"></div>
			</ul>
			<a class="disin psAb menuname" href="jsp/student/student_modify_password.jsp">修改密码&nbsp;&nbsp;Modify&nbsp;Password</a>
		</div>
		<div class="crumbs_left lf"></div>
		<div class="crumbs lh35 lf crumb">
			<a href="servlet/StudentServlet.do?operation=toIndex">首 页</a>&nbsp;&nbsp;&sdot;&nbsp;&nbsp;
			<a href="jsp/student/student_personal.jsp">个人中心</a>
		</div>

		<div class="bk"></div>
		<div class="bagf wrap_list clear grd24 mar0a">
			<div class="list_left lf">
				<div id="wrap_sub"></div>
				<ul class="wrap_sub">
					<li>
						<a class="subnav_a" href="servlet/StudentServlet.do?operation=toMyTest">我的考试</a>
					</li>
					<li>
						<a class="subnav_a" href="servlet/StudentServlet.do?operation=showScore">我的成绩</a>
					</li>
					<li>
						<a class="subnav_a" href="jsp/student/student_news.jsp">信息公告</a>
					</li>
					<li class="on">
						<a class="subnav_a" href="jsp/student/student_personal.jsp">个人中心</a>
					</li>
				</ul>
			</div>
			<div class="list-content rt list_main">
				<div class="article psRe">
					<h1>个人中心</h1>
					<div class="bk20"></div>
					<div class="content detail">
						<form name="" action="ModifyPasswordServlet?flagId=1&userID=${stu_id }" method="post" onsubmit="return login_pwd_username()">
							<table border="2" class="schoolLeader">
								<tbody>
									<tr>
										<th colspan="5">
											<p style="text-align: center;"><strong>修改密码</strong></p>
										</th>
									</tr>
									<tr>
										<td colspan="5">
											<div id="modpwdCue" class="cue">以字母开头，长度在6-18之间，只能包含字符、数字和下划线。</div>
										</td>
									</tr>
									<tr>
										<td colspan="2" width="40%">旧密码</td>
										<td colspan="3" width="60%"><input class="passwordinput" type="text" id="oldpassword" value="" name="oldpass"/></td>
									</tr>
									<tr>
										<td colspan="2" width="40%">新密码</td>
										<td colspan="3" width="60%"><input class="passwordinput" type="text" id="newpassword" value="" name="newpass"/></td>
									</tr>
									<tr>
										<td colspan="2" width="40%">确认密码</td>
										<td colspan="3" width="60%"><input class="passwordinput" type="text" id="confirmpassword" value="" name="repass"/></td>
									</tr>
									<tr>
										<td colspan="5">
											<input class="btn btn6" type="submit" value="确认修改" />
											<input class="btn btn6" value="重置" type="reset">
											<input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
										</td>
									</tr>
								</tbody>
							</table>
						</form>
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