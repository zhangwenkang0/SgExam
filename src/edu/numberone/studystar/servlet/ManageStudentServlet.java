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

import edu.numberone.studystar.entity.Classes;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.service.ClassesService;
import edu.numberone.studystar.service.PersonInformationService;
import edu.numberone.studystar.serviceimpl.ClassesServiceImpl;
import edu.numberone.studystar.serviceimpl.StudentInformationServiceImple;

/**
 * Servlet implementation class ManageAdminServlet
 * 
 * 管理学生信息
 * 
 */
@WebServlet("/ManageStudentServlet")
public class ManageStudentServlet extends HttpServlet {
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
	public ManageStudentServlet() {
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

				findAllStudent(request, response);
				break;
			}
			case "add": {
				AddStudent(request, response);
				break;
			}
			case "delete": {
				deleteStudent(request, response);
				break;
			}
			case "update": {
				updateStudent(request,response);
				break;
			}
			default:
				break;
			}
		}

	}
	
	/**
	 * 修改管理员信息
	 * 
	 * */
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String s_pass = request.getParameter("s_pass");
		String cls_id = request.getParameter("cls_id");

		String s_sex = request.getParameter("s_sex");
		
		Student student = new Student();
		switch (s_sex) {
		case "1":
			student.setS_sex("男");
			break;
		case "2":
			student.setS_sex("女");
			break;

		default:
			break;
		}
		int result = 0;
		
		student.setS_id(s_id);
		student.setS_name(s_name);
		student.setS_pass(s_pass);
		student.setCls_Id(cls_id);
		PersonInformationService<Student> studentInformationService = new StudentInformationServiceImple();
		result = studentInformationService.updatePersonInformationToModel(student);

		if (result == 1) {
			this.findAllStudent(request, response);
		} else {
			try {
				response.getWriter().write("添加管理员失败!请重新添加");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 添加学生
	 */
	private void AddStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String s_pass = request.getParameter("s_pass");
		String cls_id = request.getParameter("cls_id");
		String s_sex = request.getParameter("s_sex");
		
		
		
		Student student = new Student();
		switch (s_sex) {
		case "1":
			student.setS_sex("男");
			break;
		case "2":
			student.setS_sex("女");
			break;

		default:
			break;
		}
		int result = 0;
		
		student.setS_id(s_id);
		student.setS_name(s_name);
		student.setS_pass(s_pass);
		student.setCls_Id(cls_id);
		PersonInformationService<Student> studentInformationService = new StudentInformationServiceImple();
		result = studentInformationService.AddPersonInformationToModel(student);

		if (result == 1) {
			this.findAllStudent(request, response);
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
	 * 删除的删除
	 * 
	 */
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取学生编号
		String s_id = request.getParameter("s_id");

		System.out.println("deleteManager-->" + s_id);
		
		int result = 0;// 标识是否删除成功
		if (s_id != null) {
			List<Student> students = new ArrayList<Student>();
			Student student = new Student();
			student.setS_id(s_id);
			students.add(student);
			System.out.println("m_ids[i]-->" + s_id);
			PersonInformationService<Student> studentInformationService = new StudentInformationServiceImple();
			if (students.size() > 0) {
				//删除学生信息
				result = studentInformationService.deletePersonInformationToMedel(students);
			}

		}

		// 检测是否删除成功
		if (result == 1) {
			findAllStudent(request, response);
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
	 * 查找所有的学生
	 * 
	 */

	private void findAllStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		PersonInformationService<Student> managerInformationService = new StudentInformationServiceImple();
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));

		if (pageNum != null) {

			Pager<Student> pager = managerInformationService.findAllPersonInformationFromModel(pageNum, this.pageSize);

			System.out.println("findAllStudent-->pager" + pager.getDateList().size());

			try {
				// 获得当前上下文路径
				request.setAttribute("pager", pager);
				String url = "/jsp/admin/student_list.jsp";
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
