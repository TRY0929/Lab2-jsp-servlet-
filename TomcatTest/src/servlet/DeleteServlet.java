package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseUtil;

import javax.servlet.ServletContext;

import service.UserUtil;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		UserUtil userUtil = new UserUtil();
		
        // 获取servlet上下文对象
        ServletContext sc = getServletConfig().getServletContext();
        DatabaseUtil databaseUtil = (DatabaseUtil)sc.getAttribute("databaseUtil");
        
        int operatorResult;
		try {
			operatorResult = userUtil.delUserTableOnUsername(username,databaseUtil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			operatorResult = 0;
			e.printStackTrace();
		}
		
		// tableType 看当前操作的哪张表
        request.setAttribute("tableType",1);
        // operatorResult 当前执行操作的结果
        request.setAttribute("operatorResult",operatorResult);
        // username 对哪位用户进行操作
        request.setAttribute("username",username);
        
        // response.sendRedirect("success.jsp");
        request.getRequestDispatcher("result.jsp").forward(request,response);//内部重定向
	}

}
