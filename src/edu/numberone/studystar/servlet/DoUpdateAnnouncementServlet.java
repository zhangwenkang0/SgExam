package edu.numberone.studystar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.daoimpl.AnnouncementDaoImpl;
import edu.numberone.studystar.entity.Announcement;
import edu.numberone.studystar.service.AnnouncementService;
import edu.numberone.studystar.serviceimpl.AnnouncementServiceImpl;

@WebServlet("/DoUpdateAnnouncementServlet")
public class DoUpdateAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Announcement ann = new Announcement();
		String operation=(String) request.getParameter("operation");
		if("toUpdate".equals(operation)){
			AnnouncementService annServ=new AnnouncementServiceImpl();
			String ann_id=(String) request.getParameter("ann_id");
			ann=annServ.findNewById(ann_id);
			request.setAttribute("news", ann);
			request.getRequestDispatcher("/jsp/teacher/news_revise.jsp").forward(request, response);
		}else if("update".equals(operation)){
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
		ann.setA_id(request.getParameter("a_id"));
		AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
		adi.updateAnnouncement(ann);
		request.getRequestDispatcher("/jsp/teacher/news_list.jsp").forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
