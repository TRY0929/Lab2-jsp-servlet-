package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import utils.*;
import service.*;
import dao.*;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	    // 创建users表的管理 实例函数
	    UserUtil user = new UserUtil();
	    // 创建person表的管理 实例函数
	    PersonUtil personUtil = new PersonUtil(); 
	    
		
		String username = request.getParameter("username");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String teleno = request.getParameter("teleno");
        
        // 创建person表
        PersonTable person = new PersonTable(username, name, Integer.getInteger(age), teleno);
        
        // 获取servlet上下文对象
        ServletContext sc = getServletConfig().getServletContext();
        DatabaseUtil databaseUtil = (DatabaseUtil)sc.getAttribute("databaseUtil");
        
        int operatorResult;
		try {
			operatorResult = personUtil.addOrModifyPerson(person,databaseUtil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			operatorResult = 0;
			e.printStackTrace();
		}
		
		// tableType 看当前操作的哪张表
        request.setAttribute("tableType",0);
        // operatorResult 当前执行操作的结果
        request.setAttribute("operatorResult",operatorResult);
        // username 对哪位用户进行操作
        request.setAttribute("username",username);
        
        // response.sendRedirect("success.jsp");
        request.getRequestDispatcher("result.jsp").forward(request,response);//内部重定向
	}

}
