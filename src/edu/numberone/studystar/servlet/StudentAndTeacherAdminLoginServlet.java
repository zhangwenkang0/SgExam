package edu.numberone.studystar.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.numberone.studystar.dao.PersonInfomationDao;
import edu.numberone.studystar.daoimpl.AnnouncementDaoImpl;
import edu.numberone.studystar.daoimpl.ManagerInformationDaoImple;
import edu.numberone.studystar.daoimpl.ScoresDaoImpl;
import edu.numberone.studystar.daoimpl.StuDaoImpl;
import edu.numberone.studystar.daoimpl.StudentInformationDaoImple;
import edu.numberone.studystar.entity.Announcement;
import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.service.PaperService;
import edu.numberone.studystar.service.PersonInformationService;
import edu.numberone.studystar.serviceimpl.PaperServiceImpl;
import edu.numberone.studystar.serviceimpl.PersonInformationFactoryServiceImple;

/**
 * Servlet implementation class Stu_Login
 */
@WebServlet("/StudentAndTeacherAdminLoginServlet")
public class StudentAndTeacherAdminLoginServlet extends HttpServlet {
	private int pageSize;
	private String path;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentAndTeacherAdminLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cookieMacAge = 60 * 60 * 24 * 7;
		// 从reaquest中获取用户信息
		String status = request.getParameter("status");
		String code = request.getParameter("code");
		String password = null;
		String id = null;
		//根据status判断该用户的身份学生(staudent),教师(teacher),管理员(admin)
		switch (status) {
		case "student":
			id = request.getParameter("studentID");
			password = request.getParameter("studentPassword");
			break;
		case "teacher":
			id = request.getParameter("teacherID");
			password = request.getParameter("teacherPassword");
			break;
		case "admin":
			id = request.getParameter("adminID");

			password = request.getParameter("adminPassword");

			break;
		}
		//是否自动登录
		String autoLogin = request.getParameter("autoLogin");
		StuDaoImpl dao = new StuDaoImpl();
		HttpSession httpSession = request.getSession();

		String name = dao.AchieveName(status, id, password);
		//班级id
		String cls_id=null;
		//班级名称
		String cls_name=null;

		// 判断验证码是否正确
		if (code != null) {
			HttpSession session = request.getSession();
			String scode = (String) session.getAttribute("scode");
			//String scode1 = (String) session.getAttribute("scode1");
			//String scode2 = (String) session.getAttribute("scode2");
			if (!code.equalsIgnoreCase(scode)) {
				request.setAttribute("status", status);
				request.setAttribute("error", "code");
				request.setAttribute("id", id);
				request.setAttribute("password", password);
				request.getRequestDispatcher("jsp/student/login.jsp").forward(request, response);
				return;
			}
		}
		//将用户信息保存到session中,以便其他页面共用
		if (name != null) {
			switch (status) {
			case "student":
				PersonInfomationDao sDao=new StudentInformationDaoImple();
				Student s=new Student();
				s.setS_id(id);
				s=(Student) sDao.findPersonInforFromDB(s);
				cls_id=s.getCls_Id();
				cls_name=s.getCls_Name();
				httpSession.setAttribute("cls_id", cls_id);
				httpSession.setAttribute("cls_name", cls_name);
				httpSession.setAttribute("stu_name", name);
				httpSession.setAttribute("stu_id", id);
				httpSession.setAttribute("stu_pwd", password);
				httpSession.setAttribute("stu_sex", s.getS_sex());
				break;
			case "teacher":
				httpSession.setAttribute("tea_name", name);
				httpSession.setAttribute("tea_id", id);
				httpSession.setAttribute("tea_pwd", password);
				break;
			case "admin":
				httpSession.setAttribute("adm_name", name);
				httpSession.setAttribute("adm_id", id);
				httpSession.setAttribute("adm_pwd", password);
				break;
			}
			httpSession.setAttribute("username", name);
			if (autoLogin == null) {
				switch (status) {
				case "student":
					PaperService paperSev=new PaperServiceImpl();
					Pager<Paper> papers=paperSev.findPaperByClassesId(1, pageSize,cls_id);
					request.setAttribute("papers", papers);
					//获取学生编号
					ScoresDaoImpl sdi = new ScoresDaoImpl();
					Scores sco = new Scores();
					sco.setS_id(id);
					//查找出该学生所有成绩
					LinkedList<Scores> scores = sdi.getScores(sco, 0, 0);
					request.setAttribute("scores", scores);
					//查询公告
					AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
					Announcement ann = new Announcement();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					Date now=new Date();
					String nowStr=sdf.format(now);
					ann.setA_startTime(nowStr);
					ann.setA_endTime(nowStr);
					LinkedList<Announcement> anns = adi.getAnnouncement(ann, 0, 5);
					request.setAttribute("anns", anns);
					request.getRequestDispatcher("jsp/student/student_index.jsp").forward(request, response);
					break;
				case "teacher":
					request.getRequestDispatcher("jsp/teacher/index.jsp").forward(request, response);
					break;
				case "admin":
					// 记录最好用一次登录时间
					getLastLoginTime(request, response);
					PersonInfomationDao aDao=new ManagerInformationDaoImple();
					Manager m=new Manager();
					m.setM_id(id);
					m=(Manager) aDao.findPersonInforFromDB(m);
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					httpSession.setAttribute("lastTime", df.format(m.getM_LastLoginTime()));
					request.getRequestDispatcher("jsp/admin/index.jsp").forward(request, response);
					break;
				}
			}
			// 自动登录时，无需输入账号密码进入索引页
			if (autoLogin != null && autoLogin.equals("1")) {
				switch (status) {
				case "student":
					Cookie stuIDCookie = new Cookie("stuIDCookie", id);
					Cookie stupwdCookie = new Cookie("stupwdCookie", password);
					Cookie stunameCookie = new Cookie("stunameCookie", URLEncoder.encode(name, "utf-8"));
					stuIDCookie.setMaxAge(cookieMacAge);
					stupwdCookie.setMaxAge(cookieMacAge);
					stunameCookie.setMaxAge(cookieMacAge);
					response.addCookie(stuIDCookie);
					response.addCookie(stupwdCookie);
					response.addCookie(stunameCookie);
					PaperService paperSev=new PaperServiceImpl();
					Pager<Paper> papers=paperSev.findPaperByClassesId(1, pageSize,cls_id);
					request.setAttribute("papers", papers);
					//获取学生编号
					ScoresDaoImpl sdi = new ScoresDaoImpl();
					Scores sco = new Scores();
					sco.setS_id(id);
					//查找出该学生所有成绩
					LinkedList<Scores> scores = sdi.getScores(sco, 0, 0);
					request.setAttribute("scores", scores);
					//查询公告
					AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
					Announcement ann = new Announcement();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					Date now=new Date();
					String nowStr=sdf.format(now);
					ann.setA_startTime(nowStr);
					ann.setA_endTime(nowStr);
					LinkedList<Announcement> anns = adi.getAnnouncement(ann, 0, 5);
					request.setAttribute("anns", anns);
					request.getRequestDispatcher("jsp/student/student_index.jsp").forward(request, response);
					break;
				case "teacher":
					Cookie teaIDCookie = new Cookie("teaIDCookie", id);
					Cookie teapwdCookie = new Cookie("teapwdCookie", password);
					Cookie teanameCookie = new Cookie("teanameCookie", URLEncoder.encode(name, "utf-8"));
					teaIDCookie.setMaxAge(cookieMacAge);
					teapwdCookie.setMaxAge(cookieMacAge);
					teanameCookie.setMaxAge(cookieMacAge);
					response.addCookie(teaIDCookie);
					response.addCookie(teapwdCookie);
					response.addCookie(teanameCookie);
					request.getRequestDispatcher("jsp/teacher/index.jsp").forward(request, response);
					break;
				case "admin":
					// 记录最好用一次登录时间
					getLastLoginTime(request, response);
					PersonInfomationDao aDao=new StudentInformationDaoImple();
					Manager m=new Manager();
					m.setM_id(id);
					m=(Manager) aDao.findPersonInforFromDB(m);
					httpSession.setAttribute("lastime", m.getM_LastLoginTime());
					Cookie admIDCookie = new Cookie("teaIDCookie", id);
					Cookie admpwdCookie = new Cookie("teapwdCookie", password);
					Cookie admnameCookie = new Cookie("teanameCookie", URLEncoder.encode(name, "utf-8"));
					admIDCookie.setMaxAge(cookieMacAge);
					admpwdCookie.setMaxAge(cookieMacAge);
					admnameCookie.setMaxAge(cookieMacAge);
					response.addCookie(admIDCookie);
					response.addCookie(admpwdCookie);
					response.addCookie(admnameCookie);

					// 锟斤拷锟斤拷洗蔚锟铰枷低筹拷锟绞憋拷锟�
					getLastLoginTime(request, response);
					request.getRequestDispatcher("jsp/admin/index.jsp").forward(request, response);
					break;
				}
			}
		} else {
			request.setAttribute("status", status);
			request.setAttribute("error", "user");
			request.setAttribute("id", id);
			request.setAttribute("password", password);
			switch (status) {
			case "admin":
				request.getRequestDispatcher("jsp/admin/login.jsp").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("jsp/student/login.jsp").forward(request, response);
				break;
			}

		}
	}

	/**
	 * 
	 * 锟斤拷锟斤拷洗蔚锟铰枷低筹拷锟绞憋拷锟�
	 * 
	 */
	private void getLastLoginTime(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 锟斤拷取锟斤拷一锟轿碉拷录锟斤拷时锟斤拷,锟斤拷锟轿拷锟揭伙拷蔚锟铰斤拷锟斤拷锟揭伙拷蔚锟铰绞憋拷锟轿拷锟角笆憋拷锟�

		boolean isfirstLogin = false;// 锟斤拷锟斤拷欠锟斤拷一锟轿碉拷陆
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

			// 锟斤拷一锟轿碉拷陆时锟斤拷锟斤拷拥锟铰绞憋拷锟�
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		path = config.getServletContext().getContextPath();
		String pSize = config.getServletContext().getInitParameter("pageSize");

		pageSize = Integer.parseInt(pSize);
	}

}
