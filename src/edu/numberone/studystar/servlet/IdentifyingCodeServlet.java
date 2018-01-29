package edu.numberone.studystar.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IdentifyingCodeServlet
 */
@WebServlet("/IdentifyingCodeServlet")
public class IdentifyingCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentifyingCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int width=84;
		int height=38;
		
		//内存图像
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//画笔
//		Graphics g=image.getGraphics();
		Graphics2D g = image.createGraphics();
		
		//指定边框颜色
		g.setColor(Color.black);
		
		//画图像边框
		g.drawRect(0,0, width, height);
		
		//填充矩形的背景色：
		//设定画笔颜色：
		g.setColor(Color.white);
		g.fillRect(1, 1, width-2, height-2);
		
		Random random =new Random();
		
		g.setFont(new Font("9dh5b",Font.BOLD+Font.ITALIC,25));
		
		// 设置字体,随机选择字体,暂设置8种字体
//	    Font font = new Font((new String[] { "Arial", "Arial Black",
//	        "Arial Italic", "Courier New", "Courier New Bold Italic",
//	        "Courier New Italic", "Franklin Gothic Medium",
//	        "Franklin Gothic Medium Italic" })[random.nextInt(8)],
//	        Font.PLAIN, 25);
		// 随机设置像素点宽带(线宽)
	     g.setStroke(new BasicStroke((float)(Math.random()),
	     BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
//	    g.setFont(font);
		
		//填充内容
		//随机产生四个数字输出到页面
		
		
		g.setColor(Color.gray);
		//添加10条干扰线
		for(int i=0;i<10;i++){
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		
	
		//设定画笔颜色：
//		g.setColor(Color.RED);
		//设置字体颜色范围：
		int red =random.nextInt(255);
		int green =random.nextInt(250);
		int blue=random.nextInt(250);
		
		/*
		//产生四个随机的整数输出到页面
		for(int i=0;i<4;++i){
			int n=random.nextInt(10);
			//画到图片中	
			g.drawString(n+"", 20+20*i, 20);
		}
		*/
		
		//随机产生四个随机的汉字
//		String s="一举两得十全十美五颜六色dgfdyryrrw";
		String s="1234567890ABCDEFGHIGKLMNOKQRSTUVWSYZabcdefghigklmnopqrstuvwsyz";
		//将汉子转换为Unicode 编码
//		s ="\u4E00\u4E3E\u4E24\u5F97\u5341\u5168\u5341\u7F8E\u4E94\u989C\u516D\u8272dgfdyryrrw";
		
		StringBuffer sb=new StringBuffer();
		
		
		for(int i=0;i<4;i++){
			//设置画笔随机颜色：
//			g.setColor(new Color(red,green,blue));
			g.setColor(new Color(random.nextInt(255), random.nextInt(160), random.nextInt(160)));
			
			 // 旋转图形
		      int degree = (random.nextInt(20) - 10) % 360;
		      double ang = degree * 0.0174532925; // 将角度转为弧度
		      g.rotate(ang, width / 2, height / 2);
			
			char c=s.charAt(random.nextInt(s.length()));
			
			sb.append(c);
			
			int flag=random.nextBoolean()? 1 :-1;   //随机产生正负号

			g.drawString(c+"", 20*i + flag*random.nextInt(3), 30+flag*random.nextInt(3));
		}
		
		//将验证码转化为小写
		String sblowcass=sb.toString().toLowerCase();
		//将验证码转化为为大写
		String sbupcass=sb.toString().toUpperCase();
		
		
		//将验证码存进session
		HttpSession session=request.getSession();
		session.setAttribute("scode", sb.toString());
		//session.setAttribute("scode1", sblowcass);
		//session.setAttribute("scode2", sbupcass);
		
		//告诉客户端不要缓存图像,以免刷新图片无法更新      ，另一种是在js代码图片地址添加无意义参数，见html页面js代码
		response.setHeader("Exprise", -1 + "");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		
		//将图片输出客户端:
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
