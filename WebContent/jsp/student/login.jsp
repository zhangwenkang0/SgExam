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
		<title>SgExam在线考试系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="js/login.js" charset="gbk"></script>
		<script type="text/javascript" src="js/check.js" charset=gbk></script>
		<link href="css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
    	function change1(){
    		var img=document.getElementById("image1");
    		//加一个无意义参数，使得每一次的刷新网址发送变化，重新定位到服务端
    		img.src="IdentifyingCodeServlet?t=" + new Date().getTime();
    	}
    	function change2(){
    		var img=document.getElementById("image2");
    		//加一个无意义参数，使得每一次的刷新网址发送变化，重新定位到服务端
    		img.src="IdentifyingCodeServlet?t=" + new Date().getTime();
    	}
    	</script>
	</head>

	<body>
		<h1>SgExam在线考试系统</h1>
		<div class="login" style="margin-top:50px;">
			<div class="header">
				<div class="switch" id="switch">
					<a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);">学生登录</a>
					<a class="switch_btn" id="switch_login" href="javascript:void(0);">老师登录</a>
					<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
				</div>
			</div>
			<!-- 返回的数据 -->
			<input type="hidden" id="error" value="${error}"/>
					<input type="hidden" id="status" value="${status}"/>
					<input type="hidden" id="id" value="${id}"/>
					<input type="hidden" id="password" value="${password}"/>
			<!--学生登录-->
			<div class="web_qr_login" id="web_qr_login" style="display: block;">
				<div class="web_login">
					<form name="student_login" action="StudentAndTeacherAdminLoginServlet?status=student" method="post" onsubmit="return login_studentID_username()">
					<input type="hidden" id="password" value="${password}"/>
						<ul class="reg_form" id="reg-ul">
							<div id="studentCue" class="cue">请输入学号和密码进行登录</div>
							<li>
								<label for="user" class="input-tips2">学号：</label>
								<div class="inputOuter2">
									<input type="text" id="studentID" name="studentID" maxlength="16" class="inputstyle2" autocomplete="on"/>
								</div>
							</li>
							<li>
								<label for="passwd" class="input-tips2">密码：</label>
								<div class="inputOuter2">
									<input type="password" id="studentPassword" name="studentPassword" maxlength="16" class="inputstyle2" />
								</div>
							</li>
							<li>
								<label for="yanzheng" class="input-tips2">验证码：</label>
								<div class="inputOuter2">
									<input type="text" id="yanzheng" name="code" maxlength="16" class="inputstyle2" style="width: 135px; float: left;" />
									<img id="image1" src="IdentifyingCodeServlet" height="38px" onclick="change1()"/>
								</div>
							</li>
							<li style="height: 30px;">
								<label for="yanzheng" class="input-tips2"></label>
								<label><input type="checkbox" name="autoLogin" value="1" />七天内自动登陆</label>
							</li>
							<li>
								<div class="inputArea">
									<input type="submit" id="reg" style="margin-top:10px;" class="button_blue" value="立即登录" />
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
			<!--学生登录end-->
			<!--老师登录-->
			<div class="qlogin" id="qlogin" style="display: none; ">
				<div class="web_login">
					<form name="teacher_login" id="regUser" accept-charset="gbk" action="StudentAndTeacherAdminLoginServlet?status=teacher" method="post" onsubmit="return login_teacherID_username()">
						<ul class="reg_form" id="reg-ul">
							<div id="teacherCue" class="cue">请输入职工号和密码进行登录</div>
							<li>
								<label for="user" class="input-tips2">职工号：</label>
								<div class="inputOuter2">
									<input type="text" id="teacherID" name="teacherID" maxlength="16" class="inputstyle2" />
								</div>
							</li>
							<li>
								<label for="passwd" class="input-tips2">密码：</label>
								<div class="inputOuter2">
									<input type="password" id="teacherPassword" name="teacherPassword" maxlength="16" class="inputstyle2" />
								</div>
							</li>
							<li>
								<label for="yanzheng" class="input-tips2">验证码：</label>
								<div class="inputOuter2">
									<input type="text" id="yanzheng" name="code" maxlength="16" class="inputstyle2" style="width: 135px; float: left;" />
									<img id="image2" src="IdentifyingCodeServlet" height="38px" onclick="change2()"/>
								</div>
							</li>
							<li style="height: 30px;">
								<label for="yanzheng" class="input-tips2"></label>
								<label><input type="checkbox" name="autoLogin" value="1" />七天内自动登陆</label>
							</li>
							<li>
								<div class="inputArea">
									<input type="submit" id="reg" style="margin-top:10px;" class="button_blue" value="立即登录" />
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
			<!--老师登录end-->
		</div>
		<div class="b1"></div>
		<div class="b2"></div>
		<jsp:include page="../login_footer.jsp"></jsp:include>
	</body>

</html>