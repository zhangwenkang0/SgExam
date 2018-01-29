package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.entity.Classes;
import edu.numberone.studystar.entity.Course;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.SubAnswer;
import edu.numberone.studystar.service.ClassesService;
import edu.numberone.studystar.service.QuestionService;
import edu.numberone.studystar.service.ScoreService;
import edu.numberone.studystar.serviceimpl.ClassesServiceImpl;
import edu.numberone.studystar.serviceimpl.QuestionServiceImple;
import edu.numberone.studystar.serviceimpl.ScoreServiceImpl;

/**
 * Servlet implementation class ScoreCorrectServlet
 */
public class ScoreCorrectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize;
	private String path;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreCorrectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=gbk");
		response.setCharacterEncoding("gbk");
		String operation=request.getParameter("operation");
		//跳转到批改主观题成绩列表页
		if("toScoreCorrect".equals(operation)){
			toScoreCorrect(request,response);
		}
		//跳转到批改主观题成绩页
		else if("toCorrect".equals(operation)){
			String sc_id=request.getParameter("sc_id");
			ScoreService scoreSev=new ScoreServiceImpl();
			List<SubAnswer> subAnswerList=scoreSev.findSubAnswer(sc_id);
			request.setAttribute("sc_id", sc_id);
			request.setAttribute("subAnswerList", subAnswerList);
			request.getRequestDispatcher("/jsp/teacher/toCorrect.jsp").forward(request, response);
		}
		//批改主观题成绩
		else if("correct".equals(operation)){
			// 获取成绩编号
			String sc_id=request.getParameter("sc_id");
			ScoreService scoreSev=new ScoreServiceImpl();
			//根据成绩编号获取主观题答案列表
			List<SubAnswer> subAnswerList=scoreSev.findSubAnswer(sc_id);
			//遍历主观题答案并获取老师对应的评分
			for(SubAnswer answer:subAnswerList){
				String[] scores=request.getParameterValues(answer.getQ_id());
				//主观题总分
				Float subScore=0f;
				for(String score:scores){
					subScore+=Float.parseFloat(score);
				}
				//修改数据库中数据，得到总成绩
				scoreSev.correctSubAnswer(sc_id, subScore);
			}
			request.setAttribute("tips","2秒后返回批改列表页面！");
			response.setHeader("ReFresh", "2;url="+request.getContextPath()+"/servlet/ScoreCorrectServlet.do?operation=toScoreCorrect");
			request.getRequestDispatcher("jsp/tips.jsp").forward(request, response);
		}
	}
	
	public void toScoreCorrect(HttpServletRequest request,HttpServletResponse response){
		String keyword = request.getParameter("keyword");
		String pageNum = request.getParameter("pager");
		String c_id = request.getParameter("c_id");
		String cls_id = request.getParameter("cls_id");
		Integer currentPage = 1;
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		QuestionService questionService = new QuestionServiceImple();
		List<Course> courseList=questionService.findAllCourseType();
		ClassesService classesSev=new ClassesServiceImpl();
		List<Classes> classesList=classesSev.findAllClasses();
		request.setAttribute("courseList", courseList);
		request.setAttribute("classesList", classesList);
		ScoreService scoreSev=new ScoreServiceImpl();
		Pager<Scores> pager =scoreSev.findAllScore(currentPage, pageSize, c_id, cls_id, keyword);
		request.setAttribute("pager", pager);
		try {
			request.getRequestDispatcher("/jsp/teacher/test_correct.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
