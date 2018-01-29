package edu.numberone.studystar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.numberone.studystar.daoimpl.AnnouncementDaoImpl;
import edu.numberone.studystar.entity.Announcement;

@WebServlet("/DoDeleteAnnouncementServlet")
public class DoDeleteAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("a_ids");
		String[] ids = idStr.split("@");
		String resultStr = "";
		for(int i = 0; i < ids.length; ++i){
			Announcement ann = new Announcement();
			ann.setA_id(ids[i]);
			AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
			resultStr = adi.deleteAnnoucement(ann);
		}
		
		response.getWriter().write(resultStr);
	}

}
