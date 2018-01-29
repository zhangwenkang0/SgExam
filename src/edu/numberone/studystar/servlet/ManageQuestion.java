package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.entity.Course;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.QuestionType;
import edu.numberone.studystar.service.QuestionService;
import edu.numberone.studystar.serviceimpl.QuestionServiceImple;
import edu.numberone.studystar.utils.UUIDUtils;

/**
 * Servlet implementation class ManageQuestion
 * 
 * 试题管理,主要是考试的增删改查
 */
@WebServlet("/ManageQuestion")
public class ManageQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pageSize;

	private String path;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// TODO Auto-generated method stub
		try {
			managQuestion(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据客户端传递的标识进行相关操作
	 * 
	 */
	private void managQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");

		System.out.println("flag--->" + flag);

		if (flag != null) {
			switch (flag) {
			case "find":
				findQuestion(request, response);
				break;
			case "findOne":
				findOneQuestion(request, response);
				break;
			case "delete":
				deleteQuestion(request, response);
				break;
			case "add":
				addQuestion(request, response);
				break;
			case "update":
				updateQuestion(request, response);
				break;
			case "toAdd":
				toAddQuestion(request, response);
				break;
			default:
				break;

			}
		}

	}

	
	/**
	 * 修改试题
	 * 
	 * */
	private void updateQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String q_type = request.getParameter("q_type");
		Question question = new Question();
		QuestionService questionService = new QuestionServiceImple();
		int result = 0;
		question.setQ_type(q_type);
		String q_title = request.getParameter("q_title");
		if(q_title != null){
			question.setQ_title(q_title);
		}
		if("q1".equals(q_type)||"q2".equals(q_type)){
		String[] selects=request.getParameterValues("selects");
		StringBuffer select=new StringBuffer();
		for(int i=0;i<selects.length;i++){
			select.append(selects[i]);
			if(i!=selects.length-1){
				select.append("@");
			}
		}
		question.setQ_select(select.toString());
		}
		question.setQ_id(request.getParameter("q_id"));
		question.setQ_score(request.getParameter("q_score").trim());
		question.setQ_answer(request.getParameter("q_answer"));
		question.setCourse_id(request.getParameter("q_course"));
		question.setQ_explaination(request.getParameter("explaination"));
		
		
		result = questionService.updateQuestionToModel(question);

		if (result == 1) {
			findQuestion(request, response);
		}else{
			try {
				response.getWriter().println("添加试题失败，请重新添加");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 添加考试
	 * 
	 */
	private void addQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取试题类型
		String q_type = request.getParameter("q_type");
		Question question = new Question();
		QuestionService questionService = new QuestionServiceImple();
		int result = 0;
		question.setQ_type(q_type);
		//获取考题题目
		String q_title = request.getParameter("q_title");
		if(q_title != null){
			question.setQ_title(q_title);
		}
		//若考题类型为选择题则获取其选项
		if("q1".equals(q_type)||"q2".equals(q_type)){
		String[] selects=request.getParameterValues("selects");
		StringBuffer select=new StringBuffer();
		for(int i=0;i<selects.length;i++){
			select.append(selects[i]);
			if(i!=selects.length-1){
				select.append("@");
			}
		}
		question.setQ_select(select.toString());
		}
		//获取考题的基本信息
		question.setQ_id(UUIDUtils.getUUId());
		question.setQ_score(request.getParameter("q_score").trim());
		question.setQ_answer(request.getParameter("q_answer"));
		question.setCourse_id(request.getParameter("q_course"));
		question.setQ_explaination(request.getParameter("explaination"));
		
		//添加考题到数据库中
		result = questionService.AddQuestionToModel(question);
		
		if (result == 1) {
			findQuestion(request, response);
		}else{
			try {
				response.getWriter().println("添加试题失败，请重新添加");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	 /* 跳转添加考试页面
	 * 
	 */
	private void toAddQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// TODO Auto-generated method stub
		QuestionService questionService = new QuestionServiceImple();
		List<QuestionType> typeList=questionService.findAllQuestionType();
		request.setAttribute("typeList", typeList);
		List<Course> courseList=questionService.findAllCourseType();
		request.setAttribute("courseList", courseList);
		request.getRequestDispatcher("/jsp/teacher/subject_add.jsp").forward(request, response);

		 }
	/**
	 * 删除考试
	 * 
	 * 
	 */
	private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String q_id = request.getParameter("q_id");
		QuestionService questionService = new QuestionServiceImple();
		List<Question> questions = new ArrayList<>();
		int result = 0;
		if (q_id != null) {

			Question question = new Question();
			question.setQ_id(q_id);
			questions.add(question);

			result = questionService.deletePersonInformationToMedel(questions);

			if (result == 1) {
				findQuestion(request, response);
			}else{
					try {
						request.setAttribute("error", "添加试题失败，请返回重新添加！");
						request.getRequestDispatcher("/jsp/teacher/teacher_error.jsp").forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}

	}

	/**
	 * 根据考试类型，查找考试
	 * 
	 * 
	 */
	private void findQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取考题类型
		String type = request.getParameter("type");
		//获取查询关键字
		String keyword = request.getParameter("keyword");
		//获取当前页数
		String pageNum = request.getParameter("pager");
		
		String manager = request.getParameter("manager");
		//当前页默认为1
		Integer currentPage = 1;
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		if (type != null) {
			QuestionService questionService = new QuestionServiceImple();
			//分页查询，获取分页后的考题数据
			Pager<Question> pager = questionService.findQuestionFromModel(currentPage, pageSize, type,keyword);
			System.out.println("pager-->" + pager.getDateList().size());
			request.setAttribute("pager", pager);
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
			
			System.out.println("current-->" + pager.getCurrentPage());
			System.out.println("TotalPage()-->" + pager.getTotalPage());
			System.out.println("TotalRecord()-->" + pager.getTotalRecord());
			
			
			if("teacher".equals(manager)){
				try {
					request.getRequestDispatcher("/jsp/teacher/subject_list.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if("admin".equals(manager)){
				try {
					request.getRequestDispatcher("/jsp/admin/subject_list.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			

		} else {

			try {
				response.getWriter().println("没有查找到相关考试");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	//根据ID查找某条试题的信息
	private void findOneQuestion(HttpServletRequest request, HttpServletResponse response){
		try {
			String qId = request.getParameter("q_id");
			QuestionService questionService = new QuestionServiceImple();
			Question question=questionService.findQuestionById(qId);
			List<Course> courseList=questionService.findAllCourseType();
			request.setAttribute("courseList", courseList);
			request.setAttribute("question", question);
			String t_name=(String) request.getSession().getAttribute("tea_name");
			if(null!=t_name&&""!=t_name){
			request.getRequestDispatcher("/jsp/teacher/subject_revise.jsp").forward(request, response);
			}else{
			request.getRequestDispatcher("/jsp/admin/subject_revise.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
