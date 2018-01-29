package edu.numberone.studystar.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.entity.Teacher;
import edu.numberone.studystar.service.PersonInformationService;
import edu.numberone.studystar.service.PresonInformationFactoryService;
import edu.numberone.studystar.serviceimpl.PersonInformationFactoryServiceImple;

/**
 * Servlet implementation class PersonInformationServlet
 */
@WebServlet("/PersonInformationServlet")
public class PersonInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path;// 上下文路径

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonInformationServlet() {
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
		findPersonInformation(request, response);
	}

	/**
	 * 获得jsp页面传递的身份ID,查找人员信息
	 * 
	 * @param request
	 * @param response
	 * 
	 * 
	 */
	private void findPersonInformation(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";

		String flag = request.getParameter("flag");

		System.out.println("flag --->" + flag);

		PresonInformationFactoryService personInformationFactoryService = new PersonInformationFactoryServiceImple();

		if (flag != null) {
			switch (flag) {
			case "student":

				String personNum = request.getParameter("personNum");

				// String persionId = request.getParameter("personId");
				String studentId = "2013031012";
				System.out.println("flag --->" + flag + "persionId --->" + studentId);
				@SuppressWarnings("unchecked")
				PersonInformationService<Student> studentInformationService = (PersonInformationService<Student>) personInformationFactoryService
						.personFactory(flag);
				Student student = new Student();
				student.setS_id(studentId);
				if (studentInformationService != null) {
					Student stu = studentInformationService.findPersonInformationFromModel(student);
					if (stu != null) {
						request.setAttribute("stuId", stu.getS_id());
						request.setAttribute("stuName", stu.getS_name());
						request.setAttribute("stuSex", stu.getS_sex());
						request.setAttribute("stuClazz", stu.getCls_Name());
						try {
							if ("2".equals(personNum)) {
								request.getRequestDispatcher("/jsp/student/student_personal.jsp").forward(request,
										response);
							} else if ("1".equals(personNum)) {
								request.getRequestDispatcher("/jsp/student/student_personal.jsp").forward(request,
										response);
							}

						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else {

				}

				break;
			case "teacher":

				// String persionId = request.getParameter("personId");
				String teacherId = "2013031012";
				System.out.println("flag --->" + flag + "persionId --->" + teacherId);
				@SuppressWarnings("unchecked")
				PersonInformationService<Teacher> teacherInformationService = (PersonInformationService<Teacher>) personInformationFactoryService
						.personFactory(flag);
				Teacher teacher = new Teacher();
				teacher.setT_Id(teacherId);
				if (teacherInformationService != null) {
					Teacher teach = teacherInformationService.findPersonInformationFromModel(teacher);
					if (teach != null) {
						request.setAttribute("teacherId", teach.getT_Id());
						request.setAttribute("teacherName", teach.getT_Name());
						request.setAttribute("teacherColl", teach.getColl_Name());
						request.setAttribute("teacherJob", teach.getT_Job());
						try {
							request.getRequestDispatcher("/jsp/teacher/index.html").forward(request, response);

						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else {

				}

				break;
			case "manager":
				break;
			default:
				break;

			}
		}

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.path = config.getServletContext().getContextPath();

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
