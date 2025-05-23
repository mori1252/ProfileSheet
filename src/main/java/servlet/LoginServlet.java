package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.Account;
import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
	    String pass = request.getParameter("pass");
		Login login = new Login(id, pass);
		LoginLogic bo = new LoginLogic();
		if (!bo.execute(login)) {
	        // 管理者でなければログイン画面へ戻す
	        response.sendRedirect(request.getContextPath() + "/LoginServlet");
	        return;
	    } 
		// 管理者なら利用者一覧を取得して main.jsp へフォワード
	    AccountsDAO dao = new AccountsDAO();
	    List<Account> users = dao.findAllUsers();
	    request.setAttribute("userList", users);
//	    if (users == null) {
//	        System.out.println("userList is null");
//	    } else {
//	        System.out.println("userList size = " + users.size());
//	        for (Account user : users) {
//	            System.out.println("user.id = " + user.getId() + ", user.name = " + user.getName());
//	        }
//	    }
	    HttpSession session = request.getSession();
	    session.setAttribute("adminId", id);  // 必要に応じて管理者IDをセッション保存
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	    dispatcher.forward(request, response);
	}
}

