package edu.numberone.studystar.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.numberone.studystar.daoimpl.ManagerInformationDaoImple;
import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.service.PersonInformationService;
import edu.numberone.studystar.serviceimpl.PersonInformationFactoryServiceImple;

/**
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/AdminLoginFilter")
public class AdminLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminLoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		boolean isHaveStuName = false;
		Cookie[] cookies = req.getCookies();
		String url = req.getServletPath();
		System.out.println(url);

		// 获取上一次登录的时间,如果为第一次登陆，上一次登陆时间为当前时间
		getLastLoginTime(req, (HttpServletResponse) response);

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals("admnameCookie")) {
					session.setAttribute("adm_name", URLDecoder.decode(cookie.getValue(), "UTF-8"));
				}
				if (name.equals("admIDCookie")) {
					session.setAttribute("adm_id", cookie.getValue());
				}
				if (name.equals("admpwdCookie")) {
					session.setAttribute("adm_pwd", cookie.getValue());
					isHaveStuName = true;
				}
			}
		}
		if (url.equals("/jsp/admin/login.jsp")) {
			if (session.getAttribute("adm_id") != null) {
				req.getRequestDispatcher("index.jsp").forward(req, response);
				return;
			}
		}
		if (!isHaveStuName && (session.getAttribute("adm_id") == null)) {
			req.getRequestDispatcher("login.jsp").forward(req, response);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * 
	 * 获得上次登录系统的时间
	 * 
	 */
	private void getLastLoginTime(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 获取上一次登录的时间,如果为第一次登陆，上一次登陆时间为当前时间

		boolean isfirstLogin = false;// 检测是否第一次登陆
		Cookie[] cookies = request.getCookies();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Manager manager = new Manager();
		HttpSession session = request.getSession();
		String manager_id = (String) session.getAttribute("adm_id");
		if (manager_id != null) {
			manager.setM_id(manager_id);
			manager.setM_LastLoginTime(new Timestamp(new Date().getTime()));

			PersonInformationService<Manager> managerInformationServiceImple = (PersonInformationService<Manager>) new PersonInformationFactoryServiceImple()
					.personFactory("manager");
			ManagerInformationDaoImple managerInfomationDao = new ManagerInformationDaoImple();
			String date = null;
			try {
				date = URLEncoder.encode(simpleDateFormat.format(new Date()), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Cookie lastCookeie = new Cookie("lastTime", date);

			if (cookies != null && cookies.length > 0) {

				for (Cookie cookie : cookies) {
					if ("lastTime".equals(cookie.getName())) {
						isfirstLogin = true;
						Manager m = managerInformationServiceImple.findPersonInformationFromModel(manager);
						String lastDate = null;
						if(m != null && m.getM_LastLoginTime() != null){
							try {
								lastDate = URLEncoder.encode(m.getM_LastLoginTime().toString(), "utf-8");
								System.out.println("lastDate--->" + lastDate);
								lastCookeie.setValue(lastDate);
								System.out.println("cookie--->" + cookie.getValue());
								cookie.setValue(date);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								throw new RuntimeException(e.getMessage());
							}

							break;
						}

						
					}
				}
			}

			// 第一次登陆时，添加登陆时间
			if (!isfirstLogin) {
				response.addCookie(lastCookeie);
			}

			managerInfomationDao.updateManagerLastTime(manager);

			String osName = System.getProperty("os.name");
			request.setAttribute("osName", osName);
			request.setAttribute("version", "v-1.0");
			try {
				request.setAttribute("lastTime", URLDecoder.decode(lastCookeie.getValue(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("NowTime", simpleDateFormat.format(new Date()));

			System.out.println(System.getProperty("os.name") + ":" + lastCookeie.getValue());

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
