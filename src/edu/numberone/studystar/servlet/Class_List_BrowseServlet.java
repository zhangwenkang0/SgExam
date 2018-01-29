package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.daoimpl.ScanClassManageInfoDaoImple;
import edu.numberone.studystar.entity.ClassManageBean;
import edu.numberone.studystar.entity.Student;





@WebServlet("/Class_List_BrowseServlet")
public class Class_List_BrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Class_List_BrowseServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateclassform=request.getParameter("updateclassform");
		System.out.println(updateclassform+"-------");
		
		if(updateclassform.equals("updateClassForm")){
			//逻辑处理
			
			ScanClassManageInfoDaoImple scmDao=new ScanClassManageInfoDaoImple();
			List<ClassManageBean> list=scmDao.classQuery();
			request.setAttribute("list",list);
			
			//request携带list转发回class_list.jsp
			request.getRequestDispatcher("jsp/teacher/class_list.jsp").forward(request, response);
		}
		
		if(updateclassform.equals("updateStudentForm")){
			String classid=request.getParameter("classid");
			System.out.println("classid:"+classid);
			ScanClassManageInfoDaoImple scmDao=new ScanClassManageInfoDaoImple();
			List<Student> studentList=scmDao.studentQuery(classid);
			request.setAttribute("studentList",studentList);
			request.getRequestDispatcher("jsp/teacher/class_student_list.jsp").forward(request, response);
		}
		
		
	}

}
