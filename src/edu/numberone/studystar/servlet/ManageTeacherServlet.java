package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.entity.Teacher;
import edu.numberone.studystar.service.PersonInformationService;
import edu.numberone.studystar.service.PresonInformationFactoryService;
import edu.numberone.studystar.serviceimpl.ManagerInformationServiceImple;
import edu.numberone.studystar.serviceimpl.PersonInformationFactoryServiceImple;
import edu.numberone.studystar.serviceimpl.StudentInformationServiceImple;
import edu.numberone.studystar.serviceimpl.TeacherInformationServiceImple;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ManageAdminServlet
 * 
 * 管理学生信息
 * 
 */
@WebServlet("/ManageTeacherServlet")
public class ManageTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path;

	private int pageSize = 0;// 设置当前页面大小

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		this.path = config.getServletContext().getContextPath();
		this.pageSize = Integer.parseInt(config.getServletContext().getInitParameter("pageSize"));
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageTeacherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		manageAdmin(request, response);

	}

	/**
	 * 根据增删查找等请求，操作对应的信息
	 * 
	 */
	private void manageAdmin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");// 请求的类型

		if (flag != null) {
			switch (flag) {
			case "find": {

				findAllTeacher(request, response);
				break;
			}
			case "add": {
				AddTeacher(request, response);
				break;
			}
			case "delete": {
				deleteTeacher(request, response);
				break;
			}
			case "update": {
				updateTeacher(request,response);
				break;
			}
			default:
				break;
			}
		}

	}
	
	/**
	 * 修改管教师信息
	 * 
	 * */
	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String t_id = request.getParameter("t_id");
		String t_name = request.getParameter("t_name");
		String t_pass = request.getParameter("t_pass");
		String coll_id = request.getParameter("coll_id");
		String t_Job = request.getParameter("t_Job");
		
		int result = 0;
		Teacher teacher = new Teacher();
		teacher.setT_Id(t_id);
		teacher.setT_Name(t_name);
		teacher.setT_Pass(t_pass);
		teacher.setT_Job(t_Job);
		teacher.setColl_Id(coll_id);
		PersonInformationService<Teacher> teacherInformationService = new TeacherInformationServiceImple();
		result = teacherInformationService.updatePersonInformationToModel(teacher);

		if (result == 1) {
			this.findAllTeacher(request, response);
		} else {
			try {
				response.getWriter().write("添加教师失败!请重新修改");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 添加教师
	 */
	private void AddTeacher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String t_id = request.getParameter("t_id");
		String t_name = request.getParameter("t_name");
		String t_pass = request.getParameter("t_pass");
		String coll_id = request.getParameter("coll_id");
		String t_Job = request.getParameter("t_Job");
		
		int result = 0;
		Teacher teacher = new Teacher();
		teacher.setT_Id(t_id);
		teacher.setT_Name(t_name);
		teacher.setT_Pass(t_pass);
		teacher.setT_Job(t_Job);
		teacher.setColl_Id(coll_id);
		PersonInformationService<Teacher> teacherInformationService = new TeacherInformationServiceImple();
		result = teacherInformationService.AddPersonInformationToModel(teacher);

		if (result == 1) {
			this.findAllTeacher(request, response);
		} else {
			try {
				response.getWriter().write("添加管理员失败!请重新添加");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/***
	 * 
	 * 删除的教师
	 * 
	 */
	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String t_id = request.getParameter("T_id");

		System.out.println("deleteManager-->" + t_id);

		int result = 0;// 标识是否删除成功
		if (t_id != null) {
			List<Teacher> teachers = new ArrayList<Teacher>();
			Teacher teacher = new Teacher();
			teacher.setT_Id(t_id);
			teachers.add(teacher);
			System.out.println("m_ids[i]-->" + t_id);
			PersonInformationService<Teacher> teacherInformationService = new TeacherInformationServiceImple();
			if (teachers.size() > 0) {
				result = teacherInformationService.deletePersonInformationToMedel(teachers);
			}

		}

		// 检测是否删除成功
		if (result == 1) {
			findAllTeacher(request, response);
		} else {

			try {
				response.getWriter().write("添加管理员失败!请重新添加");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/***
	 * 
	 * 查找所有的教师
	 * 
	 */

	private void findAllTeacher(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		PersonInformationService<Teacher> teacherInformationService = new TeacherInformationServiceImple();
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));

		if (pageNum != null) {

			Pager<Teacher> pager = teacherInformationService.findAllPersonInformationFromModel(pageNum, this.pageSize);

			System.out.println("findAllStudent-->pager" + pager.getDateList().size());

			try {
				// 获得当前上下文路径
				request.setAttribute("pager", pager);
				String url = "/jsp/admin/teacher_list.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}
