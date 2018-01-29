package edu.numberone.studystar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerInfomationServlet
 * 
 *用于客户端获得服务器的基本信息
 * 
 */
@WebServlet("/ServerInfomationServlet")
public class ServerInfomationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerInfomationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sendServerInformationToClient(request,response);
	}

	
	/**
	 * 读取服务器的基本信息，将基本信息提供给管理员
	 * 
	 * 
	 * */
	private void sendServerInformationToClient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Runtime runtime = Runtime.getRuntime();
		System.out.println(System.getProperty("os.version"));   
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
