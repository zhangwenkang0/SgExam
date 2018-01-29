package edu.numberone.studystar.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class StudentLoginFilter
 */
@WebFilter("/StudentLoginFilter")
public class StudentLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StudentLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		boolean isHaveStuName = false;
		Cookie[] cookies = req.getCookies();
		String url = req.getServletPath();
		
		if (cookies!=null) {
			for (Cookie cookie:cookies) {
				String name = cookie.getName();
				if (name.equals("stunameCookie")) {
					session.setAttribute("stu_name",URLDecoder.decode(cookie.getValue(), "UTF-8"));
				}
				if (name.equals("stuIDCookie")) {
					session.setAttribute("stu_id", cookie.getValue());
				}
				if (name.equals("stupwdCookie")) {
					session.setAttribute("stu_pwd", cookie.getValue());
					isHaveStuName = true;
				}
			}
		}
		if (url.equals("/jsp/student/login.jsp")) {
			if (session.getAttribute("stu_id")!=null) {
				req.getRequestDispatcher("student_index.jsp").forward(req, response);
				return;
			}else if (session.getAttribute("tea_id")!=null) {
				req.getRequestDispatcher("../teacher/index.jsp").forward(req, response);
				return;
			}
		}
		if (!isHaveStuName&&(session.getAttribute("stu_id")==null)) {
			req.getRequestDispatcher("login.jsp").forward(req, response);
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
