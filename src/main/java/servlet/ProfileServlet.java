package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountsDAO;
import model.Account;

@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "不正なIDです");
            return;
        }

        // パスワード不要なので、Login オブジェクトは使わずに DAO に直接検索用メソッドを追加しても良いですが
        AccountsDAO dao = new AccountsDAO();
        Account account = dao.findById(id);  // findById を別途実装してください

        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ユーザーが見つかりません");
            return;
        }

        request.setAttribute("account", account);
        RequestDispatcher dispatcher = 
            request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(request, response);
    }
}
