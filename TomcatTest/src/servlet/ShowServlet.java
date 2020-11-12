package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import dao.DatabaseUtil;
import service.PersonUtil;
import service.UserUtil;
import utils.PersonTable;
import utils.UserTable;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    // 创建users表的管理 实例函数
	    UserUtil user = new UserUtil();
	    // 创建person表的管理 实例函数
	    PersonUtil person = new PersonUtil(); 
		
		// 获取servlet上下文对象
        ServletContext sc = getServletConfig().getServletContext();
        DatabaseUtil databaseUtil = (DatabaseUtil)sc.getAttribute("databaseUtil");
        int operatorResult;
		try {
			// 获取person列表
			List<PersonTable> personList = person.queAll(databaseUtil);
			request.setAttribute("personList", personList);
			
			// 获取user列表
			List<UserTable> userList = user.queAll(databaseUtil);
			request.setAttribute("userList", userList);
			
			request.getRequestDispatcher("showList.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			operatorResult = 0;
			e.printStackTrace();
		}
	}

}
