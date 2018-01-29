package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.daoimpl.AnnouncementDaoImpl;
import edu.numberone.studystar.entity.Announcement;

@WebServlet("/DoAddAnnoucementServlet")
public class DoAddAnnoucementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Announcement ann = new Announcement();
		ann.setA_title(request.getParameter("a_title"));
		ann.setA_content(request.getParameter("a_content"));
		String author=(String) request.getSession().getAttribute("tea_name");
		String admin=(String) request.getSession().getAttribute("adm_name");
		if(null!=author||!"".equals(author)){
			ann.setA_author(author);
		}else{
			ann.setA_author(admin);
		}
		ann.setA_startTime(request.getParameter("startTime"));
		ann.setA_endTime(request.getParameter("endTime"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String createTime=sdf.format(date);
		ann.setA_createDate(createTime);
		
		AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
		request.getRequestDispatcher("/jsp/teacher/news_list.jsp").forward(request, response);
	}

}
