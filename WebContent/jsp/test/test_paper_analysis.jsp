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
		<title>SgExam在线考试系统|我的考试</title>
		<link href="css/test_paper_common.css" rel="stylesheet" type="text/css">
		<link href="css/list.css" rel="stylesheet" type="text/css">

		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/js.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="clear list_top ovhi psRe">
			<div class="header psAb">
				<div class="psRe head">
					<h2 class="logo"><a class="disin" href="servlet/StudentServlet.do?operation=toIndex"><img src="images/index_logo.png" alt="SgExam在线考试系统"/></a></h2>
					<div class="menuname text_font">
						C语言
					</div>
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
			<ul class="psRe">
				<img src="images/study_background_img.png" />
				<div class="list_grd_bg psAb"></div>
			</ul>
			<a class="disin psAb menuname" href="#">答案解析</a>
		</div>
		<div class="crumbs_left lf"></div>

		<div class="bk"></div>
		<div class="bagf wrap_list clear grd24 mar0a">
			<div class="list_left lf">
				<div id="wrap_sub"></div>
				<ul class="wrap_sub">
					<li class="on">
						<a class="subnav_a" href="student_exam_list.html">单选题</a>
					</li>
					<li>
						<a class="subnav_a" href="student_score.html">多选题</a>
					</li>
					<li>
						<a class="subnav_a" href="student_news.html">判断题</a>
					</li>
					<li>
						<a class="subnav_a" href="student_personal.html">简答题</a>
					</li>
				</ul>
			</div>
			<div class="list-content rt list_main">
				<div class="article psRe">

					<form action="" method="post">
						<h1>单选题</h1>
						<fieldset>
							<legend>1.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="radio" name="1" id="" value="" />A</label><br />
							<label><input type="radio" name="1" id="" value="" />B</label><br />
							<label><input type="radio" name="1" id="" value="" />C</label><br />
							<label><input type="radio" name="1" id="" value="" />D</label><br />
							<fieldset>
								答案：<br />
								解析：
							</fieldset>
						</fieldset>
						<fieldset>
							<legend>2.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="radio" name="1" id="" value="" />A</label><br />
							<label><input type="radio" name="1" id="" value="" />B</label><br />
							<label><input type="radio" name="1" id="" value="" />C</label><br />
							<label><input type="radio" name="1" id="" value="" />D</label><br />
							<fieldset>
								答案：<br />
								解析：
							</fieldset>
						</fieldset>
						<fieldset>
							<legend>3.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="radio" name="1" id="" value="" />A</label><br />
							<label><input type="radio" name="1" id="" value="" />B</label><br />
							<label><input type="radio" name="1" id="" value="" />C</label><br />
							<label><input type="radio" name="1" id="" value="" />D</label><br />
							<fieldset>
								答案：<br />
								解析：
							</fieldset>
						</fieldset>
						<fieldset>
							<legend>4.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="radio" name="1" id="" value="" />A</label><br />
							<label><input type="radio" name="1" id="" value="" />B</label><br />
							<label><input type="radio" name="1" id="" value="" />C</label><br />
							<label><input type="radio" name="1" id="" value="" />D</label><br />
						</fieldset>
						<fieldset>
							<legend>5.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="radio" name="1" id="" value="" />A</label><br />
							<label><input type="radio" name="1" id="" value="" />B</label><br />
							<label><input type="radio" name="1" id="" value="" />C</label><br />
							<label><input type="radio" name="1" id="" value="" />D</label><br />
						</fieldset>
						<div class="bk20"></div>
						<h1>多选题</h1>
						<fieldset>
							<legend>1.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="checkbox" name="1" id="" value="" />A</label><br />
							<label><input type="checkbox" name="1" id="" value="" />B</label><br />
							<label><input type="checkbox" name="1" id="" value="" />C</label><br />
							<label><input type="checkbox" name="1" id="" value="" />D</label><br />
						</fieldset>
						<fieldset>
							<legend>2.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="checkbox" name="1" id="" value="" />A</label><br />
							<label><input type="checkbox" name="1" id="" value="" />B</label><br />
							<label><input type="checkbox" name="1" id="" value="" />C</label><br />
							<label><input type="checkbox" name="1" id="" value="" />D</label><br />
						</fieldset>
						<fieldset>
							<legend>3.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="checkbox" name="1" id="" value="" />A</label><br />
							<label><input type="checkbox" name="1" id="" value="" />B</label><br />
							<label><input type="checkbox" name="1" id="" value="" />C</label><br />
							<label><input type="checkbox" name="1" id="" value="" />D</label><br />
						</fieldset>
						<fieldset>
							<legend>4.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="checkbox" name="1" id="" value="" />A</label><br />
							<label><input type="checkbox" name="1" id="" value="" />B</label><br />
							<label><input type="checkbox" name="1" id="" value="" />C</label><br />
							<label><input type="checkbox" name="1" id="" value="" />D</label><br />
						</fieldset>
						<fieldset>
							<legend>5.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="checkbox" name="1" id="" value="" />A</label><br />
							<label><input type="checkbox" name="1" id="" value="" />B</label><br />
							<label><input type="checkbox" name="1" id="" value="" />C</label><br />
							<label><input type="checkbox" name="1" id="" value="" />D</label><br />
						</fieldset>
						<div class="bk20"></div>
						<h1>判断题</h1>
						<fieldset>
							<legend>1.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<label><input type="radio" name="1" id="" value="" />对</label><br />
							<label><input type="radio" name="1" id="" value="" />错</label><br />
						</fieldset>
						<div class="bk20"></div>
						<h1>简答题</h1>
						<fieldset>
							<legend>1.&nbsp;</legend>
							<p>问题是。。。。。。。。。。。。。。。。。。。</p>
							<textarea style="width: 99%;" name="1" rows="10" cols=""></textarea>
						</fieldset>
						
					</form>
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