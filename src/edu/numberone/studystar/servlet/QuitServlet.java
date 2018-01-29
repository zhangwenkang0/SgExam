package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuitServlet
 */
@WebServlet("/QuitServlet")
public class QuitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		for (Cookie cookie:cookies) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		session.invalidate();
		switch (status) {
		case "student":
			request.getRequestDispatcher("jsp/student/login.jsp").forward(request, response);
			break;
		case "teacher":
			request.getRequestDispatcher("jsp/student/login.jsp").forward(request, response);
			break;
		case "admin":
			request.getRequestDispatcher("jsp/admin/login.jsp").forward(request, response);
			break;

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
