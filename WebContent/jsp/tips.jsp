<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
		<title>SgExam在线考试系统|错误提示</title>
		<link href="css/common.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/js.js" type="text/javascript"></script>
		<style type="text/css">
.msg-icon, .msg-text {
	font-size: 36px;
	line-height: 80px;
	margin: 20px 5px;
	letter-spacing: 10px;
	display: inline-block;
	height: 80px;
	vertical-align: middle;
}

.msg-icon {
	font-size: 80px;
}
</style>
	</head>
	<script type="text/javascript" src="<%=path%>/js/jquery-3.1.1.js"></script>
	<body>
			<div class="clear list_top ovhi psRe">
			<jsp:include page="top.jsp"></jsp:include>
			<ul class="psRe">
				<img src="images/study_background_img.png"/>
				<div class="list_grd_bg psAb"></div>
			</ul>
		</div>
			<!--/sidebar-->
			<div class="main-wrap">

				<div class="crumb-wrap">
						<a href="jsp/teacher/index.jsp">首页</a><span class="crumb-step">&gt;</span>
						<a class="crumb-name" href="jsp/teacher/subject_list.jsp">提示</a><span class="crumb-step"></span></div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<div style="width:600px ; margin: 0 auto; margin-top: 50px; box-shadow: 1px 1px 5px #666666;" class="panel panel-default">
			<div class="panel-heading" style="text-align: center">
				<h2 class="panel-title">提示</h2>
			</div>
			<div class="panel-body">
				<div class="text-center text-danger" style="text-align: center">
					<span class="glyphicon glyphicon-remove-sign msg-icon"></span>
					<h1>${tips }</h1><br />
					<a href="javascript:void(0);" onclick="window.history.go(-1);" class="btn btn6"><i class="glyphicon glyphicon-share-alt"></i> 返 回 </a>
				</div>
			</div>
		</div>
					</div>
				</div>
			</div>
			<!--/main-->
		</div>

	</body>

</html>