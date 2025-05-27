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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            // 未ログインならログイン画面へ
        	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        	rd.forward(request, response);

            return;
        }
        
        //  userList を再取得する
        AccountsDAO dao = new AccountsDAO();
        List<Account> userList = dao.findAllUsers();
        request.setAttribute("userList", userList);
        // ログイン済みならmain.jspにフォワード
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力情報の取得
        String idStr = request.getParameter("id");
        String pass = request.getParameter("pass");

        // 入力チェック（nullまたは空文字）
        if (idStr == null || idStr.isEmpty() || pass == null || pass.isEmpty()) {
            request.setAttribute("error", "IDとパスワードを入力してください");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "IDは数値で入力してください");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        Login login = new Login(id, pass);

        // 管理者認証のみ
        LoginLogic logic = new LoginLogic();
        if (logic.execute(login)) {
            // ログイン成功
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", login);

            // ユーザー一覧を取得してリクエスト属性にセット
            AccountsDAO dao = new AccountsDAO();
            List<Account> userList = dao.findAllUsers();
            request.setAttribute("userList", userList);

            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
            return;
        } else {
            // ログイン失敗
            request.setAttribute("error", "IDまたはパスワードが違います");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}