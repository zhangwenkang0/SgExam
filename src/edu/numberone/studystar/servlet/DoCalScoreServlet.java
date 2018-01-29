package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.SubAnswer;
import edu.numberone.studystar.service.PaperService;
import edu.numberone.studystar.service.QuestionService;
import edu.numberone.studystar.serviceimpl.PaperServiceImpl;
import edu.numberone.studystar.serviceimpl.QuestionServiceImple;
import edu.numberone.studystar.utils.UUIDUtils;


@WebServlet("/DoCalScoreServlet")
public class DoCalScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符编码
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		response.setCharacterEncoding("gbk");
		//获取取班级编号
		String cid = request.getParameter("cid");
		//获取考试编号
		String pid = request.getParameter("pid");
		//获取学生信息
		String sid = (String)request.getSession().getAttribute("stu_id");
		String sname = (String)request.getSession().getAttribute("stu_name");
		String cls_id = (String)request.getSession().getAttribute("cls_id");
		String cls_name = (String)request.getSession().getAttribute("cls_name");
		float score = 0;
		PaperService paperSev=new PaperServiceImpl();
		//根据考试编号查询考试
		Paper paper=paperSev.findPaperById(pid);
		String pname=paper.getP_name();
		request.setAttribute("paper", paper);
		//获取所有的考题编号
		String[] qids=paper.getQ_id().split("@");
		QuestionService questionService = new QuestionServiceImple();
		//根据考题编号查找所有的考题
		List<Question> questionList=questionService.findSelectedQuestion(qids);
		String sAnswer="";
		//生成UUID成绩编号
		UUIDUtils util=new UUIDUtils();
		String sc_id=util.getUUId();
		//遍历所有考题，从前端页面获取相应 的答案及打分
		for(int i = 0; i < questionList.size(); ++i){
			Question que = questionList.get(i);
			sAnswer = request.getParameter(que.getQ_id());
			if(!que.getQ_type().equals("q5")){
				if(sAnswer != null){
					if(sAnswer.equalsIgnoreCase(que.getQ_answer())){
						score += Float.parseFloat(que.getQ_score());
					}
				}
				
			}else{//将主观题存储在主观题表
				if(sAnswer != null){
					SubAnswer answer=new SubAnswer();
					answer.setQ_id(que.getQ_id());
					answer.setSc_id(sc_id);
					answer.setQ_title(que.getQ_title());
					answer.setS_id(sid);
					answer.setAnswer(sAnswer);
					answer.setRightAnswer(que.getQ_answer());
					answer.setQ_score(Float.parseFloat(que.getQ_score()));
					paperSev.saveSubAnswer(answer);
				}
			}
		}
		//存储客观题成绩
		paperSev.saveScore(sc_id, pid, sid, score, cid, pname,sname,cls_id,cls_name);
	 request.setAttribute("tips", "考试提交成功！考试结束！请离开考场！2秒后将退出系统！");
	 response.setHeader("ReFresh", "2;url='QuitServlet?status=student'");
	 request.getRequestDispatcher("jsp/student/student_tips.jsp").forward(request, response);
	}
	
}
