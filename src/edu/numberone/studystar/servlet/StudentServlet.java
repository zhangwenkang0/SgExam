package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.numberone.studystar.daoimpl.AnnouncementDaoImpl;
import edu.numberone.studystar.daoimpl.ScoresDaoImpl;
import edu.numberone.studystar.entity.Announcement;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.service.PaperService;
import edu.numberone.studystar.service.QuestionService;
import edu.numberone.studystar.serviceimpl.PaperServiceImpl;
import edu.numberone.studystar.serviceimpl.QuestionServiceImple;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize;
	private String path;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation=request.getParameter("operation");
		HttpSession session=request.getSession();
		String cls_id=(String) session.getAttribute("cls_id");
		//获取学生编号
		String stu_id = (String)session.getAttribute("stu_id");
		if("toMyTest".equals(operation)){
			PaperService paperSev=new PaperServiceImpl();
			Pager<Paper> papers=paperSev.findPaperByClassesId(1, pageSize,cls_id);
			request.setAttribute("papers", papers);
			request.getRequestDispatcher("/jsp/student/student_exam_list.jsp").forward(request, response);
		}
		if("test".equals(operation)){
			String pId = request.getParameter("p_id");
			if(pId != null){
				PaperService paperSev=new PaperServiceImpl();
				Paper paper=paperSev.findPaperById(pId);
				request.setAttribute("paper", paper);
				String[] qids=paper.getQ_id().split("@");
				QuestionService questionService = new QuestionServiceImple();
				List<Question> questionList=questionService.findSelectedQuestion(qids);
				request.setAttribute("ques", questionList);
				request.setAttribute("pid", pId);
				request.getRequestDispatcher("/jsp/test/test_paper.jsp").forward(request, response);
		}
		}
		else if("showScore".equals(operation)){
			ScoresDaoImpl sdi = new ScoresDaoImpl();
			Scores sco = new Scores();
			sco.setS_id(stu_id);
			//查找出该学生所有成绩
			LinkedList<Scores> scores = sdi.getScores(sco, 0, 0);
			request.setAttribute("scores", scores);
			request.getRequestDispatcher("/jsp/student/student_score.jsp").forward(request, response);
		}else if("toIndex".equals(operation)){
			PaperService paperSev=new PaperServiceImpl();
			Pager<Paper> papers=paperSev.findPaperByClassesId(1, pageSize,cls_id);
			request.setAttribute("papers", papers);
			//获取学生编号
			ScoresDaoImpl sdi = new ScoresDaoImpl();
			Scores sco = new Scores();
			sco.setS_id(stu_id);
			//查找出该学生所有成绩
			LinkedList<Scores> scores = sdi.getScores(sco, 0, 0);
			request.setAttribute("scores", scores);
			//查询公告
			//查询公告
			AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
			Announcement ann = new Announcement();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date now=new Date();
			String nowStr=sdf.format(now);
			ann.setA_startTime(nowStr);
			ann.setA_endTime(nowStr);
			LinkedList<Announcement> anns = adi.getAnnouncement(ann, 0, 5);
			request.setAttribute("anns", anns);
			request.getRequestDispatcher("/jsp/student/student_index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		path = config.getServletContext().getContextPath();
		String pSize = config.getServletContext().getInitParameter("pageSize");

		pageSize = Integer.parseInt(pSize);
	}
}
