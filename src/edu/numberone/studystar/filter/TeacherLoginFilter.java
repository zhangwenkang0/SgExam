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
 * Servlet Filter implementation class TeacherLoginFilter
 */
@WebFilter("/TeacherLoginFilter")
public class TeacherLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TeacherLoginFilter() {
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
		if (cookies!=null) {
			for (Cookie cookie:cookies) {
				String name = cookie.getName();
				if (name.equals("teanameCookie")) {
					session.setAttribute("tea_name",URLDecoder.decode(cookie.getValue(), "UTF-8"));
				}
				if (name.equals("teaIDCookie")) {
					session.setAttribute("tea_id", cookie.getValue());
				}
				if (name.equals("teapwdCookie")) {
					session.setAttribute("tea_pwd", cookie.getValue());
					isHaveStuName = true;
				}
			}
		}
		if (!isHaveStuName&&(session.getAttribute("tea_id")==null)) {
			req.getRequestDispatcher("../student/login.jsp").forward(req, response);
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
