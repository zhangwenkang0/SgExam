//登录界面学号验证
function login_studentID_username() {
	var stuIDval = document.getElementById("studentID").value;
	var patt1 = new RegExp(/^[0-9]+$/);
	var studentCue = document.getElementById("studentCue");
	if(!patt1.test(stuIDval)){
		studentCue.innerHTML="<font color='red'><b>学号只能为纯数字</b></font>";
	}
	return patt1.test(stuIDval);
}
//登录界面职工号验证
function login_teacherID_username() {
	var stuIDval = document.getElementById("teacherID").value;
	var patt1 = new RegExp(/^[0-9]+$/);
	var teacherCue = document.getElementById("teacherCue");
	if(!patt1.test(stuIDval)){
		teacherCue.innerHTML="<font color='red'><b>职工号只能为纯数字</b></font>";
	}
	return patt1.test(stuIDval);
}
//修改密码新密码验证
function login_pwd_username() {
	var patt1 = new RegExp(/^[a-zA-Z]\w{5,17}$/);
	//获取新密码的值
	var newpasswordvalue = document.getElementById("newpassword").value;
	//获取确认密码的值
	var confirmpasswordvalue = document.getElementById("confirmpassword").value;
	var modpwdCue = document.getElementById("modpwdCue");
	if(!patt1.test(newpasswordvalue)) {
		modpwdCue.innerHTML="<font color='red'><b>以字母开头，长度在6-18之间，只能包含字符、数字和下划线。</b></font>";
	}
	if(newpasswordvalue!=confirmpasswordvalue){
		modpwdCue.innerHTML="<font color='red'><b>两次密码不一致！请重新输入</b></font>";
	}
	return patt1.test(newpasswordvalue)&&(newpasswordvalue==confirmpasswordvalue);
}