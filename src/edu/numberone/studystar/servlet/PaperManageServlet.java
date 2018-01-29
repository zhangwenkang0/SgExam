package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

import com.alibaba.fastjson.JSON;

import edu.numberone.studystar.daoimpl.PaperDaoImpl;
import edu.numberone.studystar.entity.Classes;
import edu.numberone.studystar.entity.Course;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.QuestionType;
import edu.numberone.studystar.service.ClassesService;
import edu.numberone.studystar.service.PaperService;
import edu.numberone.studystar.service.QuestionService;
import edu.numberone.studystar.serviceimpl.ClassesServiceImpl;
import edu.numberone.studystar.serviceimpl.PaperServiceImpl;
import edu.numberone.studystar.serviceimpl.QuestionServiceImple;
import edu.numberone.studystar.utils.UUIDUtils;


@WebServlet("/PaperManageServlet")
public class PaperManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Paper p=null; 
	PaperDaoImpl ppdi=null;
	private int pageSize;
	private String path;
	String t_name;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		response.setCharacterEncoding("gbk");
		p=new Paper();
		ppdi=new PaperDaoImpl();
		LinkedList<Paper> listPaper;
		String operation=request.getParameter("operation");
		PrintWriter out =response.getWriter();
		t_name=(String) request.getSession().getAttribute("tea_name");
		
		if(operation.equals("add")){
			getParam(request);
			ppdi.addPaper(p);
			request.setAttribute("tips", "添加试卷成功，2秒后返回试卷表页");
//			response.setHeader("ReFresh", "2;url="+request.getContextPath()+"/jsp/teacher/test_list.jsp");
			response.setHeader("ReFresh", "2;url="+request.getContextPath()+"/PaperManageServlet?operation=update");
			request.getRequestDispatcher("jsp/tips.jsp").forward(request, response);
		}
		if(operation.equals("findAllClasses")){
			ClassesService classesSev=new ClassesServiceImpl();
			List<Classes> classesList=classesSev.findAllClasses();
			out.write(JSON.toJSONString(classesList,true));
		}
		
		if(operation.equals("delete")){
			String p_id=request.getParameter("p_id");
			p.setP_id(p_id);
			String del=ppdi.deletePaper(p);
			System.out.println(del);
			listPaper=ppdi.getPaper(p, 0, 0);
			request.setAttribute("listPaper", listPaper);
			request.getRequestDispatcher("/PaperManageServlet?operation=update").forward(request, response);
			
		}
		if(operation.equals("update")){
			//p=new Paper();
			findAllTest(request,response);
			//request.getRequestDispatcher("/PaperManageServlet?operation=update").forward(request, response);
		}
		if(operation.equals("modify")){
			//获取考试编号
			String p_id=request.getParameter("pid");
			//获取考试设置的基本信息
			getParam(request);
			p.setP_id(p_id);
			//格式化日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			Date date=new Date();
			p.setUpdateTime(df.format(new Date()));
			HttpSession session=request.getSession();
			String updateUser=(String) session.getAttribute("username");
			p.setUpdateUser(updateUser);
			//修改保存数据
			String resultStr=ppdi.updatePaper(p);
			findAllTest(request,response);
			//request.getRequestDispatcher("/PaperManageServlet?operation=update").forward(request, response);
		}
		if(operation.equals("toModify")){
			String pid=request.getParameter("pid");
			QuestionService questionService = new QuestionServiceImple();
			List<Course> courseList=questionService.findAllCourseType();
			request.setAttribute("courseList", courseList);
			PaperService paperSev=new PaperServiceImpl();
			Paper paper=paperSev.findPaperById(pid);
			request.setAttribute("paper", paper);
			ClassesService classesSev=new ClassesServiceImpl();
			List<Classes> classesList=classesSev.findAllClasses();
			request.setAttribute("classesList", classesList);
			if(null!=t_name&&""!=t_name){
			request.getRequestDispatcher("/jsp/teacher/test_revise.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/jsp/admin/test_revise.jsp").forward(request, response);
			}
			}
		if(operation.equals("courseType")){
			QuestionService questionService = new QuestionServiceImple();
			List<Course> courseList=questionService.findAllCourseType();
			 out.write(JSON.toJSONString(courseList,true));
		}
		if(operation.equals("questionType")){
			QuestionService questionService = new QuestionServiceImple();
			List<QuestionType> questionList=questionService.findAllQuestionType();
			 out.write(JSON.toJSONString(questionList,true));
		}
		if(operation.equals("findQuestion")){
			String courseType=request.getParameter("courseType");
			String questionType=request.getParameter("questionType");
			QuestionService questionService = new QuestionServiceImple();
			List<Question> questionList=questionService.findAllQuestion(courseType, questionType);
			System.out.println(questionList);
			 out.write(JSON.toJSONString(questionList,true));
		}
		if(operation.equals("findSelectedQuestion")){
			String[] qids=request.getParameterValues("qids[]");
			QuestionService questionService = new QuestionServiceImple();
			List<Question> questionList=questionService.findSelectedQuestion(qids);
			out.write(JSON.toJSONString(questionList,true));
		}
		
	}
	
	
	public void  findAllTest(HttpServletRequest request,HttpServletResponse response){
		String c_id = request.getParameter("c_id");
		String keyword = request.getParameter("keyword");
		String pageNum = request.getParameter("pager");
		Integer currentPage = 1;
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		PaperService paperSev=new PaperServiceImpl();
		Pager<Paper> pager=paperSev.findAllPaper(currentPage, pageSize, c_id, keyword);
		request.setAttribute("pager", pager);
		QuestionService questionService = new QuestionServiceImple();
		List<Course> courseList=questionService.findAllCourseType();
		request.setAttribute("courseList", courseList);
		ClassesService classesSev=new ClassesServiceImpl();
		List<Classes> classesList=classesSev.findAllClasses();
		request.setAttribute("classesList", classesList);
		try {
			if(null!=t_name&&""!=t_name){
			request.getRequestDispatcher("jsp/teacher/test_list.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("jsp/admin/test_list.jsp").forward(request, response);	
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("刷新数据");
	}
	
	
	public void getParam(HttpServletRequest request){
		SimpleDateFormat date =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		String course=request.getParameter("course");
		String papername=request.getParameter("papername");
		String[] q_id=request.getParameterValues("q_ids");
		Float score=Float.parseFloat(request.getParameter("score"));
		String classes=request.getParameter("classes");
		Date p_date;
		try {
			String dateString = request.getParameter("p_date");
			String startTime=dateString+" "+request.getParameter("startTime")+":00";
			String endTime=dateString+" "+request.getParameter("endTime")+":00";
			HttpSession session=request.getSession();
			String createUser=(String) session.getAttribute("username");
			p=new Paper(); 
			newPaper(p, course, papername,  q_id, score,startTime, endTime,createUser,classes);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void newPaper(Paper p,String course,String papername,
	      String[] q_id,Float score,String startTime,String endTime,String  createUser,String classes) throws ParseException{
		UUIDUtils uuid=new UUIDUtils();
		p.setP_id(uuid.getUUId());
		p.setC_id(course);;
		p.setP_name(papername);
		StringBuffer str=new StringBuffer();
		for(int i=0;i<q_id.length;i++){
			str.append(q_id[i]);
			if(i!=q_id.length-1){
				str.append("@");
			}
		}
		p.setQ_id(str.toString());
		p.setP_scores(score);
		p.setStartTime(startTime);
		p.setEndTime(endTime);
		p.setCreateUser(createUser);
		p.setCls_id(classes);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date=new Date();
		p.setCreateTime(df.format(new Date()));
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
